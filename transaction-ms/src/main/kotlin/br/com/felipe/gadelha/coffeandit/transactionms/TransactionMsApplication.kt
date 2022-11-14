package br.com.felipe.gadelha.coffeandit.transactionms

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories

@SpringBootApplication
@EnableReactiveMongoRepositories
class TransactionMsApplication

fun main(args: Array<String>) {
	runApplication<TransactionMsApplication>(*args)
}
