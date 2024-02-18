package modelo.cruce;

import java.util.ArrayList;
import java.util.List;

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
	public Pair<List<Double>, List<Double>> cruzar(List<Double> crom1, List<Double> crom2) {
		List<Double> h1 = new ArrayList<>(crom1);
		List<Double> h2 = new ArrayList<>(crom2);
		
		for (int i = 0; i < h1.size(); ++i) {
			h1.set(i, h1.get(i)*alpha + (1-alpha)*h2.get(i));
			h2.set(i, h2.get(i)*alpha + (1-alpha)*h1.get(i));
		}
		return new Pair<>(h1, h2);
	}
}
