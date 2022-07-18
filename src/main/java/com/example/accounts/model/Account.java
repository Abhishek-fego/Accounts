package com.example.accounts.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Account extends BaseModel{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    public long user_id;

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String name;

    public String accountNumber;

    public String branch;

    public String status;

    public String bankName;

    public String ifscCode;

    public String miceCode;

    public String currency;

    public String accountType;

    public String  openingDate;

    public String  isPrimaryAccount;

    public BigDecimal currentBalance;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getMiceCode() {
        return miceCode;
    }

    public void setMiceCode(String miceCode) {
        this.miceCode = miceCode;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(String openingDate) {
        this.openingDate = openingDate;
    }

    public String getIsPrimaryAccount() {
        return isPrimaryAccount;
    }

    public void setIsPrimaryAccount(String isPrimaryAccount) {
        this.isPrimaryAccount = isPrimaryAccount;
    }

    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(BigDecimal currentBalance) {
        this.currentBalance = currentBalance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", branch='" + branch + '\'' +
                ", status='" + status + '\'' +
                ", bankName='" + bankName + '\'' +
                ", ifscCode='" + ifscCode + '\'' +
                ", miceCode='" + miceCode + '\'' +
                ", currency='" + currency + '\'' +
                ", accountType='" + accountType + '\'' +
                ", openingDate='" + openingDate + '\'' +
                ", isPrimaryAccount='" + isPrimaryAccount + '\'' +
                ", currentBalance=" + currentBalance +
                '}';
    }
}
