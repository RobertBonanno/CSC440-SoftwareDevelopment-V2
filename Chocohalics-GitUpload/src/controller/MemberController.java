package controller;

import java.util.concurrent.TimeoutException;

import backEnd.Address;
import backEnd.Member;
import backEnd.MemberHash;

public class MemberController extends MasterController {
	MemberHash memberHash; 
	
	MemberController(){
		super();
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
		int memberID;
		terminal.setOutput("Please enter the member ID of the member you wish to delete: "); 
		memberID  = terminal.readInt();
		
		while(memberHash.search(memberID) == null){
			
			terminal.setOutput("This ID was not found. Please re-enter you ID or enter \"0\" to exit:");
			memberID = terminal.readInt();
			
			if(memberID==0)
				return;
		}
		
		terminal.setOutput("This is your current information: "+System.lineSeparator()+((Member)memberHash.search(memberID)).toString());
		
		terminal.setOutput("What you would like to change?:"
				+ System.lineSeparator()+"\t1: Status"
				+ System.lineSeparator()+"\t2: Name"
				+ System.lineSeparator()+"\t3: Address"
				+ System.lineSeparator()+"\t4: Nothing");
		int caseNumber = terminal.readInt(); 
		
		String name = null;
		String street = null;
		String city = null;
		String state= null;
		int zipCode = 0;
		
		switch (caseNumber) {
		case 1:
			
			terminal.setOutput("Which status would you like to switch to:"
					+ System.lineSeparator()+"\t1: VALID"
					+ System.lineSeparator()+"\t2: SUSPENDED");
			int status = terminal.readInt();
			((Member)memberHash.search(memberID)).setStatus(status);
			
			if(!(status==1 || status==2))
				terminal.setOutput("You have entered an invalid option. Your status was not changed.");
			break;
		case 2:
			terminal.setOutput("Please enter your name: ");
			name = terminal.readText();
			
			((Member)memberHash.search(memberID)).setName(name);
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
			
			((Member)memberHash.search(memberID)).setAddress(address);
			break;
		case 4:
			break;
		default:
			break;
		}
		
		terminal.setOutput("This is your modified member information: "+System.lineSeparator()+((Member)memberHash.search(memberID)).toString());
	}
	
	public String ValidateMember(int memberID){
		return memberHash.validate(memberID);
	}
	
	protected void writeToDisk(){
		memberHash.writeToXML();
	}
	

}
