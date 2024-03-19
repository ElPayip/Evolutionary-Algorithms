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
import modelo.cruce.CruceMultipunto;
import modelo.cruce.CruceOX;
import modelo.cruce.CruceOXOP;
import modelo.cruce.CruceOXPP;
import modelo.cruce.CrucePMX;
import modelo.cruce.CruceERX;
import modelo.cruce.CruceUniforme;
import modelo.cruce.CruceUniformeBin;


public class FactoriaCruce {

	private Map<CategoriaCrom,List<Cruce<?>>> cruces;
	
	public <T> FactoriaCruce() {
		List<Cruce<?>> genericos = new ArrayList<>();
		genericos.add(new CruceMonopunto<>());
		genericos.add(new CruceUniforme<>());
		genericos.add(new CruceMultipunto<>());
		
		List<Cruce<?>> binarios = new ArrayList<>();
		binarios.add(new CruceMonopuntoBin<>());
		binarios.add(new CruceUniformeBin<>());
		
		List<Cruce<?>> reales = new ArrayList<>();
		reales.addAll(genericos);
		reales.add(new CruceAritmetico());
		reales.add(new CruceBLXAlpha());
		
		List<Cruce<?>> permutaciones = new ArrayList<>();
		permutaciones.add(new CrucePMX<>());
		permutaciones.add(new CruceOX<>());
		permutaciones.add(new CruceOXPP<>());
		permutaciones.add(new CruceOXOP<>());
		permutaciones.add(new CruceCX<>());
		permutaciones.add(new CruceERX<>());
		permutaciones.add(new CruceCodifOrdinal<>());
		
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
