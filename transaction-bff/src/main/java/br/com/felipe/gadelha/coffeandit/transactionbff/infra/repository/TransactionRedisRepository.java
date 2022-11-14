package br.com.felipe.gadelha.coffeandit.transactionbff.infra.repository;

import br.com.felipe.gadelha.coffeandit.transactionbff.domain.entity.Transaction;
import br.com.felipe.gadelha.coffeandit.transactionbff.domain.repository.TransactionRepository;
import br.com.felipe.gadelha.coffeandit.transactionbff.domain.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public class TransactionRedisRepository implements TransactionRepository {//extends ReactiveCrudRepository<TransactionRs, String> {

    private final ReactiveRedisOperations<String, Transaction> reactiveRedisOperations;
    private static final Logger log = LoggerFactory.getLogger(TransactionRedisRepository.class);

    public TransactionRedisRepository(ReactiveRedisOperations<String, Transaction> template) {
        this.reactiveRedisOperations = template;
    }

    @Override
    public Flux<Transaction> findAll(){
        return this.reactiveRedisOperations
            .opsForList()
            .range("Transaction", 0, -1);
    }

//    public Mono<Long> count() {
//
//    }

    @Override
    public Mono<Transaction> findById(String uuid) {
        return this.findAll()
            .filter(p -> p.getUuid() == UUID.fromString(uuid))
            .log()
            .next();
    }

    @Override
    public Mono<Long> save(Transaction transaction){
        return this.reactiveRedisOperations
            .opsForList()
            .rightPush("Transaction", transaction);
    }

    @Override
    public Mono<Boolean> deleteAll() {
        return this.reactiveRedisOperations
            .opsForList()
            .delete("Transaction");
    }
}
