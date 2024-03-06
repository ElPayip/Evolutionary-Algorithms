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

public abstract class AlgGenetico<T> {

	protected List<Individuo<T>> poblacion;
	protected Individuo<T> mejor;
	protected Cruce<T> cruce;
	protected Seleccion seleccion;
	protected Fitness<T> fitness;
	protected Mutacion mutacion;
	
	protected int nGeneraciones;
	protected int tamPoblacion;
	protected double probCruce;
	protected double probMutacion;
	
	protected Random rand;
	
	public AlgGenetico(Cruce<T> cruce, Seleccion seleccion, Fitness<T> fitness, Mutacion mutacion,
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
	
	public Individuo<T> ejecutar() {
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
					Pair<Individuo<T>,Individuo<T>> hijos = poblacion.get(pareja).cruzar(poblacion.get(i), cruce);
					poblacion.set(pareja, hijos.getFirst());
					poblacion.set(i, hijos.getSecond());
					pareja = -1;
				}
			}
	}
	
	protected void evaluar() {
		for (Individuo<T> i : poblacion)
			fitness.eval(i);
		
		poblacion.sort(new Comparator<Individuo<T>>() {
			@Override
			public int compare(Individuo<T> o1, Individuo<T> o2) {
				return Double.compare(o2.getFitness(), o1.getFitness());
			}
		});
		if (mejor == null || mejor.getFitness() < poblacion.get(0).getFitness())
			mejor = poblacion.get(0);
	}
	
	protected void mutar() {
		for (Individuo<T> ind : poblacion)
			if (rand.nextDouble() < probMutacion)
				ind.muta(mutacion);
	}
	
	protected void initPoblacion() {
		poblacion = new ArrayList<>();
		for (int i = 0; i < tamPoblacion; ++i)
			poblacion.add(generarIndividuo());
	}
	
	protected abstract Individuo<T> generarIndividuo();
}
