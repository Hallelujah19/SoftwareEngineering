package uk.ac.ed.bikerental;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.Test;

public class TestGetQuote {
	

//tests if the returned quote has the the correct results by comparing against the requirements	according to the hierarchy of the filtration 
	@Test
public void TestGetQuote() {
		
		// create a customer and set up system
					TestData_FindQuote.setUpProvider(20);
					TestData_FindQuote.createCustomer();

		// get quotes for the customer
					TestData_FindQuote.customer.BrowseQuotes();
					TestData_FindQuote.customer.setChosenIndex(1);
					TestData_FindQuote.customer.selectQuote();
		
		
		Quote result = TestData_FindQuote.customer.getChosenQuote();
		HashMap<String,Integer> bikeTypeResult = new HashMap<>();
		
		System.out.println(result == null);
		
		assertEquals(true,TestData_FindQuote.customer.getLocation().isNearTo(result.getBikeProvider().getShopLocation()));
		
		//create a number per Biketype for the quote to compare to the customers initial attribute(request)
		for(Bike bike :result.getBikeProvider().getBikes()) {
			for(String bikeId:result.getBikeIds()) {
				if(bike.getBikeId().equals(bikeId))	{
				  if(bikeTypeResult.containsKey(bike.getBikeType().getTypeName())) {
					  bikeTypeResult.put(bike.getBikeType().getTypeName(), bikeTypeResult.get(bike.getBikeType().getTypeName()).intValue()+1);
				  }else
					bikeTypeResult.put(bike.getBikeType().getTypeName(),1);
			
				}
				
			}
		}
		
		//check if the quote has enough of the requested bikes
      
		assertEquals(TestData_FindQuote.customer.getBikesPerType().size(),bikeTypeResult.size());
		//checks if the types match ->
		assertEquals(true,bikeTypeResult.keySet().containsAll(TestData_FindQuote.customer.getBikesPerType().keySet()));
		//checks if the types match <-
		assertEquals(true,TestData_FindQuote.customer.getBikesPerType().keySet().containsAll(bikeTypeResult.keySet()));

		
		//tests if all the types
		boolean allMatch = true;
		
		for(String type:TestData_FindQuote.customer.getBikesPerType().keySet()) {
			for(String resultType:bikeTypeResult.keySet()) {
				if(type.equals(resultType)) {
					allMatch = allMatch && (TestData_FindQuote.customer.getBikesPerType().get(type) == bikeTypeResult.get(resultType));
					if(allMatch == false) {
						break;
					}
				}
				
			}
			
		}
		
		assertEquals(true,allMatch );
		
		//checks if the dateranges of bikes overlaps with the customer required range
		boolean allOverlap = true;
		
		for(Bike bike :result.getBikeProvider().getBikes()) {
			if(TestData_FindQuote.customer.getDateRange().overlaps(bike.getDateRange())) {
				allOverlap = allOverlap && true;
			} else {		
				allOverlap = false;
				break;
			}

			
			
		 }
		
		
		assertEquals(true,allOverlap );
		
		
		
		
	} 

}
