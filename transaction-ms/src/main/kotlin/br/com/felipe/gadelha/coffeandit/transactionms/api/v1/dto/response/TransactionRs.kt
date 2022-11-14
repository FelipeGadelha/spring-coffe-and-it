package br.com.felipe.gadelha.coffeandit.transactionms.api.v1.dto.response

import br.com.felipe.gadelha.coffeandit.transactionms.domain.entity.Transaction
import br.com.felipe.gadelha.coffeandit.transactionms.domain.entity.TransactionStatus
import br.com.felipe.gadelha.coffeandit.transactionms.domain.entity.TransactionType
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.data.annotation.Id
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID
import javax.validation.Valid
import javax.validation.constraints.NotNull

class TransactionRs private constructor (
    @Id @Schema(description = "Código de identificação da transação")
    val uuid: UUID,
    @Schema(description = "Valor da transação")
    @NotNull(message = "informar o valor da transação")
    val value: BigDecimal,

    @Schema(description = "Data/Hora/minuto e segundo da transação")
    val date: LocalDateTime,

    @Schema(description = "Conta de origen da transação")
    @Valid @NotNull(message = "informar a conta de origen da transação")
    val account: AccountRs,

    @Schema(description = "Beneficiário da transação") //    @NotNull(message = "informar a conta de origen da transação")
    val beneficiary: @Valid BeneficiaryRs,

    @Schema(description = "Tipo de transação")
    @NotNull(message = "informar o tipo da transação")
    val transactionType: TransactionType,

    @Schema(description = "Tipo de transação")
    val transactionStatus: TransactionStatus
) {
    constructor(transaction: Transaction):
        this(
            uuid = transaction.uuid!!,
            value = transaction.value,
            date = transaction.date,
            account = AccountRs(transaction.account),
            beneficiary = BeneficiaryRs(transaction.beneficiary),
            transactionType = transaction.transactionType,
            transactionStatus = transaction.transactionStatus
        )
}