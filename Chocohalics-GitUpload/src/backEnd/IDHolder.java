package backEnd;
import java.lang.String;
public interface IDHolder {

	//String IDHname;
	//String IDHEmail;
	//String Status;
	
	//int IDHIDnum;
	
	//Address IDHaddress;


	public String getName(); 

	public String getEmail();

	//public String getStatus() {
	//	return Status;
	//}
	
	public Address getAddress();
	
	public int getID();

	public void setName(String iDHname);
	
	public void setEmail(String iDHEmail);
	
	//public void setStatus(String status) {
	//	Status = status;
	//}

	public void setAddress(Address iDHaddress);

	
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
