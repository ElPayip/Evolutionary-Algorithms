package modelo.fitness;

import java.util.List;

import modelo.individuo.Individuo;

public class FitHolderTable implements Fitness<Double> {

	@Override
	public double eval(Individuo<Double> ind) {
		List<Double> x = ind.getValores();
		double fit = Math.sqrt(Math.pow(x.get(0), 2) + Math.pow(x.get(1), 2));
		fit = Math.abs(1 - fit / Math.PI);
		fit = Math.sin(x.get(0)) * Math.cos(x.get(1)) * Math.exp(fit);
		return -Math.abs(fit);
	}
}
