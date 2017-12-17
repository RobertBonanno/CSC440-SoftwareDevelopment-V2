package backEnd;

import java.util.Date;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class Receipt {
	
	int identifier;
	Service service;
	Member member; 
	Provider provider;
	Date dateOfService;
	String comments;
	
	/**
	 * @param member: The name of the member who is receiving the service
	 * @param provider: The name of the provider who is providing the service
	 * @param dateOfService: The date in which the service was performed
	 * @param service: The name of the service provided.
	 * @param comments: Comments about the service.
	 */
	public Receipt(int identifier, Service service, Member member, Provider provider, Date dateOfService, String comments) {
		super();
		this.identifier = identifier;
		this.member = member;
		this.provider = provider;
		this.dateOfService = dateOfService;
		this.service = service;
		this.comments = comments;
	}
	

	@Override
	public String toString() {
		return "Service: " + service + "\nService recipant: " + member + "\n Service provider: " + provider + "\n Date of Service: " + dateOfService
				 + "\ncomments: " + comments;
	}
	
	
	
	//--------------------Getters----------------------
	public Member getMember() {
		return member;
	}
	public Provider getProvider() {
		return provider;
	}
	public Date getDateOfService() {
		return dateOfService;
	}
	public Service getService() {
		return service;
	}
	public String getComments() {
		return comments;
	}
	public int getIdentifier() {
		return identifier;
	}


	//---------------------Setters--------------------
	public void setMember(Member member) {
		this.member = member;
	}
	public void setProvider(Provider provider) {
		this.provider = provider;
	}
	public void setDateOfService(Date dateOfService) {
		this.dateOfService = dateOfService;
	}
	public void setService(Service service) {
		this.service = service;
	}
	public void setComments(String comments) {
		this.comments = comments;
	} 
	public void setIdentifier(int id) {
		this.identifier = id;
	}
	
	//--------------------XML Writing helper-----------
	protected Node getXMLElement(Document doc) {
		Element receiptElement = doc.createElement("Receipt");
		
		receiptElement.setAttribute("ReceiptID", identifier+"");
		
		receiptElement.appendChild(service.getXMLElement(doc));
		receiptElement.appendChild(member.getXMLElement(doc));
		receiptElement.appendChild(provider.getXMLElement(doc));
		receiptElement.appendChild(getElementValue(doc, receiptElement, "Date", dateOfService.toString()));
		receiptElement.appendChild(getElementValue(doc, receiptElement, "Comments", comments));
		
		return receiptElement;
	}

	private Node getElementValue(Document doc, Element element, String name, String value) {
		Element node = doc.createElement(name);
		node.appendChild(doc.createTextNode(value));
		return node;
	}
	
}
