package modelo;

import modelo.cruce.Cruce;
import modelo.fitness.FitCalibracionPrueba;
import modelo.mutacion.MutacionBinaria;
import modelo.seleccion.Seleccion;

public class CalibracionYPrueba extends AlgGenetico<Double,Integer> {

	public CalibracionYPrueba(Cruce<Integer> cruce, Seleccion seleccion,
			int nGeneraciones, double probCruce, double probMutacion) {
		
		super(cruce, seleccion, new FitCalibracionPrueba(), new MutacionBinaria(), nGeneraciones, probCruce, probMutacion);
	}

	@Override
	protected void initPoblacion() {
		// TODO Auto-generated method stub
		
	}
}
