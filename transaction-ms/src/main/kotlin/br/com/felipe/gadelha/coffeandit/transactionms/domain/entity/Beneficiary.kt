package br.com.felipe.gadelha.coffeandit.transactionms.domain.entity

data class Beneficiary(
    val cpf: String,
    val codeBank: Long,
    val agency: String,
    val account: String,
    val accountOwner: String
) {

}
