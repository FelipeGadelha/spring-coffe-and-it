package br.com.felipe.gadelha.coffeandit.transactionms.domain.validator

import br.com.felipe.gadelha.coffeandit.transactionms.domain.entity.Transaction
import org.springframework.stereotype.Component

//@Component
interface TransactionValidation {

    @Throws(RuntimeException::class)
    fun validate(transaction: Transaction)
}