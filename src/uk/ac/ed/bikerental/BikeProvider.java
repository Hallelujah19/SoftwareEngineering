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

	public HashMap<BikeType, BigDecimal> getDailyPricePerBikeType(BikeType bikeType) {
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
		this.messageFromPartner = messageFromPartner;
	}

	public void setMessageToPartner(String messageToPartner) {
		String beginning = "We have received your bike with ID :";
		String bikeId = messageToPartner;
		String message = beginning + bikeId;
		this.messageToPartner = message;
	}

	public ArrayList<String> getBookingNumbers() {
		return bookingNumbers;
	}

	public void setBookingNumbers(ArrayList<String> bookingNumbers) {
		this.bookingNumbers = bookingNumbers;
	}

	public void setDailyPricePerBikeType(BikeType biketype, BigDecimal price) {
		this.dailyPricePerBikeType.put(biketype,price);
	}
	
	public void addNewBikeType(String name,BigDecimal price,int number) {
		        BikeType newType= new BikeType(name,price);
		        bikeTypes.add(name);
	        	stockOfBikes.put(newType,number);
	        	depositRatePerBikeType.put(newType,depositRate);
	        	dailyPricePerBikeType.put(newType, price);
	        	
	}
	
	public void stockUpdate(BikeType type,int number){
		stockOfBikes.replace(type,stockOfBikes.get(type)+number);
	}
	
	public void registerReturn(String bikeId) {
	
		for(bike : bikes){
			if(bike.getBikeId().equals(bikeId)){
			    bike.setBikeStatus(BikeStatus.AVAILABLE);
			} 
		}
	}
	
	public void registerReturnToPartner(String bikeId) {
	     https://www.geeksforgeeks.org/bigdecimal-class-java/
		partnerProvider.setMessageFromPartner(this.setMessageToPartner(bikeId));
		
	}
	
	


}
