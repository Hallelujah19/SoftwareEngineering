import java.math.BigInteger;
import java.util.*;

enum BookingStatus {

	BOOKED, DISPATCHED, DELIVERED, RETURNED

}

public class Booking implements Deliverable {

	private BookingStatus status;
	private String bookingNumber;
	private boolean isBooked;
	private Quote quote; // set from customer attribute

	public Quote getQuote() {
		return quote;
	}

	public Customer getCustomer() {
		return customer;
	}

	private Customer customer;

//change the constructor as the unique number is set from within the system
	public Booking(Customer customer) {
		this.customer = customer;
		this.quote = customer.getChosenQuote();
		this.setBookingNumber(bookingNumber); // immediately set unique booking number
	}

	public void bookQuote() { // execute this method if the customer
								// has decided to book a quote
		assert isBooked == true;
		this.setStatus(BookingStatus.BOOKED);

		for (int i = 0; i < QuoteFinder.getAllProviders().size(); i++) {

			if (QuoteFinder.getAllProviders().get(i).equals(quote.getBikeProvider())) {
				if (QuoteFinder.getAllProviders().get(i).equals(quote.getBikeProvider())) {
					for (String bikeId : quote.getBikeIds()) {
						for (Bike bike : QuoteFinder.getAllProviders().get(i).getBikes()) {
							if (bike.getBikeId().equals(bikeId)) { // find bike whose id matches what we have
								// reserve upon booking
								QuoteFinder.getAllProviders().get(i).updateBikeStatus(bikeId, BikeStatus.RESERVED);
								// this.setStatus(BookingStatus.BOOKED);
								QuoteFinder.getAllProviders().get(i).getBookings().add(this);
								break; // go to next bikeId
							}
						}
					}
				} else
					continue;
			}

		}

		// proceed to schedule delivery
		if (customer.getMode().equals(CollectionMode.DELIVERY)) {

			// DeliveryServiceFactory;
			DeliveryService deliveryService = DeliveryServiceFactory.getDeliveryService();

			deliveryService.scheduleDelivery(this, quote.getBikeProvider().getShopLocation(), customer.getLocation(),
					customer.getDateRange().getStart());

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

		this.bookingNumber = "BN" + QuoteFinder.NumberOfRequests;
	}

	public boolean getIsBooked() {
		return isBooked;
	}

	public void setIsBooked(boolean isBooked) {
		this.isBooked = isBooked;
	}

	public void statusSetter(String bkingStatus, String bkStatus) {

		BikeStatus bikeStatus = BikeStatus.valueOf(bkStatus);
		BookingStatus bookingStatus = BookingStatus.valueOf(bkingStatus);

		for (int i = 0; i < QuoteFinder.getAllProviders().size(); i++) {

			if (QuoteFinder.getAllProviders().get(i).equals(quote.getBikeProvider())) {
				if (QuoteFinder.getAllProviders().get(i).equals(quote.getBikeProvider())) {
					for (String bikeId : quote.getBikeIds()) {
						for (Bike bike : QuoteFinder.getAllProviders().get(i).getBikes()) {
							if (bike.getBikeId().equals(bikeId)) { // find bike whose id matches what we have
								// reserve upon booking
								QuoteFinder.getAllProviders().get(i).updateBikeStatus(bikeId, bikeStatus);
								this.setStatus(bookingStatus);
								break; // go to next bikeId
							}
						}
					}
				} else
					continue;
			}

		}

	}

	public void onPickup() {
		// updates bike status on pick up
		// bike status changes to Dispatched
		statusSetter(BookingStatus.DISPATCHED.toString(), BikeStatus.PICKED_UP.toString());

	}

	public void onDropoff() {
		// bike status changes to Hired
		// booking status Delivered

		statusSetter(BookingStatus.DELIVERED.toString(), BikeStatus.HIRED.toString());

	}

}
