import java.math.BigInteger;
import java.util.*;
enum BookingStatus {
	
	BOOKED, DISPATCHED, DELIVERED
	
}

public class Booking implements Deliverable {

	private BookingStatus status;
	private String bookingNumber;
	private boolean isBooked;
	private Quote quote; // set from customer attribute
	private Customer customer;

	public Booking(Customer customer, String bookingNumber) {
		this.customer = customer;
		this.quote = customer.getChosenQuote();
		this.setBookingNumber(bookingNumber); // immediately set unique booking number
	}

	public void bookQuote() { // execute this method if the customer 
							  // has decided to book a quote
		assert isBooked == true;
		for (int i = 0; i < QuoteFinder.getAllProviders().size(); i++) {
			// pick out the bike provider
			if (QuoteFinder.getAllProviders().get(i).equals(quote.getBikeProvider())) { 
				for (String bikeId : quote.getBikeIds()) {
				   for (Bike bike : QuoteFinder.getAllProviders().get(i).getBikes()) {
						if (bike.getBikeId().equals(bikeId)) { // find bike whose id matches what we have
							// reserve upon booking
							QuoteFinder.getAllProviders().get(i).updateBikeStatus(bikeId, BikeStatus.RESERVED);
							break; // go to next bikeId
						}
					}
				}
			}
			else
				continue;
		}
		// proceed to schedule delivery
		if (customer.getMode().equals(CollectionMode.DELIVERY)) {
			;
		}
	}

	public void goToPayment() {

	}

	public BookingStatus getStatus() {
		return status;
	}

	public void setStatus(BookingStatus status) {
		this.status = status;
	}

	public String getBookingNumber() {
		return bookingNumber;
	}

	public void setBookingNumber(String bookingNumber) {
		this.bookingNumber = bookingNumber;
	}
	
	public boolean getIsBooked() {
		return isBooked;
	}

	public void setIsBooked(boolean isBooked) {
		this.isBooked = isBooked;
	}
	
	public void onPickup() {
		// updates booking and bike status on pick up
		BigInteger big;
		
	}
	
	public void onDropoff() {
		
	}

}

