package modelo.inicializaciones;

import java.util.ArrayList;
import java.util.List;

import modelo.genes.Gen;
import modelo.genes.GenNodo;
import modelo.individuo.Individuo;

public class IniRampedAndHalf<T> implements Inicializacion<T> {
	
	private Individuo<T> ejemploInd;
	private GenNodo<T> ejemploNodo;
	private int prof;
	
	public IniRampedAndHalf(Individuo<T> ejemploInd, GenNodo<T> ejemploNodo, int profundidad) {
		this.ejemploNodo = ejemploNodo.createInstance(null, false);
		prof = profundidad;
		this.ejemploInd = ejemploInd.clone();
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
		return null; //TODO
	}
}
