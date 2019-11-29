import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

public class TestBooking {

	boolean booked = true;
	CollectionMode mode;
	Booking booking;

	// tests if all the bikes that the customer wants have been booked

	@BeforeEach
	void setup() {

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
		booking.bookQuote();

		ArrayList<Bike> bikes = new ArrayList<>();
		for (String bikeId : TestData_FindQuote.customer.getChosenQuote().getBikeIds())
			for (Bike bike : TestData_FindQuote.customer.getChosenQuote().getBikeProvider().getBikes()) {
				if (bike.getBikeId().equals(bikeId)) {
					bikes.add(bike);
					break; // go to next bike id
				}
			}

		boolean booked = true;
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

		if (mode == CollectionMode.DELIVERY) {
			
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

}
