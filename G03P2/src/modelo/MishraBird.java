package modelo;

import java.util.ArrayList;
import java.util.List;

import modelo.cruce.CruceMonopuntoBin;
import modelo.fitness.FitMishraBird;
import modelo.individuo.Individuo;
import modelo.individuo.IndividuoBinarioReal;
import modelo.mutacion.MutacionBinaria;
import modelo.seleccion.SelTruncamiento;
import vista.ConfigPanel.DoubleOption;
import vista.ConfigPanel.Option;

public class MishraBird extends AlgGenetico<Double>{
	
	private Double precision = 0.01;
	
	public MishraBird(MishraBird otro) {
		super(otro);
		precision = otro.precision;
	}
	
	public MishraBird() {
		super(new FitMishraBird());
		mutacion = new MutacionBinaria<Double>();
		cruce = new CruceMonopuntoBin<Double>();
		seleccion = new SelTruncamiento();
	}

	@Override
	public <T> List<Option<T>> getExtraOpts() {
		List<Option<T>> extras = new ArrayList<>();
		extras.add(new DoubleOption<T>("precision", "precision", "precision", 0, 1000));
		return extras;
	}

	@Override
	protected Individuo<Double> generarIndividuo() {
		Double[] maxs = {0.0,0.0};
		Double[] mins = {-10.0,-6.5};
		return new IndividuoBinarioReal(mins, maxs, precision);
	}
	
	@Override
	public CategoriaCrom getCategoria() {
		return CategoriaCrom.BINARIO;
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
