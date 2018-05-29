package com.n26.txnstatastics.domain;

public class Transaction {

private 	double amount;
private	 long    timestamp;

public long getTimestamp() {
	return timestamp;
}

public void setTimestamp(long timestamp) {
	this.timestamp = timestamp;
}

public double getAmount() {
	return amount;
}

public void setAmount(double amount) {
	this.amount = amount;
}





	public Transaction(){
		
	}
	
	
@Override
	public String toString() {
		// TODO Auto-generated method stub
		return  "[amount = "+this.amount+", timestamp =  "+this.timestamp+"]";
	}
	

}
