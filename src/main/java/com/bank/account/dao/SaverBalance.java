package com.bank.account.dao;

@FunctionalInterface
public interface SaverBalance {
	
	Boolean save(Double balance);

}
