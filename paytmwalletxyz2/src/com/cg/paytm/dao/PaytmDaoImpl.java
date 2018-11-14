package com.cg.paytm.dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import com.cg.paytm.bean.Customer;
import com.cg.paytm.bean.Trans;
import com.cg.paytm.db.Database;
import com.cg.paytm.exception.PaytmException;

public class PaytmDaoImpl implements IPaytmDao {

	static ArrayList<Trans> transList = new ArrayList<>();
	static Customer customer = new Customer();

	public boolean createAccount(Customer customer1) {

		return Database.addAccount(customer1);
	}

	@Override
	public double deposit(double amount, Long phnNo) throws PaytmException {
		Customer customer = Database.validate(phnNo);
		double balance=0.0;
		if (customer!=null) {
		customer.setBalance(customer.getBalance() + amount);
		Trans trans = new Trans();
		trans.setAmount(amount);
		trans.setSender(phnNo);
		trans.setTransType("Deposit");
		trans.setDate(LocalDate.now());
		trans.setTime(LocalTime.now());
		trans.setReciever(phnNo);
		transList.add(trans);
		balance=customer.getBalance();
		}else {
			throw new PaytmException("invalid phn number");
		}
		return balance;
	}

	@Override
	public double withDraw(double amount, Long phnNo) throws PaytmException {
		Customer customer = Database.validate(phnNo);
		double balance=0.0;
		if (customer!=null) {
		if (amount < customer.getBalance() - amount) {
			customer.setBalance(customer.getBalance() - amount);
			Trans trans = new Trans();
			trans.setAmount(amount);
			trans.setSender(phnNo);
			trans.setReciever(phnNo);
			trans.setTransType("Withdraw");
			trans.setDate(LocalDate.now());
			trans.setTime(LocalTime.now());
			transList.add(trans);
			balance=customer.getBalance();

		} 
		}else {
			throw new PaytmException("invalid phn number");
		}
		return balance;
	}

	@Override
	public double fundTransfer(double amount, Long phnNo, Long transPhnNo) throws PaytmException {
		Customer customer = Database.validate(phnNo);
		Customer customer1 = Database.validate(transPhnNo);
		double bal=0.0;
		if (customer!=null && customer1!=null) {
			if (amount < customer.getBalance() - amount) {
				customer.setBalance(customer.getBalance() - amount);
				customer1.setBalance(customer1.getBalance() + amount);
				
				Trans trans = new Trans();
				trans.setSender(phnNo);
				trans.setReciever(transPhnNo);
				trans.setAmount(amount);
				trans.setTransType("Funds transfer(withdraw)");
				trans.setDate(LocalDate.now());
				trans.setTime(LocalTime.now());
				
				transList.add(trans);
				Trans trans1 = new Trans();
				trans1.setSender(transPhnNo);
				trans1.setReciever(phnNo);
				trans1.setAmount(amount);
				trans1.setTransType("Funds transfer(deposit)");
				trans1.setDate(LocalDate.now());
				trans1.setTime(LocalTime.now());
				
				transList.add(trans1);
				bal= customer.getBalance();
			}
		
		}else {
			throw new PaytmException("invalid phn number");
		}
		return bal;
	}

	@Override
	public double showBalance(Long phnNo) {
		Customer customer = Database.validate(phnNo);
			
		return customer.getBalance();
		
	}

	@Override
	public ArrayList<Trans> printTransactions(Long phnNo) {
		ArrayList<Trans> trans=new ArrayList<>();
		for (Trans transaction : transList) {
			if(phnNo.equals(transaction.getSender()))
			trans.add(transaction);		
	}
		return trans;


	}
}
