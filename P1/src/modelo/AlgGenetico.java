package modelo;

import java.util.List;

import modelo.cruce.Cruce;
import modelo.seleccion.Seleccion;

public abstract class AlgGenetico<T> {

	private List<Individuo<T>> poblacion;
	private Cruce cruce;
	private Seleccion seleccion;
	
	public AlgGenetico() {
		setParametros();
	}
	
	public void setParametros() {
		
	}
	
	public void reset() {
		
	}
	
	private void ejecutarGeneracion() {
		
	}
	
	private void seleccionar() {
		
	}
	
	private void cruzar() {
		
	}
	
	private void mutar() {
		
	}
	
	private void evaluar() {
		
	}
}
