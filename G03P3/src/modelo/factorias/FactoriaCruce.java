package modelo.factorias;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modelo.CategoriaCrom;
import modelo.cruce.Cruce;


public class FactoriaCruce {

	private Map<CategoriaCrom,List<Cruce<?>>> cruces;
	
	public <T> FactoriaCruce() {
		List<Cruce<?>> genericos = new ArrayList<>();
		
		List<Cruce<?>> binarios = new ArrayList<>();
		
		List<Cruce<?>> reales = new ArrayList<>();
		reales.addAll(genericos);
		
		List<Cruce<?>> permutaciones = new ArrayList<>();
		
		cruces = new HashMap<>();
		cruces.put(CategoriaCrom.GENERICA, genericos);
		cruces.put(CategoriaCrom.BINARIO, binarios);
		cruces.put(CategoriaCrom.REAL, reales);
		cruces.put(CategoriaCrom.PERMUTACION, permutaciones);
	}
	
	public Cruce<?>[] getCruces(CategoriaCrom categoria) {
		List<Cruce<?>> sol = cruces.get(categoria);
		Cruce<?>[] aux = new Cruce<?>[sol.size()];
		return sol.toArray(aux);
	}
}
