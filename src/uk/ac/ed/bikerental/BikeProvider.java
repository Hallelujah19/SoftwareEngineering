package uk.ac.ed.bikerental;

import java.util.ArrayList;
import java.math.BigDecimal;
import java.util.HashMap;

public class BikeProvider {

	private String name;
	private HashMap<BikeType, Integer> stockOfBikes = new HashMap<>(); // for the bike provider
	private ArrayList<Bike> bikes = new ArrayList<>();
	private Location shopLocation;
	private OpeningHours openingHours;
	private BikeProvider partnerProvider ;
	private String messageFromPartner  = "";
	private String messageToPartner = "";
	private ArrayList<String> bookingNumbers = new ArrayList<>(); // store unique booking numbers
	private HashMap<BikeType, BigDecimal> dailyPricePerBikeType = new HashMap<>();
	private BigDecimal depositRate;
	private ArrayList<String> bikeTypes = new ArrayList<>();
	// A list of bookings for these quotes
	private ArrayList<Booking> bookings = new ArrayList<>();

	public ArrayList<Booking> getBookings() {
		return bookings;
	}

	public BikeProvider(String name, Location shopLocation, OpeningHours openingHours) {
		this.name = name;
		this.shopLocation = shopLocation;
		this.openingHours = openingHours;
	}

	public String getName() {
		return name;
	}

	public HashMap<BikeType, Integer> getStockOfBikes() {
		return stockOfBikes;
	}

	public ArrayList<Bike> getBikes() {
		return bikes;
	}

	public Location getShopLocation() {
		return shopLocation;
	}

	public OpeningHours getOpeningHours() {
		return openingHours;
	}

	public BikeProvider getPartnerProvider() {
		return partnerProvider;
	}

	public String getMessageFromPartner() {
		return ""+ messageFromPartner;
	}

	public String getMessageToPartner() {
		return ""+ messageToPartner;
	}

	public BigDecimal getDailyPricePerBikeType(BikeType bikeType) {
		return dailyPricePerBikeType.get(bikeType);
	}

	public void updateName(String name) {
		this.name = name;
	}

	public void setStockOfBikes(HashMap<BikeType, Integer> stockOfBikes) {
		this.stockOfBikes = stockOfBikes;
	}

	public void setBike(Bike bike) {
		this.bikes.add(bike);
	}

	public void updateShopLocation(Location shopLocation) {
		this.shopLocation = shopLocation;
	}

	public void updateOpeningHours(OpeningHours openingHours) {
		this.openingHours = openingHours;
	}

	public void updateBikeStatus(String bikeId, BikeStatus bikeStatus) {
		for (int i = 0; i < bikes.size(); i++) {
			if (bikes.get(i).getBikeId().equals(bikeId)) {
				bikes.get(i).setBikeStatus(bikeStatus);
				break;
			}
		}
	}

	public BigDecimal getDepositRate() {
		return depositRate;
	}

	public void setDepositRate(BigDecimal depositRate) {
		this.depositRate = depositRate;
	}

	public void setPartnerProvider(BikeProvider partnerProvider) {
		this.partnerProvider = partnerProvider;
	}

	public void setMessageFromPartner(String messageFromPartner) {
		this.messageFromPartner = ""+ messageFromPartner;
	}

	public void setMessageToPartner(String bookingNumber) {
		String beginning = "We have received your bikes with Booking Number : ";
		String message = beginning + bookingNumber + "\n";
		this.messageToPartner = message;
	}

	public ArrayList<String> getBookingNumbers() {
		return bookingNumbers;
	}

	public void setBookingNumbers(ArrayList<String> bookingNumbers) {
		this.bookingNumbers = bookingNumbers;
	}

	public void setDailyPricePerBikeType(BikeType biketype, BigDecimal price) {
		this.dailyPricePerBikeType.put(biketype, price);
	}

	// this is done whenever a type is added
	public void addBikeType(BikeType bikeType, int number) {
		
		// if new bike type
		if (!bikeTypes.contains(bikeType.getTypeName()))
			bikeTypes.add(bikeType.getTypeName()); // add if absent
		if (stockOfBikes.containsKey(bikeType))
			stockOfBikes.replace(bikeType, stockOfBikes.get(bikeType) + number);
		else
			stockOfBikes.put(bikeType, number);
		// check if type is in global type list
		// if present ignore, else add
		ArrayList<String> typeNames = new ArrayList<>();
		for (BikeType biketype : QuoteFinder.getBikeTypes()) {
			typeNames.add(biketype.getTypeName());
		}
		if (!typeNames.contains(bikeType.getTypeName()))
			QuoteFinder.getBikeTypes().add(bikeType);
	}
	
	// returns true if the booking was made with this provider and false if it was
	// made with the partner
	public boolean registerReturn(String bookingNumber) {

		// If the booking is done with this provider the booking is removed from the
		// providers records
		// and the bike statuses of the bikes booked are reset
		boolean isProviders = false;

		for (Booking theBooking : bookings) {
			if (theBooking.getBookingNumber().equals(bookingNumber)) {
				isProviders = isProviders || true;

				for (String bikeID : theBooking.getQuote().getBikeIds()) {
					for (Bike bike : theBooking.getQuote().getBikeProvider().getBikes()) {
						if (bike.getBikeId().equals(bikeID)) {
							bike.setBikeStatus(BikeStatus.AVAILABLE);
						}
					}
				}

				theBooking.setStatus(BookingStatus.RETURNED);
				break;
			}

		}

		if (isProviders != true) {
			registerReturnToPartner(bookingNumber);
		}

		return isProviders;
		// If the bike is in this providers stock then set its status to available else
		// send message to partner about the bike

	}
	
	public ArrayList<String> getBikeTypes() {
		return bikeTypes;
	}
	
	public void registerReturnToPartner(String bookingNumber) {

		this.setMessageToPartner(bookingNumber);
		partnerProvider.setMessageFromPartner(" ");
		//System.out.println(partnerProvider.getMessageFromPartner()+ "message is this");
		partnerProvider.setMessageFromPartner(partnerProvider.getMessageFromPartner() + this.getMessageToPartner());

	}

}
