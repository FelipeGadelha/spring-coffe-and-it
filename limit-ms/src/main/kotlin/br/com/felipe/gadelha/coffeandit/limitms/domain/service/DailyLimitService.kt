package br.com.felipe.gadelha.coffeandit.limitms.domain.service

import br.com.felipe.gadelha.coffeandit.limitms.domain.entity.DailyLimit
import br.com.felipe.gadelha.coffeandit.limitms.domain.exception.EntityNotFoundException
import br.com.felipe.gadelha.coffeandit.limitms.domain.repository.DailyLimitRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal
import java.time.LocalDate

@Service
class DailyLimitService(
    private val dailyLimitRepository: DailyLimitRepository,
    @Value("\${limit.value:0}") val dailyValue: BigDecimal
) {
    companion object {
        const val MSG_DAILY_LIMIT_NOT_FOUND = "Não existe limite diário com o ID %d"
        const val MSG_DAILY_LIMIT_IN_USE = "Limite diário com o %d não pode ser removido, pois esta em uso"
    }
    @Transactional
    fun findById(id: Long): DailyLimit =
        dailyLimitRepository
            .findById(id)
            .orElseThrow { EntityNotFoundException(String.format(MSG_DAILY_LIMIT_NOT_FOUND, id)) }

    @Transactional
    fun findByAgencyAndAccount(agency: String, account: String): DailyLimit =
        dailyLimitRepository.findByAgencyAndAccount(agency, account)
        ?: dailyLimitRepository.save(
            DailyLimit(
                agency = agency,
                account = account,
                date = LocalDate.now(),
                value = dailyValue
            )
        )

    fun findAll(): List<DailyLimit> = dailyLimitRepository.findAll()
}