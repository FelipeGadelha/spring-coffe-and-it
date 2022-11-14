package br.com.felipe.gadelha.coffeandit.transactionbff.api.v1.dto.response;

import br.com.felipe.gadelha.coffeandit.transactionbff.domain.entity.Beneficiary;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;

public class BeneficiaryRs {

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


    public BeneficiaryRs(Beneficiary beneficiary) {
        this.cpf = beneficiary.getCpf();
        this.codeBank = beneficiary.getCodeBank();
        this.agency = beneficiary.getAgency();
        this.account = beneficiary.getAccount();
        this.accountOwner = beneficiary.getAccountOwner();
    }
    public Long getCpf() { return cpf; }
    public Long getCodeBank() { return codeBank; }
    public String getAgency() { return agency; }
    public String getAccount() { return account; }
    public String getAccountOwner() { return accountOwner; }
}
