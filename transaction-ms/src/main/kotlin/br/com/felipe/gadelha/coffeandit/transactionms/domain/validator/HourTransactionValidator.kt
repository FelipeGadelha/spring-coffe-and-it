package br.com.felipe.gadelha.coffeandit.transactionms.domain.validator

import br.com.felipe.gadelha.coffeandit.transactionms.domain.entity.Transaction
import br.com.felipe.gadelha.coffeandit.transactionms.domain.exception.BusinessException
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
@ConditionalOnProperty(
    value = ["\${transaction.validation.hour}"],
    havingValue = "true",
    matchIfMissing = false
)
class HourTransactionValidator : TransactionValidator {

    companion object { const val HOUR_END = 18 }

    override fun validate(transaction: Transaction) {
        if(LocalDateTime.now().hour > HOUR_END || transaction.date.hour > HOUR_END)
            throw BusinessException("Horário após as 18.")
    }
}