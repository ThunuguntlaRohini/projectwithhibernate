package com.capg.paymentwallet.ui;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.capg.paymentwallet.bean.AccountBean;
import com.capg.paymentwallet.bean.CustomerBean;
import com.capg.paymentwallet.bean.WalletTransaction;
import com.capg.paymentwallet.exception.CustomerException;
import com.capg.paymentwallet.service.AccountServiceImpl;
import com.capg.paymentwallet.service.IAccountService;

public class Client {

	static IAccountService service = new AccountServiceImpl();
	static CustomerBean customer = new CustomerBean();
	static Scanner scanner = new Scanner(System.in);
	private static Long phnNo;

	public static void main(String[] args) throws Exception {
		char ch;

		do {
			System.out.println("========Paytm wallet application========");
			System.out.println("1. Create Account ");
			System.out.println("2. Show Balance ");
			System.out.println("3. Deposit ");
			System.out.println("4. Withdraw ");
			System.out.println("5. Fund Transfer");
			System.out.println("6. Print Transactions");
			System.out.println("7. Exit");
			System.out.println("Choose an option");
			int option = scanner.nextInt();

			switch (option) {
			case 1:
				create();
				break;
			case 2:
				showbalance();

				break;

			case 3:
				deposit();

				break;

			case 4:
				withdraw();

				break;

			case 5:
				fundtransfer();

				break;

			case 6:
				printTransaction();

				break;
			case 7:
				System.exit(0);

				break;

			default:
				System.out.println("\ninvalid option\n");
				break;
			}

			System.out.print("\n\tDo you want to continue press Y/N\n\t");
			ch = scanner.next().charAt(0);

		} while (ch == 'y' || ch == 'Y');

		scanner.close();
	}

	static void create() throws Exception {
		CustomerBean customerBean = new CustomerBean();
		System.out.print("\tEnter Customer firstname\t:");
		String fname = scanner.next();
		customerBean.setFirstName(fname);

		System.out.print("\tEnter Customer lastname\t:");
		String lname = scanner.next();
		customerBean.setLastName(lname);

		System.out.print("\tEnter  Customer  email id\t:");
		String email = scanner.next();
		customerBean.setEmailId(email);

		System.out.print("\tEnter  Customer  phone number\t:");
		String phone = scanner.next();
		customerBean.setPhoneNo(phone);

		System.out.print("\tEnter  Customer PAN number\t:");
		String pan = scanner.next();
		customerBean.setPanNum(pan);

		System.out.print("\tEnter  Customer  address\t:");
		String address = scanner.next();
		customerBean.setAddress(address);

		AccountBean accountBean = new AccountBean();
		System.out.print("\n\tEnter Date of Opening (DD/MM/YYYY)\t:");
		String accDateInput = scanner.next();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dateOfOpeining = sdf.parse(accDateInput);
		accountBean.setDateOfOpening(dateOfOpeining);

		System.out.print("\n\tEnter balance to create account\t:");
		double balance = scanner.nextDouble();
		accountBean.setBalance(balance);
		accountBean.setInitialDeposit(balance);
		accountBean.setCustomerBean(customerBean);

		boolean result = service.createAccount(accountBean);

		if (result) {
			System.out.println("\n\tCongratulations Customer account has been created successfully...\n\t");
		} else {
			System.out.println("\n\tEnter valid details..\n\t");
		}
	}

	static void showbalance() throws CustomerException, Exception {
		System.out.print("\tEnter phone number\t:");
		Long phnNo = scanner.nextLong();

		AccountBean accountBean = service.showBalance(phnNo);

		if (accountBean == null) {
			System.out.print("Account Does not exist");
			return;
		}

		System.out.print("\n\t Account holder name \t:" + accountBean.getCustomerBean().getFirstName() + "\n\t");
		System.out.print("\n\t Account holder's address \t:" + accountBean.getCustomerBean().getAddress() + "\n\t");
		System.out.print("\n\t Current Balance \t:" + accountBean.getBalance() + "\n\t");

	}

	static void deposit() throws Exception {
		System.out.print("\tEnter phone number\t:");
		Long phnNo = scanner.nextLong();

		AccountBean accountBean = service.findAccount(phnNo);

		System.out.print("\n\t Account holder name \t:" + accountBean.getCustomerBean().getFirstName() + "\n\t");
		System.out.print("\n\t Account holder's address \t:" + accountBean.getCustomerBean().getAddress() + "\n\t");
		System.out.print("\n\t Current Balance \t:" + accountBean.getBalance() + "\n\t");

		System.out.print("\n\tEnter amount that you want to deposit\t:");
		double depositAmt = scanner.nextDouble();

		WalletTransaction wt = new WalletTransaction();
		wt.setTransactionType(1);
		wt.setTransactionDate(new Date());
		wt.setTransactionAmt(depositAmt);
		wt.setBeneficiaryAccountBean(null);

		accountBean.addTransation(wt);
		
		boolean result = service.deposit(accountBean, depositAmt);

		if (result) {
			System.out.println(
					"\n\tMoney is deposited into " + accountBean.getCustomerBean().getFirstName() + "'s Account \n\t");
			System.out.println("\n\tcurrent balance : " + accountBean.getBalance() + "\n\t");
		} else {
			System.out.println("\n\tMoney is NOT Deposited into " + accountBean.getCustomerBean().getFirstName()
					+ "'s Account\n\t ");
		}

	}

