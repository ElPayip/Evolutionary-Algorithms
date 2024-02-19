package modelo.cruce;

import java.util.ArrayList;
import java.util.List;

import modelo.genes.Gen;
import utils.Pair;

public class CruceAritmetico implements Cruce<Double> {
	
	private double alpha;
	
	public CruceAritmetico() {
		alpha = 0.5;
	}
	
	public CruceAritmetico(double alpha) {
		this.alpha = alpha;
	}

	@Override
	public Pair<List<? extends Gen<Double>>,List<? extends Gen<Double>>> cruzar(
			List<? extends Gen<Double>> crom1, List<? extends Gen<Double>> crom2) {
		
		List<Gen<Double>> h1 = new ArrayList<>(crom1);
		List<Gen<Double>> h2 = new ArrayList<>(crom2);
		
		for (int i = 0; i < h1.size(); ++i) {
			h1.get(i).setValor(h1.get(i).getValor()*alpha + (1-alpha)*h2.get(i).getValor());
			h2.get(i).setValor(h2.get(i).getValor()*alpha + (1-alpha)*h1.get(i).getValor());
		}
		return new Pair<>(h1, h2);
	}
}
