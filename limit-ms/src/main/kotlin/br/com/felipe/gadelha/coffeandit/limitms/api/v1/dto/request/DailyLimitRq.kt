package br.com.felipe.gadelha.coffeandit.limitms.api.v1.dto.request

import br.com.felipe.gadelha.coffeandit.limitms.domain.entity.DailyLimit
import java.math.BigDecimal
import java.time.LocalDate
import javax.validation.constraints.NotBlank

class DailyLimitRq(
    @field:NotBlank val account: String,
    @field:NotBlank val agency: String,
    @field:NotBlank val createdAt: LocalDate,
    @field:NotBlank val value: BigDecimal
) {
    fun toEntity() = DailyLimit(
        account = account,
        agency = agency,
        date = createdAt,
        value = value
    )
}