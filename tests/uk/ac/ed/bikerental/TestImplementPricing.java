package uk.ac.ed.bikerental;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

class ImplementPricingTest {

	Location testLocation = new Location("testPostCode", "testAddress");
	OpeningHours testHours = new OpeningHours();
	BikeProvider testProvider = new BikeProvider("providerName", testLocation, testHours);

	ImplementPricing testImplementor = new ImplementPricing(testProvider);

	BigDecimal value = BigDecimal.valueOf(200);
	BikeType testBikeType = new BikeType("name", value);
	BigDecimal testPrice = BigDecimal.valueOf(100);

	
	@Test
	void SetDailyRentalPriceTest() {

		testImplementor.setDailyRentalPrice(testBikeType, testPrice);

		System.out.println(testProvider.getDailyPricePerBikeType(testBikeType));

		assertEquals(testPrice.stripTrailingZeros(),
				testProvider.getDailyPricePerBikeType(testBikeType).stripTrailingZeros());

	}


	
	@Test
	void calculatePriceTest() {

		BigDecimal five = BigDecimal.valueOf(5);
		BigDecimal eight = BigDecimal.valueOf(8);
		BigDecimal fifteen = BigDecimal.valueOf(15);
		BigDecimal two = BigDecimal.valueOf(2);

		BigDecimal decrement = BigDecimal.valueOf(1-0.05);
		BigDecimal decrement1 = BigDecimal.valueOf(1-0.1);
		BigDecimal decrement2 = BigDecimal.valueOf(1-0.15);
		BigDecimal decrement3 = BigDecimal.valueOf(1);
		
		BigDecimal test_raw_price = BigDecimal.valueOf(0);
		BigDecimal test_raw_price1 = BigDecimal.valueOf(0);
		BigDecimal test_raw_price2 = BigDecimal.valueOf(0);
		BigDecimal test_raw_price3 = BigDecimal.valueOf(0);

		
		TestData.createBikesTypes(20);

		for (BikeType bikeType : TestData.typeList) {
			testProvider.addNewBikeType(bikeType.getTypeName(), 1, bikeType.getReplacementValue());
		}

		TestData.createBikes(20);

		for (Bike bike : TestData.bikeList) {
			testProvider.setBike(bike);
		}
         
		TestData.createDates(20); 

		TestData.createPrices(20);
		
		for (int i = 0; i < 20; i++) {
			testProvider.setDailyPricePerBikeType(TestData.typeList.get(i), TestData.priceList.get(i));
		}

		//setting the ranges of dateRanges for bikes depending on the indices
		
		for (int i=0 ;i<20;i++) {
			testProvider.getBikes().get(i).setDateRange(TestData.dateRanges.get(i));
					}
		
		
		//calculating raw prices after grouping the bikes with the same dateRanges and calculating the rate for each group
		
		for (Bike bike : testProvider.getBikes()) {
			
			BigDecimal days = BigDecimal.valueOf(bike.getDateRange().getDuration());
			
			if(days.equals(two)) {
			test_raw_price3 = test_raw_price3.add(testProvider.getDailyPricePerBikeType(bike.getBikeType()).multiply(days));
			
			}else if(days.equals(five)) {
				test_raw_price = test_raw_price.add(testProvider.getDailyPricePerBikeType(bike.getBikeType()).multiply(days));
	
				
			}else if(days.equals(eight)) {
				test_raw_price1 = test_raw_price1.add(testProvider.getDailyPricePerBikeType(bike.getBikeType()).multiply(days));

			}else if(days.equals(fifteen)) {
				test_raw_price2 = test_raw_price2.add(testProvider.getDailyPricePerBikeType(bike.getBikeType()).multiply(days));

			}
			
			
		}
		
		
		BigDecimal result = test_raw_price.multiply(decrement);
		BigDecimal result1 = test_raw_price1.multiply(decrement1);
		BigDecimal result2 = test_raw_price2.multiply(decrement2);
		BigDecimal result3 = test_raw_price3.multiply(decrement3);
		
		
		assertEquals(result, testImplementor.calculatePrice(TestData.bikeList5, TestData.dateRanges.get(1)));
		assertEquals(result1, testImplementor.calculatePrice(TestData.bikeList8, TestData.dateRanges.get(2)));
		assertEquals(result2, testImplementor.calculatePrice(TestData.bikeList15, TestData.dateRanges.get(3)));
		assertEquals(result3, testImplementor.calculatePrice(TestData.bikeList2, TestData.dateRanges.get(0)));

	//	System.out.println(test_raw_price);
	}

}
