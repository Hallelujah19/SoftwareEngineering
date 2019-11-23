import java.math.BigDecimal;
import java.util.Collection;

public class ImplementPricing implements PricingPolicy {
	
	BikeProvider bikeProvider;
	
	public ImplementPricing(BikeProvider bikeProvider) {
	    this.bikeProvider = bikeProvider;
	}	
	
	
	@Override
	public void setDailyRentalPrice(BikeType bikeType, BigDecimal dailyPrice) {
		
	bikeProvider.setDailyPricePerBikeType.put(bikeType,dailyPrice);

	}
	
	@Override
	public BigDecimal calculatePrice(Collection<Bike> bikes, DateRange dateRange) {
		
		Integer days = dateRange.getDuration();
		BigDecimal bigDays = new BigDecimal(days);

		BigDecimal rate;
		BigDecimal rawPrice = BigDecimal.valueOf(0);
		
		
		for (Bike bike : bikes) {
			rawPrice = rawPrice.add(bikeProvider.getDailyPricePerBikeType(bike.getBikeType()).multiply(bigDays));
		}
		

		if (days >= 3 && days <= 6) {
			rate = BigDecimal.valueOf(0.05);
		} else if (days >= 7 && days <= 13) {
			rate = BigDecimal.valueOf(0.1);
		} else if (days >= 14) {
			rate = BigDecimal.valueOf(0.15);
		} else {
			rate = BigDecimal.valueOf(0);
		}
		
		rawPrice = rawPrice.multiply(rate);

		return rawPrice;

	}

}

BigDecimal test_rate = BigDecimal.valueOf(0.05);
 BigDecimal test_raw_price = BigDecimal.valueOf(0);
 TestData.createBikeTypes(20);
 TestData.createBikes(20);
 testBikes.add(TestData.bikeList);
 TestData.createDates(1); // duration is 5 days so rate is 0.05
 
 for (Bike bike : TestData.bikeList) {
	test_raw_price = test_raw_price.add(bikeProvider.getDailyPricePerBikeType(bike.getBikeType()).multiply(bigDays));
		} 

