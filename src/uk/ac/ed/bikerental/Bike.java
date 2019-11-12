
import java.math.BigDecimal;
import java.time.LocalDate;

public class Bike {
	public static enum DepositStatus {
		RECIEVED, NA

	}

	public static enum BikeStatus {

		HIRED, AVAILABLE, RESERVED

	}

	private BikeStatus bikeStatus;
	private BikeType bikeType;
	private BigDecimal originalValue;
	private String bikeId;
	private DateRange dateRange;
	private DepositStatus depositStatus;

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

	public String getBikeId() {
		return bikeId;
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

	public void setBikeId(String bikeId) {
		this.bikeId = bikeId;
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
