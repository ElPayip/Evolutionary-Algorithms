package modelo.seleccion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import modelo.individuo.Individuo;
import vista.ConfigPanel.Option;

public class SelRuleta implements Seleccion {

	@Override
	public <T> List<Individuo<T>> seleccionar(List<Individuo<T>> individuos) {
		Random rnd = new Random();
		double totalFit = 0;
		for (Individuo<T> ind : individuos) totalFit += ind.getFitness();
		
		List<Individuo<T>> supervs = new ArrayList<>(individuos.size());
		for (int i = 0; i < individuos.size(); ++i) {
			double prob = rnd.nextDouble(), acum = 0;
			
			int j = -1;
			do {
				++j;
				acum += individuos.get(j).getFitness() / totalFit;
			} while(acum < prob);
			
			supervs.add(individuos.get(j).clone());
		}
		
		return supervs;
	}

	@Override
	public String toString() {
		return "Ruleta";
	}

	@Override
	public <T> List<Option<T>> getExtraOpts() {
		return null;
	}
	
	@Override
	public Seleccion clone() {
		return new SelRuleta();
	}
}
