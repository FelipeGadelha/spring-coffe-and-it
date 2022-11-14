package br.com.felipe.gadelha.coffeandit.transactionbff.domain.entity;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema
public enum TransactionStatus {
    ANALYSED,
    NOT_ANALYSED,
    IN_HUMAN_ANALYSIS,
    IN_SUSPECTED_FRAUD,
    CONFIRMED_RISK
}
