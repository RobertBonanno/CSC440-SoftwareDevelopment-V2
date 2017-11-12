package backEnd;	//new

public class Address {

	private String street;
	private String city;
	private String state;
	private int zipCode;
	
	
	/**
	 * constructor of address class, allows instance to be seen
	 */
	
	Address(){
		street = "x";
		city = "y";
		state = "z";
		zipCode = 0;
	}
	
	/**
	 * acts as a constructor for when params are provided
	 * @param street is a string value of the street name and building number
	 * @param city is a string value of the city name
	 * @param state is a 2 character string value of the state name
	 * @param zipCode is an int value of the areas zipCode 
	 */
	
	Address(String street, String city, String state, int zipCode){
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
	}
	
	/**
	 * address can change, meant to provide method for changing address
	 * @param street is a string value of the street name and building number
	 * @param city is a string value of the city name
	 * @param state is a 2 character string value of the state name
	 * @param zipCode is an int value of the areas zipCode 
	 */
	
	private void setAddress(String street, String city, String state, int zipCode){
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
	}
	
	public String getAddress(){
		return (toString());
	}
	
	/**
	 * compiles the address in a standard format when address class is called
	 * 	"not sure if this will work for gui..."
	 */
	
	public String toString(){
		String address ="";
		address = street +"\n" + city + state+ zipCode;
		return address;
	}
}
