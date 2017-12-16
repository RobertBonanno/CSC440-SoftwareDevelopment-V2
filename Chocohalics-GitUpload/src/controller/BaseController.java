package controller;

import java.util.concurrent.TimeoutException;

import backEnd.*;
import frontEnd.Terminal;

public class BaseController {

	protected Terminal terminal;
	
	BaseController(){
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
	


