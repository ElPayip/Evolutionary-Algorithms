package modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import modelo.cruce.Cruce;
import modelo.fitness.Fitness;
import modelo.genes.Gen;
import modelo.individuo.Individuo;
import modelo.inicializaciones.Inicializacion;
import modelo.mutacion.Mutacion;
import modelo.seleccion.Seleccion;
import utils.Pair;

public abstract class AlgGenetico<T> implements Cloneable, Configurable {

	protected List<Individuo<T>> poblacion;
	protected List<Individuo<T>> elite;
	protected Individuo<T> mejor;
	protected Cruce<T> cruce;
	protected Seleccion seleccion;
	protected Fitness<T> fitness;
	protected Mutacion<T> mutacion;
	protected Inicializacion<T> inicializacion;
	
	protected Integer nGeneraciones = 100;
	protected Integer tamPoblacion = 100;
	protected Double probCruce = 0.6;
	protected Double probMutacion = 0.1;
	protected Double elitismo = 0.02;
	protected Boolean escalado = true;

	protected Random rand;
	private List<Double> mediaFit = new ArrayList<>();
	private List<Double> mejorActFit = new ArrayList<>();
	private List<Double> mejorGlobFit = new ArrayList<>();
	
	public AlgGenetico() {
		rand = new Random();
	}
	
	public AlgGenetico(Fitness<T> fit) {
		rand = new Random();
		fitness = fit;
	}
	
	public AlgGenetico(AlgGenetico<T> otro) {
		this(otro.fitness);
		mutacion = otro.mutacion;
		nGeneraciones = otro.nGeneraciones; 
		tamPoblacion = otro.tamPoblacion; 
		probCruce = otro.probCruce; 
		probMutacion = otro.probMutacion;
		cruce = otro.cruce; 
		seleccion = otro.seleccion;
	}
	
	public Individuo<T> ejecutar() {
		initPoblacion();
		evaluar();
		for (int i = 0; i < nGeneraciones; ++i) {
			poblacion = seleccionar();
			cruzar();
			mutar();
			evaluar();
			
			guardar();
		}
		mejor.eval(fitness);
		return mejor;
	}
	
