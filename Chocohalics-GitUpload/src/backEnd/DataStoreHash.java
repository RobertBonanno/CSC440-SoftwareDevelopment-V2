package backEnd;
import java.util.HashMap;

import java.io.File;
import java.sql.Date;
import java.sql.Time;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.UserDataHandler;

public abstract class DataStoreHash<T> {
	
	HashMap<Integer, T> map;

	public abstract void add(String name, Address address);
		
	public abstract void remove(int ID);
	
	public abstract Object search(int ID);
	
	public abstract String getDataHashType();
	
	public abstract void writeToDisk();
	
	public void writeToXML(){
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
	
	protected abstract Node getXMLElement(Document doc, Integer i);
	
	protected Node getElementValue(Document doc, Element element, String name, String value){
		Element node = doc.createElement(name);
		node.appendChild(doc.createTextNode(value));
		return node;
	}
	
	public void readFromXML(String FileName){
		try {
			File file = new File(FileName);
			
			DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			
			Document doc = dBuilder.parse(file);
			
			doc.getDocumentElement().normalize();
		
			switch (getDataHashType()) {
			case "Member":
				
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
						map.put(member.getID(), (T)member);
					}
				}
				
				break;
				
			case "Provider":
				
				
				break;
				
			case "Service":
				
				
				break;
				
			case "Receipt":
				
				
				break;

			default:
				break;
			}
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	protected abstract void loadNote(NodeList nodeList) throws Exception; 
	
	
}
