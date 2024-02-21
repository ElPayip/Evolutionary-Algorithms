package modelo.seleccion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import modelo.individuo.Individuo;

public class SelRuleta implements Seleccion {

	@Override
	public <T,C> List<Individuo<T>> seleccionar(List<Individuo<T>> individuos) {
		Random rnd = new Random();
		double totalFit = 0, acum = 0;
		for (Individuo<T> ind : individuos) totalFit += ind.getFitness();
		
		List<Individuo<T>> supervs = new ArrayList<>(individuos.size());
		for (int i = 0; i < supervs.size(); ++i) {
			double prob = rnd.nextDouble();
			
			int j = -1;
			do {
				++j;
				acum += individuos.get(j).getFitness() / totalFit;
			} while(acum < prob);
			
			supervs.set(i, new Individuo<T>(individuos.get(j)));
		}
		
		return supervs;
	}
}
