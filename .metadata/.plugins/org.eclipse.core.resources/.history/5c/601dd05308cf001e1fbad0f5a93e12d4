package modelo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import modelo.cruce.Cruce;
import modelo.fitness.Fitness;
import modelo.individuo.Individuo;
import modelo.mutacion.Mutacion;
import modelo.seleccion.Seleccion;
import utils.Pair;

public abstract class AlgGenetico<T,C> {

	protected List<Individuo<T,C>> poblacion;
	protected Individuo<T,C> mejor;
	protected Cruce<C> cruce;
	protected Seleccion seleccion;
	protected Fitness<T> fitness;
	protected Mutacion<C> mutacion;
	
	protected int nGeneraciones;
	protected int tamPoblacion;
	protected double probCruce;
	protected double probMutacion;
	
	protected Random rand;
	
	public AlgGenetico(Cruce<C> cruce, Seleccion seleccion, Fitness<T> fitness, Mutacion<C> mutacion,
			int nGeneraciones, int tamPoblacion, double probCruce, double probMutacion) {
		this.cruce = cruce;
		this.seleccion = seleccion;
		this.fitness = fitness;
		this.mutacion = mutacion;
		
		this.nGeneraciones = nGeneraciones;
		this.tamPoblacion = tamPoblacion;
		this.probCruce = probCruce;
		this.probMutacion = probMutacion;
		
		rand = new Random();
	}
	
	public Individuo<T,C> ejecutar() {
		initPoblacion();
		evaluar();
		for (int i = 0; i < nGeneraciones; ++i)
			seleccionar();
			cruzar();
			mutar();
			evaluar();
		return mejor;
	}
	
	protected void seleccionar() {
		seleccion.seleccionar(poblacion);
	}
	
	protected void cruzar() {
		int pareja = -1;
		for (int i = 0; i < poblacion.size(); ++i)
			if (rand.nextDouble() < probCruce) {
				
				if (pareja < 0)
					pareja = i;
				else {	// Reemplazo de progenitores
					Pair<Individuo<T,C>,Individuo<T,C>> hijos = poblacion.get(pareja).cruzar(poblacion.get(i), cruce);
					poblacion.set(pareja, hijos.getFirst());
					poblacion.set(i, hijos.getSecond());
					pareja = -1;
				}
			}
	}
	
	protected void evaluar() {
		for (Individuo<T,C> i : poblacion)
			fitness.eval(i);
		
		poblacion.sort(new Comparator<Individuo<T,C>>() {
			@Override
			public int compare(Individuo<T,C> o1, Individuo<T,C> o2) {
				return Double.compare(o2.getFitness(), o1.getFitness());
			}
		});
		if (mejor == null || mejor.getFitness() < poblacion.get(0).getFitness())
			mejor = poblacion.get(0);
	}
	
	protected void mutar() {
		for (Individuo<T,C> ind : poblacion)
			if (rand.nextDouble() < probMutacion)
				ind.muta(mutacion);
	}
	
	protected void initPoblacion() {
		poblacion = new ArrayList<>();
		for (int i = 0; i < tamPoblacion; ++i)
			poblacion.add(generarIndividuo());
	}
	
	protected abstract Individuo<T,C> generarIndividuo();
}
