package br.com.felipe.gadelha.coffeandit.transactionbff.api.v1.dto.response;

import br.com.felipe.gadelha.coffeandit.transactionbff.domain.entity.Account;

public class AccountRs {

    private final Long id;
    private final Long agency;
    public AccountRs(Account account) {
        this.id = account.getId();
        this.agency = account.getAgency();
    }
    public Long getId() { return id; }
    public Long getAgency() { return agency; }
}
