package modelo;

import java.util.ArrayList;
import java.util.List;

import modelo.genes.Accion;
import modelo.individuo.Individuo;
import modelo.inicializaciones.Inicializacion;
import vista.ConfigPanel.Option;

public class Cortacesped extends AlgGenetico<Accion> {

	public enum Casilla {
		CESPED, CORTADO
	}
	
	private Integer profMax, ancho, alto;
	private Inicializacion<Accion> init;
	private List<List<Casilla>> jardin;
	
	public Cortacesped() {
		super();
	}

	public Cortacesped(AlgGenetico<Accion> otro) {
		super(otro);
	}
	
	private void initJardin() {
		jardin = new ArrayList<>();
		for (int i = 0; i < alto; ++i) {
			jardin.add(new ArrayList<>());
			for (int j = 0; j < ancho; ++j) {
				jardin.get(i).add(Casilla.CESPED);
			}
		}
	}
	
	@Override
	public Individuo<Accion> ejecutar() {
		initJardin();
		
		return super.ejecutar();
	}

	@Override
	protected Individuo<Accion> generarIndividuo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CategoriaCrom getCategoria() {
		return CategoriaCrom.ARBOL;
	}

	@Override
	public AlgGenetico<Accion> clone() {
		return new Cortacesped(this);
	}

	@Override
	public Boolean maximizacion() {
		return true;
	}

	@Override
	public <T> List<Option<T>> getExtraOpts() {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer getAncho() {
		return ancho;
	}

	public Integer getAlto() {
		return alto;
	}
}
