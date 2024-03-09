package modelo;

import java.util.ArrayList;
import java.util.List;

import modelo.cruce.CruceUniformeBin;
import modelo.fitness.FitCalibracionPrueba;
import modelo.individuo.Individuo;
import modelo.individuo.IndividuoBinarioReal;
import modelo.mutacion.MutacionBinaria;
import modelo.seleccion.SelTorneoDet;
import vista.ConfigPanel.DoubleOption;
import vista.ConfigPanel.Option;

public class CalibracionYPrueba extends AlgGenetico<Double> {
	
	private static final double MAX = 10;
	private static final double MIN = -10;
	private Double precision = 0.01;

	public CalibracionYPrueba() {
		super(new FitCalibracionPrueba());
		mutacion = new MutacionBinaria<Double>();
		cruce = new CruceUniformeBin<Double>();
		seleccion = new SelTorneoDet();
	}
	
	public CalibracionYPrueba(CalibracionYPrueba otro) {
		super(otro);
		this.precision = otro.precision;
	}

	@Override
	protected Individuo<Double> generarIndividuo() {
		Double[] maxs = {MAX,MAX};
		Double[] mins = {MIN,MIN};
		return new IndividuoBinarioReal(mins, maxs, precision);
	}

	@Override
	public CategoriaCrom getCategoria() {
		return CategoriaCrom.BINARIO;
	}

	@Override
	public String toString() {
		return "Calibracion y Prueba";
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

	@Override
	public AlgGenetico<Double> clone() {
		return new CalibracionYPrueba(this);
	}

	@Override
	public Boolean maximizacion() {
		return true;
	}
}
