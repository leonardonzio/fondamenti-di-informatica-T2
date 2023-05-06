package myfitnessdiary.persistence;

import java.io.PrintWriter;
import java.io.Writer;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import myfitnessdiary.model.FitnessDiary;

public class MyReportWriter implements ReportWriter {

	private static final int DAYSOFWEEK = DayOfWeek.values().length;

	private DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).withLocale(Locale.ITALY);

	public void printReport(Writer writer, LocalDate date, FitnessDiary diary) {
		PrintWriter pw = new PrintWriter(writer);
		
		int totMinSett = diary.getWeekWorkoutMinutes(date);
		int totCalSett = diary.getWeekWorkoutCalories(date);
		pw.println("Totali settimana del " + date.format(formatter));
		pw.println("minuti totali allenamento " + totMinSett + ", calorie totali bruciate " + totCalSett);
		pw.println("minuti medi di allenamento " + totMinSett / DAYSOFWEEK + ", calorie medie bruciate " + totCalSett / DAYSOFWEEK + " ");
		alert("Stampa", "successo", "stampato con successo sl file reportsettimanale");
	}
	
	protected void alert(String title, String headerMessage, String contentMessage) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle(title);
		alert.setHeaderText(headerMessage);
		alert.setContentText(contentMessage);
		alert.showAndWait();
	}


}
