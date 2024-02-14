package modelo.seleccion;

import java.util.List;

import modelo.Individuo;

public interface Seleccion<T> {
	public void seleccionar(List<Individuo<T>> individuos);
}
