package com.n26.txnstatastics.service;

import java.time.LocalDateTime;
import java.util.DoubleSummaryStatistics;
import java.util.stream.Collectors;
import com.n26.txnstatastics.domain.CacheConcurrentHashMap;
import com.n26.txnstatastics.domain.Transaction;
import com.n26.txnstatastics.domain.TransactionStatastics;

public class TransactionCacheService {

	private static final CacheConcurrentHashMap<String, Transaction> transcationCache = new CacheConcurrentHashMap<String, Transaction>(
			60000);

	public void insertIntoTransactionCache(Transaction txDetails) {
		transcationCache.put(LocalDateTime.now().toString(), txDetails);
	}

	public TransactionStatastics getTransactionStatastics() {
		TransactionStatastics transactionStatastics = null;
		if(transcationCache.size()>0){
		 DoubleSummaryStatistics stats = transcationCache.values().stream()
			     .collect(Collectors.summarizingDouble(Transaction::getAmount));
		transactionStatastics = new TransactionStatastics(stats.getSum(), stats.getCount(),
				 stats.getMin(), stats.getMax(), stats.getAverage());
		}
		else
			transactionStatastics = new TransactionStatastics(0,0,0,0,0);
	     		return transactionStatastics;

	}

}