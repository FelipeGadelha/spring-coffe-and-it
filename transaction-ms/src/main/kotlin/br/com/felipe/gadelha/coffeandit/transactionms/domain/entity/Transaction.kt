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
    var transactionStatus: TransactionStatus?
) {


    fun getAgency() = this.account.agency
    fun getAccountId() = this.account.id.toString()
    fun suspectedFraud() { this.transactionStatus = TransactionStatus.IN_SUSPECTED_FRAUD }
    fun notAnalyzed() { this.transactionStatus = TransactionStatus.NOT_ANALYSED }
    fun humanAnalysis() { this.transactionStatus = TransactionStatus.IN_HUMAN_ANALYSIS }
    fun analyzed() { this.transactionStatus = TransactionStatus.ANALYSED }
    fun isAnalyzed(): Boolean = this.transactionStatus == TransactionStatus.ANALYSED

}
