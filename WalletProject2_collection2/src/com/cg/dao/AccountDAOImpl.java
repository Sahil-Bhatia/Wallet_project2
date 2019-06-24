package com.cg.dao;

import java.util.*;

import com.cg.bean.Account;
import com.cg.exception.InsufficientFundException;

public class AccountDAOImpl implements AccountDAO {
	 
	public static Map<Long,Account> accmap=new HashMap<Long,Account>();
	
	@Override
	public boolean addAccount(Account ob) {
		// TODO Auto-generated method stub
		
		accmap.put(ob.getMobile(), ob);	
		
		
		
		return true;
	}

	@Override
	public boolean updateAccount(Account ob) {
		accmap.replace(ob.getMobile(), ob);
		return true;
	}

	@Override
	public boolean deleteAccount(Account ob) {
		// TODO Auto-generated method stub
		accmap.remove(ob.getMobile());
		return true;
	}

	@Override
	public Account findAccount(Long mobile) {
		// TODO Auto-generated method stub
	
		return accmap.get(mobile);
	}

	@Override
	public Map<Long, Account> getAllAccounts() {
		// TODO Auto-generated method stub
		return accmap;
	}

	@Override
	public String transferMoney(Account from, Account to, double amount) throws InsufficientFundException {
		// TODO Auto-generated method stub
		
		double from_new_balance=from.getBalance()-amount;
		if(from_new_balance<1000.00) {
			from_new_balance=from.getBalance();
			//System.out.println("Insufficient Balance");
			//from.setBalance(new_balance);
			
			//return "Amount cannot be transfered insufficient balance";
			throw new InsufficientFundException("Insufficient Fund: Cannot Process Withdrawl",from_new_balance);
		}
		from.setBalance(from_new_balance);
		double b2=to.getBalance()+amount;
		to.setBalance(b2);
		String ans="From Account: "+from.getAid()+" Balance: "+from.getBalance()+"\n"+"To Account: "+to.getAid()+" Balance "+to.getBalance();
		
		return ans;
	}

}
