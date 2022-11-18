package br.com.felipe.gadelha.coffeandit.transactionms.config

import org.springframework.boot.system.JavaVersion
import org.springframework.context.annotation.Condition
import org.springframework.context.annotation.ConditionContext
import org.springframework.core.type.AnnotatedTypeMetadata
import org.springframework.stereotype.Component

@Component
class TransactionEnableNewerThanJavaSevenTeen: Condition {

//    @Value("\${transaction.validation.enabled:true}")
//    private var isTransactionEnabled: Boolean = false

    override fun matches(
        context: ConditionContext,
        metadata: AnnotatedTypeMetadata
    ): Boolean {
        val isTransactionEnabled: Boolean =
            java.lang.Boolean.parseBoolean(
                context.environment.getProperty("transaction.validation.enabled")
            )
        println("TEST-------------------------------------------- ${!isTransactionEnabled}")
        return !isTransactionEnabled
            && JavaVersion.getJavaVersion()
                .isEqualOrNewerThan(JavaVersion.SEVENTEEN)
    }
}
