package com.n26.txnstatastics.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.n26.txnstatastics.domain.Transaction;
import com.n26.txnstatastics.service.TransactionCacheService;

@RestController
public class TxnController {

	TransactionCacheService txnService = new TransactionCacheService();
	
	@RequestMapping("/")
	public String welcome() {
		return "Transaction Statastics Example for N 26";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/transactions/add")
	public ResponseEntity addTransactionToCache(@RequestBody Transaction txDetails) {
	 ResponseEntity responseEntity = null;
	   if(txDetails.getTimestamp() <  (System.currentTimeMillis() - 60000) ){
		   responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);}
	   else{
		   responseEntity = new ResponseEntity<>(HttpStatus.CREATED);  
		   txnService.insertIntoTransactionCache(txDetails);
	   }
	   return responseEntity; 

}

}
