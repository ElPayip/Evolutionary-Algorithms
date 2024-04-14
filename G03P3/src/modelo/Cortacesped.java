package modelo;

import java.util.ArrayList;
import java.util.List;

import modelo.bloating.ControlBloating;
import modelo.factorias.FactoriaBloating;
import modelo.fitness.FitJardin;
import modelo.genes.Accion;
import modelo.genes.GenNodoJardin;
import modelo.individuo.Individuo;
import modelo.individuo.IndividuoJardin;
import vista.ConfigPanel.Option;
import vista.ConfigPanel.StrategyOption;

public class Cortacesped extends AlgGenetico<Accion> {

	public enum Casilla {
		CESPED, CORTADO
	}
	
	private Integer ancho, alto;
	private List<List<Casilla>> jardin;
	private ControlBloating controlBloating;
	
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
		fitness = new FitJardin(jardin);
		
		return super.ejecutar();
	}
	
	@Override
	protected void filtros() {
		controlBloating.controlar(poblacion);
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
		List<Option<T>> extras = new ArrayList<>();
		extras.add(new StrategyOption<T>(
				"metodo de control del bloating", 
				"metodo de control del bloating", 
				"controlBloating",
				new FactoriaBloating().getControlesBloating()));
		return extras;
	}

	public Integer getAncho() {
		return ancho;
	}

	public Integer getAlto() {
		return alto;
	}

	@Override
	public Class<?> getIndividuoClass() {
		return IndividuoJardin.class;
	}

	@Override
	public Class<?> getGenClass() {
		return GenNodoJardin.class;
	}
}
