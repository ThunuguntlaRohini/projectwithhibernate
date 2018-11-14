package com.cg.paytm.bean;

public class Customer {
	
	private Long phnNo;
	private String firstName;
	private String lastName;
	private Long adharNo;
	private String mail;
	private Double balance = 5000.00;
	private int pin;
	
	
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public int getPin() {
		return pin;
	}
	public void setPin(int pin) {
		this.pin = pin;
	}
	public Long getPhnNo() {
		return phnNo;
	}
	public void setPhnNo(Long phnNo) {
		this.phnNo = phnNo;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Long getAdharNo() {
		return adharNo;
	}
	public void setAdharNo(Long adharNo) {
		this.adharNo = adharNo;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	@Override
	public String toString() {
		return "Customer [phnNo=" + phnNo + ", firstName=" + firstName + ", lastName=" + lastName + ", adharNo="
				+ adharNo + ", mail id=" + mail + ", balance=" + balance + "]";
	}

	

	
	
	
	

}
