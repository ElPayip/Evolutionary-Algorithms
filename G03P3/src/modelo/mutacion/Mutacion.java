package modelo.mutacion;

import java.util.List;

import modelo.Configurable;
import modelo.fitness.Fitness;
import modelo.genes.Gen;
import modelo.individuo.Individuo;

public interface Mutacion<T>  extends Configurable, Cloneable{

	public void mutar(List<Gen<T>> crom);
	
	public Mutacion<T> clone();
	
	public default void update(Fitness<T> fit, Individuo<T> ind) {}
}
