package br.com.felipe.gadelha.coffeandit.transactionms.infra.event

import br.com.felipe.gadelha.coffeandit.transactionms.domain.entity.Transaction
import br.com.felipe.gadelha.coffeandit.transactionms.domain.service.TransactionService
import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class TransactionConsumer(
//    private val dailyLimitService: DailyLimitService,
    private val objectMapper: ObjectMapper,
    private val transactionService: TransactionService
//    @Value("\${app.topic}") private val topic: String
) {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(TransactionConsumer::class.java)
    }

    @KafkaListener(topics = ["\${events.consumerTopic}"])
    fun onConsumeTransaction(message: String) {
        val transaction = getTransaction(message)
        logger.info("onConsumeTransaction::transação {}", transaction)
        transactionService.update(transaction)
    }

    @KafkaListener(topics = ["\${events.returnTopic}"])
    fun onConsumeTransactionRefund(message: String) {
        val transaction = getTransaction(message)
        logger.info("onConsumeTransactionRefund::transação {}", transaction)
        if (transaction.isAnalyzed()) { return }
        else { }
        transactionService.update(transaction)
    }

    //    @Throws(IOException::class)
    private fun getTransaction(message: String): Transaction {
        val transaction: Transaction = objectMapper.readValue(message, Transaction::class.java)
        if (transaction.transactionStatus == null) { transaction.notAnalyzed() }
        return transaction.copy(date = LocalDateTime.now())
    }


}