package com.bank.account.dao;

@FunctionalInterface
public interface RetrieverBalance {

	Double retrieve(int accountId);
}
