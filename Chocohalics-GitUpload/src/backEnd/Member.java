package backEnd;	//new

import java.util.ArrayList;

public class Member implements IDHolder{

	String Status;
	int MemberIDNum;
	String MembererName = "";
	Address HomeAddress = new Address();
	//LogMethods<Service> util = new LogMethods(); 
	//ServiceComparator useMe2 = new ServiceComparator();

	private Member(){
		
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
	 * 	()
	 *  ()
	 *  ()
	 */
	public void setStatus(String status) {
			Status = status;
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
	public int getIDNum() {
		return MemberIDNum;
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
