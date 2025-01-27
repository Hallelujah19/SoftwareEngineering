package uk.ac.ed.bikerental;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestReturn {

	// Get a booking number which can be compared to the booking number of a list of
	// bookings to remove from the system
	//String bookingNumber; // provided by customer
	//BikeProvider provider; //the customer return point
	//Bike returnedBike; 

	

	@Test
public void testReturn() {
		
		// create a customer and set up system
		TestData_FindQuote.setUpProvider(20);
		TestData_FindQuote.createCustomer();

		// get quotes for the customer
		TestData_FindQuote.customer.BrowseQuotes();
		TestData_FindQuote.customer.setChosenIndex(1);
		TestData_FindQuote.customer.selectQuote();
		
		Quote result = TestData_FindQuote.customer.getChosenQuote();
		BikeProvider provider = result.getBikeProvider();
		Booking booking = new Booking(TestData_FindQuote.customer);
		// set up delivery service
		if (TestData_FindQuote.customer.getMode() == CollectionMode.DELIVERY) {
			DeliveryServiceFactory.setupMockDeliveryService();
			booking.setDeliveryService((MockDeliveryService) DeliveryServiceFactory.getDeliveryService());
		}
		// proceed to book quote
		booking.bookQuote();
		String bookingNumber = booking.getBookingNumber(); 
		
		//partner could be used to test if the message is sent to the partner
		 int index = 7;
		 BikeProvider partnerProvider = QuoteFinder.getAllProviders().get(index);
		 

		 
		//Setup partner
		provider.setPartnerProvider(QuoteFinder.getAllProviders().get(index));
		partnerProvider.setPartnerProvider(provider);
		
		//System.out.println(provider.bookings.isEmpty());
		
		// tests if the status of all the returned bikes has been made available or if a message has been sent to the partner of the return.
		boolean allAvailable = true;

		//
		boolean isProviders = provider.registerReturn(bookingNumber);
		

		if (isProviders) {
			for (Booking theBooking : provider.getBookings()) {
				if (theBooking.getBookingNumber().equals(bookingNumber)) {
					allAvailable = allAvailable || (BookingStatus.RETURNED == theBooking.getStatus());
					for (String bikeID : theBooking.getQuote().getBikeIds()) {
						for (Bike bike : theBooking.getQuote().getBikeProvider().getBikes()) {
							if (bike.getBikeId().equals(bikeID)) {
								allAvailable = allAvailable || (BikeStatus.AVAILABLE == bike.getBikeStatus());
							}
						}

					}

				}
			}
			assertEquals(true, allAvailable);

		} else {

			assertEquals(true,provider.getPartnerProvider().getMessageFromPartner().contains( provider.getMessageToPartner()));

		}

	}

}
