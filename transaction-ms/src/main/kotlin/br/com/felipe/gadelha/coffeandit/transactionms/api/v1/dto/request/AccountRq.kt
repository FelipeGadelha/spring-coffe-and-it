package br.com.felipe.gadelha.coffeandit.transactionms.api.v1.dto.request

import br.com.felipe.gadelha.coffeandit.transactionms.domain.entity.Account

class AccountRq(
    private val id: Long,
    private val agency: Long
) {

    fun toEntity(): Account {
        return Account(id, agency.toString())
    }
}
