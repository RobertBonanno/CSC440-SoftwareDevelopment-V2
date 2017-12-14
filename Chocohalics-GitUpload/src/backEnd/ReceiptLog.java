package backEnd;
import java.io.File;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
public class ReceiptLog extends DataStore {

	ArrayList<Receipt> receiptList; 
	
	/**
	 * Constructor for the ReceiptLog. 
	 * Calls the DataStore constructor
	 * then instantiates the recieptList ArrayList. 
	 */
	public ReceiptLog() {
		super();
		receiptList = new ArrayList<Receipt>(); 
		
	}
	
	/**
	 * Sorts the receipt List by the date of the service. 
	 */
	public void sort() {	
		
	}
	
	private Integer generateID(){
		Integer id; 
		while(true){
			id =(int) (Math.random()*1000.0); 
			if(receiptList.contains(id)){
				continue; 
			}
			break; 
		}
		return id;
	}
	
	/**
	 * Clears the out-dated receipt log. 
	 * Every Friday, the log is written to disk and then cleared.
	 * All of the data from the previous week is deleted.  
	 */
	public void clear(){
		
	}
	
	public void writeToDisk() {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		
		Date date = new Date(System.currentTimeMillis());
		Time time = new Time(System.currentTimeMillis());
		String hashType = "Receipt";
		
		try{
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.newDocument();
			//add element
			Element rootElement = doc.createElement("ChocAn_"+hashType);
			doc.appendChild(rootElement);
			for(Integer i : map.keySet()){
				rootElement.appendChild(getXMLElement(doc, i));
			}
			
			//for output to file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			//for pretty print
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource source = new DOMSource(doc);
			
			//write to file
			StreamResult file = new StreamResult(new File("ChocAn"+hashType+"_"+date.toString()+"_"+time.getHours()+"-"+time.getMinutes()+".XML"));
			
			transformer.transform(source, file);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	private Node getXMLElement(Document doc, Integer i) {
		Element memberElement = doc.createElement("Member");
		Receipt member = receiptList.get(i.intValue());
		//set id attribute
		memberElement.setAttribute("id", member.getID()+"");
		
		//create name attribute
		memberElement.appendChild(super.getElementValue(doc, memberElement, "Name", member.getName()));
		
		//create status attribute
		memberElement.appendChild(super.getElementValue(doc, memberElement, "Status", member.getStatus()));
		
		//create address street name attribute	
		memberElement.appendChild(super.getElementValue(doc, memberElement, "Street", member.getAddress().getStreet() ));
		
		//create address city attribute
		memberElement.appendChild(super.getElementValue(doc, memberElement, "City" , member.getAddress().getCity()));
		
		//create address state attribute
		memberElement.appendChild(super.getElementValue(doc, memberElement, "State", member.getAddress().getState()));
		
		//create address ZIP attribute
		memberElement.appendChild(super.getElementValue(doc, memberElement, "ZIP", ""+member.getAddress().getZipCode()));
		
		return memberElement;
	}

	@Override
	public void search(String identifier) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void add(Receipt receipt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object remove(String identifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
	
}