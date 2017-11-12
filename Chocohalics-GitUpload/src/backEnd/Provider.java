package backEnd; //new

import java.util.ArrayList;

//import java.util.List;
public class Provider implements IDHolder{

	int ProviderIDNum;
	String ProviderName = "";
	Address WorkAddress = new Address();
	ArrayList ServicesOffered = new ArrayList();
	LogMethods<Service> util = new LogMethods(); 
	ServiceComparator useMe2 = new ServiceComparator();
	
	private Provider(){
		ProviderIDNum = 0;
		ProviderName = "x";
	}
	
	private Provider(int num, String name){
		ProviderIDNum = num;
		ProviderName = name;
	}
	
	@Override
	public int getIDNum() {
		return ProviderIDNum;
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

	public void setIDNum(int providerIDNum) {
		ProviderIDNum = providerIDNum;
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
	
	private void addService(Service service){
		if(util.listed(service, ServicesOffered, useMe2)){}
		ServicesOffered.add(service);
	}
}
