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

public class ServiceHash extends DataStoreHash{

	HashMap servicesHash; 
	Service newService;
	
	public ServiceHash(){ 
		super();
		servicesHash = new HashMap<Integer, Service>() ;
	}
	
	
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
			id =(int) (Math.random()*1000.0); 
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
	public Object search(int serviceID){
		if(servicesHash.containsKey(serviceID))
			return servicesHash.get(serviceID);
		else
			return null;
	}
	
	public String validate(int serviceID){
		if(servicesHash.get(serviceID) == null){
			return "invalid";
		}
		else
		return "found";
	}
	
	
	@Override
	public String getDataHashType() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void writeToXML() {
		// TODO Auto-generated method stub
		
	}


	@Override
	protected Node getXMLElement(Document doc, Integer i) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void readFromXML(String FileName) {
		// TODO Auto-generated method stub
		
	}
}
	
/*	@Override
	protected Node getXMLElement(Document doc, Integer i) {
		Element serviceElement = doc.createElement("service");
		Service service = (Service) servicesHash.get(i.intValue()); //---------------------------------------------
		//set id attribute
		serviceElement.setAttribute("id", service.getID()+"");
		
		//create name attribute
		serviceElement.appendChild(super.getElementValue(doc, serviceElement, "Name", service.getName()));
		serviceElement.appendChild(super.getElementValue(doc, serviceElement, "Fee", service.getFee()));
		serviceElement.appendChild(super.getElementValue(doc, serviceElement, "Description", service.getDescrp()));

		
		return serviceElement;
	}

	
	@Override
	public void readFromXML(String FileName) {
		try {
			File file = new File(FileName);
			
			DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			
			Document doc = dBuilder.parse(file);
			
			doc.getDocumentElement().normalize();
		
			NodeList nList = doc.getElementsByTagName("service");
				
			for(int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				
				//for debugging
				System.out.println("Current element"+nNode.getNodeName());
				
				if(nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					Service service = new Service();
					Address address = new Address();
					
					service.setID(Integer.parseInt(eElement.getAttribute("id")));
					service.setName(eElement.getElementsByTagName("Name").item(0).getTextContent());
					
					String status = eElement.getElementsByTagName("Status").item(0).getTextContent();
					if(status.equals("VALID"))
						service.setStatus(1);
					else if(status.equals("SUSPENDED"))
						service.setStatus(2);
					else if(status.equals("INVALID"))
						service.setStatus(3);
					else;
					
					address.setStreet(eElement.getElementsByTagName("Street").item(0).getTextContent());
					address.setCity(eElement.getElementsByTagName("City").item(0).getTextContent());
					address.setState(eElement.getElementsByTagName("State").item(0).getTextContent());
					address.setZipCode(Integer.parseInt(eElement.getElementsByTagName("ZIP").item(0).getTextContent()));
					
					service.setAddress(address);
					
					System.out.println(service.toString());
					servicesHash.put(service.getID(), service);
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
			for(Integer i : servicesHash.keySet()){
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
		
	}*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
