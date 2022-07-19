package com.example.accounts.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AccountResponse extends Account {


    public boolean isSalaryAccount = false;

    public String lastUpdatedAt = "2016-05-16";

    public BigDecimal debitAmount = new BigDecimal(0);

    public BigDecimal creditAmount = new BigDecimal(0);

    public long numberOfDebitTransactions = 0;

    public long numberOfCreditTransactions = 0;


}
