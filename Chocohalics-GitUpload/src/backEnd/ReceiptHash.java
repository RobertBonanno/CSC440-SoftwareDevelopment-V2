package backEnd;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.sql.Time;
import java.util.HashMap;
import java.util.Map.Entry;

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
public class ReceiptHash extends DataStoreHash<Receipt> {

	HashMap<Integer, Receipt> receiptList; 
	
	/**
	 * Constructor for the ReceiptLog. 
	 * Calls the DataStore constructor
	 * then instantiates the recieptList ArrayList. 
	 */
	public ReceiptHash() {
		super();
		receiptList = new HashMap<Integer, Receipt>(); 
		
	}
	
	/**
	 * Clears the out-dated receipt log. 
	 * Every Friday, the log is written to disk and then cleared.
	 * All of the data from the previous week is deleted.  
	 */
	public void clear(){
		writeToXML();
		receiptList = new HashMap<Integer, Receipt>();
	}
	
	protected Node getXMLElement(Document doc, Integer i) {
		Element receiptElement = doc.createElement("Receipt");
		Receipt receipt = receiptList.get(i.intValue());
		//set id attribute
		receiptElement.setAttribute("id", receipt.getIdentifier()+"");
		
		//Add member element	
		receiptElement.appendChild(receipt.getMember().getXMLElement(doc));
		
		//add provider element
		receiptElement.appendChild(receipt.getProvider().getXMLElement(doc));
		
		//create address street name attribute	
		receiptElement.appendChild(receipt.getService().getXMLElement(doc));
		
		return receiptElement;
	}


	public void add(Integer id, Service service, Member member, Provider provider, Date dateOfService, String comments) {
		Receipt receipt = new Receipt(id.intValue(), service, member, provider, dateOfService, comments);
		receiptList.put(id, receipt);
	}

	@Override
	public void remove(int ID) {
		receiptList.remove(ID);
	}

	@Override
	public Receipt search(int ID) {
		if(receiptList.containsKey(ID))
			return receiptList.get(ID);
		return null;
	}
	
	public ArrayList<Entry<Integer, ArrayList<Receipt>>> transformToProviderKey (){
		HashMap<Integer, ArrayList<Receipt>> memberOrderedHash = new HashMap<Integer, ArrayList<Receipt>>();
		
		for(Receipt receipt : receiptList.values()){
			int id = receipt.getProvider().getID();
			if(memberOrderedHash.get(id) == null){
				ArrayList<Receipt> temp = new ArrayList<Receipt>();
				temp.add(receipt);
				memberOrderedHash.put(id,temp);
			} else{
				ArrayList<Receipt> temp = memberOrderedHash.get(id);
				System.out.println(temp);
				temp.add(receipt);
				Collections.sort(temp);
			}
		}
		
		ArrayList<Entry<Integer, ArrayList<Receipt>>> entries = new ArrayList<Entry<Integer, ArrayList<Receipt>>>();
		
		for(Entry<Integer, ArrayList<Receipt>> entry : memberOrderedHash.entrySet()){
			entries.add(entry);
		}
		
		entries.sort(new MemberReceiptComparator());
		
		return entries;
	}
	
	public ArrayList<Entry<Integer, ArrayList<Receipt>>> transformToMemberKey (){
		HashMap<Integer, ArrayList<Receipt>> memberOrderedHash = new HashMap<Integer, ArrayList<Receipt>>();
		
		for(Receipt receipt : receiptList.values()){
			int id = receipt.getMember().getID();
			if(memberOrderedHash.get(id) == null){
				ArrayList<Receipt> temp = new ArrayList<Receipt>();
				temp.add(receipt);
				memberOrderedHash.put(id,temp);
			} else{
				ArrayList<Receipt> temp = memberOrderedHash.get(id);
				System.out.println(temp);
				temp.add(receipt);
				Collections.sort(temp);
			}
		}
		
		ArrayList<Entry<Integer, ArrayList<Receipt>>> entries = new ArrayList<Entry<Integer, ArrayList<Receipt>>>();
		
		for(Entry<Integer, ArrayList<Receipt>> entry : memberOrderedHash.entrySet()){
			entries.add(entry);
		}
		
		entries.sort(new MemberReceiptComparator());
		
		return entries;
	}

	@Override
	public String getDataHashType() {
		return "Receipt";
	}

	@Override
	public void writeToXML() {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		
		Date date = new Date(System.currentTimeMillis());
		Time time = new Time(System.currentTimeMillis());
		String hashType = getDataHashType();
		
		try{
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.newDocument();
			//add element
			Element rootElement = doc.createElement("ChocAn_"+hashType);
			doc.appendChild(rootElement);
			for(Integer i : receiptList.keySet()){
				rootElement.appendChild(getXMLElement(doc, i));
			}
			
			//for output to file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			//for pretty print
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource source = new DOMSource(doc);
			
			//write to file
			@SuppressWarnings("deprecation")
			StreamResult file = new StreamResult(new File("ChocAn"+hashType+"_"+date.toString()+"_"+time.getHours()+"-"+time.getMinutes()+".XML"));
			
			transformer.transform(source, file);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void readFromXML(String FileName) {
		System.err.println("I ain't reading any receipt logs... Please ask your developer really nicely or buy them dinner to implement this function.");
		
	}
	
}