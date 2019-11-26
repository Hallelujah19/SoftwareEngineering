
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Objects;

public class DateRange {
	private LocalDate start, end;
	private Integer duration;

	public DateRange(LocalDate start, LocalDate end) {
		this.start = start;
		this.end = end;
		duration = Period.between(start, end).getDays()+1;
	}

	
	
	public LocalDate getStart() {
		return this.start;
	}

	public LocalDate getEnd() {
		return this.end;
	}
	
	public long toYears() {
		return ChronoUnit.YEARS.between(this.getStart(), this.getEnd());
	}
	
	public long toDays() {
		return ChronoUnit.DAYS.between(this.getStart(), this.getEnd());
	}

	public Boolean overlaps(DateRange other) {
	
	if (other == null) return false;
	if (this.getAbsoluteDays(start) >= other.getAbsoluteDays(other.getStart())
	 && this.getAbsoluteDays(end) <= other.getAbsoluteDays(other.getEnd()))
		return true;
	else
		return false;
	}
	
	public Integer getDuration() {
		return duration;
	}
	@Override
	public int hashCode() {
		// hashCode method allowing use in collections
		return Objects.hash(end, start);
	}
	
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
	
	public Integer getAbsoluteDays(LocalDate date) {
		
		// calculate the absolute sum till the present date
		Integer leaps = (date.getYear() - 1) / 4;
		Integer sum = leaps * 366 + ((date.getYear() - 1) - leaps) * 365;
		sum += date.getDayOfYear();
		return sum;
		
	}

}
