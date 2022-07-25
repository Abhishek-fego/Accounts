package com.example.accounts.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Entity {

    public long numberOfAccounts;

    public BigDecimal aggregatedBalance;

    List<AccountResponse> accounts;

}


