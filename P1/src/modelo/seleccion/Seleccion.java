package modelo.seleccion;

import java.util.List;

import modelo.individuo.Individuo;

public interface Seleccion {
	
	public <T,C> List<Individuo<T,C>> seleccionar(List<Individuo<T,C>> individuos);
}
