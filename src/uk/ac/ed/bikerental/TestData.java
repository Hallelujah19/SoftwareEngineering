import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

public class TestData {
	
 static BigDecimal one = BigDecimal.valueOf(1);
 static ArrayList<BikeType> typeList = new  ArrayList<BikeType>();
 static ArrayList<Bike> bikeList = new  ArrayList<Bike>();
 static ArrayList<BigDecimal> priceList = new ArrayList<BigDecimal>();
 static ArrayList<DateRange> dateRanges = new ArrayList<>();
 
 static ArrayList<Bike> bikeList2 = new  ArrayList<Bike>();
 static ArrayList<Bike> bikeList5 = new  ArrayList<Bike>();
 static ArrayList<Bike> bikeList8 = new  ArrayList<Bike>();
 static ArrayList<Bike> bikeList15 = new  ArrayList<Bike>();

 static Customer testCustomer = new Customer();

 
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
 
 
 static void createPrices(int number) {
	 
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
		 LocalDate end = null;
		 
		 if (i % 4 == 0) {
		 end = start.plusDays(1);
		 DateRange dateRange = new DateRange(start,end);
		 dateRanges.add(dateRange);
		 bikeList.get(i).setDateRange(dateRange);
		 bikeList2.add(bikeList.get(i));
		 } else if (i % 4 == 1) {
		 end = start.plusDays(4);
		 DateRange dateRange = new DateRange(start,end);
		 dateRanges.add(dateRange);
		 bikeList.get(i).setDateRange(dateRange);
		 bikeList5.add(bikeList.get(i));
		 } else if (i % 4 == 2) {
		 end = start.plusDays(7);
		 DateRange dateRange = new DateRange(start,end);
		 dateRanges.add(dateRange);
		 bikeList.get(i).setDateRange(dateRange);
		 bikeList8.add(bikeList.get(i));
		 } else if (i % 4 == 3) {
		 end = start.plusDays(14);
		 DateRange dateRange = new DateRange(start,end);
		 dateRanges.add(dateRange);
		 bikeList.get(i).setDateRange(dateRange);
		 bikeList15.add(bikeList.get(i));

		 }
		
	 }
	 
	 
	 
	 
	 
 }
 
 
	
/* static void createShopLocations(int number) {
	 
	 for (int i = 0; i < number; i++) {
	 	String 
		 
	 }
	 
 }
	

 static void createProviders(int number) {
	 
	 for (int j = 0; j < number; j++0 {
	 	
	 }*/
 
}
