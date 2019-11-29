package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

public class Quote {
	
	private BikeProvider bikeProvider;
	private BigDecimal totalPrice;
	private BigDecimal deposit;
	private LocalDate dateOfReturn;
	private ArrayList<String> bikeIds = new ArrayList<>();
	
	public Quote(BikeProvider bp, BigDecimal tp, BigDecimal d, LocalDate date, ArrayList<String> bikeIds) {
		bikeProvider = bp;
		totalPrice = tp;
		deposit = d;
		dateOfReturn = date;
		this.bikeIds = bikeIds;
	}
	
	public String toString() {
		String s = "";
		s += bikeProvider.getName(); s += "\n";
		s += totalPrice.toString(); s += "\n";
		s += deposit.toString(); s += "\n";
		s += dateOfReturn.toString(); s += "\n";
		return s;
	}
	
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	
	public BikeProvider getBikeProvider () {
		return this.bikeProvider;
	}
	
	public ArrayList<String> getBikeIds() {
		return bikeIds;
	}
}
