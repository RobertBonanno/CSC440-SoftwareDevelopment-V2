package controller;

import java.util.concurrent.TimeoutException;

import backEnd.*;
import frontEnd.Terminal;

public class MasterController {
	
	ReceiptHash receiptHash;
	ServiceHash serviceHash;
	protected Terminal terminal;
	
	MasterController(){
		receiptHash = new ReceiptHash();
		serviceHash = new ServiceHash();
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
	


