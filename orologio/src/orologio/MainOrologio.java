package orologio;

public class MainOrologio {

	public static void main(String[] args) {
		
		Orologio o3 = new Orologio();
		
		// unico modo per stopparlo è manualmente
		while(true) {
			System.out.println(o3.toString());
			try { Thread.sleep(100); }
			catch(InterruptedException e){}
			o3.tic();
		}
		
	}
}
