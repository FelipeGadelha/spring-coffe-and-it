package br.com.felipe.gadelha.coffeandit.transactionms.api.v1.dto.response

import br.com.felipe.gadelha.coffeandit.transactionms.domain.entity.Account

class AccountRs private constructor (
    val id: Long,
    val agency: String
) {
    constructor(account: Account):
        this(
            id = account.id!!,
            agency = account.agency
    )
}