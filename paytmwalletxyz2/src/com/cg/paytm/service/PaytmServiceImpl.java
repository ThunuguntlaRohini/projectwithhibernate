package com.cg.paytm.service;

import java.util.ArrayList;

import com.cg.paytm.bean.Customer;
import com.cg.paytm.bean.Trans;
import com.cg.paytm.dao.IPaytmDao;
import com.cg.paytm.dao.PaytmDaoImpl;
import com.cg.paytm.exception.PaytmException;

public class PaytmServiceImpl implements IPaytmService {

	IPaytmDao dao = new PaytmDaoImpl();

	@Override
	public boolean createAccount(Customer customer) throws PaytmException {
		validate(customer);
		return dao.createAccount(customer);
	}

	@Override
	public double deposit(double amount, Long phnNo) throws PaytmException {

		return dao.deposit(amount, phnNo);
	}

	@Override
	public double withDraw(double amount, Long phnNo) throws PaytmException {

		return dao.withDraw(amount, phnNo);
	}

	@Override
	public double fundTransfer(double amount, Long phnNo, Long transPhnNo) throws PaytmException {

		return dao.fundTransfer(amount, phnNo, transPhnNo);
	}

	@Override
	public double showBalance(Long phnNo) {

		return dao.showBalance(phnNo);
	}

	@Override
	public ArrayList<Trans> printTransactions(Long phnNo) {

		return dao.printTransactions(phnNo);
	}
	
	public boolean validate(Customer customer) throws PaytmException {
		
		if(!(customer.getFirstName().matches("[A-Za-z]{3,15}"))) {
			throw new PaytmException("First name should have atleast 3 characters");
		}
		if(!(customer.getLastName().matches("[A-Za-z]{3,15}"))) {
			throw new PaytmException("Last name should have atleast 3 characters");
		}
		if(!(customer.getMail().matches("[a-z0-9]{6,15}[@][a-z]{4,10}[.][c][o][m]"))) {
			throw new PaytmException("Enter valid mail");
		}
		if(!(customer.getPhnNo().toString().matches("[6-9][0-9]{9}"))) {
			throw new PaytmException("phone number should have 10 digits");
		}
		if(!(customer.getAdharNo().toString().matches("[0-9]{12}"))) {
			throw new PaytmException("Adhar number should have 12 digits");
		}
		return true;
	}



	

	
}
