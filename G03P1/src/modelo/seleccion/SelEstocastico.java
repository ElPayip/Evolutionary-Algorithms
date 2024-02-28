package modelo.seleccion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import modelo.individuo.Individuo;
import vista.ConfigPanel.Option;

public class SelEstocastico implements Seleccion {
	
	@Override
	public <T> List<Individuo<T>> seleccionar(List<Individuo<T>> individuos) {
		Random rnd = new Random();
		double totalFit = 0;
		for (Individuo<T> ind : individuos) totalFit += ind.getFitness();
		
		List<Individuo<T>> supervs = new ArrayList<>(individuos.size());
		double prob = rnd.nextDouble() / individuos.size(), acum = 0;
		int j = 0;
		
		for (int i = 0; i < individuos.size(); ++i) {
			double suma = (prob + i - 1)/ individuos.size();
			
			while(acum < suma) {
				acum += individuos.get(j).getFitness() / totalFit;
				j++;
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
		return "Estocástico universal";
	}
	
	@Override
	public Seleccion clone() {
		return new SelEstocastico();
	}

}
