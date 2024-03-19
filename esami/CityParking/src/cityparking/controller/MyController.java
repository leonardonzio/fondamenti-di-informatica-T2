package cityparking.controller;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.time.LocalDate;
import java.time.LocalDateTime;

import cityparking.model.MyCalcolatoreSosta;
import cityparking.model.Tariffa;
import cityparking.model.Ricevuta;
import cityparking.model.BadTimeIntervalException;
import cityparking.model.CalcolatoreSosta;


public class MyController implements Controller {

	private CalcolatoreSosta tickerIssuer;
	//uguale:
	//private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("HH:mm");	
	private DateTimeFormatter dateFormatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT).withLocale(Locale.ITALY);

	
	public MyController(Tariffa tariffa) {
		tickerIssuer = new MyCalcolatoreSosta(tariffa);
	}
	
	@Override
	public Ricevuta calcolaSosta(LocalDate dataInizio, String strOraInizio, LocalDate dataFine, String strOraFine) 
			throws BadTimeIntervalException, BadTimeFormatException {
		
		LocalTime oraInizio, oraFine;
		try {
			oraInizio	= LocalTime.parse(strOraInizio, dateFormatter);
			oraFine 	= LocalTime.parse(strOraFine, 	dateFormatter);
			
		} catch (DateTimeParseException e) {
			throw new BadTimeFormatException("orario formattato male");
		}
		
		return tickerIssuer.calcola(
				LocalDateTime.of(dataInizio, oraInizio),
				LocalDateTime.of(dataFine, oraFine)
			);
	}
	
}
