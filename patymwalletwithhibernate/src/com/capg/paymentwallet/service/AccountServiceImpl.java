package com.capg.paymentwallet.service;

import com.capg.paymentwallet.bean.AccountBean;
import com.capg.paymentwallet.dao.AccountDAOImpl;
import com.capg.paymentwallet.dao.IAccountDao;

public class AccountServiceImpl implements IAccountService {
	static IAccountDao dao = new AccountDAOImpl();

	@Override
	public boolean createAccount(AccountBean accountBean) throws Exception {
		validate(accountBean);
		return dao.createAccount(accountBean);

	}

	@Override
	public boolean deposit(AccountBean accountBean, double depositAmount) throws Exception {
		accountBean.setBalance(accountBean.getBalance() + depositAmount);
		boolean result = dao.updateAccount(accountBean);
		return result;
	}

	@Override
	public boolean withdraw(AccountBean accountBean, double withdrawAmount) throws Exception {
		accountBean.setBalance(accountBean.getBalance() - withdrawAmount);

		return dao.updateAccount(accountBean);
	}

	@Override
	public boolean fundTransfer(AccountBean transferingAccountBean, AccountBean beneficiaryAccountBean,
			double transferAmount) throws Exception {

		transferingAccountBean.setBalance(transferingAccountBean.getBalance() - transferAmount);
		beneficiaryAccountBean.setBalance(beneficiaryAccountBean.getBalance() + transferAmount);
		return dao.updateAccount(transferingAccountBean) && dao.updateAccount(beneficiaryAccountBean);
	}

	@Override
	public AccountBean showBalance(Long phnNo) throws Exception {

		return dao.findAccount(phnNo);

	}

	@Override
	public AccountBean findAccount(Long phnNo) throws Exception {
		return dao.findAccount(phnNo);

	}

	public boolean validate(AccountBean accountBean) throws Exception {

		if (!(accountBean.getCustomerBean().getFirstName().matches("[A-Za-z]{3,15}"))) {
			throw new Exception("First name should have atleast 3 characters");
		}
		if (!(accountBean.getCustomerBean().getLastName().matches("[A-Za-z]{3,15}"))) {
			throw new Exception("Last name should have atleast 3 characters");
		}
		if (!(accountBean.getCustomerBean().getEmailId().matches("[a-z0-9]{6,15}[@][a-z]{4,10}[.][c][o][m]"))) {
			throw new Exception("Enter valid mail");
		}
		if (!(accountBean.getCustomerBean().getPhoneNo().toString().matches("[6-9][0-9]{9}"))) {
			throw new Exception("phone number should have 10 digits");
		}
		
		return true;
	}

}
