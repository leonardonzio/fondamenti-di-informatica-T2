package esercizi.map;

import java.util.*;

public class ContaOccorrenze {
	public static void main(String[] args) {
		String[] parole = {"daje", "roma", "daje", "yahooo", "so", "de", "roma", "yahooo"};
		var occorrenze = new TreeMap<String, Integer>(); // type inference
		
		for (String s : parole) {
			Integer frequenza = occorrenze.get(s);
			if(frequenza == null) {
				occorrenze.put(s, 1);
			}
			else {
				occorrenze.put(s, frequenza + 1);
			}
			// oppure
			// occorrenze.put(s, (frequenza == null) ? 1 : frequenza + 1);
		}
		
		System.out.println(occorrenze.size() + " parole diverse:");
		
		// la to string della mappa stampa in ordine alfabetico, dato che e una TreeMap che implementa SortedMap
		// che segue l'ordine delle chiavi, che essendo stringhe è in ordine alfabetico
		System.out.println(occorrenze);
		
		// con la mia nuova funzione:
		myPrint(occorrenze);
	}
	
	// faccio una funzione per stampare meglio data una mappa con coppia stringa-intero
	public static void myPrint (Map<String,Integer> m){
		Set<String> keys = m.keySet();
		
		for (String key: keys) {
			System.out.println(key + "\t" + m.get(key));
		}
	}
	
}
