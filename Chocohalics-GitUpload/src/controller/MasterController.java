package controller;

import backEnd.Service;

public class MasterController extends BaseController{
	
	MemberController memberController;
	ProviderController providerController;
	ServiceController serviceController;
	TransactionController transactionController;
	
	public MasterController(){
		memberController = new MemberController();
		providerController = ProviderController.getInstance();
		serviceController = ServiceController.getInstance();
		transactionController = new TransactionController();
		
	}
	
	public void addServiceToProvider(){
		int providerID;
		int serviceID;
		boolean flag = false;
		
		terminal.setOutput("Please enter the ID of the provider you wish to add a service to: ");
		providerID = terminal.readInt(); 
		
		do{
			if(!providerController.Validateprovider(providerID)){
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
					
					providerController.AddService(service, providerID);
					terminal.setOutput("service added to provider: " + providerController.toString());
				}
			}
		}while(flag);
	}
	
	void runner(){
		terminal.setOutput("Hello, welcome to Chocohalics. Please enter your ID");
		int id = terminal.readInt();
		if(id<100000000 && id>0){ // they be member
			switch(memberController.ValidateMember(id)){
			case "VALID":
				terminal.setOutput("This member is valid"+System.lineSeparator());
				
				terminal.setOutput("Is this member finishing an appointment? (Y/N)");
				if(terminal.readText().toLowerCase().charAt(0) =='y'){
					//do something
				}
				return; //normally would use a break but in this case we want to infinite loop after this point...
				
			case "SUSPENDED":
				terminal.setOutput("This member has been suspended. They must pay their dues to have their membership reinstated."); 
				return;
				
			case "INVALID":
				terminal.setOutput("This member is not a part of ze plan!"); 
				return;
			}
				
				
		}else if(id>100000000 && id<200000000){ // they be provider
			if(providerController.Validateprovider(id)){
				
				providerControllerLoop: while(true){
					
					terminal.setOutput("What would you like to do:"
						+ System.lineSeparator() +"\t1: Interact with a provider account"
						+ System.lineSeparator() +"\t2: Interact with a member account"
						+ System.lineSeparator() +"\t3: Run a report"
						+ System.lineSeparator() +"\t4: Log Out");
	
					switch (terminal.readInt()) {
					
					case 1: // provider controller stuff
					
						terminal.setOutput("How would you like to interact with a provider account:"
								+ System.lineSeparator() +"\t1: Add a new provider"
								+ System.lineSeparator() +"\t2: Edit an existing provider"
								+ System.lineSeparator() +"\t3: Remove an existing provider"
								+ System.lineSeparator() +"\t4: Go Back");
						switch (terminal.readInt()) {
						case 1:
							
							break; 
						
						case 2:
							
							break;
							
						case 3:
							
							break;
							
						case 4: //go back
							
							break;
						default:
							break;
						}
						
					
						
						break;
					
					case 2: //member account stuff
						terminal.setOutput("How would you like to interact with a member account:"
								+ System.lineSeparator() +"\t1: Add a new member"
								+ System.lineSeparator() +"\t2: Edit an existing member"
								+ System.lineSeparator() +"\t3: Remove an existing member");
						//add switch
						
						break;
					
					case 3: //report running stuff
						terminal.setOutput("Which report would you like to run?"
								+ System.lineSeparator() +"\t1: Member Transaction Report"
								+ System.lineSeparator() +"\t2: Provider Transaction Report"
								+ System.lineSeparator() +"\t3: EFT Report"
								+ System.lineSeparator() +"\t3: Provider Services Provided Report");
						//add switch
						
						break;
					
					case 4:
						return;
					
					default: //You're a dumbass
						terminal.setOutput("You're a dumbass.");
						break;
					}
				}
			}else/*a ... Frozen reference*/{
				terminal.setOutput("Invalid providerID. Please try again.");
			}
				
			
		}
		else {
			terminal.setOutput("Invalid entry Please try again.");
			return;
		}
		
	}

}
