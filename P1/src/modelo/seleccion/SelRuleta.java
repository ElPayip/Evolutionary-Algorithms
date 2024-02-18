package modelo.seleccion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import modelo.individuo.Individuo;

public class SelRuleta implements Seleccion {

	@Override
	public <T,C> List<Individuo<T,C>> seleccionar(List<Individuo<T,C>> individuos) {
		Random rnd = new Random();
		double totalFit = 0, acum = 0;
		for (Individuo<T,C> ind : individuos) totalFit += ind.getFitness();
		
		List<Individuo<T,C>> supervs = new ArrayList<>(individuos.size());
		for (int i = 0; i < supervs.size(); ++i) {
			double prob = rnd.nextDouble();
			
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
