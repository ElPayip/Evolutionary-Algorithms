package modelo;

import java.util.ArrayList;
import java.util.List;

import modelo.cruce.Cruce;
import modelo.fitness.Fitness;
import modelo.genes.Gen;
import utils.Pair;

public abstract class Individuo<T> {
	
	protected List<Gen<T>> cromosoma;
	protected double fitness=-1;

	public Individuo(List<Gen<T>> cromosoma) {
		this.cromosoma = cromosoma;
	}
	
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
	
	public double getFitness() {
		return fitness;
	}
	
	public double eval(Fitness<T> func) {
		fitness = func.eval(this);
		return fitness;
	}
	
	public abstract Pair<Individuo<T>,Individuo<T>> cruzar(Individuo<T> otro, Cruce<? extends Gen<T>> cruce);
}
