package controller;

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
		
		
		serviceHash.add(serviceName, fee, description);; 
	}
	
	public Service EditServiceLog(){
		return null;
	}
	
}
