package backEnd;

import java.util.*;
public class Member implements IDHolder{

	String Status;
	String MembererName = "";
	int MemberID;
	Address HomeAddress = new Address();
	Random id = new Random();
	//LogMethods<Service> util = new LogMethods(); 
	//ServiceComparator useMe2 = new ServiceComparator();

	public Member(){
		
	}
	

<<<<<<< HEAD
	public Member(String membererName, Address homeAddress) {
		super();
=======
	public Member(/*String status, int memberIDNum,*/ String membererName, Address homeAddress) {
	public Member(String membererName, Address homeAddress) {/*String status, int memberIDNum, > code shouldn't be needed, id and status shouls be automated by constructor for simplicity/integrity*/ 
		super();
		setStatus(1);
		//MemberID = id.nextInt();
		//MemberID = id.nextInt(); to be used when constructor is known to work, possible issue with hitting same number
		MemberID = 7501;
>>>>>>> Doren-11-19
		MembererName = membererName;
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
	//public void setStatus(String status) {
	//		Status = status;
	//}
	
	public void setStatus(int status) {
		switch(status){
		case 1: this.Status = "VALID";
		case 2: this.Status = "SUSPENDED";
		case 3: this.Status = "INVALID";
		}
		
	
}
	
	private void seekService(){
		
	}
	
	// public String toString
	
///////////////////////////////////////////////	
	
	@Override
	public String getName() {
		return MembererName;
	}

	@Override
	public Address getAddress() {
		return HomeAddress;
	}


	@Override
	public int getID() {
		return MemberID;
	}


	@Override
	public void setName(String iDHname) {
		MembererName = iDHname;
		
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
		return MembererName+" [Status=" + Status + ", MemberID=" + MemberID + ", HomeAddress=" + HomeAddress + "]";
	}
	
	
}
