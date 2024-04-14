package modelo.inicializaciones;

import java.util.List;

import modelo.Configurable;
import modelo.genes.Gen;
import modelo.individuo.Individuo;

public interface Inicializacion<T> extends Configurable, Cloneable {

	public List<Individuo<T>> init(int n);
	
	public void setInstances(Individuo<T> ind, Gen<T> gen);
	
	public Inicializacion<T> clone();
}
