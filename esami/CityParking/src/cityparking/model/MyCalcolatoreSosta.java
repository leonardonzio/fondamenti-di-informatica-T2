package cityparking.model;

import java.time.Duration;
import java.time.LocalDateTime;

import org.junit.runners.model.FrameworkField;

public class MyCalcolatoreSosta extends CalcolatoreSosta {

	private final Duration dodiciOre 		= Duration.ofHours(12);
	private final Duration ventiquattroOre 	= Duration.ofHours(24);
	
	public MyCalcolatoreSosta(Tariffa tariffa) {
		super(tariffa);
	}

	@Override
	public Ricevuta calcola(LocalDateTime inizio, LocalDateTime fine) throws BadTimeIntervalException {
		
		var t = getTariffa();
		var sosta = Duration.between(inizio, fine);
		if (sosta.isNegative()) throw new BadTimeIntervalException("orario di fine sosta precede quello di inizio sosta");
				
		// sosta minore dodici ore
		double costo = 0;
		if (sosta.compareTo(dodiciOre) < 0 ) {
			
			int intervalliDa15Min = (int) Math.ceil(sosta.toMinutes() / (double) t.getDurataUnita().toMinutes()); 
			for (int i = 0; i < intervalliDa15Min; i++) {
				if (i == 0) {
					costo += t.getCostoIniziale();
					continue;
				}
				costo += t.getCostoUnitaSuccessive();
			}
			
			return new Ricevuta(inizio, fine, (costo > t.getCapGiornaliero()) ? t.getCapGiornaliero() : costo);
		}
		
		// tra 12 e 24 compresi
		if (sosta.toMillis() >= dodiciOre.toMillis() && sosta.toMillis() <= ventiquattroOre.toMillis())
			return new Ricevuta(inizio, fine, t.getCapGiornaliero());
		
		// piu di 24 ore
		costo += t.getCapGiornaliero();
		sosta = sosta.minus(ventiquattroOre);
		int blocchiDodiciOre = (int) Math.ceil(sosta.toMinutes() / (double) dodiciOre.toMinutes());
		for (int i = 0; i < blocchiDodiciOre; i++) {
			costo += t.getOltre();
		}
		
		return new Ricevuta(inizio, fine, costo);		
	}


}
