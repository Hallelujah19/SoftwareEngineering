package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.Objects;

	
	public class BikeType {
		
		protected String type;
		private BigDecimal replacementValue;//Original price assumed to equal this
		private BigDecimal dailyPrice;
		private BigDecimal depositRate;
		
		
		
		public String getType() {
			return this.type;
		}


		public void setType(String type) {
			this.type = type;
		}


		public BigDecimal getReplacementValue() {
	        // TODO: Implement Bike.getReplacementValue
	       return this.replacementValue;
	    }


		public void setReplacementValue(BigDecimal replacementValue) {
			this.replacementValue = replacementValue;
		}


		public void setDailyPrice(BigDecimal dailyPrice) {
			this.dailyPrice = dailyPrice;
		}


		public void setDepositRate(BigDecimal depositRate) {
			this.depositRate = depositRate;
		}


		public BigDecimal getDailyPrice() {
			return dailyPrice;
		}


		public BigDecimal getDepositRate() {
			return depositRate;
		}
		
		
		
	}
