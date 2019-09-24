package com.kata.bankAccount.test;


import static org.junit.Assert.*;

import org.junit.Test;

import com.kata.bankAccount.exception.IllegalAccountBalanceException;
import com.kata.bankAccount.implementation.BankAccount;

public class BankAccountTest {

	BankAccount bankAccount = new BankAccount(); 

	/**
	 * Check that an empty account has always zero as balance
	 */
	@Test
	public void testEmptyAccountHasAlwaysZeroBalance() {
		assertEquals(bankAccount.getBalance(), 0d, 0.00001);
	}

	/**
	 * Adds amount to the account and checks that the balance is as expected
	 */
	@Test
	public void testDeposit() {
		double amount = 10.5;
		bankAccount.deposit(amount);
		assertEquals(bankAccount.getBalance(), amount, 0.00001);
	}

	/**
	 * Tests that a null deposit throws the expected exception.
	 * 
	 * @throws IllegalAccountBalanceException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testDepositNullAmount() {
		Double amount = null;
		bankAccount.deposit(amount);

	}

	/**
	 * Tests that a negative deposit throws the expected exception.
	 * 
	 * @throws IllegalAccountBalanceException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testDepositNegativeAmount() {
		Double amount = -5D;
		bankAccount.deposit(amount);

	}

	/**
	 * Withdraw amount and checks that the balance is as expected
	 */
	@Test
	public void testWithdrawal() throws IllegalAccountBalanceException {
		Double amount = 5.1;
		bankAccount.deposit(10.0);
		bankAccount.withdraw(amount);
		assertEquals(bankAccount.getBalance(), 4.9, 0.00001);

	}
	
	/**
	 * Tests that a withdrawal amount greater than the available balance throws  the expected exception.
	 * @throws IllegalAccountBalanceException
	 */
	@Test(expected = IllegalAccountBalanceException.class)
	public void testWithdrawAmountGreaterThanBalance() throws IllegalAccountBalanceException {
		Double amount = 11.1;
		bankAccount.withdraw(amount);
	}

	/**
	 * Tests that a withdrawal null amount throws  the expected exception.
	 * @throws IllegalAccountBalanceException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testWithdrawNullAmount() throws IllegalArgumentException, IllegalAccountBalanceException {
		Double amount = null;
		bankAccount.withdraw(amount);
	}
	
	/**
	 * Tests that a withdrawal null amount throws  the expected exception.
	 * @throws IllegalAccountBalanceException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testWithdrawNegativeAmountThanBalance() throws IllegalArgumentException, IllegalAccountBalanceException {
		Double amount = -5D;
		bankAccount.withdraw(amount);
	}

	/**
	 * Tests that adding amount to the account is thread safe
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void testAccountAddPositifIsThreadSafe() throws InterruptedException {

		Runnable compte = new Runnable() {
			public void run() {
				for (int i = 0; i < 100; i++) {
					bankAccount.deposit(1d);
				}
			}
		};
		Thread[] threads = new Thread[5];
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(compte);
		}
		for (int i = 0; i < threads.length; i++) {
			threads[i].start();
		}

		for (int i = 0; i < threads.length; i++) {
			threads[i].join();
		}

		// vérification de la valeur de compte
		assertEquals(bankAccount.getBalance(), 500D, 0.00001);
	}

	/**
	 * Tests that withdrawing an amount from the account is thread safe
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void testAccountWithdrawIsThreadSafe() throws InterruptedException {
		bankAccount.deposit(500d);
		Runnable compte = new Runnable() {
			public void run() {
				for (int i = 0; i < 100; i++) {
					try {
						bankAccount.withdraw(1d);
					} catch (IllegalAccountBalanceException e) {
						fail("Illeagal Balance Exception");
					}
				}
			}
		};
		Thread[] threads = new Thread[5];
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(compte);
		}
		for (int i = 0; i < threads.length; i++) {
			threads[i].start();
		}

		for (int i = 0; i < threads.length; i++) {
			threads[i].join();
		}
		assertEquals(bankAccount.getBalance(), 0D, 0.00001);
	}

}
