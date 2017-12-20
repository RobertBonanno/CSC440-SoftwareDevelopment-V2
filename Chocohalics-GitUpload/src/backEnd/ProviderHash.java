package backEnd;
import java.io.File;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;
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
	ProviderHash instance = null;
	
	public ProviderHash(){ 
		super();
		providersHash = new HashMap<Integer, Provider>();
	}

	/**
	 * 
	 * @param name <= 25 char 
	 * @param address object
	 *  adds provider to providerhash 
	 */
	public Provider add(String name, Address address) {
		Integer id = generateID(); 
		//Integer id = 3; //-------------------------------------------------------
		Provider newprovider = new Provider(name,address,id.intValue());
		providersHash.put(id, newprovider); 
		return newprovider;
	}
	
	/**
	 * 
	 * @return ensures unique Integer ID is created in (T)hash
	 */
	private Integer generateID(){
		Integer id; 
		while(true){
			id = (int) (Math.random()*100000000.0)+100000000;
			if(providersHash.containsKey(id)){
				continue; 
			}
			break; 
		}
		return id;
	}

	public void remove(int providerID){
		providersHash.remove(providerID);
	}
	
	public Provider search(int providerID){
		if(providersHash.get(new Integer(providerID)) == null){
			return null;
		}
		else{
			Provider provider =  providersHash.get(new Integer(providerID));
			return provider;
		}
		
		//return "found";//((Provider)providersHash.get(providerID));
	}
	
	public String editProv(int providerID, Service service){
		if(providersHash.get(providerID) == null){		//--------------------if this is not used outside providerController Addservice, this is redundant
			return "INVALID";
		}
		else
			(providersHash.get(providerID)).addService(service);
		return "service added";
	}
	
	public boolean validate(int providerID){
		if(search(providerID) == null)
			return false;
		else
			return true;
	}
	
	/**
	 *  this method only serves for the test case, should be replaced with a read from disc for security reasons
	 * @param providerID
	 * @param provider
	 */
	public void put(int providerID, Provider provider){
		providersHash.put(providerID, provider);
	}

	public String toString(){
		String toString = "";
		for(Provider p : providersHash.values()){
			toString += p.toString()+System.lineSeparator();
		}
		if(toString.equals(""))
			return "There's nothing here Jim! (providerHash)";
		return toString;
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
			File f = new File("ChocAn"+hashType+"_"+date.toString()+"_"+time.getHours()+"-"+time.getMinutes()+".XML");
			StreamResult file = new StreamResult(f);
			
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
		
			NodeList nList = doc.getElementsByTagName("Provider");
				
			for(int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				
				//for debugging
				//System.out.println("Current element"+nNode.getNodeName());
				
				if(nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					Provider provider = new Provider();
					
					//set provider ID
					provider.setIDNum(Integer.parseInt(eElement.getAttribute("ProviderID")));
					
					//set provider Name
					provider.setName(eElement.getElementsByTagName("ProviderName").item(0).getTextContent());
					
					//set provider Address
					Address providerAddress = new Address();
					providerAddress.setStreet(eElement.getElementsByTagName("Street").item(0).getFirstChild().getTextContent());
					providerAddress.setCity(eElement.getElementsByTagName("City").item(0).getFirstChild().getTextContent());
					providerAddress.setState(eElement.getElementsByTagName("State").item(0).getFirstChild().getTextContent());
					providerAddress.setZipCode(Integer.parseInt(eElement.getElementsByTagName("ZIP").item(0).getTextContent()));
					provider.setAddress(providerAddress);
					//System.out.println(provider);
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
					//for debugging
					//System.out.println(provider.toString());
					providersHash.put(provider.getID(), provider);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	public ArrayList<Provider> getProviderList() {
		ArrayList<Provider> providerCollection = new ArrayList<Provider>();
		
		for(Provider p : providersHash.values())
			providerCollection.add(p);

		Collections.sort(providerCollection);
		
		return providerCollection;
	}
	
}