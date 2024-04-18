package modelo;

import java.util.ArrayList;
import java.util.List;

import modelo.bloating.BloatingTarpeian;
import modelo.bloating.ControlBloating;
import modelo.cruce.CruceSubArbol;
import modelo.factorias.FactoriaBloating;
import modelo.fitness.Accion;
import modelo.fitness.FitJardin;
import modelo.genes.Gen;
import modelo.genes.GenNodoJardin;
import modelo.individuo.Individuo;
import modelo.individuo.IndividuoJardin;
import modelo.inicializaciones.IniCreciente;
import modelo.mutacion.MutacionTerminal;
import modelo.seleccion.SelRanking;
import vista.ConfigPanel.Option;
import vista.ConfigPanel.StrategyOption;

public class Cortacesped extends AlgGenetico<Accion> {

	public enum Casilla {
		CESPED, CORTADO
	}
	
	private Integer ancho = 8, alto = 8;
	private List<List<Casilla>> jardin;
	private ControlBloating controlBloating;
	
	public Cortacesped() {
		inicializacion = new IniCreciente<>();
		seleccion = new SelRanking();
		mutacion = new MutacionTerminal<>();
		cruce = new CruceSubArbol<>();
		controlBloating = new BloatingTarpeian();
	}

	public Cortacesped(AlgGenetico<Accion> otro) {
		super(otro);
	}
	
	private void initJardin() {
		GenNodoJardin.setDominio(alto, ancho);
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
		fitness = new FitJardin(getJardin());
		
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
	
	public List<List<Casilla>> getJardin() {
		List<List<Casilla>> copia = new ArrayList<>();
		for (List<Casilla> fila : jardin)
			copia.add(new ArrayList<>(fila));
		return copia;
	}

	public ControlBloating getControlBloating() {
		return controlBloating;
	}

	public void setControlBloating(ControlBloating controlBloating) {
		this.controlBloating = controlBloating;
	}
	
	@Override
	public String toString() {
		return "Cortacésped";
	}

	@Override
	public Individuo<Accion> getIndividuoDefault() {
		return new IndividuoJardin();
	}

	@Override
	public Gen<Accion> getGenDefault() {
		return new GenNodoJardin(null);
	}
}
