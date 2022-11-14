package br.com.felipe.gadelha.coffeandit.transactionms.domain.validator

import br.com.felipe.gadelha.coffeandit.transactionms.domain.entity.Transaction
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
@ConditionalOnBean(value = [BankTransactionValidator::class, HourTransactionValidator::class])
@ConditionalOnExpression("\${transaction.validation.enabled:true}")
class TransactionValidationBean: TransactionValidation {

    private var transactionValidators: MutableList<TransactionValidator> = ArrayList()

    @PostConstruct
    fun addBeans() {
        transactionValidators.add(BankTransactionValidator())
        transactionValidators.add(HourTransactionValidator())
    }

    override fun validate(transaction: Transaction) {
        transactionValidators.forEach{ t -> t.validate(transaction) }
    }
}