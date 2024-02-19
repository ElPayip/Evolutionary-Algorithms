package modelo;

import java.util.Arrays;

import modelo.cruce.Cruce;
import modelo.fitness.FitMichalewicz;
import modelo.individuo.Individuo;
import modelo.individuo.IndividuoReal;
import modelo.mutacion.MutacionUniforme;
import modelo.seleccion.Seleccion;

public class MichalewiczReal extends AlgGenetico<Double> {

	private static final double MAX = Math.PI;
	private static final double MIN = 0;
	private int d;

	public MichalewiczReal(Cruce<Double> cruce, Seleccion seleccion,
			int nGeneraciones, int tamPoblacion, double probCruce, double probMutacion, int d) {
		super(cruce, seleccion, new FitMichalewicz(), new MutacionUniforme(), nGeneraciones, tamPoblacion, probCruce, probMutacion);
		this.d = d;
	}

	@Override
	protected Individuo<Double> generarIndividuo() {
		Double[] maxs = new Double[d], 
				 mins = new Double[d];
		Arrays.fill(maxs, MAX);
		Arrays.fill(mins, MIN);
		return new IndividuoReal(mins, maxs);
	}
}
