package control;

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
		
		AppointmentCollection temp = new AppointmentCollection(allAppointments.getSize());
		for (int i = 0; i < temp.getSize(); i++) {
			temp.add(allAppointments.get(i));
		}
		
		return temp;
	}
}
	