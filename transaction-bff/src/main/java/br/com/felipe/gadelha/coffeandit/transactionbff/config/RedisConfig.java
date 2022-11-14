package br.com.felipe.gadelha.coffeandit.transactionbff.config;

import br.com.felipe.gadelha.coffeandit.transactionbff.api.v1.dto.response.TransactionRs;
import br.com.felipe.gadelha.coffeandit.transactionbff.domain.entity.Transaction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

//    @Bean
//    public ReactiveRedisTemplate<String, TransactionRs> reactiveJsonTransactionRsRedisTemplate(
//        ReactiveRedisConnectionFactory connectionFactory
//    ) {
//        var serializationContext = RedisSerializationContext
//            .<String, TransactionRs>newSerializationContext(new StringRedisSerializer())
//            .hashKey(new StringRedisSerializer())
//            .hashValue(new Jackson2JsonRedisSerializer<>(TransactionRs.class))
//            .build();
//        return new ReactiveRedisTemplate<>(connectionFactory, serializationContext);
//    }
//
    @Bean
    ReactiveRedisOperations<String, Transaction> redisOperations(ReactiveRedisConnectionFactory factory) {
        var serializer = new Jackson2JsonRedisSerializer<>(Transaction.class);

        RedisSerializationContext.RedisSerializationContextBuilder<String, Transaction> builder =
            RedisSerializationContext.newSerializationContext(new StringRedisSerializer());

        RedisSerializationContext<String, Transaction> context = builder
            .value(serializer)
            .build();

        return new ReactiveRedisTemplate<>(factory, context);
    }
}
