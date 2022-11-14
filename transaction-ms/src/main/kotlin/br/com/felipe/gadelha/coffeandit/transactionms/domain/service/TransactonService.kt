package br.com.felipe.gadelha.coffeandit.transactionms.domain.service

import br.com.felipe.gadelha.coffeandit.transactionms.domain.entity.Transaction
import br.com.felipe.gadelha.coffeandit.transactionms.domain.repository.TransactionRepository
import br.com.felipe.gadelha.coffeandit.transactionms.domain.validator.TransactionValidation
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class TransactonService(
    private val transactionRepository: TransactionRepository,
    private val transactionValidation: TransactionValidation
) {

    fun save(transaction: Transaction) {
        val save = transaction.copy(date = LocalDateTime.now())
        transactionValidation.validate(save);
        transactionRepository.save(save)
    }
}