package com.example.accounts.model;

import java.math.BigDecimal;

public class AccountResponse {

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

    public boolean isSalaryAccount = false;

    public String lastUpdatedAt = "2016-05-16";

    public BigDecimal debitAmount;

    public BigDecimal creditAmount;

    public long numberOfDebitTransactions;

    public long numberOfCreditTransactions;

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

    public boolean isSalaryAccount() {
        return isSalaryAccount;
    }

    public void setSalaryAccount(boolean salaryAccount) {
        isSalaryAccount = salaryAccount;
    }

    public String getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    public void setLastUpdatedAt(String lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
    }

    public BigDecimal getDebitAmount() {
        return debitAmount;
    }

    public void setDebitAmount(BigDecimal debitAmount) {
        this.debitAmount = debitAmount;
    }

    public BigDecimal getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(BigDecimal creditAmount) {
        this.creditAmount = creditAmount;
    }

    public long getNumberOfDebitTransactions() {
        return numberOfDebitTransactions;
    }

    public void setNumberOfDebitTransactions(long numberOfDebitTransactions) {
        this.numberOfDebitTransactions = numberOfDebitTransactions;
    }

    public long getNumberOfCreditTransactions() {
        return numberOfCreditTransactions;
    }

    public void setNumberOfCreditTransactions(long numberOfCreditTransactions) {
        this.numberOfCreditTransactions = numberOfCreditTransactions;
    }

    @Override
    public String toString() {
        return "AccountResponse{" +
                "name='" + name + '\'' +
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
                ", isSalaryAccount=" + isSalaryAccount +
                ", lastUpdatedAt='" + lastUpdatedAt + '\'' +
                ", debitAmount=" + debitAmount +
                ", creditAmount=" + creditAmount +
                ", numberOfDebitTransactions=" + numberOfDebitTransactions +
                ", numberOfCreditTransactions=" + numberOfCreditTransactions +
                '}';
    }
}