	protected List<Individuo<T>> seleccionar() {
		if (elitismo > 0) {
			int corte = (int) (elitismo * poblacion.size());
			elite = new ArrayList<>();
			for (int i = 0; i < corte; ++i)
				elite.add(poblacion.get(i).clone());
		}
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
	
	protected void mutar() {
		mutacion.update(fitness, mejor.clone());
		for (Individuo<T> ind : poblacion)
			if (rand.nextDouble() < probMutacion)
				ind.muta(mutacion);
	}
	
	protected void evaluar() {
		for (Individuo<T> i : poblacion)
			i.eval(fitness);
		if (elite != null)
			for (Individuo<T> i : elite)
				i.eval(fitness);

		filtros();
		desplazamiento();
		ordenar();
		elitismo();
		if (escalado) escalado();
	}
	
	protected void filtros() {}
	
	private void desplazamiento() {
		double maxFit = -Double.MAX_VALUE, minFit = Double.MAX_VALUE;
		for (Individuo<T> i : poblacion) {
			if (i.getFitness() > maxFit) maxFit = i.getFitness();
			if (i.getFitness() < minFit) minFit = i.getFitness();
		}
		if (elite != null)
			for (Individuo<T> i : elite) {
				if (i.getFitness() > maxFit) maxFit = i.getFitness();
				if (i.getFitness() < minFit) minFit = i.getFitness();
			}
		
		maxFit += 0.01;
		minFit -= 0.01;
		for (Individuo<T> i : poblacion)
			i.setFitness(maximizacion() ? i.getFitness() - minFit : 
										  maxFit - i.getFitness());
		if (elite != null)
			for (Individuo<T> i : elite)
				i.setFitness(maximizacion() ? i.getFitness() - minFit : 
					  						  maxFit - i.getFitness());
	}
	
	private void escalado() {
		double media = 0, minFit = Double.MAX_VALUE, a, b;
		int tamPob = poblacion.size();
		for (Individuo<T> ind : poblacion) {
			media += ind.getFitness();
			if (ind.getFitness() < minFit) minFit = ind.getFitness();
		}
		media /= tamPob;
		a = media / (media - minFit);
		b = (1 - a) * media;
		
		for (Individuo<T> ind : poblacion) 
			ind.setFitness(a * ind.getFitness() + b);
	}
	
	protected void initPoblacion() {
		inicializacion.setInstances(getIndividuoDefault(), getGenDefault());
		poblacion = inicializacion.init(tamPoblacion);
	}
	
	private void ordenar() {
		poblacion.sort(new Comparator<Individuo<T>>() {
			@Override
			public int compare(Individuo<T> o1, Individuo<T> o2) {
				return Double.compare(o2.getFitness(), o1.getFitness()); // De mayor a menor fitness
			}
		});
		if (mejor == null || (maximizacion() && fitness.eval(mejor) < fitness.eval(poblacion.get(0)))
				  		  || (!maximizacion() && fitness.eval(mejor) > fitness.eval(poblacion.get(0))))
			mejor = poblacion.get(0).clone();
	}
	
	private void elitismo() {
		if (elite != null && elite.size() > 0) {
			int aux = poblacion.size() - elite.size();
			for (int i = 0; i < elite.size(); ++i)
				poblacion.set(aux + i, elite.get(i));
			
			ordenar();
		}
	}
	
	public Double mediaFitness() {
		double acum = 0;
		for (Individuo<T> ind : poblacion)
			acum += fitness.eval(ind.clone());
		return acum / poblacion.size();
	}
	
	public Double maxFitnessActual() {
		return fitness.eval(poblacion.get(0).clone());
	}
	
	public Double maxFitnessGlobal() {
		return fitness.eval(mejor.clone());
	}
	
	private void guardar() {
		mediaFit.add(mediaFitness());
		mejorActFit.add(maxFitnessActual());
		mejorGlobFit.add(maxFitnessGlobal());
	}
	
	public List<Double[]> getMetricas(){
		List<Double> presSelectiva = new ArrayList<>();
		double maxFit = Collections.max(mediaFit);
		presSelectiva.add(0.0);
		for (int i = 1; i < mediaFit.size(); ++i) {
			presSelectiva.add(maximizacion() ? mejorActFit.get(i) / mediaFit.get(i)
								: (maxFit - mejorActFit.get(i)) / (maxFit - mediaFit.get(i)));
			if (Double.isInfinite(presSelectiva.get(i))) 
				presSelectiva.set(i, presSelectiva.get(i-1));
		}
		Double[] aux = new Double[0];
		List<Double[]> metricas = new ArrayList<>();
		metricas.add(mediaFit.toArray(aux));
		metricas.add(mejorActFit.toArray(aux));
		metricas.add(mejorGlobFit.toArray(aux));
		metricas.add(presSelectiva.toArray(aux));
		return metricas;
	}
	
	public abstract CategoriaCrom getCategoria();
	
	public abstract AlgGenetico<T> clone();
	
	public abstract Boolean maximizacion();
	
	public abstract Individuo<T> getIndividuoDefault();

	public abstract Gen<T> getGenDefault();
	
	/* ----------------------------------------------------
	 
	----------		 Getters & Setters			----------
			
	---------------------------------------------------- */
	
	public Individuo<T> getMejor() {
		return mejor.clone();
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

	public Inicializacion<T> getInicializacion() {
		return inicializacion;
	}

	public void setInicializacion(Inicializacion<T> inicializacion) {
		this.inicializacion = inicializacion;
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
	
	public Double getElitismo() {
		return elitismo;
	}

	public void setElitismo(Double elitismo) {
		this.elitismo = elitismo;
	}

	public Boolean getEscalado() {
		return escalado;
	}

	public void setEscalado(Boolean escalado) {
		this.escalado = escalado;
	}
}
