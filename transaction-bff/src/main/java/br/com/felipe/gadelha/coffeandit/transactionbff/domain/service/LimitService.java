package br.com.felipe.gadelha.coffeandit.transactionbff.domain.service;

import br.com.felipe.gadelha.coffeandit.transactionbff.domain.entity.DailyLimit;
import br.com.felipe.gadelha.coffeandit.transactionbff.api.client.LimitClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreakerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class LimitService {

    private final LimitClient limitClient;
    private final ReactiveCircuitBreakerFactory reactiveCircuitBreakerFactory;
//    private final Logger log = LoggerFactory.getLogger(LimitService.class);

    public LimitService(
        LimitClient limitClient,
        ReactiveCircuitBreakerFactory reactiveCircuitBreakerFactory
    ) {
        this.limitClient = limitClient;
        this.reactiveCircuitBreakerFactory = reactiveCircuitBreakerFactory;
    }

    public Mono<DailyLimit> findByAgencyAndAccount(final String agency, final String account) {
        return fallback(limitClient.findByAgencyAndAccount(agency, account));
    }

    private Mono<DailyLimit> fallback(Mono<DailyLimit> mono) {
        return mono.transform(it -> {
            var reactiveCircuitBreaker = reactiveCircuitBreakerFactory
                .create("limitsSearchBasedOnTime");
            return reactiveCircuitBreaker.run(it, Throwable ->
                Mono.just(DailyLimit
                    .builder()
                    .id(1L)
                    .agency("001")
                    .account("0001")
                    .date(LocalDate.now())
                    .value(BigDecimal.ZERO)
                    .build()
                )
            );
        });
    }
}
