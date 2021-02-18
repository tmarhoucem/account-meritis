package com.bank.account.model;

import java.time.LocalDate;


public class Operation {

	
	private final LocalDate date;
	
	private final Double amount;
	
	private final Double balance;
	
	

	public Operation(LocalDate date, Double amount, Double balance) {
		this.date = date;
		this.amount = amount;
		this.balance = new Double(balance);
	}



	public LocalDate getDate() {
		return date;
	}



	public Double getAmount() {
		return amount;
	}



	public Double getBalance() {
		return balance;
	}
	
}
