package br.com.felipe.gadelha.coffeandit.transactionms.api.v1.dto.request

import br.com.felipe.gadelha.coffeandit.transactionms.domain.entity.Account
import br.com.felipe.gadelha.coffeandit.transactionms.domain.entity.Transaction
import br.com.felipe.gadelha.coffeandit.transactionms.domain.entity.TransactionStatus
import br.com.felipe.gadelha.coffeandit.transactionms.domain.entity.TransactionType
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

class TransactionRq(
    val beneficiary: BeneficiaryRq,
    val transactionType: TransactionType,
    val value: BigDecimal
) {

    fun toEntity(): Transaction = Transaction(
        uuid = UUID.randomUUID(),
        value = value,
        date = LocalDateTime.now(),
        account = Account(1L, "20"),
        beneficiary = beneficiary.toEntity(),
        transactionType = transactionType,
        transactionStatus = TransactionStatus.NOT_ANALYSED
    )
}

