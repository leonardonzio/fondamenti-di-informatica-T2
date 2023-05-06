package esercizi.list;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
public class SwapElementiLista {
	public static <T> void swap(List<T> list, int i, int j) {
		T temp = list.get(i);
		list.set(i, list.get(j));
		list.set(j, temp);
	}
	
	public static void main(String args[]){
		var list = new ArrayList<String>();
		list.add("daje");
		list.add("roma");
		list.add("daje");
		list.add("yahooo");
		
		System.out.println(list);
		swap(list, 2, 3);
		System.out.println(list);
		
		for (String s : list) {
			System.out.print(s + " ");
		}
		System.out.println();
		
		// stampo al contrario con il ListIterator, chiedo all iteratore quello precedente
		ListIterator<String> i = list.listIterator(list.size());
		while(i.hasPrevious()) {
			System.out.print( i.previous() + " "); 
		}
	}
	
}
