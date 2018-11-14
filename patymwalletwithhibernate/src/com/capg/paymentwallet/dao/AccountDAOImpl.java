package com.capg.paymentwallet.dao;

import javax.persistence.EntityManager;

import com.capg.paymentwallet.bean.AccountBean;

public class AccountDAOImpl implements IAccountDao {

	EntityManager em;

	@Override
	public boolean createAccount(AccountBean accountBean) throws Exception {
		try {

			this.em = JPAManager.createEntityManager();

			em.getTransaction().begin();

			em.persist(accountBean);

			em.getTransaction().commit();

			JPAManager.closeResources(em);

			return true;
		} catch (Exception e) {
e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean updateAccount(AccountBean accountBean) throws Exception {
		try {
			this.em = JPAManager.createEntityManager();
			
			em.getTransaction().begin();

			em.merge(accountBean);

			em.getTransaction().commit();
			
			JPAManager.closeResources(em);
			
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	@Override
	public AccountBean findAccount(Long phnNo) throws Exception {
		try {
			em = JPAManager.createEntityManager();
			
			AccountBean accountBean = em.find(AccountBean.class, phnNo);
			
			JPAManager.closeResources(em);
			
			return accountBean;

		} catch (Exception e) {
			return null;
		}
	}

}
