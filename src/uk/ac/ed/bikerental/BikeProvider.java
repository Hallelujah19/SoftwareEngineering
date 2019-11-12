import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;

public class BikeProvider {
	
	private String name;
	private HashMap<BikeType,Integer> stockOfBikes; 
	private Collection<Bike> bikes;
	private Location shopLocation;
	private OpeningHours openingHours;
	private BikeProvider partnerProvider;
	private String messageFromPartner;
	private String messageToPartner;
	private Collection<String> idOfReturnedBikes;
	private HashMap<BikeType,Integer> numOfBikesPerType;
	private HashMap<BikeType,Integer> dailyPricePerBikeType;
	private HashMap<BikeType,Integer> depositRatePerBikeType;
	
	public String getName() {
		return name;
	}
	public HashMap<BikeType, Integer> getStockOfBikes() {
		return stockOfBikes;
	}
	public Collection<Bike> getBikes() {
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
	public Collection<String> getIdOfReturnedBikes() {
		return idOfReturnedBikes;
	}
	public HashMap<BikeType, Integer> getNumOfBikesPerType() {
		return numOfBikesPerType;
	}
	public HashMap<BikeType, Integer> getDailyPricePerBikeType() {
		return dailyPricePerBikeType;
	}
	public HashMap<BikeType, Integer> getDepositRatePerBikeType() {
		return depositRatePerBikeType;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setStockOfBikes(HashMap<BikeType, Integer> stockOfBikes) {
		this.stockOfBikes = stockOfBikes;
	}
	public void setBikes(Collection<Bike> bikes) {
		this.bikes = bikes;
	}
	public void setShopLocation(Location shopLocation) {
		this.shopLocation = shopLocation;
	}
	public void setOpeningHours(OpeningHours openingHours) {
		this.openingHours = openingHours;
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
	public void setIdOfReturnedBikes(Collection<String> idOfReturnedBikes) {
		this.idOfReturnedBikes = idOfReturnedBikes;
	}
	public void setNumOfBikesPerType(HashMap<BikeType, Integer> numOfBikesPerType) {
		this.numOfBikesPerType = numOfBikesPerType;
	}
	public void setDailyPricePerBikeType(HashMap<BikeType, Integer> dailyPricePerBikeType) {
		this.dailyPricePerBikeType = dailyPricePerBikeType;
	}
	public void setDepositRatePerBikeType(HashMap<BikeType, Integer> depositRatePerBikeType) {
		this.depositRatePerBikeType = depositRatePerBikeType;
	}
	
	

}
