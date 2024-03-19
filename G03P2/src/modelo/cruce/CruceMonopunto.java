package modelo.cruce;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import modelo.genes.Gen;
import utils.Pair;
import vista.ConfigPanel.Option;

public class CruceMonopunto<T> implements Cruce<T> {

	@Override
	public Pair<List<Gen<T>>, List<Gen<T>>> cruzar(List<Gen<T>> crom1, List<Gen<T>> crom2) {
		
		List<Gen<T>> hijo1 = new ArrayList<>(), 
					 hijo2 = new ArrayList<>();
		
		int len = crom1.size();
		int punto = new Random().nextInt(len);
		
		for (int i = 0; i < punto; ++i) {
			hijo1.add(crom1.get(i).clone());
			hijo2.add(crom2.get(i).clone());
		}
		for (int i = punto; i < len; ++i) {
			hijo1.add(crom2.get(i).clone());
			hijo2.add(crom1.get(i).clone());
		}
		
		return new Pair<>(hijo1,hijo2);
	}

	@Override
	public <O> List<Option<O>> getExtraOpts() {
		return null;
	}
	
	@Override
	public Cruce<T> clone() {
		return new CruceMonopunto<>();
	}

	@Override
	public String toString() {
		return "Cruce monopunto";
	}
}
