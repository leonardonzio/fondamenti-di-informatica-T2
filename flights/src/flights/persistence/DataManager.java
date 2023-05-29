package flights.persistence;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;

import flights.model.*;

public class DataManager {
	
	private AircraftsReader 		aircraftsReader;
	private CitiesReader 			citiesReader;
	private FlightScheduleReader 	flightScheduleReader;

	private HashMap<String, Aircraft>		aircraftMap;
	private HashMap<String, Airport>		airportMap;
	private Collection<FlightSchedule> 		flightSchedules;
	
	public DataManager (CitiesReader citiesReader,
			AircraftsReader aircraftsReader,
			FlightScheduleReader flightScheduleReader) {
		
		this.citiesReader = citiesReader;
		this.aircraftsReader = aircraftsReader;
		this.flightScheduleReader = flightScheduleReader;
		this.aircraftMap 		= new HashMap<>();
		this.airportMap 		= new HashMap<>();
	}
	
	public Map<String, Aircraft> getAircraftMap() {
		return aircraftMap;
	}

	public Map<String, Airport> getAirportMap() {
		return airportMap;
	}

	public Collection<FlightSchedule> getFlightSchedules() {
		return flightSchedules;
	}
	
	public void readAll() throws IOException, BadFileFormatException {
		
		try (FileReader reader = new FileReader("Cities.txt")) {
			Collection<City> cities = citiesReader.read(reader);
			airportMap.clear();
			cities.stream()
				.flatMap(c -> c.getAirports().stream())
				.forEach(a -> airportMap.put(a.getCode(), a));
		};
		
		try (FileReader reader = new FileReader("Aircrafts.txt")) {
			Collection<Aircraft> aircrafts = aircraftsReader.read(reader);
			aircraftMap.clear();
			
			aircrafts.forEach(a -> aircraftMap.put(a.getCode(), a));
		};
		
		try (FileReader reader = new FileReader("FlightSchedule.txt")) {
			flightSchedules = flightScheduleReader.read(
					reader, 
					airportMap,
					aircraftMap);
		};
		
	}
}
