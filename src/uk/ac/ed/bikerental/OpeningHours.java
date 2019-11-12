import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Map;
import java.util.ArrayList;


public class OpeningHours {

	private Map<DayOfWeek, String> map;
	private ArrayList<DayOfWeek> daysOfWeek = new ArrayList<>();
	private ArrayList<LocalTime> startingHours = new ArrayList<>();
	private ArrayList<LocalTime> closingHours = new ArrayList<>();

	public void setDaysOfWeek(ArrayList<DayOfWeek> daysOfWeek) {
		this.daysOfWeek.addAll(daysOfWeek);
	}

	public ArrayList<DayOfWeek> getDaysOfWeek() {
		return this.daysOfWeek;
	}

	public ArrayList<LocalTime> getStartingHours() {
		return startingHours;
	}

	public void setStartingHours(ArrayList<LocalTime> startingHours) {
		this.startingHours.addAll(startingHours);
	}

	public ArrayList<LocalTime> getClosingHours() {
		return closingHours;
	}

	public void setClosingHours(ArrayList<LocalTime> closingHours) {
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
	
	public void display() {
		System.out.println(this.toString());
	}
}
