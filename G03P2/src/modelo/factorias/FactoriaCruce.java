package modelo.factorias;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modelo.CategoriaCrom;
import modelo.cruce.Cruce;
import modelo.cruce.CruceAritmetico;
import modelo.cruce.CruceBLXAlpha;
import modelo.cruce.CruceMonopunto;
import modelo.cruce.CruceMonopuntoBin;
import modelo.cruce.CruceUniforme;
import modelo.cruce.CruceUniformeBin;


public class FactoriaCruce {

	private Map<CategoriaCrom,List<Cruce<?>>> cruces;
	
	public <T> FactoriaCruce() {
		List<Cruce<?>> binarios = new ArrayList<>();
		binarios.add(new CruceMonopuntoBin<T>());
		binarios.add(new CruceUniformeBin<T>());
		
		List<Cruce<?>> reales = new ArrayList<>();
		reales.add(new CruceMonopunto<T>());
		reales.add(new CruceUniforme<T>());
		reales.add(new CruceAritmetico());
		reales.add(new CruceBLXAlpha());
		
		List<Cruce<?>> permutaciones = new ArrayList<>(); //TODO
		
		cruces = new HashMap<>();
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
