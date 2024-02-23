package modelo.seleccion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import modelo.individuo.Individuo;
import vista.ConfigPanel.IntegerOption;
import vista.ConfigPanel.Option;

public class SelTorneoProb implements Seleccion {
	
	private Integer k = 3;
	
	public SelTorneoProb() {}
	
	public SelTorneoProb(Integer k) {
		this.k = k;
	}

	@Override
	public <T> List<Individuo<T>> seleccionar(List<Individuo<T>> individuos) {
		if (k == null)
			throw new RuntimeException("Hay que inicializar SelTorneoProb con setK");
		
		Random rnd = new Random();
		List<Individuo<T>> supervs = new ArrayList<>(individuos.size());
		
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
			} while(acum < prob && j < individuos.size()-1);
			supervs.add(individuos.get(j).clone());
		}
		return supervs;
	}
	
	public void setK(Integer k) {
		this.k = k;
	}
	
	public Integer getK() {
		return k;
	}

	@Override
	public String toString() {
		return "Torneo probabilístico";
	}

	@Override
	public <T> List<Option<T>> getExtraOpts() {
		List<Option<T>> extras = new ArrayList<>();
		extras.add(new IntegerOption<T>("k", "k", "k", 0, 100));
		return extras;
	}
	
	@Override
	public Seleccion clone() {
		return new SelTorneoProb(k);
	}
}
