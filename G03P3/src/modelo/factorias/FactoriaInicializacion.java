package modelo.factorias;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modelo.CategoriaCrom;
import modelo.genes.Gen;
import modelo.individuo.Individuo;
import modelo.inicializaciones.IniCompleta;
import modelo.inicializaciones.IniCreciente;
import modelo.inicializaciones.IniRampedAndHalf;
import modelo.inicializaciones.IniRandom;
import modelo.inicializaciones.Inicializacion;

public class FactoriaInicializacion<T> {

	private Map<CategoriaCrom,List<Inicializacion<T>>> inits;
	
	public FactoriaInicializacion() {
		inits = new HashMap<>();
		
		List<Inicializacion<T>> genericas = new ArrayList<>();
		genericas.add(new IniRandom<T>());
		
		List<Inicializacion<T>> arboles = new ArrayList<>();
		arboles.add(new IniCreciente<>());
		arboles.add(new IniCompleta<>());
		arboles.add(new IniRampedAndHalf<>());
		
		inits.put(CategoriaCrom.ARBOL, arboles);
		inits.put(CategoriaCrom.GENERICA, genericas);
	}
	
	public Inicializacion<?>[] getInicializaciones(CategoriaCrom cat) {
		return inits.get(cat).toArray(new Inicializacion<?>[0]);
	}
	
	public void setClases(Class<Individuo<T>> ind, Class<Gen<T>> gen) {
		try {
			for (List<Inicializacion<T>> is : inits.values())
				for (Inicializacion<T> i : is)
					i.setInstances(ind.getConstructor().newInstance(), gen.getConstructor().newInstance());
		} catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException e) {
			e.printStackTrace();
		}
	}
}
