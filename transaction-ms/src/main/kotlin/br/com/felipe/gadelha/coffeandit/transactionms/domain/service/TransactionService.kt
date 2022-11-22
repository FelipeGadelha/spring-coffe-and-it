package br.com.felipe.gadelha.coffeandit.transactionms.domain.service

import br.com.felipe.gadelha.coffeandit.transactionms.domain.entity.Transaction
import br.com.felipe.gadelha.coffeandit.transactionms.domain.repository.TransactionRepository
import br.com.felipe.gadelha.coffeandit.transactionms.domain.validator.TransactionValidation
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.dao.QueryTimeoutException
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.util.retry.Retry
import java.time.Duration
import java.time.LocalDateTime

@Service
class TransactionService(
    private val transactionRepository: TransactionRepository,
    private val transactionValidation: TransactionValidation
) {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(TransactionService::class.java)
    }

    fun save(transaction: Transaction) {
        val save = transaction.copy(date = LocalDateTime.now())
        transactionValidation.validate(save)
        transactionRepository.save(save)
    }

    fun update(transaction: Transaction) {
        transactionRepository.save(transaction)
    }

    fun transaction(transaction: Transaction) {
        this.findById(transaction)
            
    }
    fun findById(transaction: Transaction): Mono<Transaction> {
        return transactionRepository.findById(transaction.uuid.toString())
//            .retryWhen(
//                Retry.backoff(3, Duration.ofSeconds(5))
//                .filter{throwable: Throwable? -> throwable is QueryTimeoutException })
    }
}