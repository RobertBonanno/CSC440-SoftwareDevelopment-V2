package backEnd;
import java.util.HashMap;
public class ServiceHash {

	HashMap servicesHash = new HashMap();
	
	/**
	 * 
	 * @param memberID
	 * @param member
	 * @return if prov is not added, notifies the caller to delete duplicate. possible to duplicate providers if accidentally given different id numbers.
	 */
	public boolean addServ(int serviceID, Service service ){
		if(servicesHash.get(serviceID) == null){
			servicesHash.put(serviceID, service);
			return true;
		}
		return false;
	}
	
	public String addServR(int serviceID, Service service ){
		if(servicesHash.get(serviceID) == null){
			servicesHash.put(serviceID, service);
			return "added Provider";
		}
		return "Provider already exists";
	}
	
	public void removeServ(int serviceID){
		servicesHash.put(serviceID, null);
	}
	
	public String removeServR(int serviceID){
		servicesHash.put(serviceID, null);
		return "Provider deleted";
	}

	public String searchServ(int serviceID){
		if(servicesHash.get(serviceID) == null){
			return "invalid";
		}
		else
		return "found";//((Provider)providersHash.get(providerID));
	}
	
	public String editServ(int serviceID, String name){
		if(servicesHash.get(serviceID) == null){
			return "invalid";
		}
		else
			((Service)servicesHash.get(serviceID)).setServiceName(name);;
		return "service name edited";
	}
	
	public String editServ(int serviceID, double fee){
		if(servicesHash.get(serviceID) == null){
			return "invalid";
		}
		else
			((Service)servicesHash.get(serviceID)).setServiceFee(fee);;
		return "service fee edited";
	}
	
	public String editServDesc(int serviceID, String descrip){
		if(servicesHash.get(serviceID) == null){
			return "invalid";
		}
		else
			((Service)servicesHash.get(serviceID)).setServiceDescrp(descrip);
		return "service description eddited";
	}
	
	////////////////////////////////////////////////////////////

	@Override
	public void writeToDisk() {
		// TODO Auto-generated method stub
		
	}
	///////////////////////////////////////////////////////////
	
}