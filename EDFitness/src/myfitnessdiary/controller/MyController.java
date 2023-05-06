package myfitnessdiary.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import myfitnessdiary.model.FitnessDiary;
import myfitnessdiary.model.Workout;
import myfitnessdiary.persistence.ActivityRepository;
import myfitnessdiary.persistence.ReportWriter;

public class MyController extends Controller{

	private DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(Locale.ITALY);
	
	public MyController(FitnessDiary diary, ActivityRepository activityRepository, ReportWriter reportWriter) {
		super(diary, activityRepository, reportWriter);
	}

	@Override
	public String getDayWorkout (LocalDate date) {
		FitnessDiary fitDiary = getFitnessDiary();
		StringBuilder sb = new StringBuilder();
		List<Workout> workouts = new ArrayList<>();
		
		workouts = fitDiary.getDayWorkouts(date);
		sb.append("allenamento di " + formatter.format(date) + System.lineSeparator());
		for (Workout w : workouts) {
			sb.append(w.getActivity().getName()).append(" minuti: ");
			sb.append(w.getDuration()).append(" calorie bruciate: ");
			sb.append(w.getBurnedCalories()).append(System.lineSeparator());
		}
		sb.append(System.lineSeparator());
		sb.append("Minuti totali di allenamento: " + 	fitDiary.getDayWorkoutMinutes(date) + System.lineSeparator());
		sb.append("Calorie totali bruciate: " + 		fitDiary.getDayWorkoutCalories(date) + System.lineSeparator());
		
		return sb.toString();
	}
	
	
	
	
}
