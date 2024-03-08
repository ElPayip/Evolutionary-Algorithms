package modelo.fitness;

import java.util.List;
import java.util.Map;

import modelo.Peso;
import modelo.individuo.Individuo;

public class FitAeropuerto implements Fitness<Integer> {
	
	private List<List<Integer>> tel;
	private Map<Peso,Map<Peso,Double>> sep;
	private List<Peso> peso;
	private int nPistas;
	
	public FitAeropuerto(List<List<Integer>> tels, Map<Peso,Map<Peso,Double>> seps, List<Peso> pesos) {
		tel = tels;
		sep = seps;
		peso = pesos;
		nPistas = tels.get(0).size();
	}

	@Override
	public double eval(Individuo<Integer> ind) {
		double fitness = 0;
		int[] heads = new int[nPistas];
		int[] tlas = new int[nPistas];
		
		for (Integer avion : ind.getValores()) {
			double minTLA = Double.MAX_VALUE;
			
			for (int pista = 0; pista < nPistas; ++pista) {
				double separacion = sep.get(peso.get(heads[pista])).get(peso.get(avion));
				double tla = Math.max(separacion + tlas[pista], tel.get(avion).get(pista));
				
				if (tla - tel.get(avion).get(pista) < minTLA)
					minTLA = tla - tel.get(avion).get(pista);
			}
			fitness += Math.pow(minTLA, 2);
		}
		return fitness;
	}

}
