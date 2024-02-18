package modelo.fitness;

import modelo.Individuo;

public interface Fitness<T> {
	
	public double eval(Individuo<T> ind);
}
