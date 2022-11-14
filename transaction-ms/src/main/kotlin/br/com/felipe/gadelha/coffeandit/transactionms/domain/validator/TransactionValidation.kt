package br.com.felipe.gadelha.coffeandit.transactionms.domain.validator

import br.com.felipe.gadelha.coffeandit.transactionms.domain.entity.Transaction

interface TransactionValidation {

    @Throws(RuntimeException::class)
    fun validate(transaction: Transaction)
}