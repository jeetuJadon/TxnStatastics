package com.n26.txnstatastics.domain;

public class TransactionStatastics {

	private double sum;
	private double count;
	private double min;
	private double max;
	private double avg;

	public TransactionStatastics() {

	}

	public TransactionStatastics(double sum, double count, double min, double max, double avg) {
		this.sum = sum;
		this.count = count;
		this.min = min;
		this.max = max;
		this.avg = avg;
	}

	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}

	public double getCount() {
		return count;
	}

	public void setCount(double count) {
		this.count = count;
	}

	public double getMin() {
		return min;
	}

	public void setMin(double min) {
		this.min = min;
	}

	public double getMax() {
		return max;
	}

	public void setMax(double max) {
		this.max = max;
	}

	public double getAvg() {
		return avg;
	}

	public void setAvg(double avg) {
		this.avg = avg;
	}

}
