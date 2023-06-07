package oroscopo.controller;

import java.io.IOException;
import java.util.Random;
import java.util.Set;

import oroscopo.model.Oroscopo;
import oroscopo.model.OroscopoMensile;
import oroscopo.model.Previsione;
import oroscopo.model.SegnoZodiacale;
import oroscopo.persistence.OroscopoRepository;

public class MyController extends AbstractController {

	private StrategiaSelezione strategiaSelezione;	
	
	public MyController(OroscopoRepository repository, StrategiaSelezione strategiaSelezione) throws IOException {
		super(repository);
		this.strategiaSelezione = strategiaSelezione;
	}
	
	@Override
	public Oroscopo[] generaOroscopoAnnuale(SegnoZodiacale segno, int fortunaMin) {
		var oroscopi = new Oroscopo[12];
		for (int i = 0; i < 12; i++) {
			Oroscopo o;
			do {
				o = generaOroscopoCasuale(segno);
			
			} while (o.getFortuna() <= fortunaMin);
			
			oroscopi[i] = o;
		}
		return oroscopi;
	}
	
	@Override
	public Oroscopo generaOroscopoCasuale(SegnoZodiacale segno) {
		var prevRandomAmore 	= strategiaSelezione.seleziona(getRepository().getPrevisioni("amore"), segno);
		var prevRandomLavoro 	= strategiaSelezione.seleziona(getRepository().getPrevisioni("lavoro"), segno);
		var prevRandomSalute 	= strategiaSelezione.seleziona(getRepository().getPrevisioni("salute"), segno);

		return new OroscopoMensile(segno, prevRandomAmore, prevRandomLavoro, prevRandomSalute);
	}
	
	@Override
	public SegnoZodiacale[] getSegni() {
		return SegnoZodiacale.values();
	}
	
	
	
}
