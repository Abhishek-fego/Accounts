package com.example.accounts.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
public class Account extends BaseModel {

    @JsonIgnore
    public long user_id;

    public String name;

    public String accountNumber;

    public String branch;

    public String status;

    public String bankName;

    public String ifscCode;

    public String miceCode;

    public String currency;

    public String accountType;

    public String openingDate;

    public String isPrimaryAccount;

    public BigDecimal currentBalance;


}
