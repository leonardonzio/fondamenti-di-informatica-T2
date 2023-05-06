package frazioni;
public class TestFrazione {
	public static void main(String[] args) {
		
		//test costruzione frazione
		//test funzionamento metodi accessor 
		Frazione frazione1 = new Frazione(-3, 12);
		assert(frazione1.getNum() == -3 && frazione1.getDen() == 12);
		
		Frazione frazione2 = new Frazione(1, 4);
		assert(frazione2.getNum() == 1 && frazione2.getDen() == 4);

		
		//test valori negativi
		Frazione frazione5 = new Frazione(-1, 8);
		assert(frazione5.getNum() == -1 && frazione5.getDen() == 8);
		
		Frazione frazione6 = new Frazione(2, -3);
		assert(frazione6.getNum() == 2 && frazione6.getDen() == -3);
		
		Frazione frazione7 = new Frazione(-5, -7);
		assert(frazione7.getNum() == -5 && frazione7.getDen() == -7);
		
		
		
		// test equals
		assert(!frazione1.equals(frazione6));
		
		
		// test minTerm
		Frazione frazioneRid = frazione1.minTerm();
		assert(frazioneRid.getNum() == -1 && frazioneRid.getDen() == 4);
	
		
		// test sum
		var f1 = new Frazione(-68,9);
		var f2 = new Frazione(66, 5);
		var fSum = new Frazione(254, 45);
		assert(f1.sum(f2).equals(fSum));;
		
		
		// test sub
		var fSub = new Frazione(934, -45);
		assert(f1.sub(f2).equals(fSub));
		
		
		// test div
		var fDiv = new Frazione(170, -297);
		assert(f1.div(f2).equals(fDiv));
		
		
		// test convertToDouble
		assert(f2.convertToDouble() == 13.2);

		assert(f1.compareTo(f2) == -1);
		
		
	}
	
}
