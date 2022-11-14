package br.com.felipe.gadelha.coffeandit.transactionbff.domain.entity;

public class Account {
    private Long id;
    private Long agency;

    @Deprecated public Account() {}
    public Account(Long id, Long agency) {
        this.id = id;
        this.agency = agency;
    }
    public Long getId() { return id; }
    public Long getAgency() { return agency; }
}
