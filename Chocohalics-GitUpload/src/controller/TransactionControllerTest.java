package controller;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Test;

import backEnd.Address;
import backEnd.Member;
import backEnd.Provider;
import backEnd.ReceiptHash;
import backEnd.Service;

public class TransactionControllerTest {

	@Test
	public void testWriteACMELog() {
		fail("Not yet implemented");
	}

	@Test
	public void testMemberReport() {
		TransactionController tc = new TransactionController();
		
		Service service1 = new Service(5555, "Relaxation", 10.00, "A session of relaxation");
		Address a1 = new Address("A Lan", "Johnsonville", "MK", 44861);
		Member member1 = new Member("John Michaels", a1, 1010);
		Address a2 = new Address("B Lane", "Detroit", "MI", 99851);
		Provider provider1 = new Provider("Mary Smith", a2, 2020);
		tc.CreateTransaction(service1, member1, provider1);
		
		Service service2 = new Service(6666, "Yawning", 10.00, "A session of relaxation");
		Address a3 = new Address("C Lan", "Johnsonville", "MK", 44861);
		Member member2 = new Member("Mary Michaels", a3, 1020);
		Address a4 = new Address("D Lane", "Detroit", "MI", 99851);
		Provider provider2 = new Provider("Michael Smith", a4, 2020);
		tc.CreateTransaction(service2, member2, provider2);
		
		Service service3 = new Service(6666, "Making macaroonie", 10.00, "A session of relaxation");
		Address a5 = new Address("C Lan", "Johnsonville", "MK", 44861);
		Member member3 = new Member("Mary Michaels", a5, 1020);
		Address a6 = new Address("D Lane", "Detroit", "MI", 99851);
		Provider provider3 = new Provider("Michael Smith", a6, 2020);
		tc.CreateTransaction(service3, member3, provider3);
		
		tc.memberReport();
	}

	@Test
	public void testProviderReport() {
		fail("Not yet implemented");
	}

	@Test
	public void testSummaryReport() {
		fail("Not yet implemented");
	}

	@Test
	public void testEFTReport() {
		fail("Not yet implemented");
	}

}
