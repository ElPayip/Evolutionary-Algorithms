package modelo.fitness;

import java.util.Arrays;
import java.util.List;

import modelo.Aeropuerto.Peso;
import modelo.individuo.Individuo;

public class FitAeropuerto implements Fitness<Integer> {
	
	private List<List<Integer>> tel;
	private Double[][] sep;
	private List<Peso> peso;
	private int nPistas;
	
	public FitAeropuerto(List<List<Integer>> tels, Double[][] seps, List<Peso> pesos) {
		tel = tels;
		sep = seps;
		peso = pesos;
		nPistas = tels.size();
	}

	@Override
	public double eval(Individuo<Integer> ind) {
		double fitness = 0;
		int[] heads = new int[nPistas]; Arrays.fill(heads, -1);
		double[] tlas = new double[nPistas]; Arrays.fill(tlas, 0);
		
		for (Integer avion : ind.getValores()) {
			double minTLA = Double.MAX_VALUE, minTEL = Double.MAX_VALUE;
			int pistaFinal = 0;
			
			for (int pista = 0; pista < nPistas; ++pista) {
				double separacion = heads[pista] < 0 ? 0 : sep[peso.get(heads[pista]).ordinal()]
									   						  [peso.get(avion).ordinal()];
				double tla = Math.max(separacion + tlas[pista], tel.get(pista).get(avion));
				
				if (tla < minTLA) {
					minTLA = tla;
					pistaFinal = pista;
				}
				if (tel.get(pista).get(avion) < minTEL)
					minTEL = tel.get(pista).get(avion);
			}
			heads[pistaFinal] = avion;
			tlas[pistaFinal] = minTLA;
			fitness += Math.pow(minTLA - minTEL, 2);
		}
		return fitness;
	}

}
