package flights.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;

import flights.model.Aircraft;
import flights.model.Airport;
import flights.model.FlightSchedule;

public class MyFlightScheduleReader implements FlightScheduleReader{

	private final String SEP = ",";
	private final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter
			.ofLocalizedTime(FormatStyle.SHORT)
			.withLocale(Locale.ITALY);
	private final int NUM_TOKENS_SCHEDULE = 8;
	
	
	@Override
	public Collection<FlightSchedule> read(Reader reader, Map<String, Airport> airportMap,
			Map<String, Aircraft> aircraftMap) throws IOException, BadFileFormatException {
		
		if (reader == null)
			throw new IOException("file non esistente");
		
		var rdr 		= new BufferedReader(reader);
		var schedules	= new ArrayList<FlightSchedule>();
		FlightSchedule schedule;
		while ((schedule = readSchedule(rdr, airportMap, aircraftMap)) != null) {
			schedules.add(schedule);
		}
		
		return schedules;
	}

	private FlightSchedule readSchedule (BufferedReader rdr,
			Map<String, Airport> airportMap, 
			Map<String, Aircraft> aircraftMap) throws IOException, BadFileFormatException{
		
		String line = rdr.readLine();
		if (line == null || line.trim().isEmpty())
			return null;
		
		StringTokenizer tokens = new StringTokenizer(line, SEP);
		if (tokens.countTokens() != NUM_TOKENS_SCHEDULE)
			throw new BadFileFormatException("il file non ha 8 elementi");
		
		try {
			
			String code 			= tokens.nextToken().trim();
			String strDepAirport 	= tokens.nextToken().trim();	
			Airport depAirport 		= airportMap.get(strDepAirport);
			if (depAirport == null)
				throw new BadFileFormatException("departure airport non esiste");
			
			String strDepLocalTime 	= tokens.nextToken().trim();
			LocalTime depLocalTime 	= LocalTime.parse(strDepLocalTime, DATE_FORMATTER);

			String strArrAirport 	= tokens.nextToken().trim();	
			Airport arrAirport	 	= airportMap.get(strArrAirport);
			if (arrAirport == null)
				throw new BadFileFormatException("arrival airport non esiste");

			String strArrLocalTime 	= tokens.nextToken().trim();
			LocalTime arrLocalTime 	= LocalTime.parse(strArrLocalTime, DATE_FORMATTER);

			String strOffset 		= tokens.nextToken().trim();
			int offset 				= Integer.parseInt(strOffset);

			String strDays 			= tokens.nextToken().trim();
			Collection<DayOfWeek> days = readDaysOfWeek(strDays);
			String strAircraft 		= tokens.nextToken().trim();
			Aircraft aircraft		= aircraftMap.get(strAircraft);
			if (aircraft == null)
				throw new BadFileFormatException("aircraft non esistente");
			
			FlightSchedule fs = new FlightSchedule(
					code,
					depAirport,
					depLocalTime,
					arrAirport,
					arrLocalTime, 
					offset,
					days,
					aircraft);
			
			return fs;
			
		} catch (DateTimeParseException | NumberFormatException e) {
			throw new BadFileFormatException("file con non parsato correttamente");
		} catch (Exception e) {
			throw new BadFileFormatException(e);
		}
	}
	
	private Collection<DayOfWeek> readDaysOfWeek (String days) throws BadFileFormatException {
		if (days.length() != 7)
			throw new BadFileFormatException();

		var daysOfWeek = new ArrayList<DayOfWeek>();
		for (int i = 0; i < 7; i++) {
			
			char c = days.charAt(i);
			if (c == '1' + i)
				daysOfWeek.add(DayOfWeek.of(c - '0'));
			
			else if (c != '-') 
				throw new BadFileFormatException("Bad Days Format");
		}

		return daysOfWeek;
	}
	
	
}
