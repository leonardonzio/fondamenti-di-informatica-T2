package counterFX.model;

import java.util.Objects;

public class Counter {

	private int value;

	public Counter(int value) {
		this.value = value;
	}
	
	public Counter() {
		this(0);
	}
	
	public int getValue() {
		return value;
	}
	
	public void inc () {
		this.value++;
	}
	
	public void dec () {
		this.value--;
	}
	
	public void reset() {
		value = 0;
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Counter))
			return false;
		Counter other = (Counter) obj;
		return value == other.value;
	}
	
	
	
}
