package com.cg.paytm.dao;

import java.util.ArrayList;

import com.cg.paytm.bean.Customer;
import com.cg.paytm.bean.Trans;
import com.cg.paytm.exception.PaytmException;

public interface IPaytmDao {

	boolean createAccount(Customer customer);

	double deposit(double amount, Long phnNo) throws PaytmException;

	double withDraw(double amount, Long phnNo) throws PaytmException;

	double showBalance(Long phnNo);



	double fundTransfer(double amount, Long transPhnNo, Long transPhnNo2) throws PaytmException;

	ArrayList<Trans> printTransactions(Long phnNo);



}