	static void withdraw() throws Exception {
		System.out.print("\tEnter phone number\t:");
		Long phnNo = scanner.nextLong();

		AccountBean accountBean = service.findAccount(phnNo);

		System.out.print("\n\t Account holder name \t:" + accountBean.getCustomerBean().getFirstName() + "\n\t");
		System.out.print("\n\t Account holder's address \t:" + accountBean.getCustomerBean().getAddress() + "\n\t");
		System.out.print("\n\t Current Balance \t:" + accountBean.getBalance() + "\n\t");

		System.out.print("\n\tEnter amount that you want to withdraw\t");
		double withdrawAmt = scanner.nextDouble();

		WalletTransaction wt = new WalletTransaction();
		wt.setTransactionType(2);
		wt.setTransactionDate(new Date());
		wt.setTransactionAmt(withdrawAmt);
		wt.setBeneficiaryAccountBean(null);

		accountBean.addTransation(wt);

		boolean result = service.withdraw(accountBean, withdrawAmt);
		if (result) {
			System.out.print(
					"\n\tMoney is withdrawn from " + accountBean.getCustomerBean().getFirstName() + "'s Account \n\t");
		} else {
			System.out.print("\n\tMoney is NOT withdrawn from " + accountBean.getCustomerBean().getFirstName()
					+ "'s Account\n\t");
		}

	}

	static void fundtransfer() throws Exception {
		System.out.print("\tEnter phone number\t:");
		Long phnNo = scanner.nextLong();

		AccountBean accountBean1 = service.findAccount(phnNo);

		System.out.print("\n\t Account holder name \t:" + accountBean1.getCustomerBean().getFirstName() + "\n\t");
		System.out.print("\n\t Account holder's address \t:" + accountBean1.getCustomerBean().getAddress() + "\n\t");
		System.out.print("\n\t Current Balance \t:" + accountBean1.getBalance() + "\n\t");

		System.out.print("\n\tEnter Account ID to Transfer Money to\t:");
		Long targetPhnNo= scanner.nextLong();

		AccountBean accountBean2 = service.findAccount(targetPhnNo);
		System.out.print("\n\t Account holder name \t:" + accountBean2.getCustomerBean().getFirstName() + "\n\t");
		System.out.print("\n\t Account holder's address \t:" + accountBean2.getCustomerBean().getAddress() + "\n\t");
		System.out.print("\n\t Current Balance \t:" + accountBean2.getBalance() + "\n\t");

		System.out.print("\n\tEnter amount that you want to transfer\t:");
		double transferAmt = scanner.nextDouble();

		WalletTransaction wt = new WalletTransaction();
		wt.setTransactionType(3);
		wt.setTransactionDate(new Date());
		wt.setTransactionAmt(transferAmt);
		wt.setBeneficiaryAccountBean(accountBean2);

		accountBean1.addTransation(wt);

		boolean result = service.fundTransfer(accountBean1, accountBean2, transferAmt);

		if (result) {
			System.out.print("\n\tMoney is transferred  from " + accountBean1.getCustomerBean().getFirstName()
					+ "'s Account \n\t");
		} else {
			System.out.print("\n\tMoney is not transferred from " + accountBean1.getCustomerBean().getFirstName()
					+ "'s Account \n\t");
		}

	}

	static void printTransaction() throws Exception {
		System.out.print("\tEnter phone number\t:");
		Long phnNo = scanner.nextLong();

		AccountBean accountBean = service.findAccount(phnNo);

		List<WalletTransaction> transactions = accountBean.getAllTransactions();

		System.out.println(accountBean);
		System.out.println(accountBean.getCustomerBean());

		System.out.println("------------------------------------------------------------------");

		for (WalletTransaction wt : transactions) {

			String str = "";
			if (wt.getTransactionType() == 1) {
				str = str + "DEPOSIT";
			}
			if (wt.getTransactionType() == 2) {
				str = str + "WITHDRAW";
			}
			if (wt.getTransactionType() == 3) {
				str = str + "FUND TRANSFER";
			}

			str = str + "\t\t" + wt.getTransactionDate();

			str = str + "\t\t" + wt.getTransactionAmt();
			System.out.println("\n\t" + str + "\n\t");
		}

		System.out.println("\n------------------------------------------------------------------\n");

	}

}
