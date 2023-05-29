package counterFX.controller;

import java.util.ArrayList;
import java.util.List;
import counterFX.model.Counter;

public class Controller {

	private Counter[] counters;
	private int max, index;
	
	public Controller(int max) {
		this.counters = new Counter[max];
		this.max = max;
		this.index = 0;
	}
	
	public int getCapacity() {
		return max;
	}
	
	
	public int newCounter() {
		if (index >= counters.length)
			throw new IllegalArgumentException("Sei arrivato al massimo");
		
		counters[index] = new Counter();
		index++;
		return index-1;
	}
	
	public void incCounter(int i) {
		if (i >= 0 && i < counters.length)
			counters[i].inc();
	}
	
	public void decCounter(int i) {
		if (i >= 0 && i < counters.length)
			counters[i].dec();
	}
	
	public void resetCounter(int i) {
		if (i >= 0 && i < counters.length)
			counters[i].reset();
	}
	
	public String getCounterValueAsString(int i) {
		return (i >= 0 && i < counters.length) ? String.valueOf(counters[i].getValue()) : "Non esiste";
	}
	
}
