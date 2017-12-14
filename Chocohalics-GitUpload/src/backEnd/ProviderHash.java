package backEnd;
import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class ProviderHash extends DataStoreHash{

	HashMap providersHash = new HashMap();
	
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
	
	public Object search(int providerID){
		if(providersHash.get(providerID) == null){
			return "invalid";
		}
		else
		return (Provider)providersHash.get(providerID);
		//return "found";//((Provider)providersHash.get(providerID));
	}
	
	public String editProv(int providerID, Service service){
		if(providersHash.get(providerID) == null){
			return "invalid";
		}
		else
			((Provider)providersHash.get(providerID)).addService(service);
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
	public void put(int providerID, Object provider ){
		providersHash.put(providerID, provider);
	}

	@Override
	public String getDataHashType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Node getXMLElement(Document doc, Integer i) {
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
/*	
	 * 
	 * @param providerID
	 * @param provider
	 * @return if prov is not added, notifies the caller to delete duplicate. possible to duplicate providers if accidentally given different id numbers.
	 
	public boolean add(int providerID, Object provider ){
		if(providersHash.get(providerID) == null){
			providersHash.put(providerID, provider);
			return true;
		}
		return false;
	}
*/
/*	public String addProvR(int providerID, Provider provider ){
		if(providersHash.get(providerID) == null){
			providersHash.put(providerID, provider);
			return "added Provider";
		}
		return "Provider already exists";
	}
		public String removeProvR(int providerID){
		providersHash.put(providerID, null);
		return "Provider deleted";
	}
	*
	*/