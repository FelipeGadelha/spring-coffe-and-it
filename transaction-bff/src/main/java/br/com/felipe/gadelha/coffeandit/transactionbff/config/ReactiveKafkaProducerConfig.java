package br.com.felipe.gadelha.coffeandit.transactionbff.config;

import br.com.felipe.gadelha.coffeandit.transactionbff.domain.entity.Transaction;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import reactor.kafka.sender.SenderOptions;

@Configuration
public class ReactiveKafkaProducerConfig {

    @Bean
    public ReactiveKafkaProducerTemplate<String, Transaction> reactiveKafkaProducerTemplate(
        final KafkaProperties kafkaProperties
    ) {
        final var properties = kafkaProperties
            .buildProducerProperties();
        return new ReactiveKafkaProducerTemplate<>(SenderOptions.create(properties));
    }
}
