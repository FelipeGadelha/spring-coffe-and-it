package br.com.felipe.gadelha.coffeandit.transactionms.domain.validator

import br.com.felipe.gadelha.coffeandit.transactionms.domain.entity.Transaction
import br.com.felipe.gadelha.coffeandit.transactionms.domain.exception.BusinessException
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Component

@Component
@ConditionalOnProperty(
    value = ["\${transaction.validation.bank}"],
    havingValue = "true",
    matchIfMissing = false
)
class BankTransactionValidator: TransactionValidator {

    companion object { const val BANK_CODE = 785 }

    override fun validate(transaction: Transaction) {
        if(transaction.beneficiary.codeBank.compareTo(BANK_CODE) != 0)
            throw BusinessException("Inválido banco do beneficiário")
    }

}