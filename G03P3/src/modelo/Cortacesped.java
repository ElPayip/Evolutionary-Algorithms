package modelo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
import vista.ConfigPanel.IntegerOption;
import vista.ConfigPanel.Option;
import vista.ConfigPanel.StrategyOption;
import vista.ConfigPanel.TextOption;

public class Cortacesped extends AlgGenetico<Accion> {

	public enum Casilla {
		CESPED(1), CORTADO(0), ROCA(0), FLORES(-3);
		
		int valor;
		Casilla(int v) {
			valor = v;
		}
		public int getValor() {return valor;}
		public boolean isTransitable() {return this != ROCA;}
		public boolean isCortable() {return this == CESPED 
										 || this == FLORES;}
		public static Casilla fromIcon(String icon) {
			if (icon.equals("C"))
				return CESPED;
			else if (icon.equals("O"))
				return CORTADO;
			else if (icon.equals("R"))
				return ROCA;
			else if (icon.equals("F"))
				return FLORES;
			return null;
		}
	}
	
	private Integer ancho = 8, alto = 8, maxPasos = 100;
	private List<List<Casilla>> jardin;
	private ControlBloating controlBloating;
	private String file = "resources/rocas.txt";
	
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
		try {
			Scanner in = new Scanner(new File(file));
			alto = in.nextInt();
			ancho = in.nextInt();
			GenNodoJardin.setDominio(alto, ancho);
			jardin = new ArrayList<>();
			for (int i = 0; i < alto; ++i) {
				jardin.add(new ArrayList<>());
				for (int j = 0; j < ancho; ++j) {
					jardin.get(i).add(Casilla.fromIcon(in.next()));
				}
			}
			in.close();
		} catch (FileNotFoundException e) {
			System.out.println("Archivo no válido, se usará el jardín por defecto");
			GenNodoJardin.setDominio(alto, ancho);
			jardin = new ArrayList<>();
			for (int i = 0; i < alto; ++i) {
				jardin.add(new ArrayList<>());
				for (int j = 0; j < ancho; ++j) {
					jardin.get(i).add(Casilla.CESPED);
				}
			}
		}
	}
	
	@Override
	public Individuo<Accion> ejecutar() {
		initJardin();
		fitness = new FitJardin(getJardin(), maxPasos);
		
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
		extras.add(new TextOption<T>("archivo del jardin", "archivo del jardin", "file"));
		extras.add(new IntegerOption<T>("máximo número de pasos", "máximo número de pasos", "maxPasos", 1, 1000));
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

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public Integer getMaxPasos() {
		return maxPasos;
	}

	public void setMaxPasos(Integer maxPasos) {
		this.maxPasos = maxPasos;
	}
}
