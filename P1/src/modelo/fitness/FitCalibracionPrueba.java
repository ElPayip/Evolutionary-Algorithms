package modelo.fitness;

import java.util.List;

import modelo.individuo.Individuo;

public class FitCalibracionPrueba implements Fitness<Double> {

	@Override
	public double eval(Individuo<Double,?> ind) {
		List<Double> vals = ind.getValores();
		if (vals.size() != 2)
			throw new IllegalArgumentException("Se esperan 2 valores en FitCalibracionPrueba");
		
		return Math.pow(vals.get(0), 2) + 2*Math.pow(vals.get(1), 2);
	}

}
