import java.math.BigDecimal;
import java.time.LocalDate;

public class Quote {
	
	private BikeProvider bikeProvider;
	private BigDecimal totalPrice;
	private BigDecimal deposit;
	private LocalDate dateOfReturn;
	
	public Quote(BikeProvider bp, BigDecimal tp, BigDecimal d, LocalDate date) {
		bikeProvider = bp;
		totalPrice = tp;
		deposit = d;
		dateOfReturn = date;
	}
	
}
