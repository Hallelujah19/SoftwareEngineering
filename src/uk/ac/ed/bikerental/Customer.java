
import java.util.HashMap;

enum CustomerStatus {
	LOCAL, TOURIST
}

enum CollectionMode {
	IN_STORE, DELIVERY
}

public class Customer {
	
	private Location location;
	private CollectionMode mode;
	private CustomerStatus status;
	private DateRange dateRange;
	private HashMap<String, Integer> bikesPerType = new HashMap<>(); 
	private HashMap<Integer, Quote> listOfQuotes = new HashMap<>();
	private Quote chosenQuote;
	private int chosenIndex;

	public void selectQuote() {
		// at this stage the index of chosen quote has been set
		chosenQuote = listOfQuotes.get(chosenIndex);
	}

	public DateRange getDateRange() {
		return dateRange;
	}

	public void setDateRange(DateRange dateRange) {
		this.dateRange = dateRange;
	}

	public void BrowseQuotes() {
		// this method is called when the rental needs
		// of the customer have been obtained
		listOfQuotes = QuoteFinder.filterQuotes(bikesPerType, dateRange, location);
		// display the indexed list of quotes in human-readable format
		for (Integer i : listOfQuotes.keySet()) {
			System.out.println(i + "." + "\n" + listOfQuotes.get(i).toString());
		}
	}
	
	public void setStatus(CustomerStatus status) {
		this.status = status;
	}

	public void setListOfQuotes(HashMap<Integer, Quote> listOfQuotes) {
		this.listOfQuotes = listOfQuotes;
	}

	public void setChosenQuote(Quote chosenQuote) {
		this.chosenQuote = chosenQuote;
	}

	public void setChosenIndex(int chosenIndex) {
		this.chosenIndex = chosenIndex;
	}

	public CustomerStatus getStatus() {
		return status;
	}

	public HashMap<String, Integer> getBikesPerType() {
		return bikesPerType;
	}
	
	public void setBikesPerType(HashMap<String, Integer> bikesPerType) {
		this.bikesPerType = bikesPerType;
	}

	public HashMap<Integer, Quote> getListOfQuotes() {
		return listOfQuotes;
	}

	public Quote getChosenQuote() {
		return chosenQuote;
	}

	public int getChosenIndex() {
		return chosenIndex;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Location getLocation() {
		return location;
	}
	
	public CollectionMode getMode() {
		return mode;
	}

	public void setMode(CollectionMode mode) {
		this.mode = mode;
	}

}
