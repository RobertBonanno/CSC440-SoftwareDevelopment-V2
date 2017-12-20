package controller;

import static org.junit.Assert.*;

import org.junit.Test;

import backEnd.Address;
import backEnd.Member;

public class MemberControllerTest {

	@Test
	public void testMemberController() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddMember() {
		MemberController memc = new MemberController(); 
		memc.AddMember();
		memc.AddMember();
		memc.writeToXML();
	}

	@Test
	public void testDeleteMember() {
		fail("Not yet implemented");
	}

	@Test
	public void testValidateMember() {
		MemberController memc = new MemberController();
		Member mem = new Member("Mary", new Address("a","s","d",12345), 77658);
		memc.AddMember(mem);
		assertTrue(memc.ValidateMember(77658).equals("VALID"));
		memc.DeleteMember();
		assertTrue(memc.ValidateMember(77658).equals("INVALID"));
	}

}
