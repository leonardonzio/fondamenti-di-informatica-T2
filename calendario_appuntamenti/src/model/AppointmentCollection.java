package model;

import java.util.Arrays;

public class AppointmentCollection {

	private int size;
	private Appointment[] innerContainer;
	private final int DEFAULT_PHYSICAL_SIZE = 10;
	private final int DEFAULT_GROWTH_FACTOR = 2;
	
	public AppointmentCollection(int s) {
		this.innerContainer = new Appointment[s];
		this.size = 0;
	}
	
	public AppointmentCollection() {
		this.innerContainer = new Appointment[DEFAULT_PHYSICAL_SIZE];
		this.size = 0;
	}
	
	public int getSize() {
		return size;
	}
	
	public int size() {
		int counter = 0;
		for (int i = 0; i < innerContainer.length; i++) {
			if(innerContainer[i] != null)
				counter++;
		}
		return counter;
	}
	
	public Appointment get(int index) {
		return innerContainer[index];
	}
	
	public int indexOf(Appointment app) {
		
		for (int i = 0; i < getSize(); i++) {
			if(innerContainer[i].equals(app)) {
				return i;
			}
		}
		return -1;
	}
	
	public void add(Appointment app) {
		
		if(size < innerContainer.length) {
			innerContainer[size] = app;
			size++;
			return;
		}
		
		Appointment[] temp = new Appointment[innerContainer.length * DEFAULT_GROWTH_FACTOR];
		temp = Arrays.copyOf(innerContainer, innerContainer.length * DEFAULT_GROWTH_FACTOR);
		temp[size] = app;
		size++;
		innerContainer = temp;
	}
	
	public void remove (int index) {
		
		for (int i = index; i < size; i++) {
			innerContainer[i] = innerContainer[i+1];
		}
		innerContainer[size-1] = null;
		size--;
	}
	
}
