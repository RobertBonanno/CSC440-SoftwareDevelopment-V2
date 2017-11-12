package frontEnd;

import java.util.Scanner;

public class Console extends UI {
	
	Scanner in; 
	
	Console(){
		in = new Scanner(System.in);
	}
	
	@Override
	public void setOutput(String output) {
		System.out.println(output); 
		
	}
	
	@Override
	public String readInput() {
		return in.nextLine(); 
		
	}

	@Override
	public String readCard() {
		return in.nextLine(); 
		
	}

}
