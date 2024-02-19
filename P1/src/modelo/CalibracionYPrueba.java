package modelo;

import modelo.cruce.Cruce;
import modelo.fitness.FitCalibracionPrueba;
import modelo.individuo.Individuo;
import modelo.individuo.IndividuoBinarioReal;
import modelo.mutacion.MutacionBinaria;
import modelo.seleccion.Seleccion;

public class CalibracionYPrueba extends AlgGenetico<Double,Integer> {
	
	private static final double MAX = 10;
	private static final double MIN = -10;
	private Double prec;

	public CalibracionYPrueba(Cruce<Integer> cruce, Seleccion seleccion,
			int nGeneraciones, int tamPoblacion, double probCruce, double probMutacion, double prec) {
		
		super(cruce, seleccion, new FitCalibracionPrueba(), new MutacionBinaria(), 
				nGeneraciones, tamPoblacion, probCruce, probMutacion);
		this.prec = prec;
	}

	@Override
	protected Individuo<Double, Integer> generarIndividuo() {
		Double[] maxs = {MAX,MAX};
		Double[] mins = {MIN,MIN};
		return new IndividuoBinarioReal<Double>(maxs, mins, prec);
	}
}
