package controller;

import java.util.concurrent.TimeoutException;

import backEnd.Address;
import backEnd.Member;
import backEnd.MemberHash;

public class MemberController extends MasterController {
	MemberHash memberHash; 
	
	MemberController(){
		memberHash = new MemberHash(); 
	}
	
	
	//==========Member Stuff=================
	public Member AddMember(){
		String name;
		String street;
		String city;
		String state;
		int zipCode; 
		
		
		terminal.setOutput("Please enter your name: ");
		name = terminal.readText(); 
		terminal.setOutput("Please enter your street name: "); 
		street  = terminal.readText(); 
		terminal.setOutput("Please enter your city name: "); 
		city  = terminal.readText(); 
		terminal.setOutput("Please enter your state name: "); 
		state  = terminal.readText(); 
		terminal.setOutput("Please enter your zip code: "); 
		zipCode  = terminal.readInt();
	
		
		Address address = new Address(street, city, state, zipCode);
		
		Member newMember = new Member(name, address); 
		
		return newMember; 
	}
	
	public Member DeleteMember(){
		return null;
	}
	
	public String ValidateMember(int uid){
		String temp;
		temp = memberHash.searchMem(uid);	
		return temp; 
	}
	

}
