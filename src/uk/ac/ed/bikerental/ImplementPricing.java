package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.Collection;

public class ImplementPricing implements PricingPolicy {
	
	//Instantiating the bikeProvider that is going to make use of the policy
	
	BikeProvider bikeProvider;	
	public ImplementPricing(BikeProvider bikeProvider) {
	    this.bikeProvider = bikeProvider;
	}	
	
	
	@Override
	public void setDailyRentalPrice(BikeType bikeType, BigDecimal dailyPrice) {
		
		bikeProvider.setDailyPricePerBikeType(bikeType,dailyPrice);

	}
	
	
	//calculates the price after the rate is chosen based on  the number of days of booking
	
	@Override
	public BigDecimal calculatePrice(Collection<Bike> bikes, DateRange dateRange) {
		
		Integer days = dateRange.getDuration();
		BigDecimal bigDays = new BigDecimal(days);

		BigDecimal decrement;
		BigDecimal rawPrice = BigDecimal.valueOf(0);
		double rate1 = 0.05;
		double rate2 = 0.1;
		double rate3 = 0.15;
		
		
		for (Bike bike : bikes) {
			rawPrice = rawPrice.add(bikeProvider.getDailyPricePerBikeType(bike.getBikeType()).multiply(bigDays));
		}
		
         assert(days>=0);
		
		if (days >= 3 && days <= 6) {
			decrement = BigDecimal.valueOf(1-rate1);
		} else if (days >= 7 && days <= 13) {
			decrement = BigDecimal.valueOf(1-rate2);
		} else if (days >= 14) {
			decrement = BigDecimal.valueOf(1-rate3);
		} else {
			decrement = BigDecimal.valueOf(1);
		}
		
		rawPrice = rawPrice.multiply(decrement);

		return rawPrice;

	}

}
