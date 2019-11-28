import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class QuoteFinder {
	
	private static ArrayList<BikeProvider> allProviders = new ArrayList<>();
	private static ArrayList<BikeType> bikeTypes = new ArrayList<>();
	//the number of requests that have been made
	static int NumberOfRequests = 0;
	

	
	public static HashMap<Integer,Quote> filterQuotes(HashMap<String, Integer> bikesPerType,DateRange dateRange,Location location){
		
		
		//update requests
		NumberOfRequests+= 1;
		
		HashMap<Integer,Quote> listOfQuotes = new HashMap<>();
		ArrayList<Bike> allBikes = new ArrayList<>(); // all bikes for a given provider
		ArrayList<Bike> potentialBikes = new ArrayList<>(); // bikes whose types match those
															// requested by the customer
		ArrayList<Bike> candidateBikes = new ArrayList<>(); // bikes which are available for the customer
		ArrayList<Bike> requestedBikes = new ArrayList<>(); // final list of bikes returned to the customer
		ArrayList<BikeProvider> bikeProviders = new ArrayList<>();
		Quote quote; // temporary variable for storing a newly found quote
		Integer index = 1; // for indexing hash map
		
		// filter bike providers that are close to the customer's location
		for (BikeProvider bp : allProviders) {
			if (bp.getShopLocation().isNearTo(location))
				bikeProviders.add(bp);
		}
		// no quotes if no provider is close to customer's location
		if (bikeProviders.isEmpty())
			return null;
		for (int i = 0; i < bikeProviders.size(); i++) {
			allBikes = bikeProviders.get(i).getBikes(); // get their collection of bikes
			potentialBikes = new ArrayList<>();
			// filter by bike types
			for (String s : bikesPerType.keySet()) {
				for (Bike bike : allBikes) {
					if (bike.getType().getTypeName().equals(s))
						potentialBikes.add(bike); // add to collection if types match
				}
			}
			// go to next provider if no bike is found
			if (potentialBikes.isEmpty())
				break;
			// check if numbers are sufficient and
			// go to next provider if bikeCount per type is not enough
			if (checkBikeCount(potentialBikes,bikesPerType))
				break;
			// check for availability of bikes for each type
			for (Bike bike : potentialBikes) {
				if (!dateRange.overlaps(bike.getDateRange()))
					candidateBikes.add(bike); // add to collection if no overlaps
			}
			// go to next provider if no bike is available
			if (candidateBikes.isEmpty())
				break;
			// check bikeCount for each type in available bikes and
			// go to next provider if bikeCount per type is not enough
			if (checkBikeCount(candidateBikes, bikesPerType))
				break;
			// get the required number of bikes requested
			requestedBikes = getRequiredNumber(candidateBikes,bikesPerType);
			quote = createQuote(bikeProviders.get(i),bikesPerType,requestedBikes,dateRange);
			listOfQuotes.put(index, quote);
			index++;
		}
		return listOfQuotes;
	}
	
	public static Quote createQuote(BikeProvider bp, HashMap<String, Integer> map, ArrayList<Bike> bikes, DateRange dateRange) {
		
		Quote quote;
		BigDecimal totalPrice = BigDecimal.valueOf(0);
		BigDecimal deposit = BigDecimal.valueOf(0);
		LocalDate dateOfReturn;
		ArrayList<String> bikeIds = new ArrayList<>();
		BikeType bikeType = null; // temporary variable
		BigDecimal dailyPrice = BigDecimal.valueOf(0); // temporary variable
		// calculate  deposit
		for (Bike bike : bikes) {
			deposit = deposit.add(bp.getDepositRate().multiply(bike.getBikeType().getReplacementValue()));
		}
		// calculate totalPrice per day per bike type
		for (String s : map.keySet()) {
			BigDecimal dailyPrice = BigDecimal.valueOf(0);
			// filter bikes by type and add daily price to total price
			for (BikeType biketype : bp.getStockOfBikes().keySet())
				if (biketype.getTypeName().equals(s))
					dailyPrice = dailyPrice.add(bp.getDailyPricePerBikeType(biketype));
			// multiply by the number of bikes requested
			dailyPrice = dailyPrice.multiply(BigDecimal.valueOf(map.get(s)));
			totalPrice = totalPrice.add(dailyPrice);
		}
		double days = (double) dateRange.getDuration();
		totalPrice = totalPrice.multiply(BigDecimal.valueOf(days));
		dateOfReturn = dateRange.getEnd();
		for (Bike bike : bikes)
			bikeIds.add(bike.getBikeId());
		// create quote
		quote = new Quote(bp,totalPrice,deposit,dateOfReturn,bikeIds);
		return quote;
	}
	
	public static Boolean checkBikeCount(ArrayList<Bike> bikes, HashMap<String, Integer> map) {
		
		Boolean notEnough = false;
		Integer bikeCount = 0;
		for (String s : map.keySet()) {
			bikeCount = 0;
			// check bikeCount for each type in potentialBikes
			for (Bike bike : bikes) {
				if (bike.getType().getTypeName().equals(s))
					bikeCount++;
			}
			if (bikeCount.compareTo(map.get(s)) == 0
			||	bikeCount.compareTo(map.get(s))  > 0)
				continue; // go to next type
			else
				notEnough = true; // set flag
		}
		return notEnough;
	}
	
	public static ArrayList<Bike> getRequiredNumber(ArrayList<Bike> bikes, HashMap<String, Integer> map) {
		
		Integer bikeCount;
		ArrayList<Bike> requestedBikes = new ArrayList<>();
		// get required number of bikes
		for (String s : map.keySet()) {
			bikeCount = 0;
			for (Bike bike : bikes) {
			 	if (bike.getBikeType().getTypeName().equals(s)) {
			 		if (bikeCount.compareTo(map.get(s)) < 0) { // if not yet equal to required number
			 			requestedBikes.add(bike); 			   // add to collection
			 			bikeCount++;
			 		}
			 		else
			 			break; // stop and go to next bike type
			 	}
			}
		}
		return requestedBikes;
	}
	
	public static ArrayList<BikeProvider> getAllProviders() {
		return allProviders;
	}
	
	public void setAllProviders(ArrayList<BikeProvider> allProviders) {
		QuoteFinder.allProviders = allProviders;
	}
	
	public static ArrayList<BikeType> getBikeTypes() {
		return bikeTypes;
	}
	
	public void setBikeTypes(ArrayList<BikeType> bikeTypes) {
		QuoteFinder.bikeTypes = bikeTypes;
	}

}
