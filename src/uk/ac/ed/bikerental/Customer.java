import java.util.HashMap;

public class Customer {
	
	public static enum CustomerStatus{
		LOCAL,TOURIST
	}
	
	public static enum CollectionMode {
		IN_STORE, DELIVERY
	}
	
	private Location customerLocation;
	private CollectionMode mode;
	private CustomerStatus customerStatus;
	private HashMap<String, Integer> numberOfBikesPerType; // string represent names of bike types
	private HashMap<Integer, Quote> indexedListOfQuotes;
	private Quote chosenQuote;
	private int indexOfChosenQuote;
	
	public Quote selectQuote() {
		Quote x = null;
		return x;
	}
	
	public void BrowseQuotes() {
			
	}
	
	public void goToBooking(Quote quote) {
		
	}
	
	public void getRentalNeeds() {
		
	}

	public void setCustomerStatus(CustomerStatus customerStatus) {
		this.customerStatus = customerStatus;
	}

	public void setIndexedListOfQuotes(HashMap<Integer, Quote> indexedListOfQuotes) {
		HasMap<Integer,Quote> search=filterQoutes(Biketype b,Integer number,Location l);//not finished
		
		this.indexedListOfQuotes = search;
	}

	public void setChosenQuote(Quote chosenQuote) {
		this.chosenQuote = chosenQuote;
	}

	public void setIndexOfChosenQuote(int indexOfChosenQuote) {
		this.indexOfChosenQuote = indexOfChosenQuote;
	}

	public CustomerStatus getCustomerStatus() {
		return customerStatus;
	}
	
	public HashMap<String, Integer> getNumberOfBikesPerType() {
		return numberOfBikesPerType;
	}

	public void setNumberOfBikesPerType(HashMap<String, Integer> numberOfBikesPerType) {
		this.numberOfBikesPerType = numberOfBikesPerType;
	}

	public HashMap<Integer, Quote> getIndexedListOfQuotes() {
		return indexedListOfQuotes;
	}

	public Quote getChosenQuote() {
		return chosenQuote;
	}
	
	public int getIndexOfChosenQuote() {
		return indexOfChosenQuote;
	}
	
	public void setCustomerLocation(Location customerLocation) {
		this.customerLocation = customerLocation;
	}
	
	public Location getCustomerLocation() {
		return customerLocation;
	}

	public CollectionMode getMode() {
		return mode;
	}

	public void setMode(CollectionMode mode) {
		this.mode = mode;
	}
	
}


