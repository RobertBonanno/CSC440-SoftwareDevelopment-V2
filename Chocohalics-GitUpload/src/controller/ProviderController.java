package controller;

import backEnd.Provider;
import backEnd.ProviderHash;

public class ProviderController extends MasterController{
	ProviderHash providerHash; 
	
	ProviderController(){
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

}
