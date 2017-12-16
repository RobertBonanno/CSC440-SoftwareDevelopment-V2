package backEnd;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

public class providerAddServiceTest {

	ProviderHash testHash = new ProviderHash();
	
	@Test
	public void test() {
		
		Service john = new Service(78, "doctor", 2.00, "body health");
		Provider me = new Provider( "john", null,  0);					//---------------------not sure address can be null, this is a testcase, not urgent
		me.addService(john);
		
		testHash.put(107, me);
		//testHash.search(107);
		ArrayList output = ((Provider)testHash.search(107)).getServicesOffered();
		assertEquals("doctor, 700, 2.00, body health" , output);
	}

}


