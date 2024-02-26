package modelo.seleccion;

import java.util.ArrayList;
import java.util.List;

import modelo.individuo.Individuo;
import vista.ConfigPanel.IntegerOption;
import vista.ConfigPanel.Option;

public class SelRestos implements Seleccion {
	
	private int k;
	
	public SelRestos() {
		k = 10;
	}

	@Override
	public <T> List<Individuo<T>> seleccionar(List<Individuo<T>> individuos) {
		List<Individuo<T>> supervs = new ArrayList<>(individuos.size());
		double totalFit = 0;
		for (Individuo<T> ind : individuos) totalFit += ind.getFitness();
		
		for (int i = 0; i < individuos.size(); ++i) {
			double prob = individuos.get(i).getFitness() / totalFit;
			int nCopias = (int) prob * k;
			for(int j = 0; j < nCopias; ++j) {
				supervs.add(individuos.get(i).clone());
				if(supervs.size() == individuos.size()) {
					return supervs;
				}
			}
		}
		if(supervs.size() < individuos.size()) {
			supervs.addAll(new SelRuleta().seleccionar(individuos).subList(0, individuos.size() - supervs.size()));
		}
		return supervs;
	}
	
	@Override
	public <T> List<Option<T>> getExtraOpts() {
		List<Option<T>> extras = new ArrayList<>();
		extras.add(new IntegerOption<T>("k", "k", "k", 0, Integer.MAX_VALUE));
		return extras;
	}
	
	@Override
	public String toString() {
		return "Restos";
	}
	
	public int getK() {
		return k;
	}

	public void setK(int k) {
		this.k = k;
	}
	
	@Override
	public Seleccion clone() {
		return new SelRestos();
	}

}
