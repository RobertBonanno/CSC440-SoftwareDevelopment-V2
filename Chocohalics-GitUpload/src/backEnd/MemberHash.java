package backEnd;
import java.io.File;
import java.sql.Date;
import java.sql.Time;
import java.util.HashMap;

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
import org.w3c.dom.NodeList;

public class MemberHash extends DataStoreHash<Member> {

	HashMap<Integer, Member> membersHash;
	
	public MemberHash(){
		super();
		membersHash = new HashMap<Integer, Member>() ;
	}
	
	/**
	 * 
	 * @param memberID: The numerical ID for a member. Typically 4-6 digits
	 * @param member
	 */

	
	public void add(String name, Address address){
		Integer id = generateID(); 
		Member newMember = new Member(name,address,id.intValue());
		membersHash.put(id, newMember); 
	}
	
	private Integer generateID(){
		Integer id; 
		while(true){
			id =(int) (Math.random()*1000.0); 
			if(membersHash.containsKey(id)){
				continue; 
			}
			break; 
		}
		return id;
	}

	@Override
	public void remove(int memberID){
		(membersHash.get(memberID)).setStatus(3); //1 = VALID 2 = SUSPENDED 3 = INVALID; limits typing errors in maintenance
	}

	/**
	 * 
	 * @param memberID: The numerical ID for a member. Typically 4-6 digits
	 * @return if member is found, calls and returns member class get status
	 */
	@Override
	public Member search(int memberID){
		if(membersHash.containsKey(memberID))
			return membersHash.get(memberID);
		else
			return null;
	}
	
	public String validate(int memberID){
		if(search(memberID).equals(null))
			return "INVALID";
		else
			return (membersHash.get(memberID)).getStatus();
	}
	
	/**
	 * 
	 * @param memberID
	 * @param member
	 * @return
	 */
	public String editMem(int memberID, Member member){
		if(membersHash.containsKey(memberID)){
			return "invalid";
		}
		else
			membersHash.put(memberID, member);
		return "edited";
	}

	
	/**
	 * Writes the content of the Member hash map to a file.
	 * FileName is typically of the form: "ChocAn Member List yyyy-mm-dd"
	 * 
	 */
	//@Override
	
	//Temporary method for testing
	public void add(Member mem) {
		membersHash.put(mem.getID(), mem);
	}
	
	

	@Override
	public String getDataHashType() {
		return "Member";
	}
	
	@Override
	public void readFromXML(String FileName) {
		try {
			File file = new File(FileName);
			
			DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			
			Document doc = dBuilder.parse(file);
			
			doc.getDocumentElement().normalize();
		
			NodeList nList = doc.getElementsByTagName("Member");
				
			for(int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				
				//for debugging
				System.out.println("Current element"+nNode.getNodeName());
				
				if(nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					Member member = new Member();
					Address address = new Address();
					
					member.setID(Integer.parseInt(eElement.getAttribute("id")));
					member.setName(eElement.getElementsByTagName("Name").item(0).getTextContent());
					
					String status = eElement.getElementsByTagName("Status").item(0).getTextContent();
					if(status.equals("VALID"))
						member.setStatus(1);
					else if(status.equals("SUSPENDED"))
						member.setStatus(2);
					else if(status.equals("INVALID"))
						member.setStatus(3);
					else;
					
					address.setStreet(eElement.getElementsByTagName("Street").item(0).getTextContent());
					address.setCity(eElement.getElementsByTagName("City").item(0).getTextContent());
					address.setState(eElement.getElementsByTagName("State").item(0).getTextContent());
					address.setZipCode(Integer.parseInt(eElement.getElementsByTagName("ZIP").item(0).getTextContent()));
					
					member.setAddress(address);
					
					System.out.println(member.toString());
					membersHash.put(member.getID(), member);
				}
			}
	}catch(Exception e) {
		e.printStackTrace();
	}
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
			
			
			for(Integer i : membersHash.keySet()){
				rootElement.appendChild(membersHash.get(i).getXMLElement(doc));
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

}
