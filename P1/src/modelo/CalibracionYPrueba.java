package modelo;

import modelo.cruce.Cruce;
import modelo.factorias.FactoriaAlgGenetico;
import modelo.fitness.FitCalibracionPrueba;
import modelo.individuo.Individuo;
import modelo.individuo.IndividuoBinarioReal;
import modelo.mutacion.MutacionBinaria;
import modelo.seleccion.Seleccion;
import vista.ConfigPanel;
import vista.ConfigPanel.InnerOption;

public class CalibracionYPrueba extends AlgGenetico<Double> {
	
	private static final double MAX = 10;
	private static final double MIN = -10;
	private Double prec;

	public CalibracionYPrueba() {
		super();
	}

	public CalibracionYPrueba(Cruce<Double> cruce, Seleccion seleccion,
			int nGeneraciones, int tamPoblacion, double probCruce, double probMutacion, double prec) {
		
		super(cruce, seleccion, new FitCalibracionPrueba(), new MutacionBinaria<Double>(), 
				nGeneraciones, tamPoblacion, probCruce, probMutacion);
		this.prec = prec;
	}

	@Override
	protected Individuo<Double> generarIndividuo() {
		Double[] maxs = {MAX,MAX};
		Double[] mins = {MIN,MIN};
		return new IndividuoBinarioReal(maxs, mins, prec);
	}

	@Override
	public void configurar(ConfigPanel<FactoriaAlgGenetico> config) {
		config.beginInner(new InnerOption<FactoriaAlgGenetico,AlgGenetico<?>>(
	  			  "Calibracion y Prueba",
	  			  "Calibracion y Prueba",
	  			  "alg",
	  			  CalibracionYPrueba.class))
	  	  	  .addInner(null);//TODO
	}
}
