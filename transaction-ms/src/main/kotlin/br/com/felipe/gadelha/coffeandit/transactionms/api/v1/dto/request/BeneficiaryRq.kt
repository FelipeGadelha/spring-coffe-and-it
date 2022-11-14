package br.com.felipe.gadelha.coffeandit.transactionms.api.v1.dto.request

import br.com.felipe.gadelha.coffeandit.transactionms.domain.entity.Beneficiary

class BeneficiaryRq(
    val cpf: String,
    val codeBank: Long,
    val agency: String,
    val account: String,
    val accountOwner: String
) {
    fun toEntity(): Beneficiary = Beneficiary(cpf, codeBank, agency, account, accountOwner)
}