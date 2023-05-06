package myfitnessdiary.model;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ambramolesini
 * 
 */
public class Activity {

	private String name;
	private Map<Intensity, Integer> calories;

	public Activity(String name) {
		this.setName(name);
		calories = new HashMap<Intensity, Integer>();
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	/**
	 * Permette di inserire le calorie di riferimento per l'intensità
	 * 
	 * @param intensity
	 *           � una stringa che rappresenta il livello di intensit�
	 * @param calories
	 *            � un intero che rappresenta le calorie bruciate riferite
	 *            all'intesit�
	 */
	public void insertCalories(Intensity intensity, int calories) {
		this.calories.put(intensity, calories);
	}

	/**
	 * Permette di recuperare le calorie bruciate associate all'intensit�
	 * 
	 * @param intensity
	 *            � una stringa che rappresenta il livello di intensità
	 */

	public int getCalories(Intensity intensity) {
		return this.calories.get(intensity);
	}

}
