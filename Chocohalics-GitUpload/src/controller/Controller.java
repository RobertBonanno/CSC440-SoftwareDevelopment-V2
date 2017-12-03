package controller;

import java.util.Date;
import java.util.concurrent.TimeoutException;

import backEnd.*;
import frontEnd.Terminal;

public class Controller {
	
	MemberLog memberLog;
	ProviderLog providerLog; 
	ReceiptLog receiptLog;
	ServiceLog serviceLog;
	Terminal terminal;
	
	Controller(){
		memberLog = new MemberLog();
		providerLog = new ProviderLog();
		receiptLog = new ReceiptLog();
		serviceLog = new ServiceLog();
		terminal = new Terminal("Console");
	}
	
	//===========Provider Stuff=================
	public Provider AddProvider(){
		return null;
	}
		
	public Provider EditProvider(){
		return null;
	}
	
	public void CreateProviderDirectory(){
		
	}
		
	
	//==========Member Stuff=================
	public Member AddMember(){
		return null;
	}
	
	public Member DeleteMember(){
		return null;
	}
	
	public String ValidateMember(int uid){
		Member temp;
		
		try{
			temp = memberLog.search(uid);
			switch(temp.getStatus()){
			case "Valid": return "Welcome";
			case "Suspended": return "Your membership has been suspended. Pay us to reinstate your membership.";
			}
		
		}catch(IndexOutOfBoundsException e){
			//e.printStackTrace();
		}
		
		return "You are not a member yet. Please consider joining us to receive the benefits of being a member.";

	}
	
	
	//==========Service Stuff====================
	/**
	 * Adds a new service to a service log.
	 * @return
	 */
	public Service AddService(){
		return null; 
	}
	
	public Service EditServiceLog(){
		return null;
	}
	
	
	//=============Terminal Stuff==================
	public void WriteToTerminal(String message){
		terminal.setOutput(message);
	}
	
	public int ReadIntFromTerminal() throws TimeoutException{
		return terminal.readInt();
	}
	
	public String ReadFromTerminal(){
		return terminal.readText();
	}
	
	//============Transactions==================
	
	public void CreateTransaction (Member member, Date date, Provider provider, Service service, String comments){
		
	}
	
	public void AutomatedAccounting(){
		
	}
	
	public void WriteACMELog(){
		
	}
	
}
	


