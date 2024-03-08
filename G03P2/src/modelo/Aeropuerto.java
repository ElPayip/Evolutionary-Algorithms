package modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import modelo.fitness.FitAeropuerto;
import modelo.individuo.Individuo;
import vista.ConfigPanel.IntegerOption;
import vista.ConfigPanel.Option;

public class Aeropuerto extends AlgGenetico<Integer> {
	
	private List<List<Integer>> tel;
	private Map<Pesos,Map<Pesos,Double>> sep;
	private List<Pesos> peso;
	
	private Integer pistas;
	private Integer vuelos;
	
	public Aeropuerto(String file) {
		fitness = new FitAeropuerto();
		mutacion = null;
		cruce = null;
		seleccion = new SelRanking();
	}

	public Aeropuerto(AlgGenetico<Integer> otro) {
		super(otro);
		// TODO Auto-generated constructor stub
	}

	@Override
	public <T> List<Option<T>> getExtraOpts() {
		List<Option<T>> extras = new ArrayList<>();
		extras.add(new IntegerOption<T>("vuelos", "vuelos", "vuelos", 0, 1000));
		extras.add(new IntegerOption<T>("pistas", "pistas", "pistas", 0, 1000));
		return extras;
	}

	@Override
	protected Individuo<Integer> generarIndividuo() {
		List<Integer> crom = IntStream.range(0, vuelos).boxed().collect(Collectors.toList());
		Collections.shuffle(crom);
		return null;
	}

	@Override
	public CategoriaCrom getCategoria() {
		return CategoriaCrom.PERMUTACION;
	}

	@Override
	public AlgGenetico<Integer> clone() {
		return new Aeropuerto(this);
	}

	@Override
	public Boolean maximizacion() {
		return false;
	}

}
