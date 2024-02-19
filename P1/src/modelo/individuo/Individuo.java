package modelo.individuo;

import java.util.ArrayList;
import java.util.List;

import modelo.cruce.Cruce;
import modelo.fitness.Fitness;
import modelo.genes.Gen;
import modelo.mutacion.Mutacion;
import utils.Pair;

public abstract class Individuo<T> {
	
	protected List<Gen<T>> cromosoma;
	protected double fitness=-1;

	public Individuo(List<Gen<T>> cromosoma) {
		this.cromosoma = cromosoma;
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
	
	public double eval(Fitness<T> func) {
		fitness = func.eval(this);
		return fitness;
	}
	
	public Pair<Individuo<T>,Individuo<T>> cruzar(Individuo<T> otro, Cruce<T> cruce) {
		Pair<List<? extends Gen<T>>,List<? extends Gen<T>>> croms = cruce.cruzar(cromosoma, otro.cromosoma);
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
	
	protected abstract Individuo<T> createInstance(List<? extends Gen<T>> crom);
}
