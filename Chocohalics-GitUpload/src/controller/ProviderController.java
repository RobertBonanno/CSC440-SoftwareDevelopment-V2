package controller;

import java.util.ArrayList;
import java.util.HashMap;

import backEnd.Address;
import backEnd.Provider;
import backEnd.Provider;
import backEnd.ProviderHash;
import backEnd.Service;
import backEnd.ServiceHash;

public class ProviderController extends BaseController{
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
	
	private ProviderController(){ 
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
	
	public void AddService(Service service, Integer providerID){
		if(providerHash.validate(providerID.intValue())){
			providerHash.editProv(providerID, service);
		}
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
	
	public void EditProvider(Integer providerID, String name, Address address, ArrayList<Service> serviceList){
		if(providerHash.validate(providerID)){
			Provider provider = providerHash.search(providerID);
			if(name!=null)
				provider.setName(name);
			if(address!=null)
				provider.setAddress(address);
			if(serviceList!=null)
				for(Service service : serviceList)
					provider.addService(service);
			
			terminal.setOutput("This is the new information for Provider:"+provider.toString());
		}
		else terminal.setOutput("Provider entered was not a part of the system.");
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
		
		terminal.setOutput("This is your current information: "+System.lineSeparator()+providerHash.search(providerID).toString());
		
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
			
			providerHash.search(providerID).setName(name);
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
			
			providerHash.search(providerID).setAddress(address);
			break;
		case 3:
			break;
		default:
			break;
		}
		
		terminal.setOutput("This is your modified provider information: "+System.lineSeparator()+providerHash.search(providerID).toString());
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
