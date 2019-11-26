
/**
 * This class is used for modelling a location
 * by means of its address and postcode, as well
 * as providing useful methods for supporting
 * operations on location objects.
 */
public class Location {
	
    /**
     * The private attribute of this class used
     * for storing the postcode of a given location.
     */
    private String postcode;
    
    /**
     * The private attribute of this class used
     * for storing the address of a given location.
     */
    private String address;
    
    /**
     * A public constructor for this class that takes two
     * arguments : a string of arbitrary length for initialising
     * the address attribute and another string of length at least
     * six for initialising the postcode attribute.
     * 
     * @param postcode is set to the value of the postcode argument.
     * @param address is set to the value of the address argument.
     */
    public Location(String postcode, String address) {
        assert postcode.length() >= 6;
        this.postcode = postcode;
        this.address = address;
    }
    
    /**
     * This method returns a boolean depending on whether a given location
     * is "near" to the other. Locations are said to be "near" to each other
     * if their respective substrings containing the first three characters
     * of their respective postcode strings are equal to each other.
     * 
     * @param other : An external location used for nearness comparison 
     * with the location in this class.
     * 
     * @returns true if locations are near to each other and false otherwise.
     */
    public boolean isNearTo(Location other) {
        if (other == null)
        	return false;
        if (this.getPostcode().substring(0,3).equals(other.getPostcode().substring(0, 3)))
        	return true;
        return false;
    }
    
    /**
     * This method returns the postcode attribute 
     * of this class, which is a private attribute.
     *
     * 
     * @returns the postcode attribute.
     */
    public String getPostcode() {
        return postcode;
    }

    /**
     * This method returns the address attribute
     * of this class, which is a private attribute.
     * 
     * @returns the address attribute.
     */
    public String getAddress() {
        return address;
    }
    
}
