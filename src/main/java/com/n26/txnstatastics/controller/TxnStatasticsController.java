package com.n26.txnstatastics.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.n26.txnstatastics.domain.TransactionStatastics;
import com.n26.txnstatastics.service.TransactionCacheService;

@RestController
public class TxnStatasticsController {

  TransactionCacheService txnService = new TransactionCacheService();
	
	// REST Endpoint.
   @RequestMapping(method = RequestMethod.GET, value = "/trans/getdetails", produces = "application/json")
    public TransactionStatastics getTransactionDetails() {
		return	txnService.getTransactionStatastics();
    
	}

	
}
