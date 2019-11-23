import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

class ImplementPricingTest {
	
	Location testLocation = new Location("testPostCode","testAddress");
	OpeningHours testHours = new OpeningHours();
	BikeProvider testProvider=new BikeProvider("providerName",testLocation,testHours);
	
	ImplementPricing testImplementor=new ImplementPricing(testProvider);
	
	BigDecimal value=BigDecimal.valueOf(200); 
	BikeType testBikeType=new BikeType("name",value);
	BigDecimal testPrice=BigDecimal.valueOf(100); 

	@Test
	void SetDailyRentalPriceTest() {
		
		 testImplementor.setDailyRentalPrice(testBikeType,testPrice);
		 System.out.println(testProvider.getDailyPricePerBikeType(testBikeType));
		assertEquals( testPrice.stripTrailingZeros(),
				testProvider.getDailyPricePerBikeType(testBikeType).stripTrailingZeros());
		
		//fail("Not yet implemented");
	}
	
	@Test
	void calculatePriceTest() {
	
		
		
		
	}

}














}
