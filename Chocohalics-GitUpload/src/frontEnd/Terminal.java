package frontEnd;

public class Terminal {
	
	String UIType;
	UI IO; 

	public Terminal(String ioType){
		IOType(ioType); 
	}
	
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
	 * pushes the output down the chain of responsibility to the correct IO class. Either GUI or Console. 
	 * @param output: The text that is to be printed on screen. 
	 */
	public void setOutput(String output) {
		IO.setOutput(output);
	}

	public String readInput() {
		return IO.readInput();
		
	}

	public String readCard() {
		return IO.readCard();
	}
	
}
