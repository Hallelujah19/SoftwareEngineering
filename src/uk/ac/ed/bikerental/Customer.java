import java.util.Collection;
import java.util.HashMap;

public class Customer {
	
	public static enum CustomerStatus{
		LOCAL,TOURIST
	}
	
	private CustomerStatus customerStatus;
	private int numberOfBikes;
	private Collection bikeTypesOfInterest;
	private HashMap indexedListOfQuotes;
	private Collection chosenQuotes;
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

	public void setNumberOfBikes(int numberOfBikes) {
		this.numberOfBikes = numberOfBikes;
	}

	public void setBikeTypesOfInterest(Collection bikeTypesOfInterest) {
		this.bikeTypesOfInterest = bikeTypesOfInterest;
	}

	public void setIndexedListOfQuotes(HashMap indexedListOfQuotes) {
		this.indexedListOfQuotes = indexedListOfQuotes;
	}

	public void setChosenQuotes(Collection chosenQuotes) {
		this.chosenQuotes = chosenQuotes;
	}

	public void setIndexOfChoesnQuote(int indexOfChoesnQuote) {
		this.indexOfChosenQuote = indexOfChoesnQuote;
	}

	public CustomerStatus getCustomerStatus() {
		return customerStatus;
	}

	public int getNumberOfBikes() {
		return numberOfBikes;
	}

	public Collection getBikeTypesOfInterest() {
		return bikeTypesOfInterest;
	}

	public HashMap getIndexedListOfQuotes() {
		return indexedListOfQuotes;
	}

	public Collection getChosenQuotes() {
		return chosenQuotes;
	}

	public int getIndexOfChosenQuote() {
		return getIndexOfChosenQuote();
	}
	

}
