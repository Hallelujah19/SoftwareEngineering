
import org.junit.Test;
import org.junit.jupiter.api.*;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;

public class SystemTests {
	// You can add attributes here
	boolean booked = true;
	CollectionMode mode;
	Booking booking;

	@BeforeEach
	void setUp() throws Exception {
		// Setup mock delivery service before each tests
		DeliveryServiceFactory.setupMockDeliveryService();

		// Put your test setup here
	}

	@Test
	public void TestGetQuote() {

		// create a customer and set up system
		TestData_FindQuote.setUpProvider(20);
		TestData_FindQuote.createCustomer();

		// get quotes for the customer
		TestData_FindQuote.customer.BrowseQuotes();
		TestData_FindQuote.customer.setChosenIndex(1);
		TestData_FindQuote.customer.selectQuote();

		Quote result = TestData_FindQuote.customer.getChosenQuote();
		HashMap<String, Integer> bikeTypeResult = new HashMap<>();

		System.out.println(result == null);

		assertEquals(true,
				TestData_FindQuote.customer.getLocation().isNearTo(result.getBikeProvider().getShopLocation()));

		// create a number per bike type for the quote to compare to the customers
		// initial attribute(request)
		for (Bike bike : result.getBikeProvider().getBikes()) {
			for (String bikeId : result.getBikeIds()) {
				if (bike.getBikeId().equals(bikeId)) {
					if (bikeTypeResult.containsKey(bike.getBikeType().getTypeName())) {
						bikeTypeResult.put(bike.getBikeType().getTypeName(),
								bikeTypeResult.get(bike.getBikeType().getTypeName()).intValue() + 1);
					} else
						bikeTypeResult.put(bike.getBikeType().getTypeName(), 1);

				}

			}
		}

		// check if the quote has enough of the requested bikes

		assertEquals(TestData_FindQuote.customer.getBikesPerType().size(), bikeTypeResult.size());

		// tests if all the types
		boolean allMatch = true;

		for (String type : TestData_FindQuote.customer.getBikesPerType().keySet()) {
			for (String resultType : bikeTypeResult.keySet()) {
				if (type.equals(resultType)) {
					allMatch = allMatch && (TestData_FindQuote.customer.getBikesPerType().get(type) == bikeTypeResult
							.get(resultType));
				}
			}

		}

		assertEquals(true, allMatch);

		// checks if the date ranges of bikes overlaps with the customer required range
		boolean allOverlap = true;

		for (Bike bike : result.getBikeProvider().getBikes()) {
			if (TestData_FindQuote.customer.getDateRange().overlaps(bike.getDateRange())) {
				allOverlap = allOverlap && true;
			} else {
				allOverlap = false;
				break;
			}

		}

		assertEquals(true, allOverlap);

	}

	@Test
	public void isBooked() {

		// create a customer and set up system
		TestData_FindQuote.setUpProvider(20);
		TestData_FindQuote.createCustomer();

		// get quotes for the customer
		TestData_FindQuote.customer.BrowseQuotes();
		TestData_FindQuote.customer.setChosenIndex(1);
		TestData_FindQuote.customer.selectQuote();
		// Book the quote
		booking = new Booking(TestData_FindQuote.customer);
		// set up delivery service
		if (TestData_FindQuote.customer.getMode() == CollectionMode.DELIVERY) {
			DeliveryServiceFactory.setupMockDeliveryService();
			booking.setDeliveryService((MockDeliveryService) DeliveryServiceFactory.getDeliveryService());
		}
		// proceed to book quote
		booking.bookQuote();

		// extract bikes from the quote for testing
		ArrayList<Bike> bikes = new ArrayList<>();
		for (String bikeId : TestData_FindQuote.customer.getChosenQuote().getBikeIds())
			for (Bike bike : TestData_FindQuote.customer.getChosenQuote().getBikeProvider().getBikes()) {
				if (bike.getBikeId().equals(bikeId)) {
					bikes.add(bike);
					break; // go to next bike id
				}
			}

		boolean booked = true; // temporary variable

		BookingStatus bookingStatus = BookingStatus.BOOKED;

		assertEquals(bookingStatus, booking.getStatus());

		for (Bike bike : bikes) {
			if (bike.getBikeStatus() == BikeStatus.RESERVED)
				booked = booked && true;
			else {
				booked = false;
				break;
			}
		}

		assertEquals(true, booked);

		if (TestData_FindQuote.customer.getMode() == CollectionMode.DELIVERY) {

			booked = true;

			booking.onPickup();

			bookingStatus = BookingStatus.DISPATCHED;

			assertEquals(bookingStatus, booking.getStatus());

			for (Bike bike : bikes) {
				if (bike.getBikeStatus() == BikeStatus.PICKED_UP) {
					booked = booked && true;
				} else {
					booked = false;
					break;
				}
			}

			assertEquals(true, booked);

			booked = true;

			booking.onDropoff();

			bookingStatus = BookingStatus.DELIVERED;

			assertEquals(bookingStatus, booking.getStatus());

			for (Bike bike : bikes) {
				if (bike.getBikeStatus() == BikeStatus.HIRED) {
					booked = booked && true;
				} else {
					booked = false;
					break;
				}
			}

			assertEquals(true, booked);

		}
	}

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
		
		// partner could be used to test if the message is sent to the partner
		int index = 7;
		BikeProvider partnerProvider = QuoteFinder.getAllProviders().get(index);

		// Setup partner
		provider.setPartnerProvider(QuoteFinder.getAllProviders().get(index));
		partnerProvider.setPartnerProvider(provider);

		// System.out.println(provider.bookings.isEmpty());

		// tests if the status of all the returned bikes has been made available or if a
		// message has been sent to the partner of the return.
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

			assertEquals(true,
					provider.getPartnerProvider().getMessageFromPartner().contains(provider.getMessageToPartner()));

		}

	}

}
