package edlift.model;

import javax.management.Query;
import javax.swing.plaf.basic.BasicTreeUI.TreeCancelEditingAction;

import edlift.model.util.Queue;

public class MultiFloorLift extends Lift{

	private Queue queue;
	
	public MultiFloorLift(int minFloor, int maxFloor, int initialFloor, double speed) {
		super(minFloor, maxFloor, initialFloor, speed);
		queue = new Queue(4); // perche lo è nel test
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
		
		boolean checkInsert = queue.insert(floor);
		if(checkInsert)
			return RequestResult.ACCEPTED;
		else {
			RequestResult res = RequestResult.REJECTED;
			res.setFloor(Integer.MIN_VALUE);
			res.setMsg("rejected");
			return res;
		}
	}
	
	@Override
	public boolean hasPendingFloors() {
		if(queue.hasItems())	{return true;}
		else					{return false;}
	}
	
	@Override
	public int nextPendingFloor(LiftState state) {
		if(hasPendingFloors())
			return queue.extract();
		
		else
			return Integer.MIN_VALUE;
	}
	
	
}
