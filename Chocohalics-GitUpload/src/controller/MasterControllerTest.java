package controller;

import static org.junit.Assert.*;

import org.junit.Test;

public class MasterControllerTest {

	@Test
	public void testAddServiceToProvider() {
		MasterController MC = new MasterController();
		MC.providerController.AddProvider();
		MC.serviceController.AddService();
		
		MC.addServiceToProvider();
	}

}
