package backEnd;

import static org.junit.Assert.*;

import org.junit.Test;

public class MemberHashTest {
		
		MemberHash test = new MemberHash();
		Address a1  = new Address();
		Member bob1 = new Member("bob1", a1);
		Address a2  = new Address();
		Member bob2 = new Member("bob2", a2);
		Address a3  = new Address();
		Member bob3 = new Member("bob3", a3);
		Address a4  = new Address();
		Member bob4 = new Member("bob4", a4);
		Address a5  = new Address();
		Member bob5 = new Member("bob5", a5);
		Address a6  = new Address();
		Member Robert = new Member("Robert", a6);
		
	@Test
	public void testAdd() {
	
		test.put(7001, bob1 );
		test.put(7003, bob2 );
		test.put(7004, bob3 );
		test.put(7005, bob4 );
		
		boolean output = test.add(7005, bob5 );
		assertEquals(false, output);
		boolean output2 = test.add(7501, bob5 );
		assertEquals(true, output2);
		
	}
	
	@Test
	public void testSearch() {
	
		test.put(7001, bob1 );
		test.put(7003, bob2 );
		test.put(7004, bob3 );
		test.put(7005, bob4 );
		
		Object output = ((Member) test.search(7005)).getName();
		assertEquals("bob4", output);
		Object output2 = test.search(7501);
		assertEquals("invalid", output2);
		
	}
	
	@Test
	public void testremove() {
		
		test.put(7001, bob1 );
		test.put(7003, bob2 );
		test.put(7004, bob3 );
		test.put(7005, bob4 );
		
		
		Object output = test.search(7001);
		assertEquals(bob1, output);
		//System.out.println(((Member)test.search(7001)).getName());
		test.remove(7001);
		Object output2 = test.search(7001);
		assertEquals("invalid", output2);
		
	}
	
	@Test
	public void testEdit() {
	
		test.put(7001, bob1 );
		test.put(7003, bob2 );
		test.put(7004, bob3 );
		test.put(7005, bob4 );
		
		String output = test.editMem(7002, bob5 );
		assertEquals("invalid", output);
		String output2 = test.editMem(7001, Robert );
		assertEquals("edited", output2);
		
	}
}
