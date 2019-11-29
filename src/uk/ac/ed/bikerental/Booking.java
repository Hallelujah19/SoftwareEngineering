
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
		this.setBookingNumber(); // immediately set unique booking number
	}

	public void bookQuote() { // execute this method if the customer
								// has decided to book a quote

		statusSetter(BookingStatus.BOOKED, BikeStatus.RESERVED, true);

		// proceed to schedule delivery
		if (customer.getMode().equals(CollectionMode.DELIVERY)) {

			// DeliveryServiceFactory;
			DeliveryService deliveryService = DeliveryServiceFactory.getDeliveryService();

			deliveryService.scheduleDelivery(this, quote.getBikeProvider().getShopLocation(),
					customer.getLocation(), customer.getDateRange().getStart());

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
							this.setStatus(bookingStatus);
							if (flag)
								QuoteFinder.getAllProviders().get(i).getBookings().add(this);
							break; // go to next bikeId
						}
					}
				}
			} else
				continue;
		}

	}

}
