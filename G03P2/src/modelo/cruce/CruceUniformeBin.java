package modelo.cruce;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import modelo.genes.Gen;
import modelo.genes.GenBinario;
import utils.Pair;
import vista.ConfigPanel.Option;

public class CruceUniformeBin<T> implements Cruce<T>{

	@Override
	public Pair<List<Gen<T>>, List<Gen<T>>> cruzar(List<Gen<T>> crom1, List<Gen<T>> crom2) {
		
		List<GenBinario<T>> hijo1 = new ArrayList<>(), 
				hijo2 = new ArrayList<>();
		try {
			for (Gen<T> g : crom1) hijo1.add((GenBinario<T>) g);
			for (Gen<T> g : crom2) hijo2.add((GenBinario<T>) g);
		} catch (ClassCastException e) {
			throw new IllegalArgumentException("CruceUniformeBin debe aplicarse sobre GenBinario");
		}
		
		List<Integer> alelos1 = GenBinario.getGenotipo(hijo1);
		List<Integer> alelos2 = GenBinario.getGenotipo(hijo2);
		
		List<Integer> c1 = new ArrayList<>(), 
					  c2 = new ArrayList<>();
		
		Random r = new Random();
		
		for(int i = 0; i < alelos1.size(); ++i) {
			if(r.nextBoolean()) {
				c1.add(alelos2.get(i));
				c2.add(alelos1.get(i));
			} 
			else {
				c1.add(alelos1.get(i));
				c2.add(alelos2.get(i));
			}
		}
		
		GenBinario.setGenotipo(hijo1, c1);
		GenBinario.setGenotipo(hijo2, c2);
		
		return new Pair<>(new ArrayList<Gen<T>>(hijo1), 
						  new ArrayList<Gen<T>>(hijo2));
	}
	
	@Override
	public String toString() {
		return "Cruce uniforme (binario)";
	}
	
	@Override
	public <O> List<Option<O>> getExtraOpts() {
		return null;
	}
	
	@Override
	public Cruce<T> clone() {
		return new CruceUniformeBin<>();
	}
	
}
