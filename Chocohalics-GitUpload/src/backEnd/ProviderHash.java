package backEnd;
import java.util.HashMap;

public class ProviderHash extends DataStoreHash{

	HashMap providersHash = new HashMap();
	
	/**
	 * 
	 * @param memberID
	 * @param member
	 * @return if prov is not added, notifies the caller to delete duplicate. possible to duplicate providers if accidentally given different id numbers.
	 */
	public boolean add(int providerID, Object provider ){
		if(providersHash.get(providerID) == null){
			providersHash.put(providerID, provider);
			return true;
		}
		return false;
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
	
	public String editProv(int providerID, Provider provider, Service service){
		if(providersHash.get(providerID) == null){
			return "invalid";
		}
		else
			((Provider)providersHash.get(providerID)).addService(service);
		return "service added";
	}
	

	@Override
	public void writeToDisk() {
		// TODO Auto-generated method stub
		
	}
	
