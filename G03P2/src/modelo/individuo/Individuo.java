package modelo.individuo;

import java.util.ArrayList;
import java.util.List;

import modelo.cruce.Cruce;
import modelo.fitness.Fitness;
import modelo.genes.Gen;
import modelo.mutacion.Mutacion;
import utils.Pair;

public abstract class Individuo<T> implements Cloneable {
	
	protected List<Gen<T>> cromosoma;
	protected double fitness=-1;

	public Individuo() {
	}

	public Individuo(Individuo<T> otro) {
		this(otro.cromosoma);
		this.fitness = otro.fitness;
	}

	public Individuo(List<Gen<T>> cromosoma) {
		this.cromosoma = new ArrayList<>();
		for (Gen<T> g : cromosoma)
			this.cromosoma.add(g.clone());
	}
	
	public Individuo(T[] mins, T[] maxs, T prec) {
		if (mins.length != maxs.length){
			throw new IllegalArgumentException("Longitud incorrecta");
		}
		cromosoma = new ArrayList<>();
		for (int i = 0; i < mins.length; ++i)
			cromosoma.add(generarGen(mins[i], maxs[i], prec));
	}
	
	public double getFitness() {
		return fitness;
	}

	public void setFitness(double fitness) {
		this.fitness = fitness;
	}
	
	public double eval(Fitness<T> func) {
		fitness = func.eval(this);
		return fitness;
	}
	
	public Pair<Individuo<T>,Individuo<T>> cruzar(Individuo<T> otro, Cruce<T> cruce) {
		
		Pair<List<Gen<T>>,List<Gen<T>>> croms = cruce.cruzar(cromosoma, otro.cromosoma);
		return new Pair<>(createInstance(croms.getFirst()), createInstance(croms.getSecond()));
	}
	
	public void muta(Mutacion<T> mutacion) {
		mutacion.mutar(cromosoma);
	}
	
	public List<T> getValores() {
		List<T> valores = new ArrayList<>();
		for (Gen<T> g : cromosoma)
			valores.add(g.getValor());
		return valores;
	}
	
	protected abstract Gen<T> generarGen(T min, T max, T prec);
	
	public abstract Individuo<T> createInstance(List<Gen<T>> crom);
	
	public abstract Individuo<T> clone();
}
