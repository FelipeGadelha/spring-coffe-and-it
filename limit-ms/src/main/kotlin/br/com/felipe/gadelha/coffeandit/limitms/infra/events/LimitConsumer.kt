package br.com.felipe.gadelha.coffeandit.limitms.infra.events

import br.com.felipe.gadelha.coffeandit.limitms.domain.entity.Transaction
import br.com.felipe.gadelha.coffeandit.limitms.domain.service.DailyLimitService
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class LimitConsumer(
    private val dailyLimitService: DailyLimitService,
    private val objectMapper: ObjectMapper,
    @Value("\${app.topic}") private val topic: String
) {

    @KafkaListener(topics = ["\${app.topic}"])
    fun onConsume(message: String) {
        val transaction = getTransaction(message)
        dailyLimitService.dailyLimitValidate(transaction)
    }

//    @Throws(IOException::class)
    private fun getTransaction(message: String): Transaction {
        val transaction: Transaction = objectMapper.readValue(message, Transaction::class.java)
        return transaction.copy(date = LocalDateTime.now())
    }
}