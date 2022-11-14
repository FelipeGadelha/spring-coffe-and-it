package br.com.felipe.gadelha.coffeandit.transactionms.domain.validator

import br.com.felipe.gadelha.coffeandit.transactionms.domain.entity.Transaction
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression
import org.springframework.stereotype.Component

@Component
//@ConditionalOnExpression("\${transaction.validation.enabled:false}")
class EmptyTransactionValidationBean: TransactionValidation {
    override fun validate(transaction: Transaction) { }
}
