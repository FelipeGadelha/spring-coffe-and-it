package br.com.felipe.gadelha.coffeandit.limitms.domain.repository

import br.com.felipe.gadelha.coffeandit.limitms.domain.entity.DailyLimit
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface DailyLimitRepository: JpaRepository<DailyLimit, Long> {

    fun findByAgencyAndAccount(Agency: String, Account: String): DailyLimit?
    fun findByAgencyAndAccountAndDate(Agency: String, Account: String, dateTime: LocalDateTime): DailyLimit?
}