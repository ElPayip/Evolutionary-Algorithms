package modelo.cruce;

import java.util.List;

import modelo.genes.Gen;
import modelo.Configurable;
import utils.Pair;

public interface Cruce<T> extends Configurable, Cloneable {
	
	public Pair<List<? extends Gen<T>>,List<? extends Gen<T>>> cruzar(
			List<? extends Gen<T>> crom1, List<? extends Gen<T>> crom2);
	
	public Cruce<T> clone();
}
