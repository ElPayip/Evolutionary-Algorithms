package modelo;

import java.util.List;

public abstract class Individuo<T> {
	
	private boolean[] cromosoma;
	private int[] tamGenes;
	
	public Individuo() {
		
	}
	
	public abstract double getFitness();
	
	public abstract List<T> getValores();
	
	public abstract void muta();
}
