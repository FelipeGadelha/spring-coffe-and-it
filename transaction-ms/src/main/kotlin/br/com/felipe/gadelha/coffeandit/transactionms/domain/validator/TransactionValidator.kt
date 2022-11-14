package br.com.felipe.gadelha.coffeandit.transactionms.domain.validator

import br.com.felipe.gadelha.coffeandit.transactionms.domain.entity.Transaction
import kotlin.jvm.Throws

@FunctionalInterface
interface TransactionValidator {

    @Throws(RuntimeException::class)
    fun validate(transaction: Transaction)
}