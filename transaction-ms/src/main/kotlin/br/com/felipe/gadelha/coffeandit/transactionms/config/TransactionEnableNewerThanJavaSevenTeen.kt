package br.com.felipe.gadelha.coffeandit.transactionms.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.system.JavaVersion
import org.springframework.context.annotation.Condition
import org.springframework.context.annotation.ConditionContext
import org.springframework.context.annotation.PropertySource
import org.springframework.core.type.AnnotatedTypeMetadata
import org.springframework.stereotype.Component

@Component
@PropertySource(value = ["file:src/main/resources/application.yml"])
class TransactionEnableNewerThanJavaSevenTeen : Condition {
//    @Value("\${transaction.validation.enabled:true}")
//    private val isTransactionEnabled = false

    override fun matches(
        context: ConditionContext,
        metadata: AnnotatedTypeMetadata
    ): Boolean {
        val isTransactionEnabled: Boolean = java.lang.Boolean.parseBoolean(context
            .environment.getProperty("transaction.validation.enabled"))
        return isTransactionEnabled
            && JavaVersion.getJavaVersion()
                .isEqualOrNewerThan(JavaVersion.SEVENTEEN)
    }
}
