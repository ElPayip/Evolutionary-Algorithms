package modelo.fitness;

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
		int[] heads = new int[nPistas];
		double[] tlas = new double[nPistas];
		
		for (Integer avion : ind.getValores()) {
			double minTLA = Double.MAX_VALUE, minTEL = 0;
			int pistaFinal = 0;
			
			for (int pista = 0; pista < nPistas; ++pista) {
				double separacion = sep[peso.get(heads[pista]).ordinal()]
									   [peso.get(avion).ordinal()];
				double tla = Math.max(separacion + tlas[pista], tel.get(pista).get(avion));
				
				if (tla - tel.get(pista).get(avion) < minTLA - minTEL) {
					minTLA = tla;
					minTEL = tel.get(pista).get(avion);
					pistaFinal = pista;
				}
			}
			heads[pistaFinal] = avion;
			tlas[pistaFinal] = minTLA;
			fitness += Math.pow(minTLA - minTEL, 2);
		}
		return fitness;
	}

}
