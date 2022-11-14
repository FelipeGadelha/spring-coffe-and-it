package br.com.felipe.gadelha.coffeandit.transactionbff.api.v1.controller;

import br.com.felipe.gadelha.coffeandit.transactionbff.api.v1.controller.doc.TransactionControllerOpenApi;
import br.com.felipe.gadelha.coffeandit.transactionbff.api.v1.dto.request.TransactionRq;
import br.com.felipe.gadelha.coffeandit.transactionbff.api.v1.dto.response.TransactionRs;
import br.com.felipe.gadelha.coffeandit.transactionbff.domain.service.TransactionService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1/transactions")
public class TransactionController implements TransactionControllerOpenApi {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Override @GetMapping("/{id}")
    public Mono<TransactionRs> findById(@PathVariable final String id) {
        return transactionService.findById(id)
            .map(TransactionRs::new);
    }

    @GetMapping
    public Flux<TransactionRs> findAll() {
        return transactionService.findAll()
            .map(TransactionRs::new);
    }

    @Override @PostMapping
    public Mono<TransactionRs> sendTransaction(@RequestBody final TransactionRq transactionRq) {
        var transaction = transactionRq.toEntity();
        return transactionService.save(transaction)
            .map(TransactionRs::new);
    }

    @Override @DeleteMapping("/{id}")
    public Mono<TransactionRs> deleteById(@PathVariable final String id) {
        return Mono.empty();
    }

    @Override @PatchMapping("/{id}/authorize")
    public Mono<TransactionRs> authorize(@PathVariable final String id) {
        return Mono.empty();
    }
}
