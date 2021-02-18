package com.bank.account.service;

import utils.Result;

public interface IOperationAccountService {

	Result withdrawal( final Double withdrawnAmount, int idAccount);
	
	Result add(final Double addedAmount, int idAccount);
}
