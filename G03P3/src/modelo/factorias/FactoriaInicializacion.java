package modelo.factorias;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import modelo.genes.Gen;
import modelo.individuo.Individuo;
import modelo.inicializaciones.IniCompleta;
import modelo.inicializaciones.IniCreciente;
import modelo.inicializaciones.IniRampedAndHalf;
import modelo.inicializaciones.Inicializacion;

public class FactoriaInicializacion<T> {

	private List<Inicializacion<T>> inits;
	
	public FactoriaInicializacion() {
		inits = new ArrayList<>();
		inits.add(new IniCreciente<>());
		inits.add(new IniCompleta<>());
		inits.add(new IniRampedAndHalf<>());
	}
	
	public Inicializacion<?>[] getInicializaciones() {
		return inits.toArray(new Inicializacion<?>[0]);
	}
	
	public void setClases(Class<Individuo<T>> ind, Class<Gen<T>> gen) {
		try {
		for (Inicializacion<T> i : inits)
			i.setInstances(ind.getConstructor().newInstance(), gen.getConstructor().newInstance());
		} catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException e) {
			e.printStackTrace();
		}
	}
}
