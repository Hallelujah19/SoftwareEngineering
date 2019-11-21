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
	
	 for(int i=0;i<number;i++) {
		String name = "Type"+i;
		value = value.add(one);
		BikeType bikeType= new BikeType(name,value);
		typeList.add(bikeType);
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
	
	 for(int i=0;i<number;i++) {
		String id = "ID"+i;
		value = value.add(one);
		Bike bike= new Bike(id,value,typeList.get(i));
		bikeList.add(bike);
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
 
}
