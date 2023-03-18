package portafoglio;

import taglio.Taglio;

public class TestPortafoglio {

	public static void main(String[] args) {
		
		Portafoglio p1 = new Portafoglio(
			new Taglio[] {Taglio.CINQUANTA, Taglio.CENTO}
		);
		
		
		Portafoglio p2 = new Portafoglio(
				new Taglio[] {Taglio.UNO, Taglio.VENTI, Taglio.DUE}
		);
		
		
		// assert p1
		System.out.println(p1.toString());
		assert(p1.getValore() == 150);
		
		
		// assert p2
		System.out.println(p2.toString());
		assert(p2.getValore() == 23);

		// assert compare
		assert(p1.compare(p2) == 1);
		assert(p2.compare(p1) == -1);
		
	}
	
}
