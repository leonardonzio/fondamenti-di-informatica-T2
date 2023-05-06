package myfitnessdiary.model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MyFitnessDiary implements FitnessDiary{

	private List<Workout> workouts;

	public MyFitnessDiary() {
		workouts = new ArrayList<>();
	}
	
	@Override
	public void addWorkout(Workout workout) {
		this.workouts.add(workout);
	}
	
	@Override
	public List<Workout> getDayWorkouts (LocalDate date) {
		List<Workout> res = new ArrayList<>();
		for (Workout w : workouts) {
			if(w.getDate().equals(date))
				res.add(w);
		}
		
		return res;
	}
	
	@Override
	public List<Workout> getWeekWorkouts (LocalDate date) {
		
		List<Workout> res = new ArrayList<>();
		int numberWeek = date.getDayOfWeek().ordinal();
		LocalDate dateInitial = date.minusDays(numberWeek);
		LocalDate dateEnd = dateInitial.plusDays(DayOfWeek.SUNDAY.ordinal());
		
		// in alternativa a come l'ho implementato io, si puo ricordare che LocalDate è una
		// classe Comparable, e che dunque si puo fare la compareTo
		for (Workout w : workouts) {
			if(	!w.getDate().isBefore(dateInitial) && 
				!w.getDate().isAfter(dateEnd)) {
				res.add(w);
			}
		}
		return res;
	}
	
	@Override
	public int getWeekWorkoutCalories (LocalDate date) {
		List<Workout> res = getWeekWorkouts(date);
		return calcCalories(res);
	}
	
	@Override
	public int getDayWorkoutCalories (LocalDate date) {		
		List<Workout> res = getDayWorkouts(date);
		return calcCalories(res);
	}
	
	@Override
	public int getWeekWorkoutMinutes(LocalDate date) {
		List<Workout> res = getWeekWorkouts(date);
		return calcMinutes(res);
	}

	@Override
	public int getDayWorkoutMinutes(LocalDate date) {
		List<Workout> res = getDayWorkouts(date);
		return calcMinutes(res);
	}
	
	private int calcCalories (List<Workout> woList) {
		int res = 0;
		for (Workout w : woList) {
			res += w.getBurnedCalories();
		}
		
		return res;
	}
	
	private int calcMinutes (List<Workout> woList) {
		int res = 0;
		for (Workout w : woList) {
			res += w.getDuration();
		}
		
		return res;
	}


	
	
	
}
