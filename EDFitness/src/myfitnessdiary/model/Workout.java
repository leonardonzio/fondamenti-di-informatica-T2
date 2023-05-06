package myfitnessdiary.model;

import java.time.*;

public class Workout {

	private Activity activity;
	private LocalDate date;
	private int duration;
	private Intensity intensity;
	
	// constructor
	public Workout(LocalDate date, int duration, Intensity intensity, Activity activity) {
		if(date == null)
			throw new IllegalArgumentException("data errata");
		
		if(duration <= 0)
			throw new IllegalArgumentException("durata errata");
		
		if(intensity == null)
			throw new IllegalArgumentException("intensità errata");
		
		if(activity == null)
			throw new IllegalArgumentException("attivitò errata");
		
		this.activity = activity;
		this.date = date;
		this.duration = duration;
		this.intensity = intensity;
	}
	
	// getters
	public Activity getActivity() {
		return activity;
	}
	public LocalDate getDate() {
		return date;
	}
	public int getDuration() {
		return duration;
	}
	public Intensity getIntensity() {
		return intensity;
	}
	public int getBurnedCalories() {
		int caloriePerMinuto = getActivity().getCalories(getIntensity());
		return caloriePerMinuto * duration;
	}

}
