package controller;

import java.util.Date;

import backEnd.Provider;
import backEnd.Service;

public class MasterController extends BaseController{
	
	MemberController memberController;
	ProviderController providerController;
	ServiceController serviceController;
	TransactionController transactionController;
	
	public MasterController(){
		memberController = new MemberController();
		providerController = new ProviderController();
		serviceController = ServiceController.getInstance();
		transactionController = new TransactionController();
		
	}
	
	@SuppressWarnings("deprecation")
	void addTransaction(int memberID) {
		terminal.setOutput("Please enter the month of the service provided (numerical in form of MM)");
		int month = terminal.readInt();
		terminal.setOutput("Please enter the Day of the service provided (numerical in form of DD)");
		int day = terminal.readInt();
		terminal.setOutput("Please enter the Year of the service provided (numerical in form of YYYY)");
		int year = terminal.readInt();
		@SuppressWarnings("unused")
		Date date = new Date(year, month, day);
		
		for(String provider : providerController.getProviderDirectory()) {
			terminal.setOutput(provider);
		}
		while(true) {
			terminal.setOutput("Please enter the code for the service provided (0 to exit):");
			int serviceCode = terminal.readInt();
			if(serviceCode == 0) {
				terminal.setOutput("Transaction aborted");
				return;
			}
			
			Service potentialService = serviceController.searchServiceHash(serviceCode);
			if(potentialService==null) {
				terminal.setOutput("Invalid service code entered.");
				continue;
			}
			
			terminal.setOutput("Is this the correct Service? (Y/N)"+potentialService);
			if(terminal.readText().toLowerCase().charAt(0)!='y')
				continue;
			
			Provider provider;
			while(true) {
				terminal.setOutput("Please enter the ID of the provider for this service (0 to exit):");
				int providerID = terminal.readInt();
				if(providerID == 0) {
					terminal.setOutput("Transaction aborted.");
					return;
				}
				
				if(providerController.validateprovider(providerID)) {
					provider = providerController.searchProviderHash(providerID);
					if(provider.getServicesOffered().contains(potentialService)) {
						break;
					}else {
						terminal.setOutput("This provider does not offer this service.");
					}
				}else {
					terminal.setOutput("Invalid Provider ID entered.");
				}
			}
			transactionController.CreateTransaction(potentialService, memberController.search(memberID), provider);
			return;
		}
	}
	
