package modelo;

import java.util.ArrayList;
import java.util.List;

import modelo.genes.Gen;

public abstract class Individuo<T> {
	
	protected List<Gen<T>> cromosoma;
	
	public Individuo(T[] mins, T[] maxs, T[] precs) {
		if (mins.length != maxs.length || maxs.length != precs.length){
			throw new IllegalArgumentException("Longitud incorrecta");
		}
		cromosoma = new ArrayList<>();
		for (int i = 0; i < mins.length; ++i)
			cromosoma.add(new Gen<T>(getRandomValue(mins[i], maxs[i], precs[i]), 
									 mins[i], maxs[i], precs[i]));
	}
	
	public abstract List<T> getValores();
	
	public abstract void muta();
	
	public abstract T getRandomValue(T min, T max, T precision);
	
	
}
