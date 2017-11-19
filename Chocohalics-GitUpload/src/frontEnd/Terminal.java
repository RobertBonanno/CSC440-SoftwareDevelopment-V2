package frontEnd;

import java.util.concurrent.TimeoutException;

public class Terminal {
	
	UI IO; 
	
	/**
	 * This constructor instantiates the terminal and determines which IO type will be used.
	 * @param ioType: Either Console or GUI
	 */
	public Terminal(String ioType){
		IOType(ioType); 
	}
	
	/**
	 * Switches between having the display on the Console or GUI. It instantiates IO as the correct UI type. 
	 * @param ioType: Either Console or GUI
	 */
	public void IOType(String ioType){
		switch(ioType){
		case "Console":
			IO = new Console();  
			break;
		case "GUI":
			IO = new GUI();
			break;
		}
	}
	/**
	 * passes the output down the chain of responsibility to the correct IO class. Either GUI or Console. 
	 * @param output: The text that is to be printed on screen. 
	 */
	public void setOutput(String output) {
		IO.setOutput(output);
	}
	/**
	 * passes the user input down the chain of responsibility to the correct IO class. Either GUI or Console. 
	 * @return: The input that was entered by the user.
	 */
	public String readText() {
		return IO.readText();
		
	}
	
	/**
	 * passes the card input down the chain of responsibility to the correct IO class. Either GUI or Console. 
	 * @return: The input that was read from the card
	 * @throws TimeoutException 
	 */
	public int readInt() throws TimeoutException {
		return IO.readInt();
	}
	
}
