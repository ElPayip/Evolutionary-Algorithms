package modelo.factorias;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modelo.CategoriaCrom;
import modelo.mutacion.Mutacion;
import modelo.mutacion.MutacionBinaria;
import modelo.mutacion.MutacionHeuristica;
import modelo.mutacion.MutacionInsercion;
import modelo.mutacion.MutacionIntercambio;
import modelo.mutacion.MutacionInversion;
import modelo.mutacion.MutacionUniforme;

public class FactoriaMutacion {
	
	private Map<CategoriaCrom, List<Mutacion<?>>> mutaciones;

	public FactoriaMutacion() {
		List<Mutacion<?>> binarias = new ArrayList<>();
		binarias.add(new MutacionBinaria<>());

		List<Mutacion<?>> reales = new ArrayList<>();
		reales.add(new MutacionUniforme());

		List<Mutacion<?>> permutaciones = new ArrayList<>();
		permutaciones.add(new MutacionInversion<>());
		permutaciones.add(new MutacionIntercambio<>());
		permutaciones.add(new MutacionInsercion<>());
		permutaciones.add(new MutacionHeuristica<>());
		
		mutaciones = new HashMap<>();
		mutaciones.put(CategoriaCrom.BINARIO, binarias);
		mutaciones.put(CategoriaCrom.REAL, reales);
		mutaciones.put(CategoriaCrom.PERMUTACION, permutaciones);
	}
	
	public Mutacion<?>[] getMutaciones(CategoriaCrom categoria) {
		List<Mutacion<?>> sol = mutaciones.get(categoria);
		Mutacion<?>[] aux = new Mutacion<?>[sol.size()];
		return sol.toArray(aux);
	}
}
