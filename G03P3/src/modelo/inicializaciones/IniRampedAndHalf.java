package modelo.inicializaciones;

import java.util.ArrayList;
import java.util.List;

import modelo.genes.Gen;
import modelo.genes.GenNodo;
import modelo.individuo.Individuo;
import vista.ConfigPanel.IntegerOption;
import vista.ConfigPanel.Option;

public class IniRampedAndHalf<T> implements Inicializacion<T> {
	
	private Individuo<T> ejemploInd;
	private GenNodo<T> ejemploNodo;
	private int prof;

	public IniRampedAndHalf() {
	}

	public IniRampedAndHalf(Individuo<T> ejemploInd, GenNodo<T> ejemploNodo, int prof) {
		super();
		this.ejemploInd = ejemploInd;
		this.ejemploNodo = ejemploNodo;
		this.prof = prof;
	}

	@Override
	public List<Individuo<T>> init(int n) {
		List<Individuo<T>> poblacion = new ArrayList<>();
		for (int i = 2; i <= prof; ++i)
			for (int j = 0; j < n / (prof-1); ++j)
				poblacion.add(ejemploInd.createInstance(j% 2 == 0 ? generarCompleta(ejemploNodo.createInstance(null, false), i-1)
																  : generarCreciente(ejemploNodo.createInstance(null, false), i-1)));
		return poblacion;
	}

	public List<Gen<T>> generarCreciente(GenNodo<T> padre, int profRestante) {
		if (profRestante == 0)
			return new ArrayList<>();
		
		List<Gen<T>> inorden = new ArrayList<>();
		List<GenNodo<T>> hijos = new ArrayList<>();
		inorden.add(padre);
		for (int i = 0; i < padre.getAridad(); ++i) {
			GenNodo<T> hijo = profRestante > 1 ? padre.createInstance(padre) 
											   : padre.createInstance(padre, true);
			hijos.add(hijo);
			inorden.addAll(generarCreciente(hijo, profRestante-1));
		}
		padre.setHijos(hijos);
		return inorden;
	}
	
	public List<Gen<T>> generarCompleta(GenNodo<T> padre, int profRestante) {
		if (profRestante == 0)
			return new ArrayList<>();
		
		List<Gen<T>> inorden = new ArrayList<>();
		List<GenNodo<T>> hijos = new ArrayList<>();
		inorden.add(padre);
		for (int i = 0; i < padre.getAridad(); ++i) {
			GenNodo<T> hijo = profRestante > 1 ? padre.createInstance(padre, false) 
											   : padre.createInstance(padre, true);
			hijos.add(hijo);
			inorden.addAll(generarCompleta(hijo, profRestante-1));
		}
		padre.setHijos(hijos);
		return inorden;
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
		return new IniRampedAndHalf<>(ejemploInd, ejemploNodo, prof);
	}
	
	@Override
	public String toString() {
		return "Ramped and Half";
	}
}
