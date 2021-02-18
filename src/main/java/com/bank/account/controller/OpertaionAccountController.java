package com.bank.account.controller;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bank.account.service.IOperationAccountService;

import utils.Result;

@Controller
public class OpertaionAccountController {

	IOperationAccountService operationAccountService;
	
	
	
    public OpertaionAccountController(IOperationAccountService operationAccountService) {
		this.operationAccountService = operationAccountService;
	}

	@RequestMapping(value = "/deposit", method = RequestMethod.POST)
    public Result addAmount(@RequestParam(value="addedAmount") Double addedAmount, @RequestParam(value="idAccount")  int idAccount) {       
        return operationAccountService.deposit(addedAmount, idAccount);
    }
    
    @RequestMapping(value = "/withdrawal", method = RequestMethod.POST)
    public Result withdrawal( @RequestParam(value="addedAmount") Double addedAmount, @RequestParam(value="idAccount")  int idAccount) {
        return operationAccountService.withdrawal(addedAmount, idAccount);
    }

    @RequestMapping(value = "/retrieve-opertaions", method = RequestMethod.GET)
    public Result retrieveOpertaions( @RequestParam(value="begin") LocalDate begin, @RequestParam(value="end") LocalDate end) {
        return operationAccountService.retrieve(begin, end);
    }
	
}
