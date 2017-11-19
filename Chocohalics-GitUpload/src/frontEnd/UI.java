package frontEnd;

import java.util.concurrent.TimeoutException;

public abstract class UI {
	
	String output;
	String input;
	
	public abstract void setOutput(String output);
	
	public abstract String readText();
	
	public abstract int readInt() throws TimeoutException; 
	

}
