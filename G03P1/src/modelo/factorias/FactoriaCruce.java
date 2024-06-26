package modelo.factorias;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modelo.CategoriaGen;
import modelo.cruce.Cruce;
import modelo.cruce.CruceAritmetico;
import modelo.cruce.CruceBLXAlpha;
import modelo.cruce.CruceMonopunto;
import modelo.cruce.CruceMonopuntoBin;
import modelo.cruce.CruceUniforme;
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
		reales.add(new CruceMonopunto<T>());
		reales.add(new CruceUniforme<T>());
		reales.add(new CruceAritmetico());
		reales.add(new CruceBLXAlpha());
		
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
