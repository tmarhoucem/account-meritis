package com.bank.account.dao;

import java.util.List;

import com.bank.account.model.Operation;

@FunctionalInterface
public interface RetrieverOperations {

	List<Operation> retrieve();
}
