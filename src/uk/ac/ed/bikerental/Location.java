/**
 * This class is used for modelling a location
 * by means of its address and postcode, as well
 * as providing useful methods for supporting
 * operations on location objects.
 */
public class Location {
	
    /**
     * The private attribute of this class used
     * for storing the postcode this location.
     */
    private String postcode;
    
    /**
     * The private attribute of this class used
     * for storing the address this location.
     */
    private String address;
    
    /**
     * A public constructor for this class that takes two
     * arguments : a string of arbitrary length for initialising
     * the address attribute and another string of length at least
     * six for initialising the postcode attribute.
     * 
     * @param postcode is used to initialise the postcode attribute.
     * @param address is used to initialise the address attribute.
     */
    public Location(String postcode, String address) {
        assert postcode.length() >= 6;
        this.postcode = postcode;
        this.address = address;
    }
    
    /**
     * This method returns a boolean depending on whether a given location is 
     * "near" to the other. Two locations are said to be "near" to each other
     * if the first two digits of their post codes are equal to each other.
     * 
     * @param other : An external location used for nearness comparison 
     * with the location in this class.
     * 
     * @return true if this location is near to @param other and false otherwise.
     */
    public boolean isNearTo(Location other) {
        
    	if (other == null)
        	return false;
        
        char[] thisArray = this.postcode.replace(" ", "").toCharArray(); // strip white spaces
        char[] otherArray = other.getPostcode().replace(" ", "").toCharArray(); // strip white spaces
        
        for (int i = 0; i < thisArray.length; i++) {
        // if we encounter the first digit, we check for equality
        	if (Character.isDigit(thisArray[i])) {
        		if (thisArray[i] == otherArray[i]
        		&& thisArray[i+1] == otherArray[i+1])
        			return true;
        	}
        	else continue;
        }
        return false;
    }
    
    /**
     * This method gets the postcode attribute 
     * of this class, which is a private member.
     *
     * 
     * @return the postcode of this location.
     */
    public String getPostcode() {
        return postcode;
    }

    /**
     * This method gets the address attribute
     * of this class, which is a private member.
     * 
     * @return the address of this location.
     */
    public String getAddress() {
        return address;
    }
    
}
