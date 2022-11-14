package br.com.felipe.gadelha.coffeandit.transactionbff.domain.entity;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;

public class Beneficiary {
    @NotNull(message = "Informar o CPF")
    private Long cpf;
    @NotNull(message = "Informar o código do banco de destino")
    private Long codeBank;
    @NotNull(message = "Informar a agência de destino")
    private String agency;
    @NotNull(message = "Informar a conta de destino")
    private String account;
    @NotNull(message = "Informar o nome do dono da conta de destino")
    private String accountOwner;

    @Deprecated public Beneficiary() { }
    public Beneficiary(Long cpf, Long codeBank, String agency, String account, String accountOwner) {
        this.cpf = cpf;
        this.codeBank = codeBank;
        this.agency = agency;
        this.account = account;
        this.accountOwner = accountOwner;
    }
    public Long getCpf() { return cpf; }
    public Long getCodeBank() { return codeBank; }
    public String getAgency() { return agency; }
    public String getAccount() { return account; }
    public String getAccountOwner() { return accountOwner; }
}