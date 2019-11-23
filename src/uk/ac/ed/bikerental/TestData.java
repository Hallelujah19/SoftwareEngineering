import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

public class TestData {
	
 static BigDecimal one = BigDecimal.valueOf(1);
 static ArrayList<BikeType> typeList = new  ArrayList<BikeType>();
 static ArrayList<Bike> bikeList = new  ArrayList<Bike>();
 static ArrayList<BigDecimal> priceList = new ArrayList<BigDecimal>();
 static ArrayList<DateRange> dateRanges = new ArrayList<>();
 
 static void createBikesTypes(int number) {
	 BigDecimal value = BigDecimal.valueOf(100);
	  StringBuilder string = new StringBuilder();
	 
	 for(int i=0;i<number;i++) {
		string.append("type"+i);
		value = value.add(one);
		BikeType bikeType= new BikeType(string.toString(),value);
		typeList.add(bikeType);
		string.delete(0,("type"+i).length());

	 }
	 
 }
 
 static void prices(int number) {
	 
	 BigDecimal basePrice =BigDecimal.valueOf(1000);

	 for(int i=0;i<number;i++) {
			basePrice = basePrice.add(one);
			priceList.add(basePrice);
		 }
 }
 
 static void createBikes(int number) {
	 BigDecimal value = BigDecimal.valueOf(500);
	 StringBuilder string = new StringBuilder();
	 
	 for(int i=0;i<number;i++) {
		string.append("ID"+i);
		value = value.add(one);
		Bike bike= new Bike(string.toString(),value,typeList.get(i));
		bikeList.add(bike);
		string.delete(0,("ID"+i).length());
	 }
	 
 }
 
 
 static void createDates(int number) {
	 
	 for(int i=0;i < number; i++) {
		 LocalDate start = LocalDate.of(2019,6,i+1);
		 LocalDate end = start.plusDays(4);
		 DateRange dateRange = new DateRange(start,end);
		 dateRanges.add(dateRange);
	 }
	 
 }

	
 static void createShopLocations(int number) {
	 
	 for (int i = 0; i < number; i++) {
	 	String 
		 
	 }
	 
 }
	

 static void createProviders(int number) {
	 
	 for (int j = 0; j < number; j++0 {
	 	
	 }
 
}
