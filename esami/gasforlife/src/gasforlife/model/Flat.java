package gasforlife.model;

public class Flat {
	private String id;
	private String owner;
	private double maxConsumption; //consumo massimo di gas espresso in m^3 di gas

	public Flat(String id, double maxConsumption, String owner) {
		this.id = id;
		this.owner = owner;
		this.maxConsumption = maxConsumption;
	}

	public String getId() {
		return id;
	}

	public String getOwner() {
		return owner;
	}

	public double getMaxConsumption() {
		return maxConsumption;
	}
	
	@Override
	public String toString() {
		
		return "appartamento: "+id+" di "+owner;
	}
}
