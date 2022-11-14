package br.com.felipe.gadelha.coffeandit.transactionbff.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DailyLimit {

    private final Long id;
    private final String agency;
    private final String account;
    private final LocalDate date;
    private final BigDecimal value;

    @Deprecated
    private DailyLimit(Long id, String agency, String account, LocalDate date, BigDecimal value) {
        this.id = id;
        this.agency = agency;
        this.account = account;
        this.date = date;
        this.value = value;
    }
    private DailyLimit(Builder builder) {
        this.id = builder.id;
        this.agency = builder.agency;
        this.account = builder.account;
        this.date = builder.date;
        this.value = builder.value;
    }
    public static Builder builder() { return new Builder(); }
    public static class Builder {
        private Long id;
        private String agency;
        private String account;
        private LocalDate date;
        private BigDecimal value;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }
        public Builder agency(String agency) {
            this.agency = agency;
            return this;
        }
        public Builder account(String account) {
            this.account = account;
            return this;
        }
        public Builder date(LocalDate date) {
            this.date = date;
            return this;
        }
        public Builder value(BigDecimal value) {
            this.value = value;
            return this;
        }
        public DailyLimit build() { return new DailyLimit(this); }
    }
    public Long getId() { return id; }
    public String getAgency() { return agency; }
    public String getAccount() { return account; }
    public LocalDate getDate() { return date; }
    public BigDecimal getValue() { return value; }
}
