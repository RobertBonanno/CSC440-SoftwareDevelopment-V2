package backEnd;
import java.util.HashMap;

public class ServiceHash extends DataStoreHash{

	HashMap servicesHash = new HashMap();
	

	
	@Override		//--------------------------------------
	public void add(String name, Address address){}
	
	public void add(String name, double fee, String description) {
		Integer id = generateID(); 
		Service newService = new Service(id.intValue(), name, fee, description);
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
	
	public String search(int serviceID){
		if(servicesHash.get(serviceID) == null){
			return "invalid";
		}
		else
		return "found";//((Provider)servicesHash.get(providerID));
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

	
	
	
	
	
	
	
	@Override
	public void writeToDisk() {
		// TODO Auto-generated method stub
		
	}


	
}


/*	
 * 
 * 
 * 
 * 	/**
	 * 
	 * @param memberID
	 * @param member
	 * @return if prov is not added, notifies the caller to delete duplicate. possible to duplicate providers if accidentally given different id numbers.
	 
	public boolean add(int serviceID, Object service ){
		if(servicesHash.get(serviceID) == null){
			servicesHash.put(serviceID, service);
			return true;
		}
		return false;
	}
 * public String addServR(int serviceID, Service service ){

		if(servicesHash.get(serviceID) == null){
			servicesHash.put(serviceID, service);
			return "added Provider";
		}
		return "Provider already exists";
	}
		public String removeServR(int serviceID){
		servicesHash.put(serviceID, null);
		return "Provider deleted";
	}
	*
	*/