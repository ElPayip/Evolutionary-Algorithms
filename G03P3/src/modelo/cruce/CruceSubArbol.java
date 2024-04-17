package modelo.cruce;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import modelo.genes.Gen;
import modelo.genes.GenNodo;
import utils.Pair;
import vista.ConfigPanel.Option;

public class CruceSubArbol<T> implements Cruce<T> {

	@Override
	public Pair<List<Gen<T>>, List<Gen<T>>> cruzar(List<Gen<T>> crom1, List<Gen<T>> crom2) {
		
		List<Gen<T>> hijo1 = new ArrayList<>(crom1); Collections.fill(hijo1, null);
		List<Gen<T>> hijo2 = new ArrayList<>(crom2); Collections.fill(hijo2, null);
		
		Random rand = new Random();
		int pos1 = rand.nextInt(hijo1.size()), pos2 = rand.nextInt(hijo2.size());
		
		GenNodo<T> inter1 = (GenNodo<T>) hijo1.get(pos1), inter2 = (GenNodo<T>) hijo2.get(pos2);
		
		hijo1.removeAll(inter1.getPreorder());
		hijo2.removeAll(inter2.getPreorder());
		
		hijo1.addAll(pos1, inter2.getPreorder());
		hijo2.addAll(pos2, inter1.getPreorder());
		
		return new Pair<>(hijo1, hijo2);
	}
	
	@Override
	public <O> List<Option<O>> getExtraOpts() {
		return null;
	}
	
	@Override
	public Cruce<T> clone() {
		return new CruceSubArbol<>();
	}
	
	@Override
	public String toString() {
		return "Cruce de subárboles";
	}
}
