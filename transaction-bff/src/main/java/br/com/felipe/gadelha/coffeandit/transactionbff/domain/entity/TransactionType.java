package br.com.felipe.gadelha.coffeandit.transactionbff.domain.entity;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema
public enum TransactionType {
    TAX_PAYMENT,
    DEBT_PAYMENT,
    TED,
    DOC
}
