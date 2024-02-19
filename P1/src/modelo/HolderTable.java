package modelo;

import modelo.cruce.Cruce;
import modelo.fitness.FitHolderTable;
import modelo.individuo.Individuo;
import modelo.individuo.IndividuoBinarioReal;
import modelo.mutacion.MutacionBinaria;
import modelo.seleccion.Seleccion;

public class HolderTable extends AlgGenetico<Double> {
	
	private static final double MAX = 10;
	private static final double MIN = -10;
	private Double prec;

	public HolderTable(Cruce<Double> cruce, Seleccion seleccion,
			int nGeneraciones, int tamPoblacion, double probCruce, double probMutacion, double prec) {
		super(cruce, seleccion, new FitHolderTable(), new MutacionBinaria<Double>(), nGeneraciones, tamPoblacion, probCruce, probMutacion);
	}

	@Override
	protected Individuo<Double> generarIndividuo() {
		Double[] maxs = {MAX,MAX};
		Double[] mins = {MIN,MIN};
		return new IndividuoBinarioReal(maxs, mins, prec);
	}
}
