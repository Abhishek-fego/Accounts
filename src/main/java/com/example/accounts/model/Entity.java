package com.example.accounts.model;

import java.math.BigDecimal;
import java.util.List;

public class Entity {

    public long numberOfAccounts;

    public BigDecimal aggregatedBalance;

    List<AccountResponse> accounts;

    public Entity() {
    }

    public Entity(long numberOfAccounts, BigDecimal aggregatedBalance, List<AccountResponse> accounts) {
        this.numberOfAccounts = numberOfAccounts;
        this.aggregatedBalance = aggregatedBalance;
        this.accounts = accounts;
    }

    public long getNumberOfAccounts() {
        return numberOfAccounts;
    }

    public void setNumberOfAccounts(long numberOfAccounts) {
        this.numberOfAccounts = numberOfAccounts;
    }

    public BigDecimal getAggregatedBalance() {
        return aggregatedBalance;
    }

    public void setAggregatedBalance(BigDecimal aggregatedBalance) {
        this.aggregatedBalance = aggregatedBalance;
    }

    public List<AccountResponse> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountResponse> accounts) {
        this.accounts = accounts;
    }
}
