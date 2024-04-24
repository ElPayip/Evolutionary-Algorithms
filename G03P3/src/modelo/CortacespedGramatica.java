package modelo;

import java.util.Arrays;
import java.util.List;

import modelo.Cortacesped.Casilla;
import modelo.bloating.ControlBloating;
import modelo.cruce.CruceMonopunto;
import modelo.fitness.FitJardinGramatica;
import modelo.genes.Gen;
import modelo.genes.GenEntero;
import modelo.individuo.Individuo;
import modelo.individuo.IndividuoEntero;
import modelo.inicializaciones.IniRandom;
import modelo.mutacion.MutacionUniforme;
import modelo.seleccion.SelRanking;
import vista.ConfigPanel.IntegerOption;
import vista.ConfigPanel.Option;

public class CortacespedGramatica extends AlgGenetico<Integer> {

	private Cortacesped cortacesped;
	private Integer tamCodon = 10, maxWraps = 2;
	
	public CortacespedGramatica() {
		inicializacion = new IniRandom<>();
		seleccion = new SelRanking();
		mutacion = new MutacionUniforme<>();
		cruce = new CruceMonopunto<>();
		cortacesped = new Cortacesped();
	}

	public CortacespedGramatica(AlgGenetico<Integer> otro) {
		super(otro);
		cortacesped = (Cortacesped) ((CortacespedGramatica) otro).cortacesped.clone();
	}
	
	@Override
	public Individuo<Integer> ejecutar() {
		cortacesped.initJardin();
		fitness = new FitJardinGramatica(cortacesped.getJardin(), cortacesped.maxPasos, maxWraps);
		
		return super.ejecutar();
	}

	@Override
	public <T> List<Option<T>> getExtraOpts() {
		List<Option<T>> extras = cortacesped.getExtraOpts();
		extras.remove(0);	// Elimina la opción del control de bloating
		extras.add(new IntegerOption<T>("tamaño de codones", "tamaño de codones", "tamCodon", 1, 100));
		extras.add(new IntegerOption<T>("máx de wraps", "máx de wraps", "maxWraps", 1, 100));
		return extras;
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
		GenEntero[] crom = new GenEntero[tamCodon];
		Arrays.fill(crom, getGenDefault());
		return new IndividuoEntero(Arrays.asList(crom));
	}

	@Override
	public Gen<Integer> getGenDefault() {
		return new GenEntero(0, 1000, 1);
	}
	
	@Override
	public String toString() {
		return cortacesped.toString() + " (gramática)";
	}

	public String getFile() {
		return cortacesped.file;
	}

	public void setFile(String file) {
		cortacesped.file = "resources/"+file+".txt";
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

	public Integer getTamCodon() {
		return tamCodon;
	}

	public void setTamCodon(Integer tamCodon) {
		this.tamCodon = tamCodon;
	}

	public Integer getMaxWraps() {
		return maxWraps;
	}

	public void setMaxWraps(Integer maxWraps) {
		this.maxWraps = maxWraps;
	}
	
	public List<List<Casilla>> getJardin() {
		return cortacesped.getJardin();
	}

	public Boolean getExtraInstr() {
		return cortacesped.extraInstr;
	}

	public void setExtraInstr(Boolean extras) {
		cortacesped.extraInstr = extras;
	}

	public Boolean getEnableBloating() {
		return cortacesped.enableBloating;
	}

	public void setEnableBloating(Boolean enableBloating) {
		cortacesped.enableBloating = enableBloating;
	}
}
