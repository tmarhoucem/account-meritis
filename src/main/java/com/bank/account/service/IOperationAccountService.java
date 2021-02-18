package com.bank.account.service;

import java.time.LocalDate;

import utils.Result;

public interface IOperationAccountService {

	/**
	 * get withdraw and report balance
	 * @param withdrawnAmount
	 * @param idAccount
	 * @return Result
	 */
	Result withdrawal( final Double withdrawnAmount, int idAccount);
	
	
	/**
	 * add amount
	 * 
	 * @param addedAmount
	 * @return Result
	 */
	Result add(final Double addedAmount, int idAccount);
	
	/**
	 * get operations by date
	 * @param begin
	 * @param end
	 * @return Result
	 * @throws 
	 */
	Result retrieve(LocalDate begin, LocalDate end);
}
