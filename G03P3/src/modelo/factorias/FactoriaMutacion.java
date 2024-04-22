package modelo.factorias;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modelo.CategoriaCrom;
import modelo.mutacion.Mutacion;
import modelo.mutacion.MutacionContraccion;
import modelo.mutacion.MutacionExpansion;
import modelo.mutacion.MutacionFuncional;
import modelo.mutacion.MutacionHoist;
import modelo.mutacion.MutacionPermutacion;
import modelo.mutacion.MutacionSubarbol;
import modelo.mutacion.MutacionTerminal;
import modelo.mutacion.MutacionUniforme;

public class FactoriaMutacion {
	
	private Map<CategoriaCrom, List<Mutacion<?>>> mutaciones;

	public FactoriaMutacion() {
		List<Mutacion<?>> genericas = new ArrayList<>();
		genericas.add(new MutacionUniforme<>());
		
		List<Mutacion<?>> binarias = new ArrayList<>();
		binarias.addAll(genericas);

		List<Mutacion<?>> reales = new ArrayList<>();
		reales.addAll(genericas);

		List<Mutacion<?>> permutaciones = new ArrayList<>();

		List<Mutacion<?>> arboles = new ArrayList<>();
		arboles.add(new MutacionTerminal<>());
		arboles.add(new MutacionFuncional<>());
		arboles.add(new MutacionPermutacion<>());
		arboles.add(new MutacionSubarbol<>());
		arboles.add(new MutacionHoist<>());
		arboles.add(new MutacionContraccion<>());
		arboles.add(new MutacionExpansion<>());
		
		mutaciones = new HashMap<>();
		mutaciones.put(CategoriaCrom.GENERICA, genericas);
		mutaciones.put(CategoriaCrom.BINARIO, binarias);
		mutaciones.put(CategoriaCrom.REAL, reales);
		mutaciones.put(CategoriaCrom.PERMUTACION, permutaciones);
		mutaciones.put(CategoriaCrom.ARBOL, arboles);
	}
	
	public Mutacion<?>[] getMutaciones(CategoriaCrom categoria) {
		List<Mutacion<?>> sol = mutaciones.get(categoria);
		Mutacion<?>[] aux = new Mutacion<?>[sol.size()];
		return sol.toArray(aux);
	}
}
