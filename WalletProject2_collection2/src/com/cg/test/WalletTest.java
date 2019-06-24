package com.cg.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.cg.bean.Account;
import com.cg.exception.InsufficientFundException;
import com.cg.service.AccountService;

class WalletTest {
	
	static Account at,at1;
	@BeforeAll
	public static void beforeAllTest() {
		at=new Account(100,1234567890,"Ram",3000);
		at1=new Account(101,1234567891,"Sham",5000);
	}
	
	
	@Test
	void testDeposit() {
		AccountService ob=new AccountService();
		ob.addAccount(at);
		assertTrue(ob.deposit(at,2500)>=at.getBalance());
		//assertEquals(ob.deposit(at, 5000),at.getBalance()+5000);
		//assertThrows(InsufficientFundException.class,()->ob.deposit(at, -2500));
	}
	@Test
	void testWithdraw() {
		AccountService ob=new AccountService();
		ob.addAccount(at);
		assertThrows(InsufficientFundException.class,()->ob.withdraw(at, at.getBalance()-500));
	}
	@Test
	void testTransfer() {
		AccountService ob=new AccountService();
		ob.addAccount(at);
		ob.addAccount(at1);
		
		assertThrows(InsufficientFundException.class,()->ob.transferMoney(at, at1, at.getBalance()-500));
	}
	@Test
	void testAddAccount() {
		AccountService ob=new AccountService();
		assertTrue(ob.addAccount(at));
		
	}
	@Test
	void testUpadteAccount() {
		AccountService ob=new AccountService();
		ob.addAccount(at);
		at.setBalance(2000);
		assertTrue(ob.updateAccount(at));
	}
	@Test
	void testDeleteAccount() {
		AccountService ob=new AccountService();
		assertTrue(ob.deleteAccount(at));
	}
	@Test
	void testFindAccount() {
		AccountService ob=new AccountService();
		ob.addAccount(at);
		assertEquals(at,ob.findAccount(at.getMobile()));
		
	}
	@Test
	void testGetAllAccounts() {
		AccountService ob=new AccountService();
		ob.addAccount(at);
		ob.addAccount(at1);
		Map<Long,Account>m=new HashMap<>();
		m.put(at.getMobile(), at);
		m.put(at1.getMobile(), at1);
		assertEquals(m,ob.getAllAccounts());
	}
}
