package flights.controller;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import flights.model.Airport;
import flights.model.FlightSchedule;
import flights.persistence.DataManager;

public class MyController implements Controller{

	private DataManager dataManager;
	private List<Airport> sortedAirports;
	private Comparator<Airport> cmp = Comparator
			.comparing((Airport a) -> a.getCity().getName())
			.thenComparing(Airport::getName);
	
	
	public MyController(DataManager dataManager) {
		this.dataManager = dataManager;
		sortedAirports = new ArrayList<>(dataManager.getAirportMap().values());
		
		sortedAirports.sort(cmp);
	}
	
	@Override
	public List<Airport> getSortedAirports() {
		return sortedAirports;
	}

	@Override
	public List<FlightSchedule> searchFlights(Airport departureAirport, Airport arrivalAirport, LocalDate date) {

		DayOfWeek dayWeek = date.getDayOfWeek();
		return dataManager.getFlightSchedules()
				.stream()
				.filter(fs -> fs.getDepartureAirport().equals(departureAirport)
						&& fs.getArrivalAirport().equals(arrivalAirport) 
						&& fs.getDaysOfWeek().contains(dayWeek))
				.toList();
	}

}
