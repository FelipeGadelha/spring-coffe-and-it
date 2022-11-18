package br.com.felipe.gadelha.coffeandit.transactionbff.domain.service;

import br.com.felipe.gadelha.coffeandit.transactionbff.domain.entity.Transaction;
import br.com.felipe.gadelha.coffeandit.transactionbff.domain.repository.TransactionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;

@Service
public class TransactionService {

    private static final Logger log = LoggerFactory.getLogger(TransactionService.class);
    private final TransactionRepository transactionRepository;
    private final ReactiveKafkaProducerTemplate reactiveKafkaProducerTemplate;
    private final ObjectMapper objectMapper;
    @Value("${app.topic}")
    private String topic;

    public TransactionService(
        TransactionRepository transactionRepository,
        ReactiveKafkaProducerTemplate reactiveKafkaProducerTemplate,
        ObjectMapper objectMapper
    ) {
        this.transactionRepository = transactionRepository;
        this.reactiveKafkaProducerTemplate = reactiveKafkaProducerTemplate;
        this.objectMapper = objectMapper;
    }

    public Mono<Transaction> findById(final String id) {
        log.info("start data initialization  ...");
        return transactionRepository.findById(id)
            .retryWhen(Retry.backoff(3, Duration.ofSeconds(5))
                .filter(throwable -> throwable instanceof QueryTimeoutException));
    }

    @Transactional
    @Retryable(value = QueryTimeoutException.class, maxAttempts = 5, backoff = @Backoff(delay = 100))
    public Mono<Transaction> save(final Transaction transaction) {
        reactiveKafkaProducerTemplate.send(topic, transaction)
            .doOnSuccess(voidSendResult -> log.info(voidSendResult.toString()))
            .subscribe();
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
