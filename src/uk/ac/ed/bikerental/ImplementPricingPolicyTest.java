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
	
	BigDecimal test_rate = BigDecimal.valueOf(0.05);
 	BigDecimal test_raw_price = BigDecimal.valueOf(0);
 	TestData.createBikeTypes(20);
	for (BikeType bikeType : TestData.typeList) {
		testProvider.addNewBikeType(bikeType.getTypeName(),1,BikeType.getReplacementValue());
		}
 	
	TestData.createBikes(20);
	testBikes.addAll(TestData.bikeList);
	for (Bike bike: TestData.bikeList) {
		testProvider.setBike(bike);
		}
 	
	testBikes.addAll(TestData.bikeList);
 	TestData.createDates(20); // duration is 5 days so rate is 0.05
	
	TestData.createPrices(20);
	 for(int i=0;i< 20;i++){
		 testProvider.setDailyPricePerBikeType(TestData.typeList.get(i),TestData.priceList.get(i));
		 }
	
 
 	for (Bike bike : testProvider.getBikes()) {
		
	test_raw_price = test_raw_price.add(testProvider.getDailyPricePerBikeType(bike.getBikeType()).multiply(BigDecimal.valueOf(5)));
	
	} 
	
	Result = test_raw_price.multiply(test_rate);
	
        AssertEquals(result,ImplementPricing.calculatePrice(TestData.bikeList,TestData.dateRanges.get(0)));
        
		
		
		
		
	}

}














}
