package contocorrente.model;

import java.time.LocalDate;

public class MyAnalizzatore implements Analizzatore {
	
	private ContoCorrente cc;
	
	public MyAnalizzatore(ContoCorrente cc) {
		if(cc==null) throw new IllegalArgumentException("CC nullo nel costruttore dell'analizzatore dati");
		this.cc=cc;
	}

	@Override
	public double getSaldo(LocalDate data) {
		// *** DA FARE ***
		// il metodo getSaldo effettua la somma algebrica dei movimenti effettuati entro la data contabile specificata, 
		// avendo cura però di escludere le righe relative ai saldi diversi dal saldo iniziale (altrimenti, gli importi 
		// verrebbero sommati più volte e il totale sarebbe inconsistente); in presenza di un saldo intermedio deve invece 
		// verificare che la somma dei movimenti fino a quel momento coincida con quella dichiarata nel saldo stesso 
		// (a meno di € 0.01): ove così non sia, deve lanciare l’apposita InconsistentBalanceException (fornita)
		
		double saldo = 0;
		for (Movimento m : cc.getMovimenti()) {
			if(!m.getDataContabile().isAfter(data)) {
				if(!m.getTipologia().equals(Tipologia.SALDO))
					saldo += m.getImporto();
									
				else if (Math.abs(m.getImporto() - saldo) > 0.01)
					throw new InconsistentBalanceException("Saldo dichiarato " + m.getImporto() + " diverso da somma movimenti precedenti " + saldo);				
			}
		}
		return saldo;
	}
	
	@Override
	public double getSommaPerTipologia(LocalDate data, Tipologia tipologia) {
		// *** DA FARE ***
		// il metodo getSommaPerTipologia procede similmente, limitando tuttavia la somma ai soli movimenti della tipologia 
		// specificata, che può essere solo ACCREDITO o ADDEBITO: in caso contrario, dev’essere lanciata IllegalArgumentException
		
		if (!tipologia.equals(Tipologia.ACCREDITO) && !tipologia.equals(Tipologia.ADDEBITO))
			throw new IllegalArgumentException("solo accrediti e addebiti ammissibili");
		
		return cc.getMovimenti().stream()
				.filter(m -> !m.getDataContabile().isAfter(data) && m.getTipologia().equals(tipologia))
				.mapToDouble(Movimento::getImporto)
				.sum();
	}
	
}
