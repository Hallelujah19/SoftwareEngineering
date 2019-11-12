import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class OpeningHours {

	private Map<DayOfWeek, String> map;
	private List<DayOfWeek> daysOfWeek = new ArrayList<>();
	private List<LocalTime> startingHours = new ArrayList<>();
	private List<LocalTime> closingHours = new ArrayList<>();

	public void setDaysOfWeek(Collection<DayOfWeek> daysOfWeek) {
		this.daysOfWeek.addAll(daysOfWeek);
	}

	public Collection<DayOfWeek> getDaysOfWeek() {
		return this.daysOfWeek;
	}

	public Collection<LocalTime> getStartingHours() {
		return startingHours;
	}

	public void setStartingHours(Collection<LocalTime> startingHours) {
		this.startingHours.addAll(startingHours);
	}

	public Collection<LocalTime> getClosingHours() {
		return closingHours;
	}

	public void setClosingHours(Collection<LocalTime> closingHours) {
		this.closingHours.addAll(closingHours);
	}
	
	public String toString() {
		String s = "";
		for (int i = 0; i < daysOfWeek.size(); i++) {
			map.put(daysOfWeek.get(i), startingHours.get(i).toString()
			+ " to " + closingHours.get(i).toString());
		}
		for (DayOfWeek day : map.keySet())
			s += day + " : " + map.get(day);
		return s;
	}
}
