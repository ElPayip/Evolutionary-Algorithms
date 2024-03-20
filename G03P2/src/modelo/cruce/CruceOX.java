package modelo.cruce;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import modelo.genes.Gen;
import utils.Pair;
import vista.ConfigPanel.Option;

public class CruceOX<T> implements Cruce<T> {

	@Override
	public Pair<List<Gen<T>>, List<Gen<T>>> cruzar(List<Gen<T>> crom1, List<Gen<T>> crom2) {
		
		List<Gen<T>> hijo1 = new ArrayList<>(crom1); Collections.fill(hijo1, null);
		List<Gen<T>> hijo2 = new ArrayList<>(crom2); Collections.fill(hijo2, null);
		
		Random rand = new Random();
		int pos1 = rand.nextInt(crom1.size()), pos2 = rand.nextInt(crom1.size());
		if (pos2 < pos1) {
			int aux = pos1; pos1 = pos2; pos2 = aux;
		}
		
		List<Gen<T>> seg1 = new ArrayList<Gen<T>>();
		List<Gen<T>> seg2 = new ArrayList<Gen<T>>();
		
		for(int i = pos1; i < pos2; ++i) {
			seg1.add(crom1.get(i));
			seg2.add(crom2.get(i));
		}
		
		for(int i = pos1; i < pos2; ++i) {
			hijo1.set(i, seg2.get(i - pos1));
			hijo2.set(i, seg1.get(i - pos1));
		}
		
		int i = (pos2 + 1) % crom1.size(),
			j = (pos2 + 1) % crom1.size(),
			count = pos2 - pos1;
		while (count < crom1.size()) {
			if (hijo1.get(j) != null) j = (j + 1) % crom1.size();
			else if (hijo1.contains(crom1.get(i))) i = (i + 1) % crom1.size();
			else {
				hijo1.set(j, crom1.get(i).clone());
				j = (j + 1) % crom1.size();
				i = (i + 1) % crom1.size();
				count++;
			}
		}
		
		i = (pos2 + 1) % crom1.size();
		j = (pos2 + 1) % crom1.size();
		count = pos2 - pos1;
		while (count < crom1.size()) {
			if (hijo2.get(j) != null) j = (j + 1) % crom1.size();
			else if (hijo2.contains(crom1.get(i))) i = (i + 1) % crom1.size();
			else {
				hijo2.set(j, crom1.get(i).clone());
				j = (j + 1) % crom1.size();
				i = (i + 1) % crom1.size();
				count++;
			}
		}
		
		return new Pair<>(hijo1, hijo2);
	}
	
	@Override
	public Cruce<T> clone() {
		return new CruceOX<>();
	}
	
	@Override
	public <O> List<Option<O>> getExtraOpts() {
		return null;
	}
	
	@Override
	public String toString() {
		return "OX";
	}
}
