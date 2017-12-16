package controller;

import java.util.HashMap;

import backEnd.Address;
import backEnd.Provider;
import backEnd.Provider;
import backEnd.ProviderHash;
import backEnd.Service;
import backEnd.ServiceHash;

public class ProviderController extends MasterController{
/*
 	ServiceController serviceController;
	ProviderHash providerHash;
	Provider provider; 
	
	
	ProviderController(){
		super();
		providerHash = new ProviderHash(); 
		serviceController = new ServiceController();
	}
	*/
	
	/////////////////--new code for singleton?--/////////////////////////////////////
	private static ProviderController instance = null;
 	ServiceController serviceController;
	ProviderHash providerHash;
	Provider provider; 
	
	protected ProviderController(){ 
		super();
		providerHash = new ProviderHash(); 
		serviceController = ServiceController.getInstance();
	}
	
	public static ProviderController getInstance(){ 
		if(instance == null){
			instance = new ProviderController() ;
		}

		return instance;
	}
	///////////////////////////////////////////////////////
	//===========Provider Stuff=================

	
	public void CreateProviderDirectory(){
		
	}
	
	public void AddService(){
		int providerID;
		int serviceID;
		boolean flag = false;
		
		terminal.setOutput("Please enter the ID of the provider you wish to add a service to: ");
		providerID = terminal.readInt(); 
		
		do{
			if(providerHash.search(providerID) == null ){
				terminal.setOutput("Provider ID entered was invalid, please enter the ID of the provider you wish to add a service to or enter '0' to exit: ");
				providerID = terminal.readInt(); 
				
				if (providerID != 0)
					flag = true;
			}
			
			else{
				terminal.setOutput("Please enter the ID of the Service you wish to add to provider: ");
				serviceID = terminal.readInt(); 
				
				while(serviceController.searchServiceHash(serviceID) == null){
					
					terminal.setOutput("Service ID entered was invalid enter the ID of the Service you wish to add to provider to or enter '0' to exit: ");
					serviceID = terminal.readInt(); 
					
					if (serviceID == 0) {
						flag = false;
						break;
					}
				}
				if(serviceController.searchServiceHash(serviceID) != null){
					providerHash.editProv(providerID, serviceController.searchServiceHash(serviceID));
					terminal.setOutput("service added to provider: " + providerHash.search(providerID).getServicesOffered());
				}
			}
		}while(flag);		
		
		
	}


	public void AddProvider(){
		String name;
		String street;
		String city;
		String state;
		int zipCode;
		
		Provider newprovider;
		
		
		terminal.setOutput("Please enter your name: ");
		//name = terminal.readText(); 
		terminal.setOutput("Please enter your street name: "); 
		//street  = terminal.readText(); 
		terminal.setOutput("Please enter your city name: "); 
		//city  = terminal.readText(); 
		terminal.setOutput("Please enter your state name: "); 
		//state  = terminal.readText(); 
		terminal.setOutput("Please enter your zip code: "); 
		//zipCode  = terminal.readInt();
	
		///*
		name = "j";
		street = "j";
		city = "j";
		state = "j";
		zipCode = 0;
		//*/
		
		Address address = new Address(street, city, state, zipCode);
		
		newprovider = providerHash.add(name, address); 
		
		terminal.setOutput("provider added with following information:"
				+ System.lineSeparator()+"\t" + newprovider.toString());
		
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
	
	public Provider searchProviderHash(int providerID){
		return providerHash.search(providerID);
	}
	
	protected void writeToXML(){
		providerHash.writeToXML();
	}













}
