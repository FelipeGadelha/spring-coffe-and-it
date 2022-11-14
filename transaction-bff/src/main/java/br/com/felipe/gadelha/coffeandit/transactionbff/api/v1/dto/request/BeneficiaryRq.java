package br.com.felipe.gadelha.coffeandit.transactionbff.api.v1.dto.request;

import br.com.felipe.gadelha.coffeandit.transactionbff.domain.entity.Beneficiary;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;

public class BeneficiaryRq {
    @Schema(description = "CPF do beneficiaŕio")
    @NotNull(message = "Informar o CPF")
    private final Long cpf;
    @Schema(description = "Código do banco de destino")
    @NotNull(message = "Informar o código do banco de destino")
    private final Long codeBank;
    @Schema(description = "Agência de destino")
    @NotNull(message = "Informar a agência de destino")
    private final String agency;
    @Schema(description = "Conta de destino")
    @NotNull(message = "Informar a conta de destino")
    private final String account;
    @Schema(description = "Nome do dono da conta de destino")
    @NotNull(message = "Informar o nome do dono da conta de destino")
    private final String accountOwner;

    public BeneficiaryRq(Long cpf, Long codeBank, String agency, String account, String accountOwner) {
        this.cpf = cpf;
        this.codeBank = codeBank;
        this.agency = agency;
        this.account = account;
        this.accountOwner = accountOwner;
    }

    public Beneficiary toEntity() {
        return new Beneficiary(cpf, codeBank, agency, account, accountOwner);
    }
    public Long getCpf() { return cpf; }
    public Long getCodeBank() { return codeBank; }
    public String getAgency() { return agency; }
    public String getAccount() { return account; }
    public String getAccountOwner() { return accountOwner; }
}
