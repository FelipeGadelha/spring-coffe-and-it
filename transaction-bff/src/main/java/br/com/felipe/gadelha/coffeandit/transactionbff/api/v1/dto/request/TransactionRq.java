package br.com.felipe.gadelha.coffeandit.transactionbff.api.v1.dto.request;

import br.com.felipe.gadelha.coffeandit.transactionbff.domain.entity.Account;
import br.com.felipe.gadelha.coffeandit.transactionbff.domain.entity.Transaction;
import br.com.felipe.gadelha.coffeandit.transactionbff.domain.entity.TransactionStatus;
import br.com.felipe.gadelha.coffeandit.transactionbff.domain.entity.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class TransactionRq {

    private final BeneficiaryRq beneficiary;
    private final TransactionType transactionType;
    private final BigDecimal value;

    public TransactionRq(BeneficiaryRq beneficiary, TransactionType transactionType, BigDecimal value) {
        this.beneficiary = beneficiary;
        this.transactionType = transactionType;
        this.value = value;
    }

    public Transaction toEntity() {
        return new Transaction(
            UUID.randomUUID(),
            value,
            LocalDateTime.now(),
            new Account(1L, 20L),
            beneficiary.toEntity(),
            transactionType,
            TransactionStatus.NOT_ANALYSED
        );
    }
    public BeneficiaryRq getBeneficiary() { return beneficiary; }
    public BigDecimal getValue() { return value; }
    public TransactionType getTransactionType() { return transactionType; }
}
