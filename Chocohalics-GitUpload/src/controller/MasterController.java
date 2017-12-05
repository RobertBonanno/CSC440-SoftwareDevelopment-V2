package controller;

import java.util.concurrent.TimeoutException;

import backEnd.*;
import frontEnd.Terminal;

public class MasterController {
	
	ReceiptLog receiptLog;
	ServiceLog serviceLog;
	protected Terminal terminal;
	
	MasterController(){
		receiptLog = new ReceiptLog();
		serviceLog = new ServiceLog();
		terminal = new Terminal("Console");
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

	
}
	


