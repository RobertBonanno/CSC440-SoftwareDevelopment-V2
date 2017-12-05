package backEnd; 

import java.util.ArrayList;

//import java.util.List;
public class Provider implements IDHolder{

	int ProviderID;
	String ProviderName = "";
	Address WorkAddress = new Address();
	ArrayList ServicesOffered = new ArrayList();
	LogMethods<Service> util = new LogMethods(); 
	ServiceComparator useMe2 = new ServiceComparator();
	
	private Provider(){
		ProviderID = 0;
		ProviderName = "x";
	}
	
	Provider(int ID, String name){
		ProviderID = ID;
		ProviderName = name;
	}
	
	//private void addService(Service service){
	//	if(util.listed(service, ServicesOffered, useMe2)){}
	//	ServicesOffered.add(service);
	//}
	
	protected void addService(Service service){
		if(util.listed(service, ServicesOffered, useMe2)){}
		ServicesOffered.add(service);
	}	

///////////////////////////////////////////
	@Override
	public int getID() {
		return ProviderID;
	}

	@Override
	public String getName() {
		return ProviderName;
	}

	@Override
	public Address getAddress() {
		return WorkAddress;
	}

	public ArrayList getServicesOffered() {
		return ServicesOffered;
	}

	public void setIDNum(int providerID) {
		this.ProviderID = providerID;
	}

	public void setName(String providerName) {
		ProviderName = providerName;
	}

	public void setWorkAddress(Address workAddress) {
		WorkAddress = workAddress;
	}

	public void setServicesOffered(ArrayList servicesOffered) {
		ServicesOffered = servicesOffered;
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
	public void setAddress(Address iDHaddress) {
		// TODO Auto-generated method stub
		
	}
	
}
