package modelo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import modelo.cruce.CrucePMX;
import modelo.fitness.FitAeropuerto;
import modelo.individuo.Individuo;
import modelo.individuo.IndividuoPermInt;
import modelo.mutacion.MutacionIntercambio;
import modelo.seleccion.SelEstocastico;
import vista.ConfigPanel.Option;
import vista.ConfigPanel.TextOption;

public class Aeropuerto extends AlgGenetico<Integer> {
	
	public static enum Peso {
		W, G, P
	}
	
	private List<List<Integer>> tel;
	private List<Peso> peso;
	private List<String> ids;
	private static final Double[][] sep = {{ 1.0, 1.5, 2.0 },
										   { 1.0, 1.5, 1.5 },
										   { 1.0, 1.0, 1.0 },};
	
	private Integer pistas;
	private Integer vuelos;
	private String fileVuelos = "resources/vuelos1.txt";
	private String fileTEL = "resources/TEL1.txt";
	
	public Aeropuerto() {
		mutacion = new MutacionIntercambio<>();
		cruce = new CrucePMX<>();
		seleccion = new SelEstocastico();
	}

	public Aeropuerto(AlgGenetico<Integer> otro) {
		super(otro);
		Aeropuerto alg = (Aeropuerto) otro;
		tel = alg.tel;
		peso = alg.peso;
		pistas = alg.pistas;
		vuelos = alg.vuelos;
		ids = alg.ids;
		fileTEL = alg.fileTEL;
		fileVuelos = alg.fileVuelos;
	}

	private void initFromFiles() {
		Scanner inVuelos, inTels;
		try {
			inVuelos = new Scanner(new File(fileVuelos));
			inTels = new Scanner(new File(fileTEL));
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
		
		ids = new ArrayList<>();
		peso = new ArrayList<>();
		vuelos = 0;
		while (inVuelos.hasNext()) {
			ids.add(inVuelos.next());
			peso.add(Peso.valueOf(inVuelos.next()));
			vuelos++;
		}
		
		tel = new ArrayList<>();
		pistas = 0;
		List<Integer> pista = new ArrayList<>();
		while (inTels.hasNext()) {
			pista.add(Integer.parseInt(inTels.next()));
			
			if (pista.size() == vuelos) {
				tel.add(pista);
				pista = new ArrayList<>();
				pistas++;
			}
		}
	}
	
	@Override
	public Individuo<Integer> ejecutar() {
		initFromFiles();
		fitness = new FitAeropuerto(tel, sep, peso);
		
		return super.ejecutar();
	}

	@Override
	public <T> List<Option<T>> getExtraOpts() {
		List<Option<T>> extras = new ArrayList<>();
		extras.add(new TextOption<T>("archivo de vuelos", "archivo de vuelos", "fileVuelos"));
		extras.add(new TextOption<T>("archivo de TELs", "archivo de TELs", "fileTEL"));
		return extras;
	}

	@Override
	protected Individuo<Integer> generarIndividuo() {
		return new IndividuoPermInt(vuelos);
	}

	@Override
	public CategoriaCrom getCategoria() {
		return CategoriaCrom.PERMUTACION;
	}

	@Override
	public AlgGenetico<Integer> clone() {
		return new Aeropuerto(this);
	}

	@Override
	public Boolean maximizacion() {
		return false;
	}

	@Override
	public String toString() {
		return "Aeropuerto";
	}

	public String getFileVuelos() {
		return fileVuelos;
	}

	public void setFileVuelos(String fileVuelos) {
		this.fileVuelos = fileVuelos;
	}

	public String getFileTEL() {
		return fileTEL;
	}

	public void setFileTEL(String fileTEL) {
		this.fileTEL = fileTEL;
	}
}
