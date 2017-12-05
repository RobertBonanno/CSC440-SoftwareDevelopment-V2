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
	

	public Member(/*String status, int memberIDNum,*/ String membererName, Address homeAddress) {
		super();
		setStatus(1);
		//MemberID = id.nextInt();
		MemberID = 7501;
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
	 * @param status is an attribute unique to members that can take one of multiple states
	 * 	switch case verz is more secure/reliable
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
}
