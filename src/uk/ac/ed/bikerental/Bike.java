
import java.time.LocalDate;

public class Bike {
	public static enum DepositStatus
	{
		RECIEVED,NA

		}

	public static enum BikeStatus{
		
		HIRED,AVAILABLE,RESERVED
		
	}
	  
	 
	  private BikeStatus bikeStatus;
	  private BikeType bikeType;
	  private Double originalValue;
	  private String bikeId;
	  private LocalDate availableFromDate;
	  private DepositStatus depositstatus;
	  
		  
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


		public Double getOriginalValue() {
			return originalValue;
		}


		public String getBikeId() {
			return bikeId;
		}


		public LocalDate getAvailableFromDate() {
			return availableFromDate;
		}


		


		public void setBikeStatus(BikeStatus bikeStatus) {
			this.bikeStatus = bikeStatus;
		}


		public void setBikeType(BikeType bikeType) {
			this.bikeType = bikeType;
		}


		public void setOriginalValue(Double originalValue) {
			this.originalValue = originalValue;
		}


		public void setBikeId(String bikeId) {
			this.bikeId = bikeId;
		}


		public void setAvailableFromDate(LocalDate availableFromDate) {
			this.availableFromDate = availableFromDate;
		}


		public void setDepositstatus(DepositStatus depositstatus) {
			this.depositstatus = depositstatus;
		}


		public DepositStatus getDepositstatus() {
			return depositstatus;
		}
	    
	    
	    
	    
	    
	    
	

}
