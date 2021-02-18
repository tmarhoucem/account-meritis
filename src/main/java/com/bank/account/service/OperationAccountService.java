package com.bank.account.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.transaction.annotation.Transactional;

import com.bank.account.dao.RetrieverBalance;
import com.bank.account.dao.RetrieverOperations;
import com.bank.account.dao.SaverBalance;
import com.bank.account.model.Operation;

import utils.Result;

@Transactional
public class OperationAccountService implements IOperationAccountService{

	RetrieverBalance retrieverBalance;
	
	SaverBalance saverBalance;

	RetrieverOperations retrieverOperations;

	public OperationAccountService(RetrieverBalance retrieverBalance, SaverBalance saverBalance, RetrieverOperations retrieverOperations) {
		this.retrieverBalance = retrieverBalance;
		this.saverBalance = saverBalance;
		this.retrieverOperations = retrieverOperations;
	}

	public synchronized Result withdrawal( final Double withdrawnAmount, int idAccount) {
		Double balance = retrieverBalance.retrieve(idAccount);
		if (withdrawnAmount < 0) {
			return new Result(400, "Cannot withdrawal a negative value." );
		}

		if (balance - withdrawnAmount < 0) {
			return new Result(400, "insufficient balance ." );
		}
		balance -= withdrawnAmount;
		saverBalance.save(balance);
		return  new Result(201, "success withdrawal! balance = " + balance);
	}

	public synchronized Result deposit(final Double addedAmount, int idAccount) {
		Double balance = retrieverBalance.retrieve(idAccount);

		if (addedAmount < 0) {
			return new Result(400, "Cannot add a negative value." );
		}

		balance +=addedAmount;
		saverBalance.save(balance);
		return new Result(201, "success add! balance = " + balance);
	}


	public Result retrieve(LocalDate begin, LocalDate end) {
		if ( begin==null && end == null) {
			return new Result(200, "success", retrieverOperations.retrieve());
		}
		
		if ( begin==null ) {
			List<Operation> operations = retrieverOperations.retrieve();
			List<Operation> op = operations.stream().filter( x -> x.getDate().isBefore(end) || x.getDate().isEqual(end))
					.collect(Collectors.toList());
			return new Result(200, "success", op);
		}
		
		if ( end ==null ) {
			List<Operation> operations = retrieverOperations.retrieve();
			List<Operation> op = operations.stream().filter( x -> x.getDate().isAfter(begin))
					.collect(Collectors.toList());
			return new Result(200, "success", op);
		}

		if ( begin.isAfter(end)) {
			return new Result(400, "Cannot get operations: begin date after end date.");
		}

		List<Operation> operations = retrieverOperations.retrieve();
		List<Operation> op = operations.stream().filter( x -> x.getDate().isAfter(begin))
				.filter( x -> x.getDate().isBefore(end) || x.getDate().isEqual(end))
				.collect(Collectors.toList());
		return new Result(200, "success", op);
	}
}
