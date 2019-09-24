package com.kata.bankAccount.implementation;

import java.util.Date;

public class Operation {
	private String type;
	private Date date;
	private double amount;
	private double balance;

	public Operation(String type, Date date, double amount, double balance) {
		this.type = type;
		this.date = date;
		this.amount = amount;
		this.balance = balance;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Operation [type=" + type + ", date=" + date + ", amount="
				+ amount + ", balance=" + balance + "]";
	}

}
