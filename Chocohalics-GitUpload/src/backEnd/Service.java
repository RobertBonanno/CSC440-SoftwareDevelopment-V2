package backEnd;

import java.util.Random;

public class Service {

	String serviceName;
	int serviceID;
	double serviceFee;
	String serviceDescrp;
	Random id = new Random();
	
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
	
	Service(int serviceID, String serviceName, double serviceFee, String serviceDescrp){
		this.serviceName = serviceName;
		this.serviceID = serviceID;
		this.serviceFee = serviceFee;
		this.serviceDescrp = serviceDescrp;
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
}
