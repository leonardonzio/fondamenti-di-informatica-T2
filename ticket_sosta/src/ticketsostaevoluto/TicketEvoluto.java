package ticketsostaevoluto;

import java.text.NumberFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class TicketEvoluto {

	private double costo;
	private LocalDateTime inizio;
	private LocalDateTime fine;
	
	public TicketEvoluto(LocalDateTime inizio, LocalDateTime fine, double costo) {
		super();
		this.costo = costo;
		this.inizio = inizio;
		this.fine = fine;
	}

	public double getCosto() {
		return costo;
	}
	
	public String getCostoAsString() {
		
		NumberFormat nF = NumberFormat.getCurrencyInstance(Locale.ITALY);
		return nF.format(costo);
	}

	public LocalDateTime getInizioSosta() {
		return inizio;
	} 

	public LocalDateTime getFineSosta() {
		return fine;
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		
		sb.append("Inizio sosta:  ").append(inizio.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).withLocale(Locale.ITALY))).append("\n");
		sb.append("Fine sosta:  ").append(fine.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).withLocale(Locale.ITALY))).append("\n");
		sb.append("Costo:  ").append(getCostoAsString());
		
		return sb.toString();
	}
	
	
	public String toStringDuration(Duration d) {
		
		int minuti = d.toMinutesPart();
		String sMin = (minuti < 10 ? "0" : "") + minuti;
		
		return d.toHours() + ":" + sMin;	
	}

	
	
}
