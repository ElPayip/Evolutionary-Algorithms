package modelo.cruce;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import modelo.genes.Gen;
import utils.Pair;
import vista.ConfigPanel.Option;

public class CruceCX<T> implements Cruce<T> {

	@Override
	public Pair<List<Gen<T>>, List<Gen<T>>> cruzar(List<Gen<T>> crom1, List<Gen<T>> crom2) {
		List<Gen<T>> hijo1 = new ArrayList<>(crom1); Collections.fill(hijo1, null);
		List<Gen<T>> hijo2 = new ArrayList<>(crom2); Collections.fill(hijo2, null);
		
		int idx = 0;
		do {
			hijo1.set(idx, crom1.get(idx).clone());
			hijo2.set(idx, crom2.get(idx).clone());
			idx = crom1.indexOf(crom2.get(idx));
		} while (hijo1.get(idx) == null);
		
		for (int i = 0; i < crom1.size(); ++i) {
			if (hijo1.get(i) == null) {
				hijo1.set(i, crom2.get(i).clone());
				hijo2.set(i, crom1.get(i).clone());
			}
		}
		return new Pair<>(hijo1, hijo2);
	}
	
	@Override
	public Cruce<T> clone() {
		return new CruceCX<>();
	}

	@Override
	public <O> List<Option<O>> getExtraOpts() {
		return null;
	}

	@Override
	public String toString() {
		return "CX";
	}
}
