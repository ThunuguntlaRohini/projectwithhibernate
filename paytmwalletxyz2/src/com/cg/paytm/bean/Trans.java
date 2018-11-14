package com.cg.paytm.bean;

import java.time.LocalDate;
import java.time.LocalTime;

public class Trans {

	private String transType;
	private Long sender;
	private Long reciever;
	private double amount;
	private LocalDate date;
	private LocalTime time;
	
	
	public String getTransType() {
		return transType;
	}
	public void setTransType(String transType) {
		this.transType = transType;
	}
	
	
	public Long getSender() {
		return sender;
	}
	public void setSender(Long sender) {
		this.sender = sender;
	}
	public Long getReciever() {
		return reciever;
	}
	public void setReciever(Long reciever) {
		this.reciever = reciever;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public LocalTime getTime() {
		return time;
	}
	public void setTime(LocalTime time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "Trans [transType=" + transType + ", sender=" + sender + ", reciever=" + reciever + ", amount=" + amount
				+ ", date=" + date + ", time=" + time + "]";
	}
	
	
	

	

}
