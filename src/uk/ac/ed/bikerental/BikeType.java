import java.math.BigDecimal;
	import java.util.Objects;

	
	public class BikeType {
		
		protected String typeName;
		private BigDecimal replacementValue;//Original price assumed to equal this
		
		public String getTypeName() {
			return typeName;
		}

		public void setTypeName(String typeName) {
			this.typeName = typeName;
		}

		public BigDecimal getReplacementValue() {
	        // TODO: Implement Bike.getReplacementValue
	          return replacementValue;
	        }

		public void setReplacementValue(BigDecimal replacementValue) {
			this.replacementValue = replacementValue;
		}
		
		public BikeType(String typeName, BigDecimal replacementValue) {
			this.typeName = typeName;
			this.replacementValue = replacementValue;
		}
		
	}
