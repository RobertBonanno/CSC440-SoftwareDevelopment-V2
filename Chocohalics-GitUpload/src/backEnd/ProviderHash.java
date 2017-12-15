package backEnd;
import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

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
		// TODO Auto-generated method stub
		return null;
	}


	protected Node getXMLElement(Document doc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void writeToXML() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void readFromXML(String FileName) {
		// TODO Auto-generated method stub
		
	}
	
}