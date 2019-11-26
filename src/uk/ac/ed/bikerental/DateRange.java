
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

/**
 * This class is used for modelling a date range
 * and for providing useful methods that support
 * various operations on a date range object
 */
public class DateRange {
	
	/**
	 * Two private attributes for this class of type
	 * LocalDate
	 * 
	 * @param start : attribute for storing the starting date.
	 * @param end : attribute for storing the end date.
	 */
	private LocalDate start, end;
	
	/**
	 * @param duration : attribute for storing the number
	 * of days between the start date (inclusive) and
	 * end date (inclusive).
	 */
	private Integer duration;

	/**
	 * Public constructor for this class
	 * 
	 * The duration attribute is calculated from the start and
	 * end dates using the java.time.Period.between(x,y) method.
	 * 
	 * @param start : used for initialising the start attribute.
	 * @param end : used for initialising the end attribute.
	 */
	public DateRange(LocalDate start, LocalDate end) {
		this.start = start;
		this.end = end;
		duration = Period.between(start, end).getDays()+1;
	}

	/**
	 * This method returns the start
	 * attribute of this class, which
	 * is a private member.
	 * 
	 * @return the value of the start attribute.
	 */
	public LocalDate getStart() {
		return this.start;
	}

	/**
	 * This method returns the end 
	 * attribute of this class, which
	 * is a private member.
	 * 
	 * @return the value of the end attribute.
	 */
	public LocalDate getEnd() {
		return this.end;
	}
	
	/**
	 * This method returns the number of years between the 
	 * start date (inclusive) and the end date (exclusive) 
	 * attributes of this class.
	 * 
	 * @return the number of years.
	 */
	public long toYears() {
		return ChronoUnit.YEARS.between(this.getStart(), this.getEnd());
	}
	
	/**
	 * Returns the number of days between the start
	 * date (inclusive) and the end date (exclusive)
	 * attributes of this class.
	 * 
	 * @return the number of days.
	 */
	public long toDays() {
		return ChronoUnit.DAYS.between(this.getStart(), this.getEnd());
	}

	/**
	 * This method returns a boolean depending on
	 * whether or not two dates overlap. 
	 * 
	 * @param other : is an external date range used for
	 * checking overlaps with this class.
	 * 
	 * @return true if both date ranges overlap
	 * and false otherwise.
	 */
	public Boolean overlaps(DateRange other) {
	
	if (other == null) return false;
	if (this.getAbsoluteDays(start) >= other.getAbsoluteDays(other.getStart())
	 && this.getAbsoluteDays(end) <= other.getAbsoluteDays(other.getEnd()))
		return true;
	else
		return false;
	}
	
	/**
	 * This method returns the duration attribute of this
	 * class which is a private member.
	 * 
	 * @return the value of the duration attribute.
	 */
	public Integer getDuration() {
		return duration;
	}
	
	/**
	 * This method overrides the java.lang.Object.hashCode()
	 * method of the java.lang.Object class.
	 * 
	 * @return the hashCode of this class
	 */
	@Override
	public int hashCode() {
		// hashCode method allowing use in collections
		return Objects.hash(end, start);
	}
	
	/**
	 * This method overrides the java.lang.Object.equals()
	 * method of the java.lag.Object class.
	 * 
	 * @return true if two objects are equal, and false otherwise.
	 */
	@Override
	public boolean equals(Object obj) {
		// equals method for testing equality in tests
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DateRange other = (DateRange) obj;
		return Objects.equals(end, other.end) && Objects.equals(start, other.start);
	}
	
	/**
	 * This is a helper method used for calculating the
	 * absolute days for a given date, i.e. the number of 
	 * days from year 0 to the present date.
	 * 
	 * @param date is used for the calculation.
	 * @return the number of absolute days.
	 */
	public Integer getAbsoluteDays(LocalDate date) {
		
		// calculate the absolute sum till the present date
		Integer leaps = (date.getYear() - 1) / 4;
		Integer sum = leaps * 366 + ((date.getYear() - 1) - leaps) * 365;
		sum += date.getDayOfYear();
		return sum;
		
	}

}
