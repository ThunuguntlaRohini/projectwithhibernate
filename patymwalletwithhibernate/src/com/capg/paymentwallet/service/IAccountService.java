package com.capg.paymentwallet.service;


import com.capg.paymentwallet.bean.AccountBean;


public interface IAccountService {

          public boolean createAccount(AccountBean accountBean) throws  Exception ;
          public boolean deposit(AccountBean accountBean,double depositAmount) throws Exception ;
          public boolean withdraw(AccountBean accountBean,double withdrawAmount) throws Exception;
          public boolean fundTransfer(AccountBean transferingAccountBean, AccountBean beneficiaryAccountBean, double transferAmount) throws Exception ;
          public AccountBean showBalance(Long phnNo) throws Exception;
          AccountBean findAccount(Long phnNo) throws Exception;
         
		 
          
	
}
