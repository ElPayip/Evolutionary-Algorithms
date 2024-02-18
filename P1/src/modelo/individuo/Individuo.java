package modelo.individuo;

import java.util.ArrayList;
import java.util.List;

import modelo.cruce.Cruce;
import modelo.fitness.Fitness;
import modelo.genes.Gen;
import modelo.mutacion.Mutacion;
import utils.Pair;

public abstract class Individuo<T,C> {
	
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
	
	public double getFitness() {
		return fitness;
	}
	
	public double eval(Fitness<T> func) {
		fitness = func.eval(this);
		return fitness;
	}
	
	public abstract Pair<Individuo<T,C>,Individuo<T,C>> cruzar(Individuo<T,C> otro, Cruce<C> cruce);
	
	public abstract void muta(Mutacion<C> mutacion);
	
	public abstract List<T> getValores();
	
	public abstract T getRandomValue(T min, T max, T precision);
}
