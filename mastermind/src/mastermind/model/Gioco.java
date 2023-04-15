package mastermind.model;


public class Gioco {

	private int dim;
	private int maxTentativi;
	private int numTentativi;
	private Risposta[] risposte;
	private Status stato;
	private Combinazione[] tentativi;
	

	public Gioco(int maxTentativi, int dim) {
		this.dim = dim;
		this.maxTentativi = maxTentativi;
		this.risposte = new Risposta[dim];
		this.tentativi = new Combinazione[dim];
		this.stato = Status.IN_CORSO;
		this.numTentativi = 0;
	}

	public int dimensione() {
		return this.dim;
	}
	
	public Status stato() {
		return this.stato;
	}
	
	public Combinazione tentativo(int index) {
		if (index >= numTentativi) {
			System.err.println("Tentativo " + index + " inesistente: l'ultimo è il " + numTentativi);
			return null;
		}
		
		return this.tentativi[index];
	}
	
	public Risposta risposta(int index) {
		if (index >= numTentativi) {
			System.err.println("Risposta " + index + " inesistente: l'ultima è la " + numTentativi);
			return null;
		}
		
		return this.risposte[index];
	}
	
	public int tentativiEffettuati() {
		return this.numTentativi;
	}
	
	public int tentativiRimasti() {
		return maxTentativi - numTentativi;
	}
	
	public int maxTentativi() {
		return this.maxTentativi;
	}
	
	public Status registraMossa(Combinazione tentativo, Risposta risposta) {
		risposte[numTentativi] = risposta;
		tentativi[numTentativi] = tentativo;
		numTentativi++;
	
		if(risposta.vittoria()) {
			stato = Status.VITTORIA;
		}
		else if(numTentativi < maxTentativi) {
			stato = Status.IN_CORSO;
		}
		else {
			stato = Status.PERSO;			
		}
		
		return stato;
	}
	
	public Risposta ultimaRisposta() {
		return risposte[tentativiEffettuati()-1];
	}
	
	public Combinazione ultimoTentativo() {
		return tentativi[tentativiEffettuati()-1];
	}
	
	
	public String situazione() {
		StringBuilder sb = new StringBuilder(); 
		for (int i=0; i < numTentativi; i++) {
			sb.append((i+1) + ") " + tentativi[i].toString() + "\t\t" +
					(tentativi[i].toString().length()<20 ? "\t" : "") +
					risposte[i].toString()+ System.lineSeparator());
		}
		return sb.toString();
	}
	
	@Override
	public String toString() {
		return 	"Situazione:" + System.lineSeparator() + situazione() + System.lineSeparator() +
				"Gioco: " + this.stato() + System.lineSeparator();
	}

}
