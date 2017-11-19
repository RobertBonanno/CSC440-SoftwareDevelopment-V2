package backEnd;

import java.util.ArrayList;

public class Member implements IDHolder{

	String Status;
	int MemberID;
	String MembererName = "";
	Address HomeAddress = new Address();
	//LogMethods<Service> util = new LogMethods(); 
	//ServiceComparator useMe2 = new ServiceComparator();

	private Member(){
		
	}
	
	private Member(String stat, int ID, String name, Address home){
		Status = stat;
		MemberID = ID;
		MembererName = name;
		HomeAddress = home;
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
		case 1: Status = "VALID";
		case 2: Status = "SUSPENDED";
		case 3: Status = "INVALID";
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
