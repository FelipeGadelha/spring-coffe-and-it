package br.com.felipe.gadelha.coffeandit.limitms.infra.event

import br.com.felipe.gadelha.coffeandit.limitms.domain.entity.Transaction
import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.Message
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Component

@Component
class LimitProducer(
    private val kafkaTemplate: KafkaTemplate<String, String>,
    private val objectMapper: ObjectMapper,
    @Value("\${app.returnTopic}") private val topic: String
) {
    companion object {
        private val logger: Logger = LoggerFactory.getLogger(LimitProducer::class.java)
    }

    fun send(transaction: Transaction) {
        val payload: String = objectMapper.writeValueAsString(transaction)
        val message: Message<String> = MessageBuilder
            .withPayload(payload)
            .setHeader(KafkaHeaders.TOPIC, topic)
            .setHeader(KafkaHeaders.PARTITION_ID, 0)
            .build()
        kafkaTemplate.send(message)
    }
}