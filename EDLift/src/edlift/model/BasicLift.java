package edlift.model;

public class BasicLift extends Lift 
{
	
	public BasicLift(int minFloor, int maxFloor, int initialFloor, double speed) {
		super(minFloor, maxFloor, initialFloor, speed);
	}
	
	@Override
	public String getMode() {
		return "Basic";
	}

	
	@Override
	public RequestResult goToFloor(int floor) {
		checkArrivalFloor(floor);
		
		if(!getCurrentState().equals(LiftState.REST)) {
			return RequestResult.REJECTED;			
		}
		
		setRequestedFloor(floor);
		return RequestResult.ACCEPTED;
	}
	
	
	
	
}
