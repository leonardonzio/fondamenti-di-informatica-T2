package ticketsosta;

import java.text.NumberFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class Ticket {

	private double costo;
	private LocalTime oraIniziale;
	private LocalTime oraFinale;
	
	public Ticket(LocalTime oraIniziale, LocalTime oraFinale, double costo) {
		super();
		this.costo = costo;
		this.oraIniziale = oraIniziale;
		this.oraFinale = oraFinale;
	}

	public double getCosto() {
		return costo;
	}
	
	public String getCostoAsString() {
		
		NumberFormat nF = NumberFormat.getCurrencyInstance(Locale.ITALY);
		return nF.format(costo);
	}

	public LocalTime getInizioSosta() {
		return oraIniziale;
	}

	public LocalTime getFineSosta() {
		return oraFinale;
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		
		sb.append("Inizio sosta:  ").append(oraIniziale.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT).withLocale(Locale.ITALY))).append("\n");
		sb.append("Fine sosta:  ").append(oraFinale.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT).withLocale(Locale.ITALY))).append("\n");
		sb.append("Costo:  ").append(getCostoAsString());
		
		return sb.toString();
	}
	
	
	public String toStringDuration(Duration d) {
		
		int minuti = d.toMinutesPart();
		String sMin = (minuti < 10 ? "0" : "") + minuti;
		
		return d.toHours() + ":" + sMin;	
	}
	
	
}
