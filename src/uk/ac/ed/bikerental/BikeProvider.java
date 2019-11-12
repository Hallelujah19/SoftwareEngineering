
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
	private Collection<String> idsOfReturnedBikes;
	private HashMap<BikeType,Integer> numOfBikesPerType;
	private HashMap<BikeType, BigDecimal> dailyPricePerBikeType;
	private BigDecimal depositRate;
	
	public BikeProvider (String name, Location shopLocation,OpeningHours openingHours) {
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
	public Collection<String> getIdsOfReturnedBikes() {
		return idsOfReturnedBikes;
	}
	public HashMap<BikeType, Integer> getNumOfBikesPerType() {
		return numOfBikesPerType;
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
	public void setBikes(Collection<Bike> bikes) {
		this.bikes = bikes;
	}
	public void updateShopLocation(Location shopLocation) {
		this.shopLocation = shopLocation;
	}
	public void updateOpeningHours(OpeningHours openingHours) {
		this.openingHours = openingHours;
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
	public void setIdsOfReturnedBikes(Collection<String> idsOfReturnedBikes) {
		this.idsOfReturnedBikes = idsOfReturnedBikes;
	}
	public void setNumOfBikesPerType(HashMap<BikeType, Integer> numOfBikesPerType) {
		this.numOfBikesPerType = numOfBikesPerType;
	}
	public void setDailyPricePerBikeType(HashMap<BikeType, BigDecimal> dailyPricePerBikeType) {
		this.dailyPricePerBikeType = dailyPricePerBikeType;
	}
	
}
