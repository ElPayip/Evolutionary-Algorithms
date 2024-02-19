package modelo.cruce;

import java.util.List;

import modelo.genes.Gen;
import utils.Pair;

public interface Cruce<T> {
	
	public Pair<List<? extends Gen<T>>,List<? extends Gen<T>>> cruzar(
			List<? extends Gen<T>> crom1, List<? extends Gen<T>> crom2);
}
