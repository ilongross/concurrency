package com.ilongross.concurrency.prof_library.company.bank_accs;

import java.math.BigDecimal;

public class BankAccount {

    private static long totalBankAccIDCounter = 0;
    private long id;
    private BigDecimal amount;

    public BankAccount() {
        incTotalBankAccId();
        id = totalBankAccIDCounter;
        amount = new BigDecimal("0.00");
    }

    public synchronized boolean addToAmount(BigDecimal income) {
        BigDecimal newAmount = amount.add(income);
        this.amount = newAmount;
        return true;
    }

    public synchronized boolean withdrawFromAccount(BigDecimal expense) {
        if(this.amount.doubleValue() >= expense.doubleValue()) {
            BigDecimal newAmount = this.amount.add(expense.negate());
            this.amount = newAmount;
            return true;
        }
        return false;
    }

    public long getId() {
        return id;
    }

    public synchronized BigDecimal getAmount() {
        return amount;
    }

    private synchronized void incTotalBankAccId() {
        ++totalBankAccIDCounter;
    }
}
