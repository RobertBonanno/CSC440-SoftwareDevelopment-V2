package controller;

import org.junit.Test;

public class MasterControllerTest {

	@Test 
	public void testRun() {
		MasterController mc = new MasterController();
		mc.run();
	}
	
	/*@Test
	public void testAddServiceToProvider() {
		MasterController MC = new MasterController();
		MC.providerController.addProvider();
		MC.serviceController.addService();
		
		MC.addServiceToProvider();
	}*/

}
