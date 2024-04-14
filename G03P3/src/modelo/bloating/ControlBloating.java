package modelo.bloating;

import java.util.List;

import modelo.Configurable;
import modelo.individuo.Individuo;

public interface ControlBloating extends Configurable, Cloneable {

	public <T> void controlar(List<Individuo<T>> poblacion);
	
	public ControlBloating clone();
}
