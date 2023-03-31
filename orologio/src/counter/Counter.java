package counter;


public class Counter {
	
	private int value;
	
	public Counter(int v) {
		this.value = v;
	}
	
	public Counter() {
		this(0);
	}
	
	public void reset() {
		this.value = 0;
	}
	
	public void inc() {
		this.value++;
	}

	public int getValue() {
		return value;
	}
	
	public boolean equals(Counter other) {
		return this.value == other.getValue();
	}
	
	@Override
	public String toString() {
		return ("Valore: " + this.value);
	}

	
}
