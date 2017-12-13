package backEnd;	//new

public class Address {

	private String street;
	private String city;
	private String state;
	private int zipCode;
	
	
	/**
	 * constructor of address class, allows instance to be seen
	 */
	
	public Address(){
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
	
	public Address(String street, String city, String state, int zipCode){
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
	}
	
	
	
	public void setStreet(String street) {
		this.street = street;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	public String getStreet() {
		return street;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public int getZipCode() {
		return zipCode;
	}

	/**
	 * address can change, meant to provide method for changing address
	 * @param street is a string value of the street name and building number
	 * @param city is a string value of the city name
	 * @param state is a 2 character string value of the state name
	 * @param zipCode is an int value of the areas zipCode 
	 */
	
	protected void setAddress(String street, String city, String state, int zipCode){
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
		address = street +", " + city +", " + state +", "+ zipCode;
		return address;
	}
}
