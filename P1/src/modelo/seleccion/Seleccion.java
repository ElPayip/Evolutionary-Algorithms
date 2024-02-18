package modelo.seleccion;

import java.util.List;

import modelo.Individuo;

public interface Seleccion {
	
	public <T> List<Individuo<T>> seleccionar(List<Individuo<T>> individuos);
}
