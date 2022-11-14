package br.com.felipe.gadelha.coffeandit.transactionbff.api.v1.dto.request;

import br.com.felipe.gadelha.coffeandit.transactionbff.domain.entity.Account;

public class AccountRq {
    private final Long id;
    private final Long agency;
    public AccountRq(Account account) {
        this.id = account.getId();
        this.agency = account.getAgency();
    }
    public Account toEntity() {
        return new Account(id, agency);
    }
}
