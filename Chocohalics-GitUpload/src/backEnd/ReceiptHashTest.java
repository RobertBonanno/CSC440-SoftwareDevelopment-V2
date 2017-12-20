package backEnd;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Test;

public class ReceiptHashTest {

	@Test
	public void testRemove() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetDataHashType() {
	}

	@Test
	public void testWriteToXML() {
		ReceiptHash test = new ReceiptHash();
		Service service1 = new Service(5555, "Realaxation", 10.00, "A session of relaxation");
		Address a1 = new Address("A Lan", "Johnsonville", "MK", 44861);
		Member member1 = new Member("John Michaels", a1, 1010);
		Address a2 = new Address("B Lane", "Detroit", "MI", 99851);
		Provider provider1 = new Provider("Mary Smith", a2, 2020);
		test.add(65413,service1, member1, provider1, new Date(System.currentTimeMillis()), "It was a success!");
		
		test.writeToXML();
	}
	
	@Test
	public void testTransformToMemberKey(){
		
		ReceiptHash test = new ReceiptHash();
		Service service1 = new Service(5555, "Relaxation", 10.00, "A session of relaxation");
		Address a1 = new Address("A Lan", "Johnsonville", "MK", 44861);
		Member member1 = new Member("John Michaels", a1, 1010);
		Address a2 = new Address("B Lane", "Detroit", "MI", 99851);
		Provider provider1 = new Provider("Mary Smith", a2, 2020);
		test.add(98743,service1, member1, provider1, new Date(System.currentTimeMillis()), "It was a success!");
		
		Service service2 = new Service(6666, "Yawning", 10.00, "A session of relaxation");
		Address a3 = new Address("C Lan", "Johnsonville", "MK", 44861);
		Member member2 = new Member("Mary Michaels", a3, 1020);
		Address a4 = new Address("D Lane", "Detroit", "MI", 99851);
		Provider provider2 = new Provider("Michael Smith", a4, 2020);
		test.add(261844,service2, member2, provider2, new Date(2016, 10, 10), "It was a success!");
		
		Service service3 = new Service(6666, "Yawning", 10.00, "A session of relaxation");
		Address a5 = new Address("C Lan", "Johnsonville", "MK", 44861);
		Member member3 = new Member("Mary Michaels", a5, 1020);
		Address a6 = new Address("D Lane", "Detroit", "MI", 99851);
		Provider provider3 = new Provider("Michael Smith", a6, 2020);
		test.add(24135,service3, member3, provider3, new Date(2016, 10, 10), "It was a success!");
		
		test.transformToMemberKey();
	}

	@Test
	public void testReadFromXML() {
		ReceiptHash test = new ReceiptHash();
		test.readFromXML("ChocAnReceipt_2017-12-15_14-55.XML");
		test.writeToXML();
	}

	@Test
	public void testClear() {
		fail("Not yet implemented");
	}


	@Test
	public void testAdd() {
		fail("Not yet implemented");
	}

	@Test
	public void testSearchInt() {
		fail("Not yet implemented");
	}

}
