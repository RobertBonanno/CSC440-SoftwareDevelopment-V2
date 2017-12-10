package frontEnd;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

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
	public String readText() throws IllegalArgumentException{
		String input = in.nextLine(); 
		if(input.length()>25)
			throw new IllegalArgumentException();
		return input;
	}

	@Override
	public int readInt() throws TimeoutException{
		int inputAttemptcount = 0;
		while(in.hasNext()){
			if(inputAttemptcount>4)
				break;
			try{
				return in.nextInt()+in.nextLine().length()*0; 
			} catch (InputMismatchException e){
				in.nextLine();
				System.out.println("Invalid input. Please try again:");
				inputAttemptcount++;
			}
		}
		throw new TimeoutException();
	}
	
	public void cleanUp(){
		in.nextLine();
	}

}
