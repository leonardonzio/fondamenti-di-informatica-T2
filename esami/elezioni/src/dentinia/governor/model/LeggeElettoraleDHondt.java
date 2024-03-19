package dentinia.governor.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class LeggeElettoraleDHondt implements LeggeElettorale {
	
	
	
	@Override
	public RisultatoElezioni apply(Elezioni t) {
		
		RisultatoElezioni result;		
		var list = new ArrayList<Quoziente>();
		for (Partito p : t.getPartiti()) {
			
			for (int i = 1; i < t.getSeggiDaAssegnare(); i++) {
				long voti = t.getVoti(p) / i;
				list.add(new Quoziente(p, voti));
	
			}
		}
		
		var listBest = list.stream()
				.limit(t.getSeggiDaAssegnare())
				.collect(Collectors.toList());
		
		
		
		
	}

}
