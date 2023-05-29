package flights.model;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Objects;
import java.util.Set;
import java.util.Collection;
import java.util.HashSet;

public class FlightSchedule {

	private Aircraft aircraft;
	private Airport arrivalAirport;
	private LocalTime arrivalLocalTime;
	private String code;
	private int dayOffset;
	private Set<DayOfWeek> daysOfWeek;
	private Airport departureAirport;
	private LocalTime departureLocalTime;
	
	public FlightSchedule (String code, Airport departureAirport, LocalTime departureLocalTime, Airport arrivalAirport,
			LocalTime arrivalLocalTime, int dayOffset, Collection<DayOfWeek> daysOfWeek, Aircraft aircraft) {
		/*
		if (daysOfWeek.isEmpty() || daysOfWeek == null)
			throw new IllegalArgumentException("giorni settimana vuoti o inesistenti");
		
		if (aircraft == null)
			throw new IllegalArgumentException("aircraft non esiste");
		
		if (departureAirport == null || arrivalAirport == null)
			throw new IllegalArgumentException("almeno un aeroporto non esiste");

		if (departureLocalTime == null || arrivalLocalTime == null)
			throw new IllegalArgumentException("almeno un localtime non esiste");
		*/
		
		this.code = code;
		this.departureAirport = departureAirport;
		this.departureLocalTime = departureLocalTime;
		this.arrivalAirport = arrivalAirport;
		this.arrivalLocalTime = arrivalLocalTime;
		this.dayOffset = dayOffset;
		this.daysOfWeek = new HashSet<>(daysOfWeek);
		this.aircraft = aircraft;
	}

	public Aircraft getAircraft() {
		return aircraft;
	}

	public Airport getArrivalAirport() {
		return arrivalAirport;
	}

	public LocalTime getArrivalLocalTime() {
		return arrivalLocalTime;
	}

	public String getCode() {
		return code;
	}

	public int getDayOffset() {
		return dayOffset;
	}

	public Set<DayOfWeek> getDaysOfWeek() {
		return daysOfWeek;
	}

	public Airport getDepartureAirport() {
		return departureAirport;
	}


	public LocalTime getDepartureLocalTime() {
		return departureLocalTime;
	}

	public Duration getFlightDuration() {
		OffsetDateTime departure = 	OffsetDateTime.of (
				LocalDate.now(), 
				departureLocalTime, 
				ZoneOffset.ofHours (getDepartureAirport().getCity().getTimeZone()));
		
		OffsetDateTime arrival =	OffsetDateTime.of (
				LocalDate.now().plusDays(dayOffset),
				arrivalLocalTime, 
				ZoneOffset.ofHours (getArrivalAirport().getCity().getTimeZone()));

		var res = Duration.between(departure, arrival);
		return (res.isNegative()) ? res.plusDays(1) : res;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(aircraft, arrivalAirport, arrivalLocalTime, code, dayOffset, daysOfWeek, departureAirport,
				departureLocalTime);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof FlightSchedule))
			return false;
		FlightSchedule other = (FlightSchedule) obj;
		return Objects.equals(aircraft, other.aircraft) 
				&& Objects.equals(arrivalAirport, other.arrivalAirport)
				&& Objects.equals(arrivalLocalTime, other.arrivalLocalTime) 
				&& Objects.equals(code, other.code)
				&& dayOffset == other.dayOffset 
				&& Objects.equals(daysOfWeek, other.daysOfWeek)
				&& Objects.equals(departureAirport, other.departureAirport)
				&& Objects.equals(departureLocalTime, other.departureLocalTime);
	}

}
