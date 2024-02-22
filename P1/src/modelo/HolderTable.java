package modelo;

import java.util.ArrayList;
import java.util.List;

import modelo.cruce.Cruce;
import modelo.fitness.FitHolderTable;
import modelo.individuo.Individuo;
import modelo.individuo.IndividuoBinarioReal;
import modelo.mutacion.MutacionBinaria;
import modelo.seleccion.Seleccion;
import vista.ConfigPanel.DoubleOption;
import vista.ConfigPanel.Option;

public class HolderTable extends AlgGenetico<Double> {
	
	private static final double MAX = 10;
	private static final double MIN = -10;
	private Double precision;

	public HolderTable() {
		super(new FitHolderTable());
	}

	public HolderTable(Cruce<Double> cruce, Seleccion seleccion,
			int nGeneraciones, int tamPoblacion, double probCruce, double probMutacion, double prec) {
		super(cruce, seleccion, new FitHolderTable(), new MutacionBinaria<Double>(), nGeneraciones, tamPoblacion, probCruce, probMutacion);
	}

	@Override
	protected Individuo<Double> generarIndividuo() {
		Double[] maxs = {MAX,MAX};
		Double[] mins = {MIN,MIN};
		return new IndividuoBinarioReal(maxs, mins, precision);
	}

	@Override
	public CategoriaGen getCategoria() {
		return CategoriaGen.BINARIO;
	}

	@Override
	public String toString() {
		return "Holder Table";
	}

	@Override
	public <T> List<Option<T>> getExtraOpts() {
		List<Option<T>> extras = new ArrayList<>();
		extras.add(new DoubleOption<T>("precision", "precision", "precision", 0, 1000));
		return extras;
	}

	public Double getPrecision() {
		return precision;
	}

	public void setPrecision(Double precision) {
		this.precision = precision;
	}
}
