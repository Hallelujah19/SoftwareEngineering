package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.Collection;

public interface PricingPolicy {
    
    public void setDailyRentalPrice(BikeType bikeType, BigDecimal dailyPrice);
    
    public BigDecimal calculatePrice(Collection<Bike> bikes, DateRange duration);
    
}
