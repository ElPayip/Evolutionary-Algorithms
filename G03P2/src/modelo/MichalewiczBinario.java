package modelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import modelo.cruce.Cruce;
import modelo.cruce.CruceMonopuntoBin;
import modelo.fitness.FitMichalewicz;
import modelo.individuo.Individuo;
import modelo.individuo.IndividuoBinarioReal;
import modelo.mutacion.MutacionBinaria;
import modelo.seleccion.SelEstocastico;
import modelo.seleccion.Seleccion;
import vista.ConfigPanel.DoubleOption;
import vista.ConfigPanel.IntegerOption;
import vista.ConfigPanel.Option;

public class MichalewiczBinario extends AlgGenetico<Double>{
	
	private static final double MAX = Math.PI;
	private static final double MIN = 0;
	private int d = 2;
	private Double precision = 0.01;

	public MichalewiczBinario(MichalewiczBinario otro) {
		super(otro);
		d = otro.d;
	}
	
	public MichalewiczBinario() {
		super(new FitMichalewicz());
		mutacion = new MutacionBinaria<Double>();
		cruce = new CruceMonopuntoBin<Double>();
		seleccion = new SelEstocastico();
	}
	
	public MichalewiczBinario(Cruce<Double> cruce, Seleccion seleccion,
			int nGeneraciones, int tamPoblacion, double probCruce, double probMutacion, double prec, int d) {
		super(cruce, seleccion, new FitMichalewicz(), new MutacionBinaria<Double>(), nGeneraciones, tamPoblacion, probCruce, probMutacion);
		this.d = d;
	}

	@Override
	public <T> List<Option<T>> getExtraOpts() {
		List<Option<T>> extras = new ArrayList<>();
		extras.add(new DoubleOption<T>("precision", "precision", "precision", 0, 1000));
		extras.add(new IntegerOption<T>("dimension", "dimension", "d", 0, 1000));
		return extras;
	}

	@Override
	protected Individuo<Double> generarIndividuo() {
		Double[] maxs = new Double[d], 
				 mins = new Double[d];
		Arrays.fill(maxs, MAX);
		Arrays.fill(mins, MIN);
		return new IndividuoBinarioReal(mins, maxs, precision);
	}
	
	@Override
	public String toString() {
		return "Michalewicz (Binario)";
	}

	@Override
	public CategoriaGen getCategoria() {
		return CategoriaGen.BINARIO;
	}

	@Override
	public AlgGenetico<Double> clone() {
		return new MichalewiczBinario(this);
	}

	@Override
	public Boolean maximizacion() {
		return false;
	}
	
	public int getD() {
		return d;
	}

	public void setD(int d) {
		this.d = d;
	}

	public Double getPrecision() {
		return precision;
	}

	public void setPrecision(Double precision) {
		this.precision = precision;
	}
}
