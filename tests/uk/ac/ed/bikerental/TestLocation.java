package uk.ac.ed.bikerental;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

class TestLocation {
	
	Location location ;
	Location location1 ;
	Location location2 ;
	
    @BeforeEach
    void setUp() throws Exception {
        // TODO: setup some resources before each test
    	 location = new Location("EH3 9QG","FountainBridge");
    	 location1 = new Location("EH1 1JT","Kinkaids Court");
         location2 = new Location("EH3 9RT","FountainBridge");
    
    }
    
    // TODO: put some tests here
      @Test 
   void testIsNearTo() { 
    	  
      	assertEquals(false,location.isNearTo(location1));
      	assertEquals(true,location.isNearTo(location2));
      	assertEquals(false,location1.isNearTo(location));
      	assertEquals(false,location1.isNearTo(location2));
      	assertEquals(true,location2.isNearTo(location));
      	assertEquals(false,location2.isNearTo(location1));
 
      }      	
        	   
}
