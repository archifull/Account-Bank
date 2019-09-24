package com.kata.bankAccount.service;

import com.kata.bankAccount.exception.IllegalAccountBalanceException;

public interface BankAccountService {
    
    /**
     * Adds money to this account.
     * @param amount - the amount to add
     */
    public void deposit(Double amount);
    
    /**
     * Withdraws money from this account.
     * @param amount - the money to withdraw
     * @return the remaining account balance
     * @throws IllegalAccountBalanceException
     */
    public void withdraw(Double amount) throws IllegalAccountBalanceException;
    
    /**
     * Display the account operations history.
     */
    public Double getBalance();
    
    /**
     * Get the available account balance
     */
    public void displayOperationHistory();
}
