package modelo.factorias;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modelo.CategoriaCrom;
import modelo.cruce.Cruce;
import modelo.cruce.CruceMonopunto;
import modelo.cruce.CruceMultipunto;
import modelo.cruce.CruceSubArbol;
import modelo.cruce.CruceUniforme;


public class FactoriaCruce {

	private Map<CategoriaCrom,List<Cruce<?>>> cruces;
	
	public <T> FactoriaCruce() {
		List<Cruce<?>> genericos = new ArrayList<>();
		genericos.add(new CruceMonopunto<>());
		genericos.add(new CruceUniforme<>());
		genericos.add(new CruceMultipunto<>());
		
		List<Cruce<?>> binarios = new ArrayList<>();
		
		List<Cruce<?>> reales = new ArrayList<>();
		reales.addAll(genericos);
		
		List<Cruce<?>> permutaciones = new ArrayList<>();
		
		List<Cruce<?>> arboles = new ArrayList<>();
		arboles.add(new CruceSubArbol<>());
		
		cruces = new HashMap<>();
		cruces.put(CategoriaCrom.GENERICA, genericos);
		cruces.put(CategoriaCrom.BINARIO, binarios);
		cruces.put(CategoriaCrom.REAL, reales);
		cruces.put(CategoriaCrom.PERMUTACION, permutaciones);
		cruces.put(CategoriaCrom.ARBOL, arboles);
	}
	
	public Cruce<?>[] getCruces(CategoriaCrom categoria) {
		List<Cruce<?>> sol = cruces.get(categoria);
		Cruce<?>[] aux = new Cruce<?>[sol.size()];
		return sol.toArray(aux);
	}
}
