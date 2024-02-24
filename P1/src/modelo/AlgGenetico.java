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

public abstract class AlgGenetico<T> implements Cloneable, Configurable {

	protected List<Individuo<T>> poblacion;
	protected Individuo<T> mejor;
	protected Cruce<T> cruce;
	protected Seleccion seleccion;
	protected Fitness<T> fitness;
	protected Mutacion<T> mutacion;
	
	protected Integer nGeneraciones = 100;
	protected Integer tamPoblacion = 100;
	protected Double probCruce = 0.6;
	protected Double probMutacion = 0.1;
	
	protected Random rand;
	
	public AlgGenetico(Fitness<T> fit) {
		rand = new Random();
		fitness = fit;
	}
	
	public AlgGenetico(Cruce<T> cruce, Seleccion seleccion, Fitness<T> fitness, Mutacion<T> mutacion,
			int nGeneraciones, int tamPoblacion, double probCruce, double probMutacion) {
		this(fitness);
		this.cruce = cruce;
		this.seleccion = seleccion;
		this.mutacion = mutacion;
		
		this.nGeneraciones = nGeneraciones;
		this.tamPoblacion = tamPoblacion;
		this.probCruce = probCruce;
		this.probMutacion = probMutacion;
	}
	
	public AlgGenetico(AlgGenetico<T> otro) {
		this(otro.cruce, otro.seleccion, otro.fitness, otro.mutacion, 
				otro.nGeneraciones, otro.tamPoblacion, otro.probCruce, otro.probMutacion);
	}
	
	public Individuo<T> ejecutar() {
		initPoblacion();
		evaluar();
		for (int i = 0; i < nGeneraciones; ++i) {
			poblacion = seleccionar();
			cruzar();
			mutar();
			evaluar();
			
			System.out.println("Generacion "+(i+1));
			mostrarFitness();
		}
		mejor.eval(fitness);
		return mejor;
	}
	
	protected List<Individuo<T>> seleccionar() {
		return seleccion.seleccionar(poblacion);
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
		double maxFit = -Double.MAX_VALUE, minFit = Double.MAX_VALUE;
		for (Individuo<T> i : poblacion) {
			i.eval(fitness);
			if (i.getFitness() > maxFit) 
				maxFit = i.getFitness();
			if (i.getFitness() < minFit)
				minFit = i.getFitness();
		}
		
		maxFit *= 1.05;
		minFit *= 0.95;
		for (Individuo<T> i : poblacion)
			if (maximizacion())
				i.setFitness(i.getFitness() - minFit);
			else
				i.setFitness(maxFit - i.getFitness());
		
		poblacion.sort(new Comparator<Individuo<T>>() {
			@Override
			public int compare(Individuo<T> o1, Individuo<T> o2) {
				return Double.compare(o2.getFitness(), o1.getFitness());
			}
		});
		if (mejor == null || (maximizacion() && fitness.eval(mejor) < fitness.eval(poblacion.get(0)))
						  || (!maximizacion() && fitness.eval(mejor) > fitness.eval(poblacion.get(0))))
			mejor = poblacion.get(0).clone();
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
	
	public Double mediaFitness() {
		double acum = 0;
		for (Individuo<T> ind : poblacion)
			acum += fitness.eval(ind);
		return acum / poblacion.size();
	}
	
	public Double maxFitnessActual() {
		return fitness.eval(poblacion.get(0));
	}
	
	public Double maxFitnessGlobal() {
		return fitness.eval(mejor);
	}
	
	private void mostrarFitness() {
		System.out.println("Media: "+mediaFitness());
		System.out.println("Max actual: "+maxFitnessActual());
		System.out.println("Max global: "+maxFitnessGlobal());
	}
	
	protected abstract Individuo<T> generarIndividuo();
	
	public abstract CategoriaGen getCategoria();
	
	public abstract AlgGenetico<T> clone();
	
	public abstract Boolean maximizacion();
	
	/* ----------------------------------------------------
	 
	----------		 Getters & Setters			----------
			
	---------------------------------------------------- */
	
	public Individuo<T> getMejor() {
		return mejor;
	}

	public Cruce<T> getCruce() {
		return cruce;
	}

	public void setCruce(Cruce<T> cruce) {
		this.cruce = cruce;
	}

	public Seleccion getSeleccion() {
		return seleccion;
	}

	public void setSeleccion(Seleccion seleccion) {
		this.seleccion = seleccion;
	}

	public Fitness<T> getFitness() {
		return fitness;
	}

	public void setFitness(Fitness<T> fitness) {
		this.fitness = fitness;
	}

	public Mutacion<T> getMutacion() {
		return mutacion;
	}

	public void setMutacion(Mutacion<T> mutacion) {
		this.mutacion = mutacion;
	}

	public Integer getnGeneraciones() {
		return nGeneraciones;
	}

	public void setnGeneraciones(Integer nGeneraciones) {
		this.nGeneraciones = nGeneraciones;
	}

	public Integer getTamPoblacion() {
		return tamPoblacion;
	}

	public void setTamPoblacion(Integer tamPoblacion) {
		this.tamPoblacion = tamPoblacion;
	}

	public Double getProbCruce() {
		return probCruce;
	}

	public void setProbCruce(Double probCruce) {
		this.probCruce = probCruce;
	}

	public Double getProbMutacion() {
		return probMutacion;
	}

	public void setProbMutacion(Double probMutacion) {
		this.probMutacion = probMutacion;
	}
}
