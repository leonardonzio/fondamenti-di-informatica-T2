package control;

import java.time.*;

import org.junit.runners.model.FrameworkField;

import model.*;

public class MyCalendar {

	private AppointmentCollection allAppointments;
	
	public MyCalendar() {
		allAppointments = new AppointmentCollection(); // physical = 10, logsize = 2
	}
	
	public void add (Appointment app) {
		allAppointments.add(app);
	}
	
	public boolean remove (Appointment app) {
		int position = allAppointments.indexOf(app);
		if(position == -1) {
			return false;
		}
		
		allAppointments.remove(position);
		return true;
	}
	
	public AppointmentCollection getAllAppointments() {
		
		AppointmentCollection res = new AppointmentCollection(allAppointments.size());
		
		for (int i = 0; i < allAppointments.size(); i++) {
			res.add(allAppointments.get(i));
		}
		
		return res;
	}
	
	
	private AppointmentCollection getAppointmentsIn(LocalDateTime from, LocalDateTime to) {
		
		AppointmentCollection res = new AppointmentCollection(allAppointments.size());
		for (int i = 0; i < allAppointments.size(); i++) {
			
			Appointment current = allAppointments.get(i);			
			if(isOverlapped(from, to, current.getFrom(), current.getTo())) {
				res.add(current);
			}
		}
		
		return res;
	}
	
	private boolean isOverlapped(LocalDateTime start, LocalDateTime end,
								LocalDateTime refStart, LocalDateTime refEnd) {

		boolean conditionOverlapped = 	(start.isAfter(refStart) && start.isBefore(refEnd)) ||
										(start.isBefore(refStart) && end.isAfter(refStart));
		
		return conditionOverlapped;
	}
	
	
	public AppointmentCollection getDayAppointments(LocalDate date) {
	
		return getAppointmentsIn(	LocalDateTime.of(date, LocalTime.of(0, 0)), 		// from
									LocalDateTime.of(date, LocalTime.of(23, 59)));	// to

	}
	
	public AppointmentCollection getMonthAppointments(LocalDate date) {
	
		LocalDateTime l = LocalDateTime.of(date.withDayOfMonth(1), LocalTime.of(0,0));
		LocalDateTime l2 = l.plusMonths(1);
		
		return getAppointmentsIn(l, l2);
	}
	
	
	public AppointmentCollection getWeekAppointments(LocalDate date) {
		// completare
	}
	
	
	
}
	