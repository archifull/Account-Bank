package com.kata.bankAccount.exception;


public class IllegalAccountBalanceException extends Exception {
    
    private static final long serialVersionUID = -9204191749972551939L;
    
	private Double balance;
    
    public IllegalAccountBalanceException(Double balance) {
        this.balance = balance;
    }
    
    public String toString() {
        return "Transaction cancelled due to insufficient funds: " + balance;
    }
}
