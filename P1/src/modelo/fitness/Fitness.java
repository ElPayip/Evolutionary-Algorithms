package modelo.fitness;

import modelo.individuo.Individuo;

public interface Fitness<T> {
	
	public double eval(Individuo<T,?> ind);
}
