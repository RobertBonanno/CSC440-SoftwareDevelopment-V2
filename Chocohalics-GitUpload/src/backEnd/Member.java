package backEnd;

public class Member extends IDHolder{

	String Status;
	
	
	
	/**
	 * 
	 * @return string state of member status
	 */
	public String getStatus() {
		return Status;
	}
	
	
	/**
	 * 
	 * @param status is an attribute unique to members that can take one of multiple states
	 * 	()
	 *  ()
	 *  ()
	 */
	public void setStatus(String status) {
			Status = status;
	}
	
	private void seekService(){
		
	}
}
