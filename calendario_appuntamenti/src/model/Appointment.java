package model;

import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.time.*;

public class Appointment {

	private String description;
	private LocalDateTime from;
	private LocalDateTime to;
	
	public Appointment(String description, LocalDateTime from, LocalDateTime to) {
		this.description = description;
		this.from = from;
		this.to = to;
	}
	
	public Appointment(String description, LocalDateTime from, Duration duration) {
		this(description, from, from.plus(duration));
	}
	
	public boolean equals(Appointment app) {
		
		if(this.description.equals(app.getDescription()) &&
			this.from.equals(app.getFrom()) &&
			this.to.equals(app.getTo())) {
			return true;
		}
			
		return false;
	}

	public String getDescription() {
		return description;
	}

	public LocalDateTime getFrom() {
		return from;
	}

	public LocalDateTime getTo() {
		return to;
	}
	
	public Duration getDuration() {
		return Duration.between(from, to);
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setFrom(LocalDateTime from) {
		this.from = from;
	}

	public void setTo(LocalDateTime to) {
		this.to = to;
	}
	
	public void setDuration(Duration duration) {
<<<<<<< HEAD
		this.to = getFrom().plus(duration);
=======
		//..
>>>>>>> ca2d3d1b75498183e1a6ce8d16b6c5085b984147
	}
	
	@Override
	public String toString() {
		
		StringBuilder s = new StringBuilder();
		DateTimeFormatter formatterDate = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).withLocale(Locale.ITALY);
		
		s.append("Description:\t").append(description);
		s.append(System.lineSeparator());
		s.append("From:\t\t").append(getFrom().format(formatterDate));
		s.append(System.lineSeparator());
		s.append("To:\t\t").append(getTo().format(formatterDate));
		s.append(System.lineSeparator());		
		
		return s.toString();
	}
	
	
	public static void main(String[] args) {
		Appointment app = new Appointment("cacca",
				LocalDateTime.of(LocalDate.of(2005, 3, 12), LocalTime.of(13, 30)),
				LocalDateTime.of(LocalDate.of(2005, 6, 21), LocalTime.of(20, 19)));
		
		System.out.print(app.toString());
	}
	
}
