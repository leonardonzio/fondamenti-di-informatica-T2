package oroscopo.controller;

import java.util.List;
import java.util.Random;

import javax.crypto.interfaces.DHPrivateKey;

import oroscopo.model.Previsione;
import oroscopo.model.SegnoZodiacale;

public class MyStrategiaSelezione implements StrategiaSelezione {

	private Random r = new Random();
	
	
	@Override
	public Previsione seleziona(List<Previsione> previsioni, SegnoZodiacale segno) {

		var previsioniValide = previsioni.stream()
				.filter(p -> p.validaPerSegno(segno))
				.toList();
		
		int randIndex = r.nextInt(previsioniValide.size());
		return previsioniValide.get(randIndex);
	}

	
	
}
