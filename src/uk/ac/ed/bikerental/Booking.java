import java.util.ArrayList;

public class Booking {

	public static enum BookingStatus {
		BOOKED, DISPATCHED, DELIVERED
	}

	private BookingStatus bookingStatus;
	private String bookingNumber;
	private boolean isBooked;
	private ArrayList<Quote> quotes; // set from customer attribute
	private Customer customer;
	
	public BookingStatus getBookingStatus() {
		return bookingStatus;
	}
	
	public void setBookingStatus(BookingStatus bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	public String getBookingNumber() {
		return bookingNumber;
	}

	public void setBookingNumber(String bookingNumber) {
		this.bookingNumber = bookingNumber;
	}

	public boolean isBooked() {
		return isBooked;
	}

	public void setBooked(boolean isBooked) {
		this.isBooked = isBooked;
	}

}
