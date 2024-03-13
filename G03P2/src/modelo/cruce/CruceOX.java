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
		
		return null;
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
