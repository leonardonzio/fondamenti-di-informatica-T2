package edlift.model.util;

import java.util.Arrays;

public class Queue {
	
	private int booked;
	private int[] bookings;
	private int maxBookings;
	
	public Queue(int maxBookings) {
		this.maxBookings = maxBookings;
		this.bookings = new int[this.maxBookings];
		this.booked = 0;	
	}
	
	public boolean insert (int n) {
		if(booked < maxBookings) {
			this.bookings[this.booked] = n;
			this.booked++;
			return true;
		}
		
		return false;
	}
	
	public int extract() {
		if(!hasItems()) {
			return Integer.MIN_VALUE;			
		}
		
		int extracted = this.bookings[0];
		this.bookings = Arrays.copyOfRange(bookings, 1, booked);
		this.booked--;
		
		return extracted;
	}
	
	public int peek() {
		return hasItems() ? this.bookings[0] : Integer.MIN_VALUE;
	}
	
	public int size() {
		return this.booked;
	}
	
	public boolean hasItems() {
		return (this.booked > 0);
	}
	
	
}
