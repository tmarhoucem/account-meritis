package utils;

import java.util.List;

import com.bank.account.model.Operation;

public class Result {
	
	private String message;
	
	private int status;
	
	private List<Operation> operations;

	public Result(int status, String message) {
		this.status = status;
		this.message = message;
	}

	public Result(int status, String message, List<Operation> operations) {
		this.status = status;
		this.message = message;
		this.operations = operations;
	}
	public int getStatus() {
		return status;
	}


	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Operation> getOperations() {
		return operations;
	}

}
