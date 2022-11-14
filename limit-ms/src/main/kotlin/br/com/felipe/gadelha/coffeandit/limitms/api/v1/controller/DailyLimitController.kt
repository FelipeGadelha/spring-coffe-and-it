package br.com.felipe.gadelha.coffeandit.limitms.api.v1.controller

import br.com.felipe.gadelha.coffeandit.limitms.api.v1.dto.response.DailyLimitRs
import br.com.felipe.gadelha.coffeandit.limitms.domain.service.DailyLimitService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/daily-limits")
class DailyLimitController(
    private val dailyLimitService: DailyLimitService
) {

    @GetMapping()
    fun findById(): ResponseEntity<List<DailyLimitRs>> =
        ResponseEntity
            .ok(dailyLimitService.findAll().map { DailyLimitRs(it) })

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<DailyLimitRs> =
        ResponseEntity
            .ok(DailyLimitRs(dailyLimitService.findById(id)))

    @GetMapping("/{agency}/{account}")
    fun findByAgencyAndAccount(
        @PathVariable agency: String,
        @PathVariable account: String
    ): ResponseEntity<DailyLimitRs> {
        val dailyLimit = dailyLimitService.findByAgencyAndAccount(agency, account)
        return ResponseEntity.ok(DailyLimitRs(dailyLimit))
    }
}