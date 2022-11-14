package br.com.felipe.gadelha.coffeandit.transactionbff.api.v1.controller;

import br.com.felipe.gadelha.coffeandit.transactionbff.domain.entity.DailyLimit;
import br.com.felipe.gadelha.coffeandit.transactionbff.domain.service.LimitService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1/daily-limits")
public class LimitController {

    private final LimitService limitService;

    public LimitController(LimitService limitService) {
        this.limitService = limitService;
    }

    @GetMapping("/{agency}/{account}")
    public Mono<DailyLimit> findByAgencyAndAccount(
        @PathVariable final String agency,
        @PathVariable final String account
    ) {
        return limitService.findByAgencyAndAccount(agency, account);
    }
}
