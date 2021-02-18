package com.bank.account;


import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.bank.account.dao.RetrieverBalance;
import com.bank.account.dao.RetrieverOperations;
import com.bank.account.dao.SaverBalance;
import com.bank.account.model.Operation;
import com.bank.account.service.OperationAccountService;

import utils.Result;

public class OperationAccountTest {

	OperationAccountService operationAccountService;
	

	/**
	 * Tests that an empty account always has a balance of 0.0, not a NULL.
	 */
	@Test
	public void should_return_success_add_positive_amount() {
		RetrieverBalance retrieverBalance = x -> 0.0;
		SaverBalance saverBalance = x -> true;
		operationAccountService = new OperationAccountService(retrieverBalance, saverBalance , null) ;
		Result resulat = operationAccountService.deposit(1.0, 001);
		assertTrue("success add! balance = 1.0".equals(resulat.getMessage()));
		assertTrue(resulat.getStatus() == 201);
	}
	
	@Test
	public void should_return_failed_add_negative_amount() {
		RetrieverBalance retrieverBalance = x -> 0.0;
		operationAccountService = new OperationAccountService(retrieverBalance, null , null);
		Result resulat = operationAccountService.deposit(-1.0, 001);
		assertTrue("Cannot add a negative value.".equals(resulat.getMessage()));
		assertTrue(resulat.getStatus() == 400);
	}
	
	@Test
	public void should_return_success_add_positive_amount_to_negatice_balance() {
		RetrieverBalance retrieverBalance = x -> -2.0;
		SaverBalance saverBalance = x -> true;
		operationAccountService = new OperationAccountService(retrieverBalance, saverBalance, null);
		Result resulat = operationAccountService.deposit(1.0, 001);
		assertTrue("success add! balance = -1.0".equals(resulat.getMessage()));
		assertTrue(resulat.getStatus() == 201);
	}
	
	@Test
	public void should_return_success_add_negative_amount_to_negatice_balance() {
		RetrieverBalance retrieverBalance = x -> -2.0;
		operationAccountService = new OperationAccountService(retrieverBalance, null, null);
		Result resulat = operationAccountService.deposit(-1.0, 001);
		assertTrue("Cannot add a negative value.".equals(resulat.getMessage()));
		assertTrue(resulat.getStatus() == 400);
	}
	
	@Test
	public void should_return_success_add_positive_amount_to_positive_balance() {
		RetrieverBalance retrieverBalance = x -> 2.0;
		SaverBalance saverBalance = x -> true;
		operationAccountService = new OperationAccountService(retrieverBalance, saverBalance, null);
		Result resulat = operationAccountService.deposit(1.0, 001);
		assertTrue("success add! balance = 3.0".equals(resulat.getMessage()));
		assertTrue(resulat.getStatus() == 201);
	}
	
	@Test
	public void should_return_success_withdrawal_positive_amount_less_than_balance() {
		RetrieverBalance retrieverBalance = x -> 2.0;
		SaverBalance saverBalance = x -> true;
		operationAccountService = new OperationAccountService(retrieverBalance, saverBalance , null);
		Result resulat = operationAccountService.withdrawal(1.0, 001);
		assertTrue("success withdrawal! balance = 1.0".equals(resulat.getMessage()));
		assertTrue(resulat.getStatus() == 201);
	}
	
	@Test
	public void should_return_success_withdrawal_positive_amount_greater_than_balance() {
		RetrieverBalance retrieverBalance = x -> 2.0;
		operationAccountService = new OperationAccountService(retrieverBalance, null, null);
		Result resulat = operationAccountService.withdrawal(3.0, 001);
		assertTrue("insufficient balance .".equals(resulat.getMessage()));
		assertTrue(resulat.getStatus() == 400);
	}
	
	@Test
	public void should_return_success_withdrawal_positive_amount_to_negative_balance() {
		RetrieverBalance retrieverBalance = x -> -2.0;
		operationAccountService = new OperationAccountService(retrieverBalance, null, null);
		Result resulat = operationAccountService.withdrawal(3.0, 001);
		assertTrue("insufficient balance .".equals(resulat.getMessage()));
		assertTrue(resulat.getStatus() == 400);
	}
	
