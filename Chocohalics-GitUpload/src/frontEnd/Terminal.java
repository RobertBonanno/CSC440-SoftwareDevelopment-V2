package frontEnd;

public class Terminal {
	
	String UIType;
	UI IO; 

	public void IOType(String output){
		switch(UIType){
		case "Console":
			Console.setOutput(output); 
			break;
		case "GUI":
			GUI.setOutput(output);
			break;
		}
	}
}
