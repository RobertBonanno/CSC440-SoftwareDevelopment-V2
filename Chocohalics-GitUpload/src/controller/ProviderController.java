package controller;

import backEnd.Address;
import backEnd.Provider;
import backEnd.Provider;
import backEnd.ProviderHash;
import backEnd.Service;
import backEnd.ServiceHash;

public class ProviderController extends MasterController{
	ServiceController serviceController;
	ProviderHash providerHash;
	Provider provider; 
	
	
	ProviderController(){
		super();
		providerHash = new ProviderHash(); 
		serviceController = new ServiceController();
	}
	
	//===========Provider Stuff=================

	
	public void CreateProviderDirectory(){
		
	}
	
	public void AddService(){
		int id;
		terminal.setOutput("Please enter the ID of the provider you wish to add a service to: ");
		id = terminal.readInt(); 
		
		
		

		
		
	}








	public void AddProvider(){
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
		
		providerHash.add(name, address); 
	}
	
	public void Deleteprovider(){
		int id;
		
		terminal.setOutput("Please enter the provider ID of the provider you wish to delete: "); 
		id  = terminal.readInt();
		
		providerHash.remove(id);
		
		terminal.setOutput("provider " + id + " has been deleted"); 
	}
	
	public void Editprovider(){//This method is currently a work in progress. 
		int providerID;
		terminal.setOutput("Please enter the provider ID of the provider you wish to delete: "); 
		providerID  = terminal.readInt();
		
		while(providerHash.search(providerID) == null){
			
			terminal.setOutput("This ID was not found. Please re-enter you ID or enter \"0\" to exit:");
			providerID = terminal.readInt();
			
			if(providerID==0)
				return;
		}
		
		terminal.setOutput("This is your current information: "+System.lineSeparator()+((Provider)providerHash.search(providerID)).toString());
		
		terminal.setOutput("What you would like to change?:"
				+ System.lineSeparator()+"\t1: Name"
				+ System.lineSeparator()+"\t2: Address"
				+ System.lineSeparator()+"\t3: Nothing");
		int caseNumber = terminal.readInt(); 
		
		String name = null;
		String street = null;
		String city = null;
		String state= null;
		int zipCode = 0;
		
		switch (caseNumber) {

		case 1:
			terminal.setOutput("Please enter your name: ");
			name = terminal.readText();
			
			((Provider)providerHash.search(providerID)).setName(name);
			break;
		case 2:
			terminal.setOutput("Please enter your street name: "); 
			street  = terminal.readText(); 
			terminal.setOutput("Please enter your city name: "); 
			city  = terminal.readText(); 
			terminal.setOutput("Please enter your state name: "); 
			state  = terminal.readText(); 
			terminal.setOutput("Please enter your zip code: "); 
			zipCode  = terminal.readInt();
			
			Address address = new Address(street, city, state, zipCode);
			
			((Provider)providerHash.search(providerID)).setAddress(address);
			break;
		case 3:
			break;
		default:
			break;
		}
		
		terminal.setOutput("This is your modified provider information: "+System.lineSeparator()+((Provider)providerHash.search(providerID)).toString());
	}
	
	public boolean Validateprovider(int providerID){
		return providerHash.validate(providerID);
	}
	
	protected void writeToXML(){
		providerHash.writeToXML();
	}













}
