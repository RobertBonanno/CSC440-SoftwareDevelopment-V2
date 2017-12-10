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
	public void AddMember(){
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
		
		memberHash.add(name, address); 
	}
	
	//temporary method for testing
	protected void AddMember(Member mem){
		memberHash.add(mem);
	}
	
	public void DeleteMember(){
		int id;
		
		terminal.setOutput("Please enter the member ID of the member you wish to delete: "); 
		id  = terminal.readInt();
		
		memberHash.remove(id);
		
		terminal.setOutput("Member " + id + " has been deleted"); 
	}
	
	public void EditMember(){//This method is currently a work in progress. 
		int id;
		terminal.setOutput("Please enter the member ID of the member you wish to delete: "); 
		id  = terminal.readInt();
		
		
		
		
		
		terminal.setOutput("Please enter what you would like to change:"
				+ System.lineSeparator()+"\t1: Status"
				+ System.lineSeparator()+"\t2: Name"
				+ System.lineSeparator()+"\t3: Address");
		int caseNumber = terminal.readInt(); 
		
		String name = null;
		String street = null;
		String city = null;
		String state= null;
		int zipCode = null;
		
		switch (caseNumber) {
		case 1:
			
			break;
		case 2:
			terminal.setOutput("Please enter your name: ");
			name = terminal.readText();
			break;
		case 3:
			terminal.setOutput("Please enter your street name: "); 
			street  = terminal.readText(); 
			terminal.setOutput("Please enter your city name: "); 
			city  = terminal.readText(); 
			terminal.setOutput("Please enter your state name: "); 
			state  = terminal.readText(); 
			terminal.setOutput("Please enter your zip code: "); 
			zipCode  = terminal.readInt();
			
			Address address = new Address(street, city, state, zipCode);
			
			memberHash.add(name, address); 
			
			break;
			
		default:
			break;
		}
		
		
		if(ValidateMember(memberID).equals(VALID));
	}
	
	public String ValidateMember(int memberID){
		return memberHash.validate(memberID);
	}
	
	protected void writeToDisk(){
		memberHash.writeToDisk();
	}
	

}
