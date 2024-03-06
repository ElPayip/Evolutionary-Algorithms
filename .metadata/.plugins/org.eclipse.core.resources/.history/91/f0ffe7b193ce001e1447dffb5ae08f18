package modelo;

import java.util.List;

import modelo.cruce.Cruce;
import modelo.fitness.Fitness;
import modelo.seleccion.Seleccion;

public abstract class AlgGenetico<T> {

	protected List<Individuo<T>> poblacion;
	protected Individuo<T> mejor;
	protected Cruce<T> cruce;
	protected Seleccion<T> seleccion;
	protected Fitness<T> fitness;
	
	private int nGeneraciones;
	
	public AlgGenetico() {
		reset();
	}
	
	public void reset() {
		
	}
	
	public Individuo<T> ejecutar() {
		initPoblacion();
		evaluar();
		for (int i = 0; i < nGeneraciones; ++i)
			ejecutarGeneracion();
		return mejor;
	}
	
	private void ejecutarGeneracion() {
		seleccionar();
		cruzar();
		mutar();
		evaluar();
	}
	
	protected abstract void initPoblacion();
	
	protected void seleccionar() {
		seleccion.seleccionar(poblacion);
	}
	
	protected void cruzar() {
		cruce.cruzar(poblacion);
	}
	
	protected abstract void mutar();
	
	protected void evaluar() {
		for (Individuo<T> i : poblacion) {
			fitness.eval(i);
		}
	}
}
