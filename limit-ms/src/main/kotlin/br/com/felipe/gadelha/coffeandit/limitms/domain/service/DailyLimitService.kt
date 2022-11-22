package br.com.felipe.gadelha.coffeandit.limitms.domain.service

import br.com.felipe.gadelha.coffeandit.limitms.domain.entity.DailyLimit
import br.com.felipe.gadelha.coffeandit.limitms.domain.entity.Transaction
import br.com.felipe.gadelha.coffeandit.limitms.domain.exception.EntityNotFoundException
import br.com.felipe.gadelha.coffeandit.limitms.domain.repository.DailyLimitRepository
import br.com.felipe.gadelha.coffeandit.limitms.infra.event.LimitProducer
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal
import java.time.LocalDateTime

@Service
class DailyLimitService(
    private val dailyLimitRepository: DailyLimitRepository,
    private val limitProducer: LimitProducer,
    @Value("\${limit.value:0}") val dailyValue: BigDecimal
) {
    companion object {
        const val MSG_DAILY_LIMIT_NOT_FOUND = "Não existe limite diário com o ID %d"
        const val MSG_DAILY_LIMIT_IN_USE = "Limite diário com o %d não pode ser removido, pois esta em uso"
        private val logger: Logger = LoggerFactory.getLogger(DailyLimitService::class.java)
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
                date = LocalDateTime.now(),
                value = dailyValue
            )
        )

    fun findAll(): List<DailyLimit> = dailyLimitRepository.findAll()

    fun dailyLimitValidate(transaction: Transaction) {
        transaction.let {
            val dailyLimit = dailyLimitRepository.
                findByAgencyAndAccountAndDate(it.getAgency(), it.getAccountId(), LocalDateTime.now())
                    ?: dailyLimitRepository.save(DailyLimit(
                            agency = it.getAgency(),
                            account = it.getAccountId(),
                            date = it.date,
                            value = dailyValue
                        )
                    )
            if (dailyLimit.value < it.value) {
                it.suspectedFraud()
                logger.info("Transação excede valor diário: {}", it)
            }
            else if (dailyLimit.value > BigDecimal.valueOf(10000)) {
                it.humanAnalysis()
                logger.info("Transação está em analise humana: {}", it)
            }
            else {
                it.analyzed()
                logger.info("Transação está analisada")
                dailyLimit.value.subtract(it.value)
                dailyLimitRepository.save(dailyLimit)
            }
            limitProducer.send(it)
        }
    }
}