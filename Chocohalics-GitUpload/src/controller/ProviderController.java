package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import backEnd.Address;
import backEnd.Provider;
import backEnd.ProviderHash;
import backEnd.Service;

public class ProviderController extends BaseController{

	//ProviderController instance;
	ProviderHash providerHash;
	
	ProviderController(){ 
		super();
		providerHash = new ProviderHash(); 
	}
	

	public void createProviderDirectory(){
		Calendar calendar = Calendar.getInstance();
		String date = calendar.get(Calendar.DAY_OF_MONTH)+"_"+calendar.get(Calendar.WEEK_OF_YEAR); //Use this instead of Date Class for day/month
		File file = new File("Provider Directory "+date+".txt");
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(file.getName()))){
			
			bw.write("Chocohalics Anonymous Provider Directory "+System.lineSeparator()+"As of: "+date+System.lineSeparator());
			bw.write("------------------------------"+System.lineSeparator());
			
			ArrayList<Provider> providerList = providerHash.getProviderList();
			for(Provider provider : providerList) {
				bw.write(provider.getName()+"'s Services Offered:"+System.lineSeparator());
				bw.write("------------------------------"+System.lineSeparator());
				for(Service service : provider.getServicesOffered()) {
					bw.write(service.getName()+"-Service ID: "+service.getID()+"-ServiceFee: "+service.getFee()+System.lineSeparator());
				}
				bw.write("------------------------------"+System.lineSeparator());
			}
			bw.close();
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	public void addService(Service service, Integer providerID){
		if(providerHash.validate(providerID.intValue())){
			providerHash.editProv(providerID, service);
		}
	}


	public void addProvider(){
		String name;
		String street;
		String city;
		String state;
		int zipCode;
		
		Provider newProvider;
		
		
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

		newProvider = providerHash.add(name, address);

		terminal.setOutput("provider added with following information:"
				+ System.lineSeparator()+"\t" + newProvider.toString());
	}
	
	protected void addProvider(String name, Address address, ArrayList<Service> services){
		Provider newProvider;	
		newProvider = providerHash.add(name, address); 
		for(Service s : services) {
			newProvider.addService(s);
		}
		terminal.setOutput("provider added with following information:"
				+ System.lineSeparator()+"\t" + newProvider.toString());
		
	}
	
	public void deleteProvider(){
		int id;
		
		terminal.setOutput("Please enter the provider ID of the provider you wish to delete: "); 
		id  = terminal.readInt();
		
		providerHash.remove(id);
		
		terminal.setOutput("provider " + id + " has been deleted"); 
	}
	
	public void editProvider(Integer providerID, String name, Address address, ArrayList<Service> serviceList){
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
	
	public void removeServiceFromProvider(){
		int providerID = -1;
		while(!providerHash.validate(providerID)){
			terminal.setOutput("Invalid provider entered. Please enter the provider's ID that you would like to remove a service from or enter 0 to exit.");
			providerID = terminal.readInt();
			
			//check for exit
			if(providerID==0)
				{terminal.setOutput("Operation aborted");return;}
		}
		Provider provider = providerHash.search(providerID);
		String providerName = provider.getName();
		
		ArrayList<Service> servicesOffered = provider.getServicesOffered();
		HashMap<Integer, Service> serviceOfferedHash = new HashMap<Integer, Service>();
		for(Service s : servicesOffered)
			serviceOfferedHash.put(s.getID(), s);
		
		int serviceID = -1;
		
		while(!serviceOfferedHash.containsKey(serviceID)){
			terminal.setOutput("These are the services currently offered by "+providerName+":"+servicesOffered
				+System.lineSeparator()+"Please enter the service you would like to remove from "+providerName+" or enter 0 to exit: ");
			
			serviceID = terminal.readInt();
			//check for exit
			if(serviceID == 0) 
				{terminal.setOutput("Operation aborted");return;}
		}
		
		Service toRemove = serviceOfferedHash.get(serviceID);
		servicesOffered.remove(toRemove);
		terminal.setOutput("The following service has been removed: "+toRemove);
	}
	
	public void editprovider(){//This method is currently a work in progress. 
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
	
	public boolean validateprovider(int providerID){
		return providerHash.validate(providerID);
	}
	
	public Provider searchProviderHash(int providerID){
		return providerHash.search(providerID);
	}
	
	protected void writeToXML(){
		providerHash.writeToXML();
	}

	public void loadFromXML(String readText) {
		providerHash.readFromXML(readText);
	}

	public boolean isEmpty() {
		return providerHash.getProviderList().isEmpty();
	}


	public ArrayList<String> getProviderDirectory() {
		ArrayList<String> toReturn = new ArrayList<String>();
		ArrayList<Provider> providerList = providerHash.getProviderList();
		
		for(Provider provider : providerList) {
			String toAdd = "";
			toAdd += provider.getName()+"'s Services Offered: ";
			for(Service service : provider.getServicesOffered()) {
				toAdd+= "["+service.getName()+"-Service ID: "+service.getID()+"-ServiceFee: "+service.getFee()+"]"+System.lineSeparator();
			}
			toReturn.add(toAdd);
		}
		return toReturn;
	}













}
