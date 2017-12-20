package backEnd;

import java.text.DecimalFormat;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
public class Member implements Comparable<Member>,IDHolder{

	String Status;
	String MemberName;
	int MemberID;
	Address HomeAddress;

	public Member(){
		Status = "INVALID";
		MemberName = "";
		MemberID = -1;
		HomeAddress = null;
	}
	

	public Member(String MemberName, Address homeAddress, int id){
		setStatus(1);
		MemberID = id; 
		this.MemberName = MemberName;
		HomeAddress = homeAddress;
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
	 * @param status: attribute unique to members that can take one of the following states:
	 * case 1 sets status to "VALID" 
	 * case 2 sets status to "SUSPENDED"
	 * case 3 sets status to "INVALID"
	 *  limits typing errors in maintenance
	 */
	public void setStatus(int status) {
		switch(status){
		case 1: this.Status = "VALID";
			break;
		case 2: this.Status = "SUSPENDED";
			break;
		case 3: this.Status = "INVALID";
			break;
		}
		
	
}
	
	@Override
	public String getName() {
		return MemberName;
	}

	@Override
	public Address getAddress() {
		return HomeAddress;
	}


	@Override
	public int getID() {
		return MemberID;
	}
	
	protected void setID(int id) {
		MemberID = id;
	}


	@Override
	public void setName(String iDHname) {
		MemberName = iDHname;
		
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


	@Override
	public String toString() {
		return MemberName+"-" + idToString() + "- Has a status of "+Status + System.lineSeparator() + "HomeAddress:" + HomeAddress;
	}
	
	protected Node getXMLElement(Document doc) {
		Element memberElement = doc.createElement("Member");
		
		//set id attribute
		memberElement.setAttribute("id", idToString());
		
		//create name attribute
		memberElement.appendChild(getElementValue(doc, memberElement, "Name", getName()));
		
		//create status attribute
		memberElement.appendChild(getElementValue(doc, memberElement, "Status", getStatus()));
		
		//create address street name attribute	
		memberElement.appendChild(HomeAddress.getXMLElement(doc));
		
		return memberElement;
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
		toReturn+=dformat.format(MemberID);
		return toReturn;
	}


	@Override
	public int compareTo(Member o) {
		return MemberName.compareTo(o.getName());
	}
	
}
