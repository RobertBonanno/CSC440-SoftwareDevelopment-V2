package controller;

import static org.junit.Assert.*;

import org.junit.Test;

public class ProviderControllerAddServTest {

	
	@Test
	public void testAddProvider() {
		 ProviderController Provadded = new  ProviderController();
		 Provadded.AddProvider();
		 Provadded.writeToXML();
	}
	
	@Test
	public void testProviderController() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddService() {
		fail("Not yet implemented");
	}



	@Test
	public void testDeleteprovider() {
		fail("Not yet implemented");
	}

	@Test
	public void testEditprovider() {
		fail("Not yet implemented");
	}

}
