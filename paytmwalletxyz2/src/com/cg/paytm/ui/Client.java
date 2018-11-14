package com.cg.paytm.ui;

import java.util.ArrayList;
import java.util.Scanner;

import com.cg.paytm.bean.Customer;
import com.cg.paytm.bean.Trans;
import com.cg.paytm.exception.PaytmException;
import com.cg.paytm.service.IPaytmService;
import com.cg.paytm.service.PaytmServiceImpl;

public class Client {

	static Customer customer = new Customer();
	static IPaytmService service = new PaytmServiceImpl();
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws PaytmException {
		try {
			while (true) {
				System.out.println("----Welcome to XYZ Bank------");
				System.out.println("1. Create Account");
				System.out.println("2. Deposit");
				System.out.println("3. Withdraw");
				System.out.println("4. Funds Transfer");
				System.out.println("5. Show Balance");
				System.out.println("6. Print Transactions");
				System.out.println("7. Exit");
				System.out.println("Enter your choice");
				int choice = sc.nextInt();
				switch (choice) {
				case 1:
					createAccount();
					break;
				case 2:
					deposit();
					break;
				case 3:
					withDraw();
					break;
				case 4:
					fundsTransfer();
					break;

				case 5:
					showbalance();
					break;
				case 6:
					printTransactions();
					break;
				case 7:
					System.exit(0);
					break;

				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	private static void createAccount() throws PaytmException {

		System.out.println("Enter First Name");
		String firstName = sc.next();
		customer.setFirstName(firstName);
		System.out.println("Enter Last Name");
		String lastName = sc.next();
		customer.setLastName(lastName);
		System.out.println("Enter Phone number");
		Long phnNo = sc.nextLong();
		customer.setPhnNo(phnNo);
		System.out.println("Enter Adhar number");
		Long adharNo = sc.nextLong();
		customer.setAdharNo(adharNo);
		System.out.println("Enter mail Id");
		String mail = sc.next();
		customer.setMail(mail);
		System.out.println("enter a pin to quick access");
		int pin = sc.nextInt();
		customer.setPin(pin);

		boolean isAdded = service.createAccount(customer);

		if (isAdded) {
			System.out.println("Added Sucessfully");
			System.out.println("customer details" + customer);
		} else {
			System.out.println("Not Added");
		}

	}

	private static void deposit() throws PaytmException {
		System.out.println("enter phnNo");
		Long phnNo = sc.nextLong();
		System.out.println("Enter the Amount to Deposit");
		double amount = sc.nextDouble();
		if (amount <= 0) {
			throw new PaytmException("amount should be positive value");
		}
		double balance = service.deposit(amount, phnNo);
			if (balance!=0) {
				System.out.println("deposited Sucessfully"+balance);
			} else {
				System.out.println("Not deposited");
			}
		} 

	

	private static void withDraw() throws PaytmException {
		System.out.println("enter phnNo");
		Long phnNo = sc.nextLong();
			System.out.println("Enter the Amount to Withdraw");
			double amount = sc.nextDouble();
			if (amount <= 0) {
				throw new PaytmException("amount should be positive value");
			}
			double balance = service.withDraw(amount,phnNo);

			if (balance!=0) {
				System.out.println("Withdrawn Sucessfully"+balance);
			} else {
				System.out.println("Insufficient Balance");
			}
		}


	private static void fundsTransfer() throws PaytmException {
		System.out.println("enter phnNo");
		Long phnNo = sc.nextLong();
			System.out.println("Enter the Phone Number to transfer Funds");
			long transPhnNo = sc.nextLong();
			System.out.println("Enter the Amount to Transfer");
			double amount = sc.nextDouble();
			if (amount <= 0) {
				throw new PaytmException("amount should be positive value");
			}
			double balance = service.fundTransfer(amount, phnNo, transPhnNo);

			if (balance!=0) {
				System.out.println("Transferred Sucessfully"+balance);
			} else {
				System.out.println("Insufficient Balance");
			}
		} 

	private static void showbalance() {
		System.out.println("enter phnNo");
		Long phnNo = sc.nextLong();
		Double balance = service.showBalance(phnNo);
		System.out.println("Your  Current Balance is : " + balance);

	}

	private static void printTransactions() {
		System.out.println("enter phnNo");
		Long phnNo = sc.nextLong();
		ArrayList<Trans> transaction = service.printTransactions(phnNo);
		for (Trans trans : transaction) {
			System.out.println(trans);
		}

	}

}
