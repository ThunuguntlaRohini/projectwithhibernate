package com.cg.paytm.db;

import java.util.ArrayList;
import java.util.List;

import com.cg.paytm.bean.Customer;

public class Database {

	static List<Customer> customerDetails = new ArrayList<>();

	static {
		Customer c1= new Customer();
		c1.setFirstName("sasi");
		c1.setLastName("devi");
		c1.setPhnNo(9247144559l);
		c1.setAdharNo(123456789123L);
		c1.setBalance(5000.00);
		c1.setMail("sasidevi@gmail.com");
		customerDetails.add(c1);
	
		Customer c2= new Customer();
		c2.setFirstName("manu");
		c2.setLastName("ranjan");
		c2.setPhnNo(9396939591L);
		c2.setAdharNo(456789123456l);
		c2.setBalance(7000.00);
		c2.setMail("manoranjan@gmail.com");
		customerDetails.add(c2);
	
		Customer c3= new Customer();
		c3.setFirstName("satya");
		c3.setLastName("narayana");
		c3.setPhnNo(9248444559l);
		c3.setAdharNo(789123456789l);
		c3.setBalance(6000.00);
		c3.setMail("satyanarayana@gmail.com");
		customerDetails.add(c3);
	
	}

	public static boolean addAccount(Customer customer) {

		return customerDetails.add(customer);
	}

	public static Customer validate(Long phnno) {
	Customer customer=null;
		for (Customer customer1 : customerDetails)
			if (customer1.getPhnNo().equals(phnno)) {
				customer=customer1;
			}
				

		return customer;

	}

	public static boolean validatePin(int pin) {
		boolean isValid = false;
		for (Customer customer : customerDetails)
			if (pin == customer.getPin())
				isValid = true;

		return isValid;

	}

}
