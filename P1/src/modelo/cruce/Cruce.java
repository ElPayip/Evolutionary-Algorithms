package modelo.cruce;

import java.util.List;

import utils.Pair;

public interface Cruce<T> {
	
    public Pair<List<T>,List<T>> cruzar (List<T> crom1, List<T> crom2);
}
