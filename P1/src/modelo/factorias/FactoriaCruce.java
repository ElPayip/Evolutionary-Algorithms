package modelo.factorias;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import modelo.CategoriaGen;
import modelo.cruce.Cruce;
import modelo.cruce.CruceAritmetico;
import modelo.cruce.CruceMonopuntoBin;
import modelo.cruce.CruceUniformeBin;


public class FactoriaCruce {

	private List<Cruce<?>> binarios;
	private List<Cruce<?>> reales;
	
	private Map<CategoriaGen,List<Cruce<?>>> cruces;
	
	public <T> FactoriaCruce() {
		binarios = new ArrayList<>();
		binarios.add(new CruceMonopuntoBin<T>());
		binarios.add(new CruceUniformeBin<T>());
		
		reales = new ArrayList<>();
		reales.add(new CruceAritmetico());
		
		cruces.put(CategoriaGen.BINARIO, binarios);
		cruces.put(CategoriaGen.REAL, reales);
	}
	
	public List<Cruce<?>> getCruces(CategoriaGen categoria) {
		return cruces.get(categoria);
	}
}
