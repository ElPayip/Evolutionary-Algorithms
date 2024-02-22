package modelo.seleccion;

import java.util.List;

import modelo.Configurable;
import modelo.individuo.Individuo;

public interface Seleccion extends Configurable, Cloneable {
	
	public <T> List<Individuo<T>> seleccionar(List<Individuo<T>> individuos);
}
