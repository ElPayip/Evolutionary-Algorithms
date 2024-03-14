package modelo.cruce;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import modelo.genes.Gen;
import utils.Pair;
import vista.ConfigPanel.Option;

public class CruceRecombinacionRutas<T> implements Cruce<T> {

	@Override
	public Pair<List<Gen<T>>, List<Gen<T>>> cruzar(List<Gen<T>> crom1, List<Gen<T>> crom2) {
		List<Gen<T>> hijo1 = new ArrayList<>(crom1); Collections.fill(hijo1, null);
		List<Gen<T>> hijo2 = new ArrayList<>(crom2); Collections.fill(hijo2, null);
		
		Map<Gen<T>, Set<Gen<T>>> conectividades = new HashMap<Gen<T>, Set<Gen<T>>>();
		
		for(int i = 0; i < crom1.size(); ++i) {
			conectividades.put(crom1.get(i).clone(), null);
			conectividades.get(crom1.get(i).clone()).add(crom1.get(i - 1 %crom1.size()));
			conectividades.get(crom1.get(i).clone()).add(crom1.get(i + 1 %crom1.size()));
			conectividades.get(crom1.get(i).clone()).add(crom2.get(i - 1 %crom2.size()));
			conectividades.get(crom1.get(i).clone()).add(crom2.get(i + 1 %crom2.size()));
		}
		
		return new Pair<>(hijo1, hijo2);
	}
	
	@Override
	public Cruce<T> clone() {
		return new CruceRecombinacionRutas<>();
	}
	
	@Override
	public <O> List<Option<O>> getExtraOpts() {
		return null;
	}
	
	@Override
	public String toString() {
		return "Recombinación de Rutas";
	}
}
