package ghigliottina.ui;

import java.util.List;
import java.util.Random;

import ghigliottina.model.Ghigliottina;

public class MyController implements Controller {

	private List<Ghigliottina> list;
	
	public MyController(List<Ghigliottina> list) {
		if (list == null)
			throw new IllegalArgumentException("lista nulla");
		
		this.list = list;
	}
	
	
	@Override
	public Ghigliottina sorteggiaGhigliottina() {
		int tot = listaGhigliottine().size();
		Random r = new Random();
		
		return listaGhigliottine().get(r.nextInt(tot));
	}

	@Override
	public List<Ghigliottina> listaGhigliottine() {
		return list;
	}

	
	
}
