package com.bank.account.controller;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bank.account.service.OperationAccountService;

import utils.Result;

@Controller
public class OpertaionAccountController {

	OperationAccountService operationAccountService;
	
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result addAmount(@RequestParam(value="addedAmount") Double addedAmount, @RequestParam(value="idAccount")  int idAccount) {       
        return operationAccountService.add(addedAmount, idAccount);
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
