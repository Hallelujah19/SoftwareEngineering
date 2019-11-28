import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.time.DayOfWeek;

public class TestData_FindQuote {

	static BigDecimal one = BigDecimal.valueOf(1);
	static BigDecimal delta = BigDecimal.valueOf(0.01);
	static ArrayList<BikeType> typeList = new ArrayList<>();
	static ArrayList<ArrayList<Bike>> bikeList = new ArrayList<>();
	static ArrayList<BigDecimal> priceList = new ArrayList<>();
	static ArrayList<BigDecimal> rateList = new ArrayList<>();
	static ArrayList<DateRange> dateRanges = new ArrayList<>();
	static ArrayList<String> postCodes = new ArrayList<>();
	static ArrayList<String> addresses = new ArrayList<>();
	static OpeningHours openingHours = new OpeningHours();
	static ArrayList<String> providerNames = new ArrayList<>();
	static Customer customer = new Customer();
	static ArrayList<ArrayList<Bike>> special = new ArrayList<>();

	// default value for number is 20
	static void createBikesTypes(int number) {
		BigDecimal value = BigDecimal.valueOf(100);
		StringBuilder string = new StringBuilder();

		for (int i = 0; i < number; i++) {
			string.append("Type-" + i);
			value = value.add(one);
			BikeType bikeType = new BikeType(string.toString(), value);
			typeList.add(bikeType);
			string.delete(0, ("Type-" + i).length());

		}

	}

	// daily prices of bikes for a given provider
	static void createPrices(int number) {

		BigDecimal basePrice = BigDecimal.valueOf(1000);

		for (int i = 0; i < number; i++) {
			basePrice = basePrice.add(one);
			priceList.add(basePrice);
		}
	}

	// for deposit rates
	static void createRates(int number) {

		BigDecimal baseRate = BigDecimal.valueOf(0.10);

		for (int i = 0; i < number; i++) {
			baseRate = baseRate.add(delta);
			rateList.add(baseRate);
		}

	}

	static void createBikes(int number) {
		// bike's original value = replacement value
		BigDecimal value = BigDecimal.valueOf(100);
		StringBuilder string = new StringBuilder();
		Bike bike; // temporary variable
		ArrayList<Bike> array0 = new ArrayList<>();
		ArrayList<Bike> array1 = new ArrayList<>();
		
		// for general bikes
		for (int i = 0; i < number; i++) {
			ArrayList<Bike> array = new ArrayList<>();
			for (int j = 0; j < number; j++) {
				string.append("ID-" + i + "-" + j);
				value = value.add(one);
				bike = new Bike(string.toString(), value, typeList.get(j));
				array.add(bike);
				string.delete(0, ("ID-" + i + "-" + j).length());
			}
			bikeList.add(array);
			// reset value for next round of bikes
			value = BigDecimal.valueOf(100);
		}
		// for special bikes
		for (int i = 0; i < 2; i++) {
			bike = new Bike(("ID-17-S00" + (1+i)), typeList.get(3).getReplacementValue(),
						typeList.get(3));
			bike.setDateRange(new DateRange(LocalDate.of(2019, 6, 4),LocalDate.of(2019, 6, 18)));
			array0.add(bike);
		}
		for (int i = 0; i < 6; i++) {
			bike = new Bike(("ID-17-S00" + (3+i)), typeList.get(14).getReplacementValue(),
							typeList.get(14));
			bike.setDateRange(new DateRange(LocalDate.of(2019, 6, 15),LocalDate.of(2019, 6, 22)));
			array0.add(bike);
		}
		for (int i = 0; i < 4; i++) {
		   bike = new Bike(("ID-19-S00" + (1+i)), typeList.get(3).getReplacementValue(),
				   			typeList.get(3));
		   bike.setDateRange(new DateRange(LocalDate.of(2019, 6, 4),LocalDate.of(2019, 6, 18)));
		   array1.add(bike);
		}
		for (int i = 0; i < 5; i++) {
			bike = new Bike(("ID-19-S00" + (5+i)), typeList.get(14).getReplacementValue(),
							typeList.get(14));
			bike.setDateRange(new DateRange(LocalDate.of(2019, 6, 15),LocalDate.of(2019, 6, 22)));
			array1.add(bike);
		}
		special.add(array0);
		special.add(array1);
	}
	
