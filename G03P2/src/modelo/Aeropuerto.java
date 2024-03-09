package modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import modelo.cruce.CrucePMX;
import modelo.fitness.FitAeropuerto;
import modelo.individuo.Individuo;
import modelo.mutacion.MutacionIntercambio;
import modelo.seleccion.SelRuleta;
import vista.ConfigPanel.Option;
import vista.ConfigPanel.TextOption;

public class Aeropuerto extends AlgGenetico<Integer> {
	
	private List<List<Integer>> tel;
	private Map<Peso,Map<Peso,Double>> sep;
	private List<Peso> peso;
	
	private Integer pistas;
	private Integer vuelos;
	private String file = "";
	
	public Aeropuerto() {
		mutacion = new MutacionIntercambio<>();
		cruce = new CrucePMX<>();
		seleccion = new SelRuleta();
	}

	public Aeropuerto(AlgGenetico<Integer> otro) {
		super(otro);
		Aeropuerto alg = (Aeropuerto) otro;
		tel = alg.tel;
		sep = alg.sep;
		peso = alg.peso;
		pistas = alg.pistas;
		vuelos = alg.vuelos;
	}
	
	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
		initFromFile(file);
		fitness = new FitAeropuerto(tel, sep, peso);
	}

	private void initFromFile(String file) {
		//TODO
	}

	@Override
	public <T> List<Option<T>> getExtraOpts() {
		List<Option<T>> extras = new ArrayList<>();
		extras.add(new TextOption<T>("archivo", "archivo", "file"));
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

	@Override
	public String toString() {
		return "Aeropuerto";
	}
}
