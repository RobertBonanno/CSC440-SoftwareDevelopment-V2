package controller;

import backEnd.Service;
import backEnd.ServiceHash;

public class ServiceController extends MasterController{
	ServiceHash serviceHash; 
	

	ServiceController(){
		serviceHash = new ServiceHash();
	}
	//==========Service Stuff====================
	/**
	 * Adds a new service to a service log.
	 * @return
	 */
	public Service AddService(){
		String serviceName;
		double fee;
		String discription; 
		
		
		terminal.setOutput("Please enter the name of the service:  ");
		serviceName = terminal.readText();
		terminal.setOutput("Please enter the fee of the service: ");
		fee = terminal.readInt(); 
		terminal.setOutput("Please enter the discription of the service:  ");
		discription = terminal.readText();
		
		
		return null; 
	}
	
	public Service EditServiceLog(){
		return null;
	}
	
}
