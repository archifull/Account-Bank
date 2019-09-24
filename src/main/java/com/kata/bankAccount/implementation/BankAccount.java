package com.kata.bankAccount.implementation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.kata.bankAccount.exception.IllegalAccountBalanceException;
import com.kata.bankAccount.service.BankAccountService;

public class BankAccount implements BankAccountService {

	private static final String DEPOSIT = "Deposit";
	private static final String WITHDRAWAL = "Withdrawal";
	private Double balance = 0D;
	public List<Operation> operations = new ArrayList();

	public synchronized Double getBalance() {
		return balance;
	}

	public synchronized void deposit(Double amount) {
		// if the addedAmount is not valid(null or negative) throw an exception
		if (amount == null || amount < 0) {
			throw new IllegalArgumentException("Amount to add is not valid");
		}
		balance += amount;
		Operation operation = new Operation(DEPOSIT, new Date(), amount,
				balance);
		operations.add(operation);
	}

	public synchronized void withdraw(Double amount)
			throws IllegalAccountBalanceException {

		// if the withdrawnAmount is not valid(null or negative) throw an
		// exception
		if (amount == null || amount < 0) {
			throw new IllegalArgumentException(
					"Amount to withdraw is not valid");
		}

		if (amount <= balance) {
			// perform operation
			balance -= amount;
			Operation operation = new Operation(WITHDRAWAL, new Date(), amount,
					balance);
			operations.add(operation);
		} else {
			throw new IllegalAccountBalanceException(balance);
		}
	}

	@Override
	public String toString() {
		return "BankAccount [balance=" + balance + "]";
	}

	public synchronized void displayOperationHistory() {
		for (Operation operation : operations) {
			System.out.println(operation.toString());
		}

	}

}
