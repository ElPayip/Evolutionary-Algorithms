package modelo.fitness;

import java.util.List;

import modelo.individuo.Individuo;

public class FitMichalewicz implements Fitness<Double> {
	
	private static final int M = 10;

	@Override
	public double eval(Individuo<Double> ind) {
		double fit = 0;
		List<Double> x = ind.getValores();
		for (int i = 0; i < x.size(); ++i) {
			double aux = (i+1) * Math.pow(x.get(i), 2) / Math.PI;
			fit -= Math.sin(x.get(i)) * Math.pow(Math.sin(aux), 2 * M);
		}
		return fit;
	}
}
