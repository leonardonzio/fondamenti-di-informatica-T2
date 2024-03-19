package rfd.model;

import java.time.Duration;
import java.time.LocalTime;

import rfd.model.test.MyTripDurationCalculatorTest;

public class MyTripDurationCalculator implements TripDurationCalculator{
	
	@Override
	public Duration getDuration (Route route) {
		
		double minutes = route.getRouteSegments().stream()
				.mapToDouble(s -> getSegmentDuration(s) * 60.0)
				.sum();
		
		return Duration.ofMinutes((long) minutes);
	}
	
	private double getSegmentDuration (Segment segment) {
		
		return segment.split().stream()
				.mapToDouble(s -> s.getLength() / s.getSpeed())
				.sum();
	}
	
	

}
