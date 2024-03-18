package modelo.cruce;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import modelo.genes.Gen;
import utils.Pair;
import vista.ConfigPanel.Option;

public class CruceERX<T> implements Cruce<T> { //Cruce de recombinación por rutas

	@Override
	public Pair<List<Gen<T>>, List<Gen<T>>> cruzar(List<Gen<T>> crom1, List<Gen<T>> crom2) {
		List<Gen<T>> hijo1 = new ArrayList<>(crom1); Collections.fill(hijo1, null);
		List<Gen<T>> hijo2 = new ArrayList<>(crom2); Collections.fill(hijo2, null);
		
		Map<Gen<T>, List<Gen<T>>> conectividades = new HashMap<Gen<T>, List<Gen<T>>>();
		
		for(int i = 0; i < crom1.size(); ++i) {
			conectividades.put(crom1.get(i), null);
			conectividades.get(crom1.get(i)).add(crom1.get(i - 1 %crom1.size()));
			conectividades.get(crom1.get(i)).add(crom1.get(i + 1 %crom1.size()));
			conectividades.get(crom1.get(i)).add(crom2.get(i - 1 %crom2.size()));
			conectividades.get(crom1.get(i)).add(crom2.get(i + 1 %crom2.size()));
		}
		
		recombinar(hijo1, crom2, conectividades);
		recombinar(hijo2, crom1, conectividades);
		
		return new Pair<>(hijo1, hijo2);
	}
	
	private void recombinar(List<Gen<T>> hijo, List<Gen<T>> crom, Map<Gen<T>, List<Gen<T>>> conectividades) {
		int i = 1;
		hijo.add(crom.get(0).clone());
		while(i < crom.size()) {
			Gen<T> seleccionado = null;
			
			for(int j = 0; j < conectividades.get(hijo.get(i)).size(); ++j) {
				if(conectividades.get(hijo.get(i)).size() < conectividades.get(seleccionado).size() && !hijo.contains(conectividades.get(hijo.get(i)).get(j))){
					seleccionado = conectividades.get(hijo.get(i)).get(j);
				}
			}
			hijo.add(seleccionado);
			++i;
		}
	}
	
	@Override
	public Cruce<T> clone() {
		return new CruceERX<>();
	}
	
	@Override
	public <O> List<Option<O>> getExtraOpts() {
		return null;
	}
	
	@Override
	public String toString() {
		return "ERX";
	}
}
