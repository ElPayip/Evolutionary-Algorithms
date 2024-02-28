package modelo.cruce;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import modelo.genes.Gen;
import utils.Pair;
import vista.ConfigPanel.Option;

public class CruceUniforme<T> implements Cruce<T> {

	@Override
	public Pair<List<? extends Gen<T>>, List<? extends Gen<T>>> cruzar(List<? extends Gen<T>> crom1,
			List<? extends Gen<T>> crom2) {
		
		List<Gen<T>> hijo1 = new ArrayList<>(), 
					 hijo2 = new ArrayList<>();
		
		Random r = new Random();
		
		for(int i = 0; i < crom1.size(); ++i) {
			if(r.nextBoolean()) {
				hijo1.add(crom2.get(i).clone());
				hijo2.add(crom1.get(i).clone());
			} 
			else {
				hijo1.add(crom1.get(i).clone());
				hijo2.add(crom2.get(i).clone());
			}
		}
		
		return new Pair<>(hijo1, hijo2);
	}
	
	@Override
	public Cruce<T> clone() {
		return new CruceUniforme<>();
	}

	@Override
	public String toString() {
		return "Cruce uniforme";
	}

	@Override
	public <O> List<Option<O>> getExtraOpts() {
		return null;
	}
}
