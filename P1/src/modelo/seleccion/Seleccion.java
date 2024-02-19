package modelo.seleccion;

import java.util.List;

import modelo.individuo.Individuo;

public interface Seleccion {
	
	public <T,C> List<Individuo<T>> seleccionar(List<Individuo<T>> individuos);
}
