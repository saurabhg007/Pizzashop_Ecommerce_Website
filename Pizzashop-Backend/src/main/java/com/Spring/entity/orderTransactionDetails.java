package com.Spring.entity;

public class orderTransactionDetails 
{
	private String orderId;
	private String currency;
	private int amount;
	private String key;
	public orderTransactionDetails(String orderId, String currency, int
	amount, String key) {
	super();
	this.orderId = orderId;
	this.currency = currency;
	this.amount = amount;
	this.key = key;
	}
	public String getKey() {
	return key;
	}
	public void setKey(String key) {
	this.key = key;
	}
	public String getOrderId() {
	return orderId;
	}
	public void setOrderId(String orderId) {
	this.orderId = orderId;
	}
	public String getCurrency() {
	return currency;
	}
	public void setCurrency(String currency) {
	this.currency = currency;
	}
	public int getAmount() {
	return amount;
	}
	public void setAmount(int amount) {
	this.amount = amount;
	}


}
