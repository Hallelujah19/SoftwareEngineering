package uk.ac.ed.bikerental;


enum BookingStatus {

	BOOKED, DISPATCHED, DELIVERED, RETURNED

}

public class Booking implements Deliverable {

	private BookingStatus status;
	private String bookingNumber;
	private boolean isBooked;
	private Quote quote; // set from customer attribute
	public DeliveryService deliveryService;
	
	public Quote getQuote() {
		return quote;
	}
	
	public void setDeliveryService(DeliveryService deliveryService) {
		this.deliveryService = deliveryService;
	}

	public DeliveryService getDeliveryService() {
		return deliveryService;
	}
	
	public Customer getCustomer() {
		return customer;
	}

	private Customer customer;

	// constructor
	public Booking(Customer customer) {
		
		this.customer = customer;
		this.quote = customer.getChosenQuote();
		this.setBookingNumber(); // immediately set unique booking number
		
	}

	public void bookQuote() { // execute this method if the customer
								// has decided to book a quote
		statusSetter(BookingStatus.BOOKED, BikeStatus.RESERVED, true);

		// proceed to schedule delivery
		if (customer.getMode() == CollectionMode.DELIVERY) {
			
			deliveryService.scheduleDelivery(this, quote.getBikeProvider().getShopLocation(),
					customer.getLocation(), customer.getDateRange().getStart());
		}
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

	public void setBookingNumber() {
		this.bookingNumber = "BN" + QuoteFinder.NumberOfRequests;
	}
	
	public boolean getIsBooked() {
		return isBooked;
	}

	public void setIsBooked(boolean isBooked) {
		this.isBooked = isBooked;
	}

	public void onPickup() {
		// updates bike status on pick up
		// bike status changes to Dispatched
		statusSetter(BookingStatus.DISPATCHED, BikeStatus.PICKED_UP, false);

	}

	public void onDropoff() {
		// bike status changes to Hired
		// booking status Delivered
		statusSetter(BookingStatus.DELIVERED, BikeStatus.HIRED, false);

	}
	
	public void statusSetter(BookingStatus bookingStatus, BikeStatus bikeStatus, boolean flag) {
		
		this.setStatus(bookingStatus);
		
		for (int i = 0; i < QuoteFinder.getAllProviders().size(); i++) {
			// pick out the provider
			if (QuoteFinder.getAllProviders().get(i).equals(quote.getBikeProvider())) {
				// for each bike id in the quote
				for (String bikeId : quote.getBikeIds()) {
					// go through all the provider's bikes
					for (Bike bike : QuoteFinder.getAllProviders().get(i).getBikes()) {
						// fish out the bike whose id matches the selected id
						if (bike.getBikeId().equals(bikeId)) {
							// reserve upon booking
							QuoteFinder.getAllProviders().get(i).updateBikeStatus(bikeId, bikeStatus);
							break; // go to next bikeId
						}
					}
				}
				if (flag) QuoteFinder.getAllProviders().get(i).getBookings().add(this);
				break; // end of booking registration for provider
			} else
				continue; // to next provider
		}
	}

}
