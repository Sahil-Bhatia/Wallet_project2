package com.cg.dao;

import java.util.*;

import com.cg.bean.Account;
import com.cg.exception.InsufficientFundException;

public interface AccountDAO {

	
	public boolean addAccount(Account ob);
	public boolean updateAccount(Account ob);
	public boolean deleteAccount(Account ob);
	public Account findAccount(Long mobile);
	public Map<Long,Account> getAllAccounts();
	public String transferMoney(Account from, Account to, double amount) throws InsufficientFundException;
	
	
	
	
}
