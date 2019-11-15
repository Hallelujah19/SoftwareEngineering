import java.util.ArrayList;
import java.math.BigDecimal;
import java.util.HashMap;

public class BikeProvider {

	private String name;
	private HashMap<BikeType, Integer> stockOfBikes; // for the bike provider
	private ArrayList<Bike> bikes;
	private Location shopLocation;
	private OpeningHours openingHours;
	private BikeProvider partnerProvider;
	private String messageFromPartner;
	private String messageToPartner;
	private ArrayList<String> bookingNumbers; // store unique booking numbers
	private HashMap<BikeType, BigDecimal> dailyPricePerBikeType;
	private BigDecimal depositRate;
	private Collection<String> bikeTypes;

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
		return messageFromPartner;
	}

	public String getMessageToPartner() {
		return messageToPartner;
	}

	public HashMap<BikeType, BigDecimal> getDailyPricePerBikeType() {
		return dailyPricePerBikeType;
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
		this.messageFromPartner = messageFromPartner;
	}

	public void setMessageToPartner(String messageToPartner) {
		this.messageToPartner = messageToPartner;
	}

	public ArrayList<String> getBookingNumbers() {
		return bookingNumbers;
	}

	public void setBookingNumbers(ArrayList<String> bookingNumbers) {
		this.bookingNumbers = bookingNumbers;
	}

	public void setDailyPricePerBikeType(HashMap<BikeType, BigDecimal> dailyPricePerBikeType) {
		this.dailyPricePerBikeType = dailyPricePerBikeType;
	}
	
	public void addNewBikeType(String name,BigDecimal price,int number) {
		        BikeType newType= new BikeType(name,price);
		        bikeTypes.add(name);
	        	numOfBikesPerType.put(name,number);
	        	depositRatePerBikeType.put(name,depositRate);
	        	dailyPricePerBikeType.put(name, price);
	        	
	}


}
