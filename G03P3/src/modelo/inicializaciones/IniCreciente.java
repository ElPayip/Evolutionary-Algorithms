package modelo.inicializaciones;

import java.util.ArrayList;
import java.util.List;

import modelo.genes.Gen;
import modelo.genes.GenNodo;
import modelo.individuo.Individuo;

public class IniCreciente<T> implements Inicializacion<T> {
	
	private Individuo<T> ejemploInd;
	private GenNodo<T> ejemploNodo;
	private int prof;
	
	public IniCreciente(Individuo<T> ejemploInd, GenNodo<T> ejemploNodo, int profundidad) {
		this.ejemploNodo = ejemploNodo.createInstance(null, false);
		prof = profundidad;
		this.ejemploInd = ejemploInd.clone();
	}

	@Override
	public List<Individuo<T>> init(int n) {
		List<Individuo<T>> poblacion = new ArrayList<>();
		for (int i = 0; i < n; ++i)
			poblacion.add(ejemploInd.createInstance(generar(ejemploNodo.createInstance(null, false), prof)));
		return poblacion;
	}

	public List<Gen<T>> generar(GenNodo<T> padre, int profRestante) {
		if (profRestante == 0)
			return new ArrayList<>();
		
		List<Gen<T>> inorden = new ArrayList<>();
		List<GenNodo<T>> hijos = new ArrayList<>();
		inorden.add(padre);
		for (int i = 0; i < padre.getAridad(); ++i) {
			GenNodo<T> hijo = profRestante > 1 ? padre.createInstance(padre) 
											   : padre.createInstance(padre, true);
			hijos.add(hijo);
			inorden.addAll(generar(hijo, profRestante-1));
		}
		padre.setHijos(hijos);
		return inorden;
	}
}
