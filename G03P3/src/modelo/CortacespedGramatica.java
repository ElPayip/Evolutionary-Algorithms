package modelo;

import java.util.List;

import modelo.bloating.ControlBloating;
import modelo.fitness.FitJardinGramatica;
import modelo.genes.Gen;
import modelo.individuo.Individuo;
import modelo.seleccion.SelRanking;
import vista.ConfigPanel.Option;

public class CortacespedGramatica extends AlgGenetico<Integer> {

	private Cortacesped cortacesped;
	
	public CortacespedGramatica() {
		inicializacion = null;	//TODO
		seleccion = new SelRanking();
		mutacion = null;		//TODO
		cruce = null;			//TODO
	}

	public CortacespedGramatica(AlgGenetico<Integer> otro) {
		super(otro);
		cortacesped = (Cortacesped) ((CortacespedGramatica) otro).cortacesped.clone();
	}
	
	@Override
	public Individuo<Integer> ejecutar() {
		cortacesped.initJardin();
		fitness = new FitJardinGramatica(cortacesped.getJardin(), cortacesped.maxPasos);
		
		return super.ejecutar();
	}

	@Override
	public <T> List<Option<T>> getExtraOpts() {
		return cortacesped.getExtraOpts();
	}

	@Override
	public CategoriaCrom getCategoria() {
		return CategoriaCrom.GENERICA;
	}

	@Override
	public AlgGenetico<Integer> clone() {
		return new CortacespedGramatica(this);
	}

	@Override
	public Boolean maximizacion() {
		return cortacesped.maximizacion();
	}

	@Override
	public Individuo<Integer> getIndividuoDefault() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Gen<Integer> getGenDefault() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String toString() {
		return "Cortacésped con gramática";
	}

	public String getFile() {
		return cortacesped.file;
	}

	public void setFile(String file) {
		cortacesped.file = file;
	}

	public Integer getMaxPasos() {
		return cortacesped.maxPasos;
	}

	public void setMaxPasos(Integer maxPasos) {
		cortacesped.maxPasos = maxPasos;
	}

	public ControlBloating getControlBloating() {
		return cortacesped.controlBloating;
	}

	public void setControlBloating(ControlBloating controlBloating) {
		cortacesped.controlBloating = controlBloating;
	}
}
