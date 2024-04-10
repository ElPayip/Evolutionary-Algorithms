package modelo.seleccion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import modelo.individuo.Individuo;
import vista.ConfigPanel.Option;

public class SelRanking implements Seleccion {

	@Override
	public <T> List<Individuo<T>> seleccionar(List<Individuo<T>> individuos) {
		int n = individuos.size();
		Random rand = new Random();
		List<Individuo<T>> supervs = new ArrayList<>(n);
		double minProb = 1.0 / (n * n / 2.0);
		for (int i = 0; i < n; ++i) {
			
			int j = 0;
			double prob = rand.nextDouble(), acum = minProb * n;
			while (prob > acum)  {
				j++;
				acum += minProb * (n - j);
			}
			
			supervs.add(individuos.get(j).clone());
		}
		return supervs;
	}
	
	@Override
	public <T> List<Option<T>> getExtraOpts() {
		return null;
	}
	
	@Override
	public String toString() {
		return "Ranking";
	}
	
	@Override
	public Seleccion clone() {
		return new SelRanking();
	}
}
