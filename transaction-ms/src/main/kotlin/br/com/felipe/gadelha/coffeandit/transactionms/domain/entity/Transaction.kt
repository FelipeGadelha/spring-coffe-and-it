package br.com.felipe.gadelha.coffeandit.transactionms.domain.entity

import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

@Document(collection = "transactions")
data class Transaction(
    var uuid: UUID? = null,
    val value: BigDecimal,
    val date: LocalDateTime,
    val account: Account,
    val beneficiary: Beneficiary,
    val transactionType: TransactionType,
    val transactionStatus: TransactionStatus
) {

}
