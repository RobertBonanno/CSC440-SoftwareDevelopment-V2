package backEnd; 

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

//import java.util.List;
public class Provider implements IDHolder{

	int ProviderID;
	String ProviderName;
	Address WorkAddress;
	ArrayList<Service> ServicesOffered;
	LogMethods<Service> util; 
	ServiceComparator useMe2;
	
	public Provider(){
		ProviderID = 0;
		ProviderName = "x";
		WorkAddress = new Address();
		ServicesOffered = new ArrayList<Service>();
		util = new LogMethods<Service>();
		useMe2 = new ServiceComparator();
	}
	

	Provider(String name, Address address, int ID){
		ProviderID = ID;
		ProviderName = name;
		WorkAddress = address; 
		ServicesOffered = new ArrayList<Service>(); 
		util = new LogMethods<Service>();
		useMe2 = new ServiceComparator();
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

	public ArrayList<Service> getServicesOffered() {
		
		return ServicesOffered;
		//return ServicesOffered;
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

	public void setServicesOffered(ArrayList<Service> servicesOffered) {
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


	protected Node getXMLElement(Document doc) {
		Element providerElement = doc.createElement("Provider");
		
		//set id attribute
		providerElement.setAttribute("id", ProviderID+"");
		
		//create name element
		providerElement.appendChild(getElementValue(doc, providerElement, "Name", ProviderName));
		
		//create address element
		providerElement.appendChild(WorkAddress.getXMLElement(doc));
		
		//add service elements
		for(Service service : ServicesOffered) {
			providerElement.appendChild(service.getXMLElement(doc));
		}
		
		return providerElement;
	}
	
	private Node getElementValue(Document doc, Element element, String name, String value){
		Element node = doc.createElement(name);
		node.appendChild(doc.createTextNode(value));
		return node;
	}
	
}
