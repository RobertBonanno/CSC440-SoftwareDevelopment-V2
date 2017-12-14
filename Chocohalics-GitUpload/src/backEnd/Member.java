package backEnd;

import java.util.*;
public class Member implements IDHolder{

	String Status;
	String MemberName;
	int MemberID;
	Address HomeAddress;
	//LogMethods<Service> util = new LogMethods(); 
	//ServiceComparator useMe2 = new ServiceComparator();

	public Member(){
		Status = "INVALID";
		MemberName = "";
		MemberID = -1;
		HomeAddress = null;
	}
	

	public Member(String MemberName, Address homeAddress, int id){
		setStatus(1);
		MemberID = id; 
		this.MemberName = MemberName;
		HomeAddress = homeAddress;
	}


	/**
	 * 
	 * @return string state of member status
	 */
	public String getStatus() {
		return Status;
	}
	
	
	/**
	 * 
	 * @param status: attribute unique to members that can take one of the following states:
	 * case 1 sets status to "VALID" 
	 * case 2 sets status to "SUSPENDED"
	 * case 3 sets status to "INVALID"
	 *  limits typing errors in maintenance
	 */
	public void setStatus(int status) {
		switch(status){
		case 1: this.Status = "VALID";
			break;
		case 2: this.Status = "SUSPENDED";
			break;
		case 3: this.Status = "INVALID";
			break;
		}
		
	
}
	
	private void seekService(){
		
	}
	
	// public String toString
	
///////////////////////////////////////////////	
	
	@Override
	public String getName() {
		return MemberName;
	}

	@Override
	public Address getAddress() {
		return HomeAddress;
	}


	@Override
	public int getID() {
		return MemberID;
	}
	
	protected void setID(int id) {
		MemberID = id;
	}


	@Override
	public void setName(String iDHname) {
		MemberName = iDHname;
		
	}

	@Override
	public void setAddress(Address iDHaddress) {
		HomeAddress = iDHaddress;
		
	}
	
	@Override
	public String getEmail() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void setEmail(String iDHEmail) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public String toString() {
		return MemberName+" [Status=" + Status + ", MemberID=" + MemberID + ", HomeAddress=" + HomeAddress + "]";
	}
	
	
}
