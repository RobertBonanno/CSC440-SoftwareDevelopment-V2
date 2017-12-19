package backEnd;

import java.text.DecimalFormat;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class Service {

	String serviceName;
	int serviceID;
	double serviceFee;
	String serviceDescrp;
	
	/**
	 * constructor of address class, allows instance to be seen
	 */
	
	Service(){
		serviceName ="x";
		serviceID = 0;
		serviceFee = 0.0;
		serviceDescrp = "zztop";
	}
	
	/**
	 * acts as a constructor for when params are provided
	 * @param serviceName is a string value of service Name
	 * @param serviceID is an int value of the identifying service number
	 * @param serviceFee is a double value of the cost for the service
	 * @param serviceDescrp is a string value detailing what occurs during the service
	 */
	
	public Service(int serviceID, String serviceName, double serviceFee, String serviceDescrp){
		this.serviceName = serviceName;
		this.serviceID = serviceID;
		this.serviceFee = serviceFee;
		this.serviceDescrp = serviceDescrp;
	}

	@Override
	public String toString() {
		return "serviceName = " + serviceName + ", serviceID = " + idToString() + ", serviceFee = " + serviceFee
				+ ", serviceDescrp = " + serviceDescrp;
	}
////////////////////////////
	/**
	 * 
	 * @return name of service
	 */
	public String getName() {
		return serviceName;
	}
	
	
	/**
	 * 
	 * @return cost of service
	 */
	public double getFee() {
		return serviceFee;
	}

	
	/**
	 * 
	 * @return description of service
	 */
	public String getDescrp() {
		return serviceDescrp;
	}
	
	
	/**
	 * 
	 * @return unique ID of service
	 */
	public int getID() {
		return serviceID;
	}
	
	protected void setID(int id) {
		serviceID = id;
	}
	
	
	/**
	 * 
	 * @param serviceName is used to update a name change
	 */
	public void setName(String serviceName) {
		this.serviceName = serviceName;
	}
	//public void setServiceID(int serviceID) {
	//	this.serviceID = serviceID;
	//}
	
	/**
	 * 
	 * @param serviceFee is used to update a price change
	 */
	public void setFee(double serviceFee) {
		this.serviceFee = serviceFee;
	}
	
	/**
	 * 
	 * @param serviceDescrp replaces old service description with new description
	 */
	public void setDescrp(String serviceDescrp) {
		this.serviceDescrp = serviceDescrp;
	}
	
	protected Node getXMLElement(Document doc) {
		Element serviceElement = doc.createElement("Service");
		
		serviceElement.setAttribute("ServiceID", idToString());
		
		serviceElement.appendChild(getElementValue(doc, serviceElement, "ServiceName", serviceName));
		
		serviceElement.appendChild(getElementValue(doc, serviceElement, "ServiceFee", ""+serviceFee));
		
		serviceElement.appendChild(getElementValue(doc, serviceElement, "ServiceDescription", serviceDescrp));
		
		return serviceElement;
	}
	
	private Node getElementValue(Document doc, Element element, String name, String value){
		Element node = doc.createElement(name);
		node.appendChild(doc.createTextNode(value));
		return node;
	}
	
	public String idToString() {
		String toReturn = "";
		DecimalFormat dformat = new DecimalFormat("000000");
		toReturn+=dformat.format(serviceID);
		return toReturn;
	}
}
