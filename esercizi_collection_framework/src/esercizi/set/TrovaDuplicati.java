package esercizi.set;

import java.util.HashSet;

public class TrovaDuplicati {
	// trovare parole duplicate
	
	public static void main(String[] args) {
		var mySet = new HashSet<String>();
		String[] parole = 	{"daje", "roma", "daje"};
		
		for (String p : parole) {
			if(!mySet.add(p)) {
				System.out.println("Parola duplicata: " + p);
			}
		}
		
		// se avessimo usato TreeSet, sarebbero in ordine alfabetico
		System.out.println(mySet.size() + " parole distinte: " + mySet);
	}
	
}
