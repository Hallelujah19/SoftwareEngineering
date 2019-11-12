
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class QuoteFinder {
	
	ArrayList<BikeProvider> allProviders = new ArrayList<>();
	ArrayList<BikeType> bikeTypes = new ArrayList<>();
	
	public HashMap<Integer,Quote> filterQuotes(HashMap<String, Integer> numberOfBikesPerType,DateRange dateRange,Location location){
		
		HashMap<Integer,Quote> indexedListOfQuotes = new HashMap<>();
		ArrayList<Bike> allBikes = new ArrayList<>(); // all bikes for a given provider
		ArrayList<Bike> potentialBikes = new ArrayList<>(); // bikes whose types match those
															// requested by the customer
		ArrayList<Bike> candidateBikes = new ArrayList<>(); // bikes which are available for the customer
		ArrayList<BikeProvider> bikeProviders = new ArrayList<>();
		Quote quote; // temporary variable for storing a newly found quote
		Integer index = 0; // for indexing hash map
		
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
			for (String s : numberOfBikesPerType.keySet()) {
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
			if (checkBikeCount(potentialBikes,numberOfBikesPerType))
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
			if (checkBikeCount(candidateBikes, numberOfBikesPerType))
				break;
			// create a quote for this provider
			quote = createQuote(bikeProviders.get(i),numberOfBikesPerType,candidateBikes,dateRange);
			indexedListOfQuotes.put(index, quote);
			index++;
		}
		return indexedListOfQuotes;
	}
	
	public Quote createQuote(BikeProvider bp, HashMap<String, Integer> map, ArrayList<Bike> bikes, DateRange dateRange) {
		
		Quote quote;
		BigDecimal totalPrice = BigDecimal.valueOf(0);
		BigDecimal deposit = BigDecimal.valueOf(0);
		LocalDate dateOfReturn;
		BikeType bikeType = null; // temporary variable
		BigDecimal dailyPrice = BigDecimal.valueOf(0); // temporary variable
		// calculate  deposit
		for (Bike bike : bikes) {
			deposit = deposit.add(bp.getDepositRate().multiply(bike.getBikeType().getReplacementValue()));
		}
		// calculate totalPrice per day
		for (String s : map.keySet()) {
			// find bike type using its name
			for (BikeType biketype : bp.getStockOfBikes().keySet())
				if (biketype.getTypeName().equals(s))
					bikeType = biketype;
			dailyPrice = bp.getDailyPricePerBikeType().get(bikeType);
			// filter bikes by type and add daily price to total price
			for (Bike bike : bikes) {
				if (bike.getBikeType().equals(bikeType)) {
				   totalPrice = totalPrice.add(dailyPrice);
				}
			}
		}
		double days = (double) dateRange.getDuration();
		totalPrice = totalPrice.multiply(BigDecimal.valueOf(days));
		dateOfReturn = dateRange.getEnd();
		// create quote
		quote = new Quote(bp,totalPrice,deposit,dateOfReturn);
		return quote;
	}
	
	public Boolean checkBikeCount(ArrayList<Bike> bikes, HashMap<String, Integer> map) {
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
			||	bikeCount.compareTo(map.get(s)) == 1)
				continue; // go to next type
			else
				notEnough = true; // set flag
		}
		return notEnough;
	}
	
	public ArrayList<BikeProvider> getAllProviders() {
		return allProviders;
	}

	public void setAllProviders(ArrayList<BikeProvider> allProviders) {
		this.allProviders = allProviders;
	}

	public ArrayList<BikeType> getBikeTypes() {
		return bikeTypes;
	}

	public void setBikeTypes(ArrayList<BikeType> bikeTypes) {
		this.bikeTypes = bikeTypes;
	}

}


