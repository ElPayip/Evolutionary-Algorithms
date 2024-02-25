package modelo;

import java.util.ArrayList;
import java.util.List;

import modelo.cruce.Cruce;
import modelo.fitness.FitMishraBird;
import modelo.fitness.Fitness;
import modelo.individuo.Individuo;
import modelo.individuo.IndividuoBinarioReal;
import modelo.mutacion.Mutacion;
import modelo.mutacion.MutacionBinaria;
import modelo.seleccion.Seleccion;
import vista.ConfigPanel.DoubleOption;
import vista.ConfigPanel.Option;

public class MishraBird extends AlgGenetico<Double>{
	
	private static final double MAX = 10;
	private static final double MIN = -10;
	private Double precision = 0.01;
	
	public MishraBird(MishraBird otro) {
		super(otro);
		precision = otro.precision;
	}
	
	public MishraBird() {
		super(new FitMishraBird());
		mutacion = new MutacionBinaria<Double>();
	}
	
	
	public MishraBird(Cruce<Double> cruce, Seleccion seleccion, Fitness<Double> fitness, Mutacion<Double> mutacion,
			int nGeneraciones, int tamPoblacion, double probCruce, double probMutacion) {
		super(cruce, seleccion, new FitMishraBird(), new MutacionBinaria<Double>(), nGeneraciones, tamPoblacion, probCruce, probMutacion);
	}

	@Override
	public <T> List<Option<T>> getExtraOpts() {
		List<Option<T>> extras = new ArrayList<>();
		extras.add(new DoubleOption<T>("precision", "precision", "precision", 0, 1000));
		return extras;
	}

	@Override
	protected Individuo<Double> generarIndividuo() {
		Double[] maxs = {MAX,MAX};
		Double[] mins = {MIN,MIN};
		return new IndividuoBinarioReal(mins, maxs, precision);
	}
	
	@Override
	public CategoriaGen getCategoria() {
		return CategoriaGen.BINARIO;
	}

	@Override
	public String toString() {
		return "Mishra Bird";
	}
	
	public Double getPrecision() {
		return precision;
	}

	public void setPrecision(Double precision) {
		this.precision = precision;
	}

	@Override
	public AlgGenetico<Double> clone() {
		return new MishraBird(this);
	}

	@Override
	public Boolean maximizacion() {
		return false;
	}

}
