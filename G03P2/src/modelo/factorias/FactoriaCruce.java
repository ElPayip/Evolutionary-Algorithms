package modelo.factorias;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modelo.CategoriaCrom;
import modelo.cruce.Cruce;
import modelo.cruce.CruceAritmetico;
import modelo.cruce.CruceBLXAlpha;
import modelo.cruce.CruceCX;
import modelo.cruce.CruceCodifOrdinal;
import modelo.cruce.CruceMonopunto;
import modelo.cruce.CruceMonopuntoBin;
import modelo.cruce.CruceOX;
import modelo.cruce.CruceOXOP;
import modelo.cruce.CruceOXPP;
import modelo.cruce.CrucePMX;
import modelo.cruce.CruceRecombinacionRutas;
import modelo.cruce.CruceUniforme;
import modelo.cruce.CruceUniformeBin;


public class FactoriaCruce {

	private Map<CategoriaCrom,List<Cruce<?>>> cruces;
	
	public <T> FactoriaCruce() {
		List<Cruce<?>> genericos = new ArrayList<>();
		genericos.add(new CruceMonopunto<T>());
		genericos.add(new CruceUniforme<T>());
		
		List<Cruce<?>> binarios = new ArrayList<>();
		binarios.add(new CruceMonopuntoBin<T>());
		binarios.add(new CruceUniformeBin<T>());
		
		List<Cruce<?>> reales = new ArrayList<>();
		reales.addAll(genericos);
		reales.add(new CruceAritmetico());
		reales.add(new CruceBLXAlpha());
		
		List<Cruce<?>> permutaciones = new ArrayList<>();
		permutaciones.add(new CrucePMX<T>());
		permutaciones.add(new CruceOX<T>());
		permutaciones.add(new CruceOXPP<T>());
		permutaciones.add(new CruceOXOP<T>());
		permutaciones.add(new CruceCX<T>());
		permutaciones.add(new CruceRecombinacionRutas<T>());
		permutaciones.add(new CruceCodifOrdinal<T>());
		
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
