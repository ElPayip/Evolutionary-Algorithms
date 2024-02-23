package modelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import modelo.cruce.Cruce;
import modelo.fitness.FitMichalewicz;
import modelo.individuo.Individuo;
import modelo.individuo.IndividuoReal;
import modelo.mutacion.MutacionUniforme;
import modelo.seleccion.Seleccion;
import vista.ConfigPanel.IntegerOption;
import vista.ConfigPanel.Option;

public class MichalewiczReal extends AlgGenetico<Double> {

	private static final double MAX = Math.PI;
	private static final double MIN = 0;
	private int d;
	
	public MichalewiczReal(MichalewiczReal otro) {
		super(otro);
		d = otro.d;
	}

	public MichalewiczReal() {
		super(new FitMichalewicz());
		mutacion = new MutacionUniforme();
	}

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

	@Override
	public CategoriaGen getCategoria() {
		return CategoriaGen.REAL;
	}

	@Override
	public String toString() {
		return "Michalewicz (Real)";
	}

	@Override
	public <T> List<Option<T>> getExtraOpts() {
		List<Option<T>> extras = new ArrayList<>();
		extras.add(new IntegerOption<T>("dimension", "dimension", "d", 0, 1000));
		return extras;
	}

	@Override
	public AlgGenetico<Double> clone() {
		return new MichalewiczReal(this);
	}

	@Override
	public Boolean maximizacion() {
		return false;
	}
}
