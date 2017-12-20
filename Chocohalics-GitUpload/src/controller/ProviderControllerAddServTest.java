package controller;

import static org.junit.Assert.*;

import org.junit.Test;

import frontEnd.Terminal;

public class ProviderControllerAddServTest {
	
	ProviderController Provadded = new ProviderController();
	ServiceController servadded = ServiceController.getInstance();
	Terminal terminal = new Terminal("Console");
	
	/*
	@Test
	public void testAddProvider() {
		 
		 Provadded.AddProvider();
		 Provadded.writeToXML();
	}
	*/
	@Test
	public void testProviderController() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddService() {
		 Provadded.addProvider();
		 
		 servadded.addService();
		 servadded.writeToXML();
		 
		terminal.setOutput("Serviec IDs available:" 
		+ System.lineSeparator() + servadded.listServiceHash());
		//Provadded.AddService();
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
