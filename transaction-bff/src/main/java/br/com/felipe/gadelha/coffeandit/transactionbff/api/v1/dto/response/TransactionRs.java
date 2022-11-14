package br.com.felipe.gadelha.coffeandit.transactionbff.api.v1.dto.response;

import br.com.felipe.gadelha.coffeandit.transactionbff.domain.entity.Transaction;
import br.com.felipe.gadelha.coffeandit.transactionbff.domain.entity.TransactionStatus;
import br.com.felipe.gadelha.coffeandit.transactionbff.domain.entity.TransactionType;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@RedisHash(value = "TransactionRs", timeToLive = 300)
public class TransactionRs {
    @Id @Schema(description = "Código de identificação da transação")
    private final UUID uuid;
    @Schema(description = "Valor da transação")
    @NotNull(message = "informar o valor da transação")
    private final BigDecimal value;
    @Schema(description = "Data/Hora/minuto e segundo da transação")
    private final LocalDateTime date;
    @Valid
    @Schema(description = "Conta de origen da transação")
    @NotNull(message = "informar a conta de origen da transação")
    private final AccountRs account;
    @Valid
    @Schema(description = "Beneficiário da transação")
//    @NotNull(message = "informar a conta de origen da transação")
    private final BeneficiaryRs beneficiary;
    @Schema(description = "Tipo de transação")
    @NotNull(message = "informar o tipo da transação")
    private final TransactionType transactionType;

    @Schema(description = "Tipo de transação")
    private final TransactionStatus transactionStatus;

    public TransactionRs(Transaction transaction) {
        this.uuid = transaction.getUuid();
        this.value = transaction.getValue();
        this.date = transaction.getDate();
        this.account = new AccountRs(transaction.getAccount());
        this.beneficiary = new BeneficiaryRs(transaction.getBeneficiary());
        this.transactionType = transaction.getTransactionType();
        this.transactionStatus = transaction.getTransactionStatus();
    }
    public UUID getUuid() { return uuid; }
    public BigDecimal getValue() { return value; }
    public LocalDateTime getDate() { return date; }
    public AccountRs getAccount() { return account; }
    public BeneficiaryRs getBeneficiary() { return beneficiary; }
    public TransactionType getTransactionType() { return transactionType; }
    public TransactionStatus getTransactionStatus() { return transactionStatus; }
}
