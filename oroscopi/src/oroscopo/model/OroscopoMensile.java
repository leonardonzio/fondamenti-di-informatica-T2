package oroscopo.model;

import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;

public class OroscopoMensile implements Oroscopo, Comparable<Oroscopo>{

	private Previsione amore, lavoro, salute;
	private SegnoZodiacale segnoZodiacale;
	
	public OroscopoMensile(SegnoZodiacale segnoZodiacale, Previsione amore, Previsione lavoro, Previsione salute) {
		
		if (segnoZodiacale == null)
			throw new IllegalArgumentException("segno zodiacale nullo");
		
		if (amore == null || lavoro == null || salute == null)
			throw new IllegalArgumentException("una o piu previsioni nulle");
			
		boolean valido = amore.validaPerSegno(segnoZodiacale) &&
				lavoro.validaPerSegno(segnoZodiacale) &&
				salute.validaPerSegno(segnoZodiacale);
		
		if (!valido) 
			throw new IllegalArgumentException("previsioni non valide per tale segno zodiacale");
			
		this.segnoZodiacale = segnoZodiacale;
		this.amore = amore;
		this.lavoro = lavoro;
		this.salute = salute;
	}
	
	public OroscopoMensile(String nomeSegnoZodiacale, Previsione amore, Previsione lavoro, Previsione salute) {
		this(getSegnoZodiacaleFrom(nomeSegnoZodiacale), amore, lavoro, salute);
	}
	
	private static SegnoZodiacale getSegnoZodiacaleFrom (String nomeSegnoZodiacale) {
		
		if (nomeSegnoZodiacale == null || nomeSegnoZodiacale.isEmpty())
			throw new IllegalArgumentException("segno");

		return SegnoZodiacale.valueOf(nomeSegnoZodiacale);
	}

	public Previsione getPrevisioneAmore() {
		return amore;
	}

	public int getFortuna() {
		return Math.round((
				(float) amore.getValore()
				+ lavoro.getValore()
				+ salute.getValore())
				/ 3
		);
	}
	
	public Previsione getPrevisioneLavoro() {
		return lavoro;
	}

	public Previsione getPrevisioneSalute() {
		return salute;
	}

	public SegnoZodiacale getSegnoZodiacale() {
		return segnoZodiacale;
	}

	@Override
	public int hashCode() {
		return Objects.hash(amore, lavoro, salute, segnoZodiacale);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof OroscopoMensile))
			return false;
		OroscopoMensile other = (OroscopoMensile) obj;
		return Objects.equals(amore, other.amore) && Objects.equals(lavoro, other.lavoro)
				&& Objects.equals(salute, other.salute) && segnoZodiacale == other.segnoZodiacale;
	}

	@Override
	public int compareTo(Oroscopo o) {
		
		return segnoZodiacale.compareTo(o.getSegnoZodiacale());
	}
	
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("AMORE:\t" + amore.getPrevisione()).append(System.lineSeparator());
		sb.append("LAVORO:\t" + lavoro.getPrevisione()).append(System.lineSeparator());
		sb.append("SALUTE:\t" + salute.getPrevisione());
		return sb.toString();
	}

	
	
	
}
