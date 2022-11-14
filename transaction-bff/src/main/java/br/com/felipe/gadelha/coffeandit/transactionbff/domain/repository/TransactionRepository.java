package br.com.felipe.gadelha.coffeandit.transactionbff.domain.repository;

import br.com.felipe.gadelha.coffeandit.transactionbff.domain.entity.Transaction;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TransactionRepository {

    Flux<Transaction> findAll();

    Mono<Transaction> findById(String uuid);

    Mono<Long> save(Transaction transaction);

    Mono<Boolean> deleteAll();
}
