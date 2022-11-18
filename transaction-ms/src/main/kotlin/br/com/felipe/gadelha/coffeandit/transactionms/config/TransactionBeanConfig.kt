package br.com.felipe.gadelha.coffeandit.transactionms.config

import br.com.felipe.gadelha.coffeandit.transactionms.domain.validator.EmptyTransactionValidationBean
import br.com.felipe.gadelha.coffeandit.transactionms.domain.validator.TransactionValidation
import br.com.felipe.gadelha.coffeandit.transactionms.domain.validator.TransactionValidationBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnJava
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.system.JavaVersion
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Conditional
import org.springframework.context.annotation.Configuration
import org.springframework.boot.autoconfigure.condition.ConditionalOnJava.Range.OLDER_THAN


@Configuration
class TransactionBeanConfig {

    @Bean
    @ConditionalOnMissingBean
    @Conditional(TransactionEnableNewerThanJavaSevenTeen::class)
    fun emptyTransactionNewerThanJavaSeventeenValidation(): TransactionValidation {
        return EmptyTransactionValidationBean()
    }

    @Bean
    @ConditionalOnMissingBean
    @Conditional(TransactionEnableNewerThanJavaSevenTeen::class)
    @ConditionalOnJava(range = OLDER_THAN, value = JavaVersion.SEVENTEEN)
    fun emptyTransactionJavaOlderThanSeventeenValidation(): TransactionValidation {
        return EmptyTransactionValidationBean()
    }
}