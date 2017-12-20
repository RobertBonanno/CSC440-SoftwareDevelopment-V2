package backEnd;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class providerAddServiceTest {

	
	
	@Test
	public void test() {
		
		ProviderHash testHash = new ProviderHash();
		
		Service john = new Service(78, "doctor", 2.00, "body health");
		Provider me = new Provider( "john", null,  0);					//---------------------not sure address can be null, this is a testcase, not urgent
		me.addService(john);
		
		testHash.put(107, me);
		//testHash.search(107);
		ArrayList<Service> output = testHash.search(107).getServicesOffered();
		assertEquals("doctor, 700, 2.00, body health" , output);
	}

}


