import java.math.BigDecimal;

enum BikeStatus {

	HIRED, AVAILABLE, RESERVED

}

enum DepositStatus {
	
	RECEIVED, PENDING

}

public class Bike {

	private BikeStatus bikeStatus;
	private BikeType bikeType;
	private BigDecimal originalValue; // set to the initial replacement value
	private String bookingNumber; 
	private DateRange dateRange;
	private DepositStatus depositStatus;
	private String bikeId;
	
	public Bike(String bikeId, BigDecimal originalValue, BikeType bikeType) {
		 this.bikeId = bikeId;
		 this.originalValue = originalValue;
		 this.bikeType = bikeType;
	}
	
	public String getBikeId() {
		return bikeId;
	}
	
	public void setBikeId(String bikeId) {
		this.bikeId = bikeId;
	}
	
	public BikeType getType() {
		// TODO: Implement Bike.getType
		
		return this.bikeType;
	}
	
	public BikeStatus getBikeStatus() {
		return bikeStatus;
	}
	
	public BikeType getBikeType() {
		return this.bikeType;
	}
	
	public BigDecimal getOriginalValue() {
		return originalValue;
	}
	
	public String getBookingNumber() {
		return bookingNumber;
	}
	
	public DateRange getDateRange() {
		return dateRange;
	}

	public void setBikeStatus(BikeStatus bikeStatus) {
		this.bikeStatus = bikeStatus;
	}
	
	public void setBikeType(BikeType bikeType) {
		this.bikeType = bikeType;
	}
	
	public void setOriginalValue(BigDecimal originalValue) {
		this.originalValue = originalValue;
	}
	
	public void setBookingNumber(String bookingNumber) {
		this.bookingNumber = bookingNumber;
	}
	
	public void setDateRange(DateRange dateRange) {
		this.dateRange = dateRange;
	}
	
	public void setDepositstatus(DepositStatus depositstatus) {
		this.depositStatus = depositstatus;
	}
	
	public DepositStatus getDepositstatus() {
		return depositStatus;
	}
	
}
