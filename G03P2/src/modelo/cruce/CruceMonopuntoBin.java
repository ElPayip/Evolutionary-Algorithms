package modelo.cruce;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import modelo.genes.Gen;
import modelo.genes.GenBinario;
import utils.Pair;
import vista.ConfigPanel.Option;

public class CruceMonopuntoBin<T> implements Cruce<T> {
	
	@SuppressWarnings("unchecked")
	@Override
	public Pair<List<? extends Gen<T>>,List<? extends Gen<T>>> cruzar(
			List<? extends Gen<T>> crom1, List<? extends Gen<T>> crom2) {
		
		List<GenBinario<T>> hijo1 = new ArrayList<>(), 
							hijo2 = new ArrayList<>();
		try {
			hijo1.addAll((List<GenBinario<T>>) crom1);
			hijo2.addAll((List<GenBinario<T>>) crom2);
		} catch (ClassCastException e) {
			throw new IllegalArgumentException("CruceMonopuntoBin debe aplicarse sobre GenBinario");
		}

		List<Integer> alelos1 = GenBinario.getGenotipo(hijo1);
		List<Integer> alelos2 = GenBinario.getGenotipo(hijo2);
		
		int len = alelos1.size();
		int punto = (int) (new Random().nextDouble() * len);
		
		List<Integer> c1 = alelos1.subList(0, punto);
		List<Integer> c2 = alelos2.subList(0, punto);
		c1.addAll(alelos2.subList(punto, len));
		c2.addAll(alelos1.subList(punto, len));
		
		GenBinario.setGenotipo(hijo1, c1);
		GenBinario.setGenotipo(hijo2, c2);
		
		return new Pair<>(hijo1, hijo2);
	}

	@Override
	public String toString() {
		return "Cruce monopunto (binario)";
	}

	@Override
	public <O> List<Option<O>> getExtraOpts() {
		return null;
	}
	
	@Override
	public Cruce<T> clone() {
		return new CruceMonopuntoBin<>();
	}
}