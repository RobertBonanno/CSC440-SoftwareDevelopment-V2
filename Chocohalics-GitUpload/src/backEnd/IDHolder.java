package backEnd;
import java.lang.String;
abstract class IDHolder {

	String IDHname;
	String IDHEmail;
	//String Status;
	
	int IDHIDnum;
	
	Address IDHaddress;

	
	
	
	
	public String getName() {
		return IDHname;
	}

	public String getEmail() {
		return IDHEmail;
	}

	//public String getStatus() {
	//	return Status;
	//}
	
	public Address getAddress() {
		return IDHaddress;
	}	
	
	public int getIDnum() {
		return IDHIDnum;
	}

	public void setName(String iDHname) {
		IDHname = iDHname;
	}	

	public void setEmail(String iDHEmail) {
		IDHEmail = iDHEmail;
	}	
	
	//public void setStatus(String status) {
	//	Status = status;
	//}

	public void setAddress(Address iDHaddress) {
		IDHaddress = iDHaddress;
	}

	
/*	request service
 *  payInvoice
 *  
*/
	
/*
	public void setIDHIDnum(int iDHIDnum) {
		IDHIDnum = iDHIDnum;
	}
*/
}
