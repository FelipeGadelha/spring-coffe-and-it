package br.com.felipe.gadelha.coffeandit.transactionbff.domain.service;

import br.com.felipe.gadelha.coffeandit.transactionbff.domain.entity.Transaction;
import br.com.felipe.gadelha.coffeandit.transactionbff.domain.repository.TransactionRepository;
import br.com.felipe.gadelha.coffeandit.transactionbff.infra.repository.TransactionRedisRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;

@Service
public class TransactionService {

    private static final Logger log = LoggerFactory.getLogger(TransactionService.class);
    private final TransactionRepository transactionRepository;
    private final ObjectMapper objectMapper;

    public TransactionService(
        TransactionRepository transactionRepository,
        ObjectMapper objectMapper
    ) {
        this.transactionRepository = transactionRepository;
        this.objectMapper = objectMapper;
    }

    public Mono<Transaction> findById(final String id) {
        log.info("start data initialization  ...");
        return transactionRepository.findById(id)
            .retryWhen(Retry.backoff(3, Duration.ofSeconds(5))
                .filter(throwable -> throwable instanceof QueryTimeoutException));
    }

    public Mono<Transaction> save(final Transaction transaction) {
        return transactionRepository.save(transaction)
            .flatMap(t -> Mono.just(transaction));
    }

    public Flux<Transaction> findAll() { return transactionRepository.findAll(); }

    private Mono<Transaction> handleResponse(String response) {
        try {
            return Mono.just(objectMapper.readValue(response, Transaction.class));
        } catch (Exception ex) {
            log.error("Erro ao tentar converter resposta do CEP.", ex);
            return Mono.empty();
        }
    }
}
