package com.cg.paytm.test;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;


import com.cg.paytm.bean.Customer;
import com.cg.paytm.exception.PaytmException;
import com.cg.paytm.service.IPaytmService;
import com.cg.paytm.service.PaytmServiceImpl;

public class Test {
	private static IPaytmService service;

	@BeforeClass
	public static void createInstance() {
		service = new PaytmServiceImpl();
	}

	@org.junit.Test
	public void testCreateAccountPositive() throws PaytmException {
		Customer customer = new Customer();
		customer.setFirstName("rohini");
		customer.setLastName("thunuguntla");
		customer.setPhnNo(9059900989l);
		customer.setAdharNo(355960351676l);
		customer.setMail("rohini6rani@gmail.com");
		customer.setPin(1234);
		boolean res = service.validate(customer);
		assertTrue(res);
	}

	@org.junit.Test(expected = PaytmException.class)
	public void testCreateAccountForFirstNameLength() throws PaytmException {
		Customer customer = new Customer();
		customer.setFirstName("ro");
		customer.setLastName("thunuguntla");
		customer.setPhnNo(9059900989l);
		customer.setAdharNo(355960351676l);
		customer.setMail("rohini6rani@gmail.com");
		customer.setPin(1234);
		service.validate(customer);
	}

	@org.junit.Test(expected = PaytmException.class)
	public void testCreateAccountForLastNameLength() throws PaytmException {
		Customer customer = new Customer();
		customer.setFirstName("rohini");
		customer.setLastName("th");
		customer.setPhnNo(9059900989l);
		customer.setAdharNo(355960351676l);
		customer.setMail("rohini6rani@gmail.com");
		customer.setPin(1234);
		service.validate(customer);
	}

	@org.junit.Test(expected = PaytmException.class)
	public void testCreateAccountLastNameFornumbers() throws PaytmException {
		Customer customer = new Customer();
		customer.setFirstName("rohini");
		customer.setLastName("thunugu123ntla");
		customer.setPhnNo(9059900989l);
		customer.setAdharNo(355960351676l);
		customer.setMail("rohini6rani@gmail.com");
		customer.setPin(1234);
		service.validate(customer);
	}

	@org.junit.Test(expected = PaytmException.class)
	public void testCreateAccountForFirstNameFornumbers() throws PaytmException {
		Customer customer = new Customer();
		customer.setFirstName("rohi123ni");
		customer.setLastName("thunuguntla");
		customer.setPhnNo(9059900989l);
		customer.setAdharNo(355960351676l);
		customer.setMail("rohini6rani@gmail.com");
		customer.setPin(1234);
		service.validate(customer);
	}

	@org.junit.Test(expected = PaytmException.class)
	public void testCreateAccountForPhnNoLength() throws PaytmException {
		Customer customer = new Customer();
		customer.setFirstName("rohini");
		customer.setLastName("thunuguntla");
		customer.setPhnNo(90599009l);
		customer.setAdharNo(355960351676l);
		customer.setMail("rohini6rani@gmail.com");
		customer.setPin(1234);
		service.validate(customer);
	}

	@org.junit.Test(expected = PaytmException.class)
	public void testCreateAccountForAdharNolength() throws PaytmException {
		Customer customer = new Customer();
		customer.setFirstName("rohini");
		customer.setLastName("thunuguntla");
		customer.setPhnNo(9059900989l);
		customer.setAdharNo(3559603516l);
		customer.setMail("rohini6rani@gmail.com");
		customer.setPin(1234);
		service.validate(customer);
	}

	@org.junit.Test(expected = PaytmException.class)
	public void testCreateAccountForEmail() throws PaytmException {
		Customer customer = new Customer();
		customer.setFirstName("rohini");
		customer.setLastName("thunuguntla");
		customer.setPhnNo(9059900989l);
		customer.setAdharNo(355960351676l);
		customer.setMail("rohini6ranigmail.com");
		customer.setPin(1234);
		service.validate(customer);
	}

	@org.junit.Test(expected = PaytmException.class)
	public void testCreateAccountForEmailForLength() throws PaytmException {
		Customer customer = new Customer();
		customer.setFirstName("rohini");
		customer.setLastName("thunuguntla");
		customer.setPhnNo(9059900989l);
		customer.setAdharNo(355960351676l);
		customer.setMail("rohin@gmail.com");
		customer.setPin(1234);
		service.validate(customer);
	}

	@org.junit.Test(expected = PaytmException.class)
	public void testCreateAccountForEmailForMailLength() throws PaytmException {
		Customer customer = new Customer();
		customer.setFirstName("rohini");
		customer.setLastName("thunuguntla");
		customer.setPhnNo(9059900989l);
		customer.setAdharNo(355960351676l);
		customer.setMail("rohini@gil.com");
		customer.setPin(1234);
		service.validate(customer);
	}

	@org.junit.Test(expected = PaytmException.class)
	public void testCreateAccountForEmailForDotcom() throws PaytmException {
		Customer customer = new Customer();
		customer.setFirstName("rohini");
		customer.setLastName("thunuguntla");
		customer.setPhnNo(9059900989l);
		customer.setAdharNo(355960351676l);
		customer.setMail("rohini@gmail");
		customer.setPin(1234);
		service.validate(customer);
	}

	@org.junit.Test
	public void testDeposit() {
		double amount = 100;
		boolean res=service.deposit(amount);
		assertTrue(res);
	}

	@org.junit.Test
	public void testDepositForNegative() {
		double amount =-100;
		service.deposit(amount);
		
	}
	@org.junit.Test
	public void testWithdraw() {
		double amount = 100;
		boolean res=service.withDraw(amount);
		assertTrue(res);
	}

	@org.junit.Test
	public void testWithDrawForNegative() {
		double amount =-100;
		service.withDraw(amount);
		
	}
	
	@org.junit.Test
	public void testfundstransfer() throws PaytmException {
		double amount = 100;
		Long transphnNo=9247144559l;
		boolean res=service.fundTransfer(amount, transphnNo);
		assertTrue(res);
	}

	

}