	static void createDates(int number) {

		// We make each type to have the same date range
		for (int i = 0; i < number; i++) {

			for (int j = 0; j < number; j++) {

				LocalDate start = LocalDate.of(2019, 6, j + 1);
				LocalDate end;

				if (j % 4 == 0) {
					end = start.plusDays(1);
					bikeList.get(i).get(j).setDateRange(new DateRange(start, end));
				} else if (j % 4 == 1) {
					end = start.plusDays(4);
					bikeList.get(i).get(j).setDateRange(new DateRange(start, end));

				} else if (j % 4 == 2) {
					end = start.plusDays(7);
					bikeList.get(i).get(j).setDateRange(new DateRange(start, end));
				} else {
					end = start.plusDays(14);
					bikeList.get(i).get(j).setDateRange(new DateRange(start, end));
				}
				// add to static collection of date ranges
				if (!dateRanges.contains(new DateRange(start, end)))
					dateRanges.add(new DateRange(start, end));

			}
		}
	}

	static void createLocations(int number) {

		StringBuilder string = new StringBuilder();

		for (int i = 0; i < number - 1; i++) {
			string.append("EH" + (i / 10) + " " + (i % 10) + "YAW");
			postCodes.add(string.toString());
			string.delete(0, ("EH" + (i / 10) + " " + (i % 10) + "YAW").length());
		}
		postCodes.add("EH1 7AX");
	}

	// set customer details
	static void createCustomer() {

		customer.setStatus(CustomerStatus.LOCAL);
		customer.setMode(CollectionMode.DELIVERY);
		customer.setLocation(new Location("EH1 7KS", "Cowgate"));
		customer.setDateRange(new DateRange(LocalDate.of(2019, 6, 16), LocalDate.of(2019, 6, 18)));
		HashMap<String, Integer> map = new HashMap<>();
		map.put("Type-3", 2);
		map.put("Type-14", 1);
		customer.setBikesPerType(map);

	}

