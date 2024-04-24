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
	private Integer prof = 3;

	public IniCompleta() {}
	
	public IniCompleta(Individuo<T> ejemploInd, GenNodo<T> ejemploNodo, Integer prof) {
		super();
		this.ejemploInd = ejemploInd;
		this.ejemploNodo = ejemploNodo;
		this.prof = prof;
	}

	@Override
	public List<Individuo<T>> init(int n) {
		List<Individuo<T>> poblacion = new ArrayList<>();
		for (int i = 0; i < n; ++i)
			poblacion.add(ejemploInd.createInstance(generar(ejemploNodo.createInstance(null, false), prof-1)));
		return poblacion;
	}
	
	public List<Gen<T>> generar(GenNodo<T> padre, int profRestante) {
		if (profRestante == 0)
			return new ArrayList<>();
		
		List<GenNodo<T>> hijos = new ArrayList<>();
		for (int i = 0; i < padre.getAridad(); ++i) {
			GenNodo<T> hijo = profRestante > 1 ? padre.createInstance(padre, false) 
											   : padre.createInstance(padre, true);
			hijos.add(hijo);
			generar(hijo, profRestante-1);
		}
		padre.setHijos(hijos);
		return padre.getPreorder();
	}

	@Override
	public <O> List<Option<O>> getExtraOpts() {
		List<Option<O>> extras = new ArrayList<>();
		extras.add(new IntegerOption<O>("Profundidad", "Profundidad", "prof", 2, 5));
		return extras;
	}

	@Override
	public void setInstances(Individuo<T> ind, Gen<T> gen) {
		ejemploInd = ind;
		ejemploNodo = (GenNodo<T>) gen;
	}
	
	@Override
	public Inicializacion<T> clone() {
		return new IniCompleta<>(ejemploInd, ejemploNodo, prof);
	}
	
	@Override
	public String toString() {
		return "Completa";
	}

	public Integer getProf() {
		return prof;
	}

	public void setProf(Integer prof) {
		this.prof = prof;
	}
}
