package modelo.fitness;

import java.util.Arrays;
import java.util.List;

import modelo.Aeropuerto.Peso;
import modelo.individuo.Individuo;
import utils.Pair;

public class FitAeropuerto implements Fitness<Integer> {
	
	private List<List<Integer>> tel;
	private Double[][] sep;
	private List<Peso> peso;
	private int nPistas;

	int[] asignacion;
	double[] tlas;
	
	public FitAeropuerto(List<List<Integer>> tels, Double[][] seps, List<Peso> pesos) {
		tel = tels;
		sep = seps;
		peso = pesos;
		nPistas = tels.size();
		asignacion = new int[pesos.size()];
		tlas = new double[pesos.size()];
	}

	@Override
	public double eval(Individuo<Integer> ind) {
		double fitness = 0;
		int[] heads = new int[nPistas]; Arrays.fill(heads, -1);
		double[] tlaPista = new double[nPistas]; Arrays.fill(tlaPista, 0);
		
		for (Integer avion : ind.getValores()) {
			double minTLA = Double.MAX_VALUE, minTEL = Double.MAX_VALUE;
			int pistaFinal = 0;
			
			for (int pista = 0; pista < nPistas; ++pista) {
				double separacion = heads[pista] < 0 ? 0 : sep[peso.get(heads[pista]).ordinal()]
									   						  [peso.get(avion).ordinal()];
				double tla = Math.max(separacion + tlaPista[pista], tel.get(pista).get(avion));
				
				if (tla < minTLA) {
					minTLA = tla;
					pistaFinal = pista;
				}
				if (tel.get(pista).get(avion) < minTEL)
					minTEL = tel.get(pista).get(avion);
			}
			heads[pistaFinal] = avion;
			tlaPista[pistaFinal] = minTLA;
			asignacion[avion] = pistaFinal;
			tlas[avion] = minTLA;
			fitness += Math.pow(minTLA - minTEL, 2);
		}
		return fitness;
	}

	public Pair<int[],double[]> getSolucion() {
		return new Pair<>(asignacion, tlas);
	}
}
