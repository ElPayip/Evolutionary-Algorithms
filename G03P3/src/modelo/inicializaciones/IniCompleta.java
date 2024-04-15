package modelo.inicializaciones;

import java.util.ArrayList;
import java.util.List;

import modelo.genes.Gen;
import modelo.genes.GenNodo;
import modelo.individuo.Individuo;
import vista.ConfigPanel.IntegerOption;
import vista.ConfigPanel.Option;

public class IniCompleta<T> implements Inicializacion<T> {
	
	private Individuo<T> ejemploInd;
	private GenNodo<T> ejemploNodo;
	private Integer prof;

	public IniCompleta() {
	}
	
	public IniCompleta(Individuo<T> ejemploInd, GenNodo<T> ejemploNodo, Integer prof) {
		super();
		this.ejemploInd = ejemploInd;
		this.ejemploNodo = ejemploNodo;
		this.prof = prof;
	}

	@Override
	public List<Individuo<T>> init(int n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <O> List<Option<O>> getExtraOpts() {
		List<Option<O>> extras = new ArrayList<>();
		extras.add(new IntegerOption<O>("Profundidad", "Profundidad", "prof", 0, 1000));
		return extras;
	}

	@Override
	public void setInstances(Individuo<T> ind, Gen<T> gen) {
		ejemploInd = ind.clone();
		ejemploNodo = (GenNodo<T>) gen.clone();
	}
	
	@Override
	public Inicializacion<T> clone() {
		return new IniCompleta<>(ejemploInd, ejemploNodo, prof);
	}

}
