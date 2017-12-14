package controller;

import backEnd.Address;
//import backEnd.service;
import backEnd.Service;
import backEnd.ServiceHash;

public class ServiceController extends MasterController{
	ServiceHash serviceHash; 
	

	ServiceController(){
		super();
		serviceHash = new ServiceHash();
	}
	//==========Service Stuff====================
	/**
	 * Adds a new service to a service log.
	 */
	public void AddService(){
		String serviceName;
		double fee;
		String description; 
		
		
		terminal.setOutput("Please enter the name of the service:  ");
		serviceName = terminal.readText();
		terminal.setOutput("Please enter the fee of the service: ");
		fee = terminal.readInt(); 
		terminal.setOutput("Please enter the description of the service:  ");
		description = terminal.readText();
		
		
		serviceHash.add(serviceName, fee, description);
	}
	
	public void EditServiceLog(){
		int serviceID;
		terminal.setOutput("Please enter the service ID of the service you wish to edit: "); 
		serviceID  = terminal.readInt();
		
		while(serviceHash.search(serviceID) == null){
			
			terminal.setOutput("This ID was not found. Please re-enter you ID or enter \"0\" to exit:");
			serviceID = terminal.readInt();
			
			if(serviceID==0)
				return;
		}
		
		terminal.setOutput("This is your current information: "+System.lineSeparator()+((Service)serviceHash.search(serviceID)).toString());
		
		terminal.setOutput("What you would like to change?:"
				+ System.lineSeparator()+"\t1: Name"
				+ System.lineSeparator()+"\t2: fee"
				+ System.lineSeparator()+"\t3: description"
				+ System.lineSeparator()+"\t4: Nothing");
		int caseNumber = terminal.readInt(); 
		
		String name = null;
		String description = null;
		double fee = 0;
		
		switch (caseNumber) {
		case 1:
			
			terminal.setOutput("What would you like to change the name to:");
			name = terminal.readText();
			((Service)serviceHash.search(serviceID)).setName(name);
			
				terminal.setOutput("Service name was changed to: ------------.");
			break;
		case 2:
			terminal.setOutput("Please enter the new fee: ");
			fee = terminal.readDouble();
			
			((Service)serviceHash.search(serviceID)).setName(name);
			break;
		case 3:
			terminal.setOutput("Please enter your new service description: "); 
			description = terminal.readText();
			terminal.setOutput("Please enter your city name: "); 
		 
			terminal.setOutput("Please enter your state name: "); 
			
			terminal.setOutput("Please enter your zip code: "); 
			//zipCode  = terminal.readInt();
			
			
			
			((Service)serviceHash.search(serviceID)).setAddress(address);
			break;
		case 4:
			break;
		default:
			break;
		}
		
		terminal.setOutput("This is your modified service information: "+System.lineSeparator()+((Service)serviceHash.search(serviceID)).toString());
	}
	
}


/*

	public void Editservice(){//This method is currently a work in progress. 

		

		
		terminal.setOutput("This is your current information: "+System.lineSeparator()+((service)serviceHash.search(serviceID)).toString());
		
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
			((service)serviceHash.search(serviceID)).setStatus(status);
			
			if(!(status==1 || status==2))
				terminal.setOutput("You have entered an invalid option. Your status was not changed.");
			break;
		case 2:
			terminal.setOutput("Please enter your name: ");
			name = terminal.readText();
			
			((service)serviceHash.search(serviceID)).setName(name);
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
			
			((service)serviceHash.search(serviceID)).setAddress(address);
			break;
		case 4:
			break;
		default:
			break;
		}
		
		terminal.setOutput("This is your modified service information: "+System.lineSeparator()+((service)serviceHash.search(serviceID)).toString());
	}


*/