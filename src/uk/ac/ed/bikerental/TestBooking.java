import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.DisabledIf;
import org.junit.jupiter.api.condition.EnabledIf;

import java.util.ArrayList;

import org.junit.Test;

class TestBook {


	// create a customer who has a chosen quote which is to be booked
		Customer customer = TestData.testCustomer;//change this to TestData_Quote
		CollectionMode mode = customer.getMode();
		
	// Book the quote
		Booking book = new Booking(customer);
		
		ArrayList<Bike> bikes = customer.getChosenQuote().getBikeProvider().getBikes();

		boolean booked = true ;
		
	//tests  if all the bikes that the customer wants have been booked 
   
		@Test	
  void  testBooked(ArrayList<Bike> bikeList) {
	  
	  boolean booked = true ;
	  BookingStatus booking = BookingStatus.BOOKED;
	 
		 assertEquals(booking,book.getStatus());

	  
	  for(Bike bike:bikes)
				{
					if (bike.getBikeStatus() == BikeStatus.RESERVED) {
						booked = booked || true;
					} else {
						booked = booked ||false;
					}
				}
	
	 assertEquals(true,booked);
	 
	 /*if(customer.getMode() == CollectionMode.DELIVERY) {
	 for() {
		 
	 }
	 }*/
	
  }
      
      
      
   //Tests if pickup has been recorded  
		 @Test	
	     @EnabledIf("mode == CollectionMode.DELIVERY")      
  void  testOnPickUp(ArrayList<Bike> bikeList) {
    	  
    	  
    	  
    	  book.onPickup();
    	  
    	  boolean picked = true ;
    	  BookingStatus booking = BookingStatus.DISPATCHED;
    	 
    		 assertEquals(booking,book.getStatus());

    	  
    	  for(Bike bike:bikes)
    				{
    					if (bike.getBikeStatus() == BikeStatus.PICKED_UP) {
    						booked = booked || true;
    					} else {
    						booked = booked ||false;
    					}
    				}
    	
    	 assertEquals(true,booked);
    	 
    	 /*if(customer.getMode() == CollectionMode.DELIVERY) {
    	 for() {
    		 
    	 }
    	 }*/
    	
      }
      
   
      
      //  
      @Test	
      @EnabledIf("mode == CollectionMode.DELIVERY")
      void  testOnDropOff(ArrayList<Bike> bikeList) {
        	  
        	  
        	  
        	  book.onDropoff();
        	  
        	  boolean dropped = true ;
        	  BookingStatus booking = BookingStatus.DELIVERED;
        	 
        		 assertEquals(booking,book.getStatus());

        	  
        	  for(Bike bike:bikes)
        				{
        					if (bike.getBikeStatus() == BikeStatus.HIRED) {
        						booked = booked || true;
        					} else {
        						booked = booked ||false;
        					}
        				}
        	
        	 assertEquals(true,booked);
        	 
        	 /*if(customer.getMode() == CollectionMode.DELIVERY) {
        	 for() {
        		 
        	 }
        	 }*/
        	
          }
      
      
	  

    


  

  		








}
