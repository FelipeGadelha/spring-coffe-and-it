package br.com.felipe.gadelha.coffeandit.transactionbff.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.time.Duration;

@Configuration
public class CircuitBrakerConfig {
    // https://betterprogramming.pub/implementing-reactive-circuit-breaker-using-resilience4j-4fe81d28e100

//    @Bean
//    public Customizer<ReactiveResilience4JCircuitBreakerFactory> countCircuitBreaker() {
//        return factory -> factory
//            .configure(builder -> builder
//                .circuitBreakerConfig(CircuitBreakerConfig.custom()
//                    .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
//                    .slidingWindowSize(5)
//                    .slowCallRateThreshold(65.0f) // Limite de taxa de chamada lenta = 65%
//                    .slowCallDurationThreshold(Duration.ofSeconds(2)) // Limite de duração de chamada lenta = 2 segundos
//                    .build()
//                ), "limitsSearchBasedOnCount"
//            );
//    }

    @Bean
    public Customizer<ReactiveResilience4JCircuitBreakerFactory> timeCircuitBraker() {
        return factory -> factory
            .configure(builder -> builder
                .circuitBreakerConfig(CircuitBreakerConfig.custom()
                    .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.TIME_BASED)
                    .minimumNumberOfCalls(3)
                    .slidingWindowSize(5)
                    .failureRateThreshold(70.0f)
                    .waitDurationInOpenState(Duration.ofSeconds(10))
                    .writableStackTraceEnabled(false)
                    .build()
                ), "limitsSearchBasedOnTime"
            );
    }
}
