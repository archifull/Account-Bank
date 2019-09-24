package com.kata.bankAccount.implementation;

import com.kata.bankAccount.exception.IllegalAccountBalanceException;

public class MainBankAccount {
	public static void main(String[] args) {
		BankAccount bankAccount = new BankAccount();
		System.out.println(bankAccount.toString());
		bankAccount.deposit(5.0);
		System.out.println("********* Deposit ********");
		System.out.println(bankAccount.toString());
		try{
			bankAccount.withdraw(4.2);
			System.out.println("********* Withdrawal ********");
			bankAccount.withdraw(2.0);
			System.out.println("********* Withdrawal ********");
		}catch(IllegalAccountBalanceException e){
			e.printStackTrace();
		}
		System.out.println(bankAccount.toString());
		System.out.println("********* Withdrawal ********");
		bankAccount.displayOperationHistory();
		
	}
}
