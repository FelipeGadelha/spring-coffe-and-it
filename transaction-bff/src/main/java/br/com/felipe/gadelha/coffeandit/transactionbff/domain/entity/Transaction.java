package br.com.felipe.gadelha.coffeandit.transactionbff.domain.entity;


import br.com.felipe.gadelha.coffeandit.transactionbff.api.v1.dto.response.AccountRs;
import br.com.felipe.gadelha.coffeandit.transactionbff.api.v1.dto.response.BeneficiaryRs;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@RedisHash(value = "TransactionRs", timeToLive = 300)
public class Transaction {
    @Id
    @Schema(description = "Código de identificação da transação")
    private UUID uuid;
    @Schema(description = "Valor da transação")
    @NotNull(message = "informar o valor da transação")
    private BigDecimal value;
    @Schema(description = "Data/Hora/minuto e segundo da transação")
//    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime date;
    @Valid
    @Schema(description = "Conta de origen da transação")
    @NotNull(message = "informar a conta de origen da transação")
    private Account account;
    @Valid
    @Schema(description = "Beneficiário da transação")
//    @NotNull(message = "informar a conta de origen da transação")
    private Beneficiary beneficiary;
    @Schema(description = "Tipo de transação")
    @NotNull(message = "informar o tipo da transação")
    private TransactionType transactionType;

    @Schema(description = "Tipo de transação")
    private TransactionStatus transactionStatus;

    @Deprecated public Transaction() { }

    public Transaction(UUID uuid, BigDecimal value, LocalDateTime date, Account account, Beneficiary beneficiary, TransactionType transactionType, TransactionStatus transactionStatus) {
        this.uuid = uuid;
        this.value = value;
        this.date = date;
        this.account = account;
        this.beneficiary = beneficiary;
        this.transactionType = transactionType;
        this.transactionStatus = transactionStatus;
    }
    public UUID getUuid() { return uuid; }
    public BigDecimal getValue() { return value; }
    public LocalDateTime getDate() { return date; }
    public Account getAccount() { return account; }
    public Beneficiary getBeneficiary() { return beneficiary; }
    public TransactionType getTransactionType() { return transactionType; }
    public TransactionStatus getTransactionStatus() { return transactionStatus; }
}

