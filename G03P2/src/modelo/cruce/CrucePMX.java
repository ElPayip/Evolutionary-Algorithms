package modelo.cruce;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import modelo.genes.Gen;
import utils.Pair;
import vista.ConfigPanel.Option;

public class CrucePMX<T> implements Cruce<T> {

	@Override
	public Pair<List<Gen<T>>, List<Gen<T>>> cruzar(List<Gen<T>> crom1, List<Gen<T>> crom2) {

		Random rand = new Random();
		int pos1 = rand.nextInt(crom1.size()), pos2 = rand.nextInt(crom1.size());
		if (pos2 < pos1) {
			int aux = pos1; pos1 = pos2; pos2 = aux;
		}
		
		List<Gen<T>> hijo1 = new ArrayList<>(crom1); Collections.fill(hijo1, null);
		List<Gen<T>> hijo2 = new ArrayList<>(crom2); Collections.fill(hijo2, null);
		int i = pos1;
		while (i <= pos2) {
			hijo1.set(i, crom2.get(i).clone());
			hijo2.set(i, crom1.get(i).clone());
			++i;
		}

		i %= crom1.size();
		while (i != pos1) {
			Gen<T> g = crom1.get(i);
			while (hijo1.contains(g)) g = crom1.get(crom2.indexOf(g));
			hijo1.set(i, g.clone());

			g = crom2.get(i);
			while (hijo2.contains(g)) g = crom2.get(crom1.indexOf(g));
			hijo2.set(i, g.clone());
			
			i = (i + 1) % crom1.size();
		}
		return new Pair<>(hijo1, hijo2);
	}
	
	@Override
	public Cruce<T> clone() {
		return new CrucePMX<>();
	}

	@Override
	public <O> List<Option<O>> getExtraOpts() {
		return null;
	}

	@Override
	public String toString() {
		return "PMX";
	}
}