	void addServiceToProvider(){ // method is in master because it needs to interact with multiple controllers
		int providerID;
		int serviceID;
		boolean flag = false;
		
		serviceController.addService();
		terminal.setOutput(serviceController.listServiceHash());
		
		terminal.setOutput("Please enter the ID of the provider you wish to add a service to: ");
		providerID = terminal.readInt(); 
		
		do{
			if(!providerController.validateprovider(providerID)){
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
					
					Service service = serviceController.searchServiceHash(serviceID);
					
					providerController.addService(service, providerID);
					terminal.setOutput("Service added to provider: " + providerController.searchProviderHash(providerID).getName()+System.lineSeparator());
				}
			}
		}while(flag);
	}
	
	@SuppressWarnings("unused")
	public void run(){
		try {
		mainLoop: while(true) {
			if(providerController.isEmpty())
				load();
			
			terminal.setOutput("Hello, welcome to Chocohalics. Please enter your ID");
			int id = terminal.readInt();
			if(id<100000000 && id>0){ // they be member
				switch(memberController.ValidateMember(id)){
				case "VALID":
					terminal.setOutput("This member is valid"+System.lineSeparator());
					
					terminal.setOutput("Is this member finishing an appointment? (Y/N)");
					if(terminal.readText().toLowerCase().charAt(0) =='y'){
						
						
						addTransaction(id);
						
						//write receipt from transaction controller
					}
					break; 
					
				case "SUSPENDED":
					terminal.setOutput("This member has been suspended. They must pay their dues to have their membership reinstated."); 
					break;
					
				case "INVALID":
					terminal.setOutput("Invalid entry. Please try again."); 
					break;
				}
					
					
			}else if(id>100000000 && id<200000000){ // they be provider
				if(providerController.validateprovider(id)){
					
					providerControllerLoop: while(true){
						
						terminal.setOutput("What would you like to do:"
							+ System.lineSeparator() +"\t1: Interact with a provider account"
							+ System.lineSeparator() +"\t2: Interact with a member account"
							+ System.lineSeparator() +"\t3: Run a report"
							+ System.lineSeparator() +"\t4: Load from XML"
							+ System.lineSeparator() +"\t5: Log out");
		
						switch (terminal.readInt()) {
						
						case 1: // provider controller stuff
							providerInternalLoop: while(true){
								terminal.setOutput("How would you like to interact with a provider account:"
										+ System.lineSeparator() +"\t1: Add a new provider"
										+ System.lineSeparator() +"\t2: Edit an existing provider"
										+ System.lineSeparator() +"\t3: Remove an existing provider"
										+ System.lineSeparator() +"\t4: Write provider to XML"
										+ System.lineSeparator() +"\t5: Go Back");
								switch (terminal.readInt()) {
								
								case 1: //add new provider
									providerController.addProvider();
									break; 
								
								case 2: // edit an existing provider
									terminal.setOutput("Would you like to add or remove a service? (Y/N)");
									char response = terminal.readText().toLowerCase().charAt(0);
									
									if(response=='y'){
										terminal.setOutput("Would you like to Add (A) or remove (R) a service? (or anything else to exit)"); 
										switch(terminal.readText().toLowerCase().charAt(0)){
										case 'a':
											addServiceToProvider(); 
											break;
										case 'r':
											providerController.removeServiceFromProvider();
											break;
										default: break;
										}
									}else if(response == 'n'){
										providerController.editprovider();
									}
									break;
									
								case 3: // remove an existing provider
									providerController.deleteProvider();
									break;
									
								case 4: //write to xml
									providerController.writeToXML();
									terminal.setOutput("Providers have been written to disk.");
									break;
									
								case 5:
									
									break providerInternalLoop;
								default:
									terminal.setOutput("Invalid input.");
									break;
								}
							}
							break;
						
						case 2: //member account stuff
							memberInternalLoop: while(true){
									
								terminal.setOutput("How would you like to interact with a member account:"
									+ System.lineSeparator() +"\t1: Add a new member"
									+ System.lineSeparator() +"\t2: Edit an existing member"
									+ System.lineSeparator() +"\t3: Remove an existing member"
									+ System.lineSeparator() +"\t4: Write to XML"
									+ System.lineSeparator() +"\t5: Go back");
								
								switch(terminal.readInt()){
								case 1: // add a new member
									memberController.AddMember();
									break;
									
								case 2: //edit an existing member
									memberController.EditMember();
									break;
									
								case 3: //remove an existing member
									memberController.DeleteMember();
									break;
									
								case 4: //write to XMl
									memberController.writeToXML();
									terminal.setOutput("Member has been written to XML");
									break;
									
								case 5:
									break memberInternalLoop;
								
								default: 
									terminal.setOutput("Invalid input.");
									break;
								}
							}
							break;
						
						case 3: //report running stuff
							reportInternalLoop: while(true){
								terminal.setOutput("Which report would you like to run?"
										+ System.lineSeparator() +"\t1: Member Transaction Report"
										+ System.lineSeparator() +"\t2: Provider Transaction Report"
										+ System.lineSeparator() +"\t3: EFT Report"
										+ System.lineSeparator() +"\t4: Provider Services Provided Report"
										+ System.lineSeparator() +"\t5: Go back");
								switch(terminal.readInt()){
								case 1: //member report from transactionController
									transactionController.memberReport();
									terminal.setOutput("Member Reports exported");
									break;
								case 2: //provider report from transactionController
									transactionController.providerReport();
									terminal.setOutput("Provider Reports exported");
									break;
									
								case 3: //EFT report from transactionController
									transactionController.eftReport();
									terminal.setOutput("EFT Report exported");
									break;
									
								case 4: //Provider Services Provided report from transactionController
									transactionController.summaryReport();
									terminal.setOutput("Summary Report exported");
									break;
								case 5: 
									break reportInternalLoop;
								default: 
									terminal.setOutput("Invalid input");
									break;
								}
							}
							break;
						
						case 4: //load from XML
							xmlInternalLoop: while(true) {
								terminal.setOutput("Where would you like to load from xml?"
										+ System.lineSeparator() +"\t1: Members"
										+ System.lineSeparator() +"\t2: Providers "
										+ System.lineSeparator() +"\t3: Go back");
								switch (terminal.readInt()) {
								case 1:
									terminal.setOutput("Please enter the file name to load into the members");
									memberController.loadFromXML(terminal.readText());
									break;
									
								case 2:
									terminal.setOutput("Please enter the file name to load into the providers");
									providerController.loadFromXML(terminal.readText());
									break;
									
								case 3:
									break xmlInternalLoop;
								default:
									terminal.setOutput("Invalid input.");
									break;
								}
							}
							break;
						case 5:
							break providerControllerLoop;
						
						default: //You're a dumbass
							terminal.setOutput("Invalid input");
							break;
						}
					}
				}else/*a ... Frozen reference*/{
					terminal.setOutput("Invalid entry. Please try again.");
					break;
				}
			}
			else {
				terminal.setOutput("Invalid entry. Please try again.");
				
			}
		}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			memberController.writeToXML();
			providerController.writeToXML();
			transactionController.writeToXML();
			serviceController.writeToXML();
			System.out.println("System Exited");
		}
	}
	
	public void load() {
		providerController.loadFromXML("ChocAnProvider_AdminLoad.XML");
	}
}
