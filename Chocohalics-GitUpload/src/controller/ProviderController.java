package controller;

import backEnd.Provider;
import backEnd.ProviderHash;
import backEnd.Service;

public class ProviderController extends MasterController{
	ProviderHash providerHash;
	Provider provider; 
	
	ProviderController(){
		super();
		providerHash = new ProviderHash(); 
	}
	
	//===========Provider Stuff=================
	public void AddProvider(){
		
		String name; 
		terminal.setOutput("Please enter the provider's name: ");
		name = terminal.readText();
		Provider newProvider = new Provider(name);
		terminal.setOutput(providerHash.addProvR(newProvider.getID(), newProvider));
	}
		
	public Provider EditProvider(){
		return null;
	}
	
	public void CreateProviderDirectory(){
		
	}
	
	public void AddService(){
		int id;
		terminal.setOutput("Please enter the ID of the provider you wish to add a service to: ");
		id = terminal.readInt(); 
		

		
		Service newService = new Service(); 
		
		
		id = terminal.readInt();
	}

}
