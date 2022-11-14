package br.com.felipe.gadelha.coffeandit.transactionbff.api.client;

import br.com.felipe.gadelha.coffeandit.transactionbff.domain.entity.DailyLimit;
import br.com.felipe.gadelha.coffeandit.transactionbff.domain.exception.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;

@Component
public class LimitClient {
    private final WebClient webClient;

    public LimitClient(WebClient webClient) { this.webClient = webClient; }

    public Mono<DailyLimit> findByAgencyAndAccount(String agency, String account) {
        return webClient.get()
            .uri("/v1/daily-limits/{agency}/{account}", agency, account)
            .retrieve()
            .onStatus(HttpStatus::is4xxClientError,
                error -> Mono.error(new ServiceException(
                    "verifique os parâmetros informados", error.rawStatusCode())
                ))
            .onStatus(HttpStatus::is5xxServerError,
                error -> Mono.error(new ServiceException(
                    "Internal server error", error.rawStatusCode())))
            .bodyToMono(DailyLimit.class)
            .retryWhen(Retry.backoff(5, Duration.ofSeconds(3))
                .filter(ctx -> ctx instanceof ServiceException
                    && ((ServiceException) ctx).getStatusCode() == 500));
    }
}
