public class Location {
    private String postcode;
    private String address;
    
    public Location(String postcode, String address) {
        assert postcode.length() >= 6;
        this.postcode = postcode;
        this.address = address;
    }
    
    public boolean isNearTo(Location other) {
        if (other == null)
        	return false;
        if (this.getPostcode().substring(0,3).equals(other.getPostcode().substring(0, 3)))
        	return true;
        return false;
    }
    
    public String getPostcode() {
        return postcode;
    }

    public String getAddress() {
        return address;
    }
    
}
