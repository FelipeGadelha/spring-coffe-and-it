package br.com.felipe.gadelha.coffeandit.transactionms.api.v1.controller

import br.com.felipe.gadelha.coffeandit.transactionms.domain.entity.Transaction
import br.com.felipe.gadelha.coffeandit.transactionms.domain.repository.TransactionRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/v1/transactions")
class TransactionController(
    private val transactionRepository: TransactionRepository
) {

//    @PostMapping("/dev")
//    @ResponseStatus(HttpStatus.CREATED)
//    fun createDev(@Valid @RequestBody devs: Devs?): Mono<Devs?>? {
//        return transactionRepository.save(devs)
//    }

    @GetMapping("/hello") fun string() = ResponseEntity.ok("Hello World")

    @GetMapping fun findAll(): Flux<Transaction> = transactionRepository.findAll()

    @GetMapping("/{id}")
    fun findById(@PathVariable(value = "id") id: String): Mono<ResponseEntity<Transaction>> {
        return transactionRepository.findById(id)
            .map { saved -> ResponseEntity.ok(saved) }
            .defaultIfEmpty(ResponseEntity.notFound().build())
    }

//    @PutMapping("/dev/{id}")
//    fun update(@PathVariable(value = "id") id: String,
//                  @Valid @RequestBody devs: Devs): Mono<ResponseEntity<Transaction>> {
//        return transactionRepository.findById(id)
//                .flatMap { existingDev ->
//                    existingDev.setName(devs.getName())
//                    transactionRepository.save(existingDev)
//                }.map { updateDev -> ResponseEntity<T>(updateDev, HttpStatus.OK) }
//                .defaultIfEmpty(ResponseEntity<T>(HttpStatus.NOT_FOUND))
//    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteById(@PathVariable(value = "id") id: String) {
        transactionRepository.findById(id)
            .flatMap { transaction -> transactionRepository.delete(transaction) }
    }
}