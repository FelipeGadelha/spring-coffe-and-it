package br.com.felipe.gadelha.coffeandit.limitms.domain.entity

import java.math.BigDecimal
import java.time.LocalDate
import java.util.Objects
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "daily_limits")
data class DailyLimit(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    val agency: String,
    val account: String,
    val date: LocalDate,
    val value: BigDecimal
) {
    fun update(updated: DailyLimit) = DailyLimit(
        id = if (Objects.isNull(id)) this.id else updated.id,
        agency = updated.agency,
        account = updated.account,
        date = updated.date,
        value = updated.value
    )
}
