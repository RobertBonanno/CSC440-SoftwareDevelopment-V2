package backEnd;

import java.util.ArrayList;

//import java.util.List;
public class Provider extends IDHolder{

	ArrayList services = new ArrayList();
	
	private void addService(Service service){
		//if(service){
			services.add(service);
		//}
	}
}
