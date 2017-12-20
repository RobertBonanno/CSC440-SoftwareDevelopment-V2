package controller;

import backEnd.Service;
import backEnd.ServiceHash;

public class ServiceController extends BaseController{

	private static ServiceController instance = null;
 	
 	ServiceHash serviceHash;
	
	private ServiceController(){ 
		super();
		serviceHash = new ServiceHash(); 
	}
	
	 static ServiceController getInstance(){ 
		if(instance == null){
			instance = new ServiceController() ;
		}

		return instance;
	}
	
	//==========Service Stuff====================
	/**
	 * Adds a new service to a service log.
	 */
	public void addService(){
		String serviceName;
		double fee;
		String description; 
		
		
		terminal.setOutput("Please enter the name of the service:  ");
		serviceName = terminal.readText();
		terminal.setOutput("Please enter the fee of the service: ");
		fee = terminal.readDouble(); 
		terminal.setOutput("Please enter the description of the service:  ");
		description = terminal.readText();
		
		serviceHash.add(serviceName, fee, description);
	}
	
	public void editServiceHash(){
		int serviceID;
		terminal.setOutput("Please enter the service ID of the service you wish to edit: "); 
		serviceID  = terminal.readInt();
		
		while(serviceHash.search(serviceID) == null){
			
			terminal.setOutput("This ID was not found. Please re-enter you ID or enter \"0\" to exit:");
			serviceID = terminal.readInt();
			
			if(serviceID==0)
				return;
		}
		
		terminal.setOutput("This is your current information: "+System.lineSeparator()+(serviceHash.search(serviceID)).toString());
		
		terminal.setOutput("What you would like to change?:"
				+ System.lineSeparator()+"\t1: Name"
				+ System.lineSeparator()+"\t2: fee"
				+ System.lineSeparator()+"\t3: description"
				+ System.lineSeparator()+"\t4: Nothing");
		int caseNumber = terminal.readInt(); 
		
		String name = null;
		String description = null;
		@SuppressWarnings("unused")
		double fee = 0;
		
		switch (caseNumber) {
		case 1:
			
			terminal.setOutput("What would you like to change the name to:");
			name = terminal.readText();
			(serviceHash.search(serviceID)).setName(name);
			
				terminal.setOutput("Service name was changed to: ------------.");
			break;
		case 2:
			terminal.setOutput("Please enter the new fee: ");
			fee = terminal.readDouble();
			
			(serviceHash.search(serviceID)).setName(name);
			break;
		case 3:
			terminal.setOutput("Please enter your new service description: "); 
			description = terminal.readText();
			
			(serviceHash.search(serviceID)).setDescrp(description);
			break;
		case 4:
			break;
		default:
			break;
		}
		
		terminal.setOutput("This is your modified service information: "+System.lineSeparator()+(serviceHash.search(serviceID)).toString());
	}
	
	public Service searchServiceHash(int serviceID){
		return serviceHash.search(serviceID);
	}
	
	public String listServiceHash(){
		return serviceHash.toString();
	}
	protected void writeToXML(){
		serviceHash.writeToXML();
	}
}