package edlift.model;

public class HealthyLift extends Lift {

	public HealthyLift(int minFloor, int maxFloor, int initialFloor, double speed) {
		super(minFloor, maxFloor, initialFloor, speed);
	}
	
	@Override
	public String getMode() {
		return "Healthy";
	}
	
	@Override
	public RequestResult goToFloor (int floor) {
		checkArrivalFloor(floor);
		
		if(!getCurrentState().equals(LiftState.REST))
			return RequestResult.REJECTED;
		
		int diff = Math.abs(floor - getCurrentFloor());
		if(diff <= 1) {
			return RequestResult.REJECTED;			
		}
		
		if(floor < getCurrentFloor()) {
			floor += 1;			
		}
		
		else if (floor > getCurrentFloor()) {
			floor -= 1;
		}
		
		setRequestedFloor(floor);
		RequestResult res = RequestResult.MODIFIED;
		res.setFloor(floor);
		res.setMsg(getMode());
		
		return res;
	}
	
	
}
