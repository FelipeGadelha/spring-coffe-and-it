package br.com.felipe.gadelha.coffeandit.transactionms.api.v1.dto.response

import br.com.felipe.gadelha.coffeandit.transactionms.domain.entity.Beneficiary
import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.constraints.NotNull

class BeneficiaryRs private constructor (
    @Schema(description = "CPF do beneficiaŕio")
    @NotNull(message = "Informar o CPF")
    val cpf: String,
    @Schema(description = "Código do banco de destino")
    @NotNull(message = "Informar o código do banco de destino")
    val codeBank: Long,
    @Schema(description = "Agência de destino")
    @NotNull(message = "Informar a agência de destino")
    val agency: String,
    @Schema(description = "Conta de destino")
    @NotNull(message = "Informar a conta de destino")
    val account: String,
    @Schema(description = "Nome do dono da conta de destino")
    @NotNull(message = "Informar o nome do dono da conta de destino")
    val accountOwner: String
) {
    constructor(beneficiary: Beneficiary):
        this(
            cpf = beneficiary.cpf,
            codeBank = beneficiary.codeBank,
            agency = beneficiary.agency,
            account = beneficiary.account,
            accountOwner = beneficiary.accountOwner

        )

}