	static void setUpProvider(int number) {

		// initialise providers and add to the QuoteFinder
		TestData_FindQuote.createBikesTypes(number);
		TestData_FindQuote.createBikes(number);
		TestData_FindQuote.createPrices(number);
		TestData_FindQuote.createAddresses(number);
		TestData_FindQuote.createRates(number);
		TestData_FindQuote.createNames(number);
		TestData_FindQuote.createDates(number);
		TestData_FindQuote.createOpeningHours();
		TestData_FindQuote.createLocations(number);
		
		ArrayList<BikeProvider> bikeProviders = new ArrayList<>();
		BikeProvider bikeProvider; // temporary variable
		
		// provide general information for first 17 providers
		for (int i = 0; i < 17; i++) {
			bikeProvider = new BikeProvider(providerNames.get(i),
											new Location(postCodes.get(i), addresses.get(i)), openingHours);
			bikeProvider.setDepositRate(rateList.get(i));
			// add bike and extract bike type to add to provider database
			for (int j = 0; j < 4; j++) {
				bikeProvider.setBike(bikeList.get(i).get(i + j));
				bikeProvider.addBikeType(typeList.get(i + j), 1);
				bikeProvider.setDailyPricePerBikeType(typeList.get(i+j), priceList.get(i+j));
			}
			bikeProviders.add(bikeProvider);
		}
		// for the 18th provider
		bikeProvider = new BikeProvider(providerNames.get(17),
										new Location(postCodes.get(17), addresses.get(17)), openingHours);
		bikeProvider.setDepositRate(rateList.get(17));
		for (int i = 0; i < 2; i++) {
			bikeProvider.setBike(special.get(0).get(i));
			bikeProvider.addBikeType(typeList.get(3), 1);
			bikeProvider.setDailyPricePerBikeType(typeList.get(3), priceList.get(3));
		}
		for (int i = 2; i < 8; i++) {
			bikeProvider.setBike(special.get(0).get(i));
			bikeProvider.addBikeType(typeList.get(14), 1);
			bikeProvider.setDailyPricePerBikeType(typeList.get(14), priceList.get(14));
		}
		bikeProviders.add(bikeProvider);
		// for the 19th provider (general)
		bikeProvider = new BikeProvider(providerNames.get(18),
										new Location(postCodes.get(18), addresses.get(18)), openingHours);
		bikeProvider.setDepositRate(rateList.get(18));
		for (int i = 0; i < 12; i++) {
			bikeProvider.setBike(bikeList.get(18).get(i));
			bikeProvider.addBikeType(typeList.get(i), 1);
			bikeProvider.setDailyPricePerBikeType(typeList.get(i), priceList.get(i));
		}
		bikeProviders.add(bikeProvider);
		// for the 20th provider
		bikeProvider = new BikeProvider(providerNames.get(19),
		new Location(postCodes.get(19), addresses.get(19)), openingHours);
		bikeProvider.setDepositRate(rateList.get(19));
		for (int i = 0; i < 4; i++) {
			bikeProvider.setBike(special.get(1).get(i));
			bikeProvider.addBikeType(typeList.get(3), 1);
			bikeProvider.setDailyPricePerBikeType(typeList.get(3), priceList.get(3));
		}
		for (int i = 4; i < 9; i++) {
			bikeProvider.setBike(special.get(1).get(i));
			bikeProvider.addBikeType(typeList.get(14), 1);
			bikeProvider.setDailyPricePerBikeType(typeList.get(14), priceList.get(14));
		}
		bikeProviders.add(bikeProvider);
		// add list to static structure
		QuoteFinder.setAllProviders(bikeProviders);
	}
	
	static void createAddresses(int number) {

		StringBuilder string = new StringBuilder();

		for (int i = 0; i < number; i++) {
			string.append("address " + i);
			addresses.add(string.toString());
			string.delete(0, ("address " + i).length());
		}
	}

	static void createOpeningHours() {

		ArrayList<LocalTime> startingHours = new ArrayList<>();
		ArrayList<LocalTime> closingHours = new ArrayList<>();
		ArrayList<DayOfWeek> daysOfWeek = new ArrayList<>();
		// initialise days of the week
		for (int i = 1; i <= 5; i++) {
			daysOfWeek.add(DayOfWeek.of(i));
		}
		// initialise starting hours...
		for (int i = 1; i <= 5; i++) {
			startingHours.add(LocalTime.of(9, 0)); // 9:00 a.m.
		}
		// and closing hours
		for (int i = 1; i <= 5; i++) {
			closingHours.add(LocalTime.of(17, 0)); // 5:00 p.m.
		}
		openingHours.setStartingHours(startingHours);
		openingHours.setClosingHours(closingHours);
		openingHours.setDaysOfWeek(daysOfWeek);
		openingHours.setOpeningHours();
	}

	static void createNames(int number) {

		StringBuilder string = new StringBuilder();

		for (int i = 0; i < number; i++) {
			string.append("Provider " + i);
			providerNames.add(string.toString());
			string.delete(0, ("Provider " + i).length());
		}
	}
	
	public static void main(String[] args) {
		
		TestData_FindQuote.setUpProvider(20);
		TestData_FindQuote.createCustomer();
		
		ArrayList<Quote> quotesObtained = new ArrayList<>();
		
		
		for (Quote quote : QuoteFinder.filterQuotes(customer.getBikesPerType(),
				customer.getDateRange(), customer.getLocation()).values())
		quotesObtained.add(quote);
		
		for (Quote quote : quotesObtained)
			System.out.println(quote.toString());
		 
	}
}
