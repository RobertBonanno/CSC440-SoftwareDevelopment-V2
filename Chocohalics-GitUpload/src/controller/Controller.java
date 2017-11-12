package controller;

import java.util.Date;

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
		terminal = new Terminal();
	}
	
	//===========Provider Stuff=================
	public Provider AddProvider(){
		return provider;
	}
		
	public Provider EditProvider(){
			return provider;
	}
	
	public void CreateProviderDirectory(){
		
	}
		
	
	//==========Member Stuff=================
	public Member AddMember(){
		return member;
	}
	
	public Member DeleteMember(){
		return member;
	}
	
	public Member ValidateMember(){
		return member;
	}
	
	
	//==========Service Stuff====================
	/**
	 * Adds a new service to a service log.
	 * @return
	 */
	public Service AddService(){
		return service; 
	}
	
	public Service EditServiceLog(){
		return service;
	}
	
	
	//=============Terminal Stuff==================
	public String WriteToTerminal(){
		return "";
	}
	
	public void ReadFromTerminal(){
		
	}
	
	//============Transactions==================
	
	public void CreateTransaction (Member member, Date date, Provider provider, Service service, String comments){
		
	}
	
	public void AutomatedAccounting(){
		
	}
	
	public void WriteACMELog(){
		
	}
	
}
	


