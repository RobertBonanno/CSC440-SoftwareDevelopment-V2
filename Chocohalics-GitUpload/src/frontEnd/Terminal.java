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
		try {
			String toReturn = IO.readText();
			IO.setOutput("");
			return toReturn;
		}catch(IllegalArgumentException e) {
			//e.printStackTrace();
			setOutput("Text too long, please shorten your message");
			return readText();
		}
		
	}
	
	/**
	 * passes the card input down the chain of responsibility to the correct IO class. Either GUI or Console. 
	 * @return: The input that was read from the card
	 * @throws TimeoutException 
	 */
	public int readInt() {
		try {
			int toReturn = IO.readInt();
			IO.setOutput("");
			return toReturn;
		} catch (TimeoutException e) {
			//e.printStackTrace();		//uncomment this line to reinstate timeone exceptions
			return readInt(); //ignores a timeout exception by calling recursively
							  //May cause a memory leak
		}
	}
	
	
	public double readDouble() {
		try {
			double toReturn = IO.readDouble();
			IO.setOutput("");
			return toReturn;
		} catch (TimeoutException e) {
			//e.printStackTrace();		//uncomment this line to reinstate timeone exceptions
			return readDouble(); //ignores a timeout exception by calling recursively
							  //May cause a memory leak
		}
	}
	
}
