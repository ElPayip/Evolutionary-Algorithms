package modelo.factorias;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modelo.CategoriaGen;
import modelo.cruce.Cruce;
import modelo.cruce.CruceAritmetico;
import modelo.cruce.CruceMonopuntoBin;


public class FactoriaCruce {

	private List<Cruce<?>> binarios;
	private List<Cruce<?>> reales;
	
	private Map<CategoriaGen,List<Cruce<?>>> cruces;
	
	public <T> FactoriaCruce() {
		binarios = new ArrayList<>();
		binarios.add(new CruceMonopuntoBin<T>());
		
		reales = new ArrayList<>();
		reales.add(new CruceAritmetico());
		
		cruces = new HashMap<>();
		cruces.put(CategoriaGen.BINARIO, binarios);
		cruces.put(CategoriaGen.REAL, reales);
	}
	
	public Cruce<?>[] getCruces(CategoriaGen categoria) {
		List<Cruce<?>> sol = cruces.get(categoria);
		Cruce<?>[] aux = new Cruce<?>[sol.size()];
		return sol.toArray(aux);
	}
}
