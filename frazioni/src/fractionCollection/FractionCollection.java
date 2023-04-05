package fractionCollection;

import java.util.Arrays;
import java.util.StringJoiner;

import frazioni.Frazione;

public class FractionCollection {

	private int size;
	private Frazione[] innerContainer;
	private final int DEFAULT_PHYSICAL_SIZE = 10;
	private final int DEFAULT_GROWTH_FACTOR = 2;
	
	public FractionCollection(int s) {
		this.innerContainer = new Frazione[s];
		this.size = 0;
	}
	
	public FractionCollection() {
		this.innerContainer = new Frazione[DEFAULT_PHYSICAL_SIZE];
		this.size = 0;
	}
	
	public FractionCollection(Frazione[] collection) {
		
		// perchè è un riferiemnto che è privato
		this.innerContainer = collection.clone();
		this.size = size();
	}
	
	public int getSize() {
		return size;
	}
	
	public int size() {
		int counter = 0;
		for (int i = 0; i < innerContainer.length; i++) {
			if(innerContainer[i] != null)
				counter++;
		}
		return counter;
	}
	
	public Frazione get(int index) {
		return innerContainer[index];
	}
	
	public void put(Frazione f) {
		
		if(size < innerContainer.length) {
			innerContainer[size] = f;
			size++;
			return;
		}
		
		Frazione[] temp;
		temp = Arrays.copyOf(innerContainer, innerContainer.length * DEFAULT_GROWTH_FACTOR);
		temp[size] = f;
		size++;
		innerContainer = temp;
	}
	
	public void remove (int index) {
		
		for (int i = index; i < size; i++) {
			innerContainer[i] = innerContainer[i+1];
		}
		innerContainer[size-1] = null;
		size--;
	}
	
	
	@Override
	public String toString() {
		
		StringJoiner res = new StringJoiner(", ", "[", "]");
		for (Frazione f : innerContainer) {
			res.add(f.toString());
		}
		
		return res.toString();
	}
	
	
	public FractionCollection sum(FractionCollection other) {
		
		if(size != other.getSize())
			return null;
		
		Frazione[] temp = new Frazione[size];
		for (int i = 0; i < size; i++) {
			temp[i] = innerContainer[i].sum(other.innerContainer[i]);
			temp[i] = temp[i].minTerm();
		}
		
		return new FractionCollection(temp); 
	}
	
	public FractionCollection sub(FractionCollection other) {
		
		if(size != other.getSize())
			return null;
		
		Frazione[] temp = new Frazione[size];
		for (int i = 0; i < size; i++) {
			temp[i] = innerContainer[i].sub(other.innerContainer[i]);
			temp[i] = temp[i].minTerm();
		}
		
		return new FractionCollection(temp); 
	}
	
	
	public FractionCollection mul(FractionCollection other) {
		
		if(size != other.getSize())
			return null;
		
		Frazione[] temp = new Frazione[size];
		for (int i = 0; i < size; i++) {
			temp[i] = innerContainer[i].mul(other.innerContainer[i]);	
			temp[i] = temp[i].minTerm();
		}
		
		return new FractionCollection(temp); 
	}
	
	public FractionCollection div(FractionCollection other) {
		
		if(size != other.getSize())
			return null;
		
		Frazione[] temp = new Frazione[size];
		for (int i = 0; i < size; i++) {
			temp[i] = innerContainer[i].div(other.innerContainer[i]);	
			temp[i] = temp[i].minTerm();
		}
		
		return new FractionCollection(temp); 
	}
	
	
	
	
	
	
}
