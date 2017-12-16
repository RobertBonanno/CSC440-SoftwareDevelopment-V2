package backEnd;
import java.io.File;
import java.sql.Date;
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
import org.w3c.dom.NodeList;

public class ServiceHash extends DataStoreHash<Service>{
/*
	HashMap<Integer, Service> servicesHash; 
	Service newService;
	
	public ServiceHash(){ 
		super();
		servicesHash = new HashMap<Integer, Service>() ;
	}
	*/
/////////////////--new code for singleton?--/////////////////////////////////////
	private static HashMap<Integer, Service> servicesHash = null; 
	Service newService;
	
	public ServiceHash(){ 
		super();
		if(servicesHash == null){
			servicesHash = new HashMap<Integer, Service>() ;
		}
		else{
			
		}
	}
	////////////////////////////////////////////////////////////////////////
	
	
	
	
	public void add(String name, double fee, String description) {
		Integer id = generateID(); 
		newService = new Service(id.intValue(), name, fee, description);
		servicesHash.put(id, newService); 
		
		
	}
	/**
	 * 
	 * @return ensures unique Integer ID is created in (T)hash
	 */
	private Integer generateID(){
		Integer id; 
		while(true){
			id =(int) (Math.random()*100000.0); 
			if(servicesHash.containsKey(id)){
				continue; 
			}
			break; 
		}
		return id;
	}
	
	public void remove(int serviceID){
		servicesHash.put(serviceID, null);
	}
	
	
	@Override
	public Service search(int serviceID){
		if(servicesHash.get(serviceID) == null){
			return null;
		}
		else{
			return servicesHash.get(serviceID);
		}
/*		System.out.println("im in ServiceHash search " + serviceID);
		if(servicesHash.containsKey(serviceID)){
			System.out.println("im in ServiceHash search \"if\" " + serviceID);
			return servicesHash.get(serviceID);
		}
		else{
			System.out.println("im returning null");
			return null;
			}
*/
	}
	
	public String validate(int serviceID){
		if(servicesHash.get(serviceID) == null){
			return "invalid";
		}
		else
		return "found";
	}
	
	private String iterate (){
		String servList = "";
		for(Entry<Integer, Service> entry : servicesHash.entrySet()){
			servList += "\n\tID: " + entry.getKey() + " service name: " + entry.getValue().getName();
		}
		if(servList == ""){
			servList = "No services available";
		}
			
		return servList;
		
	}
	
	public String toString(){
		return iterate();
	}
	
	@Override
	public String getDataHashType() {
		return "Service";
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
			
			
			for(Integer i : servicesHash.keySet()){
				rootElement.appendChild(servicesHash.get(i).getXMLElement(doc));
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
					Service service = new Service();
					
					service.setID(Integer.parseInt(eElement.getAttribute("ServiceID")));
					service.setName(eElement.getElementsByTagName("ServiceName").item(0).getTextContent());
					
					service.setFee(Double.parseDouble(eElement.getElementsByTagName("ServiceFee").item(0).getTextContent()));
					service.setDescrp(eElement.getElementsByTagName("ServiceDescription").item(0).getTextContent());
					
					System.out.println(service.toString());
					servicesHash.put(service.getID(), service);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
