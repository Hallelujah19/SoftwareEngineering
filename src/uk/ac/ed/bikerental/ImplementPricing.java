import java.math.BigDecimal;
import java.util.Collection;

public class ImplementPricing implements PricingPolicy {
	

	@Override
	public void setDailyRentalPrice(BikeType bikeType, BigDecimal dailyPrice) {
		
		bikeType.setDailyPrice(dailyPrice);

	}

	@Override
	public BigDecimal calculatePrice(Collection<Bike> bikes, DateRange duration) {
		long days = duration.toDays();
		BigDecimal bigDays = new BigDecimal(days);

		BigDecimal rate;
		BigDecimal rawPrice = null;
		

		for (Bike bike : bikes) {
			rawPrice = rawPrice.add(bike.getBikeType().getDailyPrice()).multiply(bigDays);
		}
		

		if (days >= 3 && days <= 6) {
			rate = new BigDecimal("0.05");
		} else if (days >= 7 && days <= 13) {
			rate = new BigDecimal("0.1");
		} else if (days >= 14) {
			rate = new BigDecimal("0.15");
		} else {
			rate = new BigDecimal("0");
		}

		rawPrice = rawPrice.multiply(rate);

		return rawPrice;

	}

}
