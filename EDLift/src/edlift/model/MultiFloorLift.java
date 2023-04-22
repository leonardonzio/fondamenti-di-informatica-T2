package edlift.model;

import edlift.model.util.Queue;

public class MultiFloorLift extends Lift{

	private Queue queue;
	
	public MultiFloorLift(int minFloor, int maxFloor, int initialFloor, double speed) {
		super(minFloor, maxFloor, initialFloor, speed);
		this.queue = new Queue(4); // per far funzionare il test
	}
	
	@Override
	public String getMode() {
		return "Multi";
	}
	
	@Override
	public RequestResult goToFloor(int floor) {
		checkArrivalFloor(floor);
		
		if(getCurrentState().equals(LiftState.REST)) {
			setRequestedFloor(floor);
			return RequestResult.ACCEPTED;
		}
		
		boolean insertResult = queue.insert(floor);
		if(insertResult) {
			return RequestResult.ACCEPTED;			
		}
		else {
			RequestResult res = RequestResult.REJECTED;
			res.setFloor(Integer.MIN_VALUE);
			res.setMsg("rejected");
			return res;
		}
	}

	@Override
	public boolean hasPendingFloors() {
		return queue.hasItems();
	}
	
	@Override
	public int nextPendingFloor(LiftState state) {
		return hasPendingFloors() ? queue.extract() : Integer.MIN_VALUE;
	}
	
	
}
