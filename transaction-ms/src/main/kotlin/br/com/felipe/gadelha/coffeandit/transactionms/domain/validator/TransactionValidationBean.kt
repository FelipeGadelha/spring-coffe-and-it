package br.com.felipe.gadelha.coffeandit.transactionms.domain.validator

import br.com.felipe.gadelha.coffeandit.transactionms.domain.entity.Transaction
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
@ConditionalOnBean(value = [BankTransactionValidator::class, HourTransactionValidator::class])
@ConditionalOnExpression("\${transaction.validation.enabled:true}")
class TransactionValidationBean: TransactionValidation {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(TransactionValidationBean::class.java)
    }

    private var transactionValidators: MutableList<TransactionValidator> = ArrayList()

//    init {
//        transactionValidators.add(BankTransactionValidator())
//        transactionValidators.add(HourTransactionValidator())
//    }

    @PostConstruct
    fun addBeans() {
        transactionValidators.add(BankTransactionValidator())
        transactionValidators.add(HourTransactionValidator())
    }
    override fun validate(transaction: Transaction) {
        logger.info("TransactionValidationBean::validate iniciando validação")
        transactionValidators.forEach{ t -> t.validate(transaction) }
    }
}