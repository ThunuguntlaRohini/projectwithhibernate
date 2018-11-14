package com.cg.paytm.service;

import java.util.ArrayList;

import com.cg.paytm.bean.Customer;
import com.cg.paytm.bean.Trans;
import com.cg.paytm.exception.PaytmException;

public interface IPaytmService {

	boolean createAccount(Customer customer) throws PaytmException;

	double withDraw(double amount, Long phnNo) throws PaytmException;

	double showBalance(Long phnNo);



	double fundTransfer(double amount, Long phnNo, Long transPhnNo) throws PaytmException;



	boolean validate(Customer customer) throws PaytmException;

	double deposit(double amount, Long phnNo) throws PaytmException;

	ArrayList<Trans> printTransactions(Long phnNo);

}
