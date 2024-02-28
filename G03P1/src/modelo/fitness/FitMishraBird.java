package modelo.fitness;

import java.util.List;

import modelo.individuo.Individuo;

public class FitMishraBird implements Fitness<Double>{

	@Override
	public double eval(Individuo<Double> ind) {
		List<Double> x = ind.getValores();
		double fit = Math.sin(x.get(1)) * Math.exp(Math.pow((1-Math.cos(x.get(0))), 2));
		fit += Math.cos(x.get(0)) * Math.exp(Math.pow((1-Math.sin(x.get(1))), 2));
		fit += Math.pow(x.get(0) - x.get(1), 2);
		return fit;
	}
}
