package mediaesami.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.print.attribute.standard.MediaSize.Engineering;

import org.junit.platform.commons.util.CollectionUtils;

public class Carriera {	
	private List<Esame> listaEsami;
	private Comparator<Esame> cmp;
	
	public Carriera() {
		this.listaEsami = new ArrayList<>();
		cmp = Comparator.comparing(Esame::getDate)
				.thenComparing(e -> e.getAf().getId());
	}
	
	public void inserisci(Esame esame) {
		if (esame == null)
			throw new IllegalArgumentException("esame inesistente");

		Optional<Esame> ultimoTentativo = getUltimoTentativo(esame);		
 		if (ultimoTentativo.isPresent() && (
 				ultimoTentativo.get().getVoto().superato() 
				|| esame.getDate().isBefore(ultimoTentativo.get().getDate())
				|| esame.getDate().equals(ultimoTentativo.get().getDate()) ))
					throw new IllegalArgumentException("Esame già sostenuto " + ultimoTentativo.get());
		
		boolean provaFinalePrimaDiEsami = listaEsami.stream()
				.anyMatch(e -> !esame.getDate().isAfter(e.getDate()));
		
		if (esame.getAf().getNome().contains("PROVA FINALE") &&
				provaFinalePrimaDiEsami) {
			throw new IllegalArgumentException("l'esame finale è prima di altri esami");
		}
		
		listaEsami.add(esame);
		listaEsami.sort(cmp);
	}
	
	public List<Esame> getListaEsami() {
		return listaEsami;
	}

	public List<Esame> getTentativiPrecedenti(Esame esame){
		
		return listaEsami.stream()
				.filter(e -> e.getAf().getId() == esame.getAf().getId())
				.sorted(cmp)
				.toList();
	}
		
	public Optional<Esame> getUltimoTentativo(Esame esame){
		
		List<Esame> tentativi = getTentativiPrecedenti(esame);
		
		return tentativi.isEmpty() 
				? Optional.empty()
				: Optional.of(tentativi.get(tentativi.size() - 1));
	}

	public Optional<Esame> getUltimoEsameDato(){
		
		return Optional.ofNullable(listaEsami.get(listaEsami.size() - 1)); 
	}
	
	public double creditiAcquisiti() {
		return listaEsami.stream()
				.filter(e -> e.getVoto().superato())
				.mapToDouble(e -> e.getAf().getCfu())
				.sum();
	}
	
	public double mediaPesata() {
		
		return 	(listaEsami.stream()
				.filter(e -> e.getVoto().getValue().isPresent())
				.mapToDouble(this::votoPesato)
				.sum())
				/ this.creditiAcquisitiDaVoto();
	}
	
	@Override
	public String toString() {
		return listaEsami.stream().map(Esame::toString).collect(Collectors.joining(System.lineSeparator()));
	}
	
	private double votoPesato (Esame esame) {
		return esame.getVoto().getValue().getAsInt() * esame.getAf().getCfu();
	}
	
	private double creditiAcquisitiDaVoto() {
		return listaEsami.stream()
				.filter(e -> e.getVoto().getValue().isPresent())
				.mapToDouble(e -> e.getAf().getCfu())
				.sum();
	}
	
}