	@Test
	public void should_return_success_withdrawal_negative_amount_to_negative_balance() {
		RetrieverBalance retrieverBalance = x -> 2.0;
		operationAccountService = new OperationAccountService(retrieverBalance, null, null);
		Result resulat = operationAccountService.withdrawal(-3.0, 001);
		assertTrue("Cannot withdrawal a negative value.".equals(resulat.getMessage()));
		assertTrue(resulat.getStatus() == 400);
	}
	
	@Test
	public void should_return_success_withdrawal_amount_equals_to_balance() {
		RetrieverBalance retrieverBalance = x -> 3.0;
		SaverBalance saverBalance = x -> true;
		operationAccountService = new OperationAccountService(retrieverBalance, saverBalance, null);
		Result resulat = operationAccountService.withdrawal(3.0, 001);
		assertTrue("success withdrawal! balance = 0.0".equals(resulat.getMessage()));
		assertTrue(resulat.getStatus() == 201);
	}
	
	@Test
	public void should_return_success_retrieve_opeations_null_begin_null_end_date() {
		RetrieverOperations retrieverOperations = () -> createOperations();
		operationAccountService = new OperationAccountService(null, null, retrieverOperations);
		Result resulat = operationAccountService.retrieve(null, null);
		assertTrue("success".equals(resulat.getMessage()));
		assertTrue(4 == resulat.getOperations().size());
		assertTrue(resulat.getStatus() == 200);
	}
	
	@Test
	public void should_return_success_retrieve_opeations_not_null_begin_null_end_date() {
		RetrieverOperations retrieverOperations = () -> createOperations();
		LocalDate beginDate = LocalDate.of(2020,4,01);
		operationAccountService = new OperationAccountService(null, null, retrieverOperations);
		Result resulat = operationAccountService.retrieve(beginDate, null);
		assertTrue("success".equals(resulat.getMessage()));
		assertTrue(2 == resulat.getOperations().size());
		assertTrue(resulat.getStatus() == 200);
	}
	
	@Test
	public void should_return_success_retrieve_opeations_null_begin_not_null_end_date() {
		RetrieverOperations retrieverOperations = () -> createOperations();
		LocalDate endDate = LocalDate.of(2020,11,01);
		operationAccountService = new OperationAccountService(null, null, retrieverOperations);
		Result resulat = operationAccountService.retrieve(null, endDate);
		assertTrue("success".equals(resulat.getMessage()));
		assertTrue(3 == resulat.getOperations().size());
		assertTrue(resulat.getStatus() == 200);
	}
	
	@Test
	public void should_return_success_retrieve_opeations_not_null_begin_not_null_end_date() {
		RetrieverOperations retrieverOperations = () -> createOperations();
		LocalDate beginDate = LocalDate.of(2020,4,01);
		LocalDate endDate = LocalDate.of(2020,11,01);
		operationAccountService = new OperationAccountService(null, null, retrieverOperations);
		Result resulat = operationAccountService.retrieve(beginDate, endDate);
		assertTrue("success".equals(resulat.getMessage()));
		assertTrue(1 == resulat.getOperations().size());
		assertTrue(resulat.getStatus() == 200);
	}
	
	@Test
	public void should_return_success_retrieve_opeations_begin_after_end_date() {
		LocalDate beginDate = LocalDate.of(2020,11,01);
		LocalDate endDate = LocalDate.of(2020,9,01);
		operationAccountService = new OperationAccountService(null, null, null);
		Result resulat = operationAccountService.retrieve(beginDate, endDate);
		assertTrue("Cannot get operations: begin date after end date.".equals(resulat.getMessage()));
		assertTrue(null == resulat.getOperations());
		assertTrue(resulat.getStatus() == 400);
	}

	private List<Operation> createOperations() {
		List<Operation> operations = new ArrayList<>();
		operations.add(new Operation(LocalDate.of(2020,12,01), 10.0, 70.5));
		operations.add(new Operation(LocalDate.of(2020,5,01), 20.0, 60.5));
		operations.add(new Operation(LocalDate.of(2020,3,01), -10.0, 40.5));
		operations.add(new Operation(LocalDate.of(2020,1,01), 10.0, 50.5));
		return operations;
	}
	
	
	
}
