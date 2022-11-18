package br.com.felipe.gadelha.coffeandit.limitms.api.v1.dto.response

import br.com.felipe.gadelha.coffeandit.limitms.domain.entity.DailyLimit
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

class DailyLimitRs private constructor(
    val id: Long,
    val agency: String,
    val account: String,
    val date: LocalDateTime,
    val value: BigDecimal
) {
    constructor(dailyLimit: DailyLimit) :
        this(
            id = dailyLimit.id!!,
            agency = dailyLimit.agency,
            account = dailyLimit.account,
            date = dailyLimit.date,
            value = dailyLimit.value
        )
}