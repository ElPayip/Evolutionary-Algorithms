package modelo.cruce;

import java.util.List;

import modelo.genes.Gen;
import modelo.Configurable;
import utils.Pair;

public interface Cruce<T> extends Configurable, Cloneable {
	
	public Pair<List<Gen<T>>,List<Gen<T>>> cruzar(List<Gen<T>> crom1, List<Gen<T>> crom2);
	
	public Cruce<T> clone();
}
