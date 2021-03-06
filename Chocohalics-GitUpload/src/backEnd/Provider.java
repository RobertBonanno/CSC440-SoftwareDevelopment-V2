package backEnd; 

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

//import java.util.List;
public class Provider implements Comparable<Provider>, IDHolder{

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
	

	public Provider(String name, Address address, int ID){
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
	
	public void addService(Service service){
		if(!util.listed(service, ServicesOffered, useMe2)){}
		ServicesOffered.add(service);
		Collections.sort(ServicesOffered);
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

	@Override
	public void setAddress(Address iDHaddress) {
		WorkAddress = iDHaddress;
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
	public String toString() {
		return "Provider [ProviderID = " + idToString() + ", ProviderName = " + ProviderName + ", WorkAddress = " + WorkAddress
				+ ", ServicesOffered = " + ServicesOffered + "]";
	}


	protected Node getXMLElement(Document doc) {
		Element providerElement = doc.createElement("Provider");
		
		//set id attribute
		providerElement.setAttribute("ProviderID", idToString());
		
		//create name element
		providerElement.appendChild(getElementValue(doc, providerElement, "ProviderName", ProviderName));
		
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


	@Override
	public String idToString() {
		String toReturn = "";
		DecimalFormat dformat = new DecimalFormat("000000000");
		toReturn+=dformat.format(ProviderID);
		return toReturn;
	}

	@Override
	public int compareTo(Provider provider) {
		return ProviderName.compareTo(provider.getName());
	}
	
}
