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

public class ProviderHash extends DataStoreHash<Provider>{

	HashMap<Integer, Provider> providersHash;
	
	/**
	 * 
	 * @param name <= 25 char 
	 * @param address object
	 *  adds provider to providerhash 
	 */
	public void add(String name, Address address) {
		Integer id = generateID(); 
		Provider newprovider = new Provider(name,address,id.intValue());
		providersHash.put(id, newprovider); 
		
	}
	
	/**
	 * 
	 * @return ensures unique Integer ID is created in (T)hash
	 */
	private Integer generateID(){
		Integer id; 
		while(true){
			id =(int) (Math.random()*1000.0); 
			if(providersHash.containsKey(id)){
				continue; 
			}
			break; 
		}
		return id;
	}

	public void remove(int providerID){
		providersHash.put(providerID, null);
	}
	
	public Provider search(int providerID){
		if(providersHash.get(providerID) == null){
			return null;
		}
		else
		return providersHash.get(providerID);
		//return "found";//((Provider)providersHash.get(providerID));
	}
	
	public String editProv(int providerID, Service service){
		if(providersHash.get(providerID) == null){
			return "INVALID";
		}
		else
			(providersHash.get(providerID)).addService(service);
		return "service added";
	}
	
	public boolean validate(int providerID){
		if(search(providerID).equals(null))
			return false;
		else
			return true;
	}
	
	/**
	 *  this method only serves for the test case, sould be replaced with a read from disc for security reasons
	 * @param providerID
	 * @param provider
	 */
	public void put(int providerID, Provider provider){
		providersHash.put(providerID, provider);
	}

	@Override
	public String getDataHashType() {
		return "Provider";
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
			
			
			for(Integer i : providersHash.keySet()){
				rootElement.appendChild(providersHash.get(i).getXMLElement(doc));
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
		try {
			File file = new File(FileName);
			
			DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			
			Document doc = dBuilder.parse(file);
			
			doc.getDocumentElement().normalize();
		
			NodeList nList = doc.getElementsByTagName("Service");
				
			for(int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				
				//for debugging
				System.out.println("Current element"+nNode.getNodeName());
				
				if(nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					Provider provider = new Provider();
					
					//set provider ID
					provider.setIDNum(Integer.parseInt(eElement.getAttribute("ProviderID")));
					
					//set provider Name
					provider.setName(eElement.getElementsByTagName("ProviderName").item(0).getTextContent());
					
					//set provider Address
					Address providerAddress = new Address();
					providerAddress.setStreet(eElement.getElementsByTagName("Street").item(0).getTextContent());
					providerAddress.setCity(eElement.getElementsByTagName("City").item(0).getTextContent());
					providerAddress.setState(eElement.getElementsByTagName("State").item(0).getTextContent());
					providerAddress.setZipCode(Integer.parseInt(eElement.getElementsByTagName("ZIP").item(0).getTextContent()));
					provider.setAddress(providerAddress);
					
					//add provider Services
					NodeList eList = eElement.getElementsByTagName("Service");
					for(int i = 0; i < eList.getLength(); i++) {
						Node eNode = eList.item(i);
						
						if(eNode.getNodeType() == Node.ELEMENT_NODE) {
							Element serviceElement = (Element) eNode;
							Service service = new Service();
							
							service.setID(Integer.parseInt(serviceElement.getAttribute("ServiceID")));
							service.setName(serviceElement.getElementsByTagName("ServiceName").item(0).getTextContent());
							service.setFee(Double.parseDouble(serviceElement.getElementsByTagName("ServiceFee").item(0).getTextContent()));
							service.setDescrp(serviceElement.getElementsByTagName("ServiceDescription").item(0).getTextContent());
							
							provider.addService(service);
						}
					}
					
					System.out.println(provider.toString());
					providersHash.put(provider.getID(), provider);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}