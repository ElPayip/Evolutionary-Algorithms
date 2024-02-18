package modelo.seleccion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import modelo.individuo.Individuo;

public class SelTorneoProb implements Seleccion {
	
	private int k;
	
	public SelTorneoProb(int k) {
		this.k = k;
	}

	@Override
	public <T,C> List<Individuo<T,C>> seleccionar(List<Individuo<T,C>> individuos) {
		Random rnd = new Random();
		List<Individuo<T,C>> supervs = new ArrayList<>(individuos.size());
		
		for (int i = 0; i < individuos.size(); ++i) {
			int[] idx = rnd.ints(k, 0, individuos.size()).toArray();
			
			double totalFit = 0;
			for (int j : idx)
				totalFit += individuos.get(j).getFitness();
			
			double prob = rnd.nextDouble(), acum = 0;
			int j = -1;
			do {
				++j;
				acum += individuos.get(j).getFitness() / totalFit;
			} while(acum < prob);
			supervs.set(i, individuos.get(j));
		}
		return supervs;
	}
}
