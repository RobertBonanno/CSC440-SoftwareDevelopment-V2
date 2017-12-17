package controller;

import java.util.Date;
import java.util.Map.Entry;

import backEnd.Member;
import backEnd.Provider;
import backEnd.ReceiptHash;
import backEnd.Service;

public class TransactionController extends BaseController{
	
	ReceiptHash receiptHash;
	
	public TransactionController() {
		super();
		receiptHash = new ReceiptHash(); 
		
	}
	//============Transactions==================
	
	public void CreateTransaction(Service service, Member member, Provider provider){
		
		Date date = new Date(System.currentTimeMillis()); 
		String comments;
		
		
		terminal.setOutput("Please enter in any comments about the service: ");
		comments = terminal.readText(); 
		
		receiptHash.add(service, member, provider, date, comments);
		
	}
	
	public void AutomatedAccounting(){
		
		
		
	}
	
	public void WriteACMELog(){
		
	}
	
	public void memberReport(){
		for(Entry<Integer, Receipt> entry : receiptHash.entrySet()){
			
		}
		//ittereate through the receipt hash
		//build an array for each member
		//fill each array with service date, name, and provider
		//organize it by date of service 
		
		
	}
	
	public void providerReport(){
		
	}
	
	public void summaryReport(){
		
	}
	
	public void EFTReport(){
		
	}

}