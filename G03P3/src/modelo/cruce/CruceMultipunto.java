package modelo.cruce;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import modelo.genes.Gen;
import utils.Pair;
import vista.ConfigPanel.IntegerOption;
import vista.ConfigPanel.Option;

public class CruceMultipunto<T> implements Cruce<T> {
	
	private Integer nPos = 2;

	@Override
	public Pair<List<Gen<T>>, List<Gen<T>>> cruzar(List<Gen<T>> crom1, List<Gen<T>> crom2) {
		List<Gen<T>> hijo1 = new ArrayList<>(), 
				 	 hijo2 = new ArrayList<>();
	
		int len = crom1.size();
		Random rand = new Random();
		List<Integer> idx = new ArrayList<>();
		while (idx.size() < nPos) {
			int pos = rand.nextInt(1, len);
			while (idx.contains(pos)) pos = rand.nextInt(1, len);
			idx.add(pos);
		}
		idx.sort(null);
		
		int j = 0;
		for (int i = 0; i < crom1.size(); ++i) {
			if (j < nPos && i == idx.get(j))
				j++;
			
			if (j % 2 == 0) {
				hijo1.add(crom1.get(i).clone());
				hijo2.add(crom2.get(i).clone());
			}
			else {
				hijo1.add(crom2.get(i).clone());
				hijo2.add(crom1.get(i).clone());
			}
		}
		
		return new Pair<>(hijo1,hijo2);
	}

	@Override
	public <O> List<Option<O>> getExtraOpts() {
		List<Option<O>> extras = new ArrayList<>();
		extras.add(new IntegerOption<O>("posiciones", "posiciones", "nPos", 1, 100));
		return extras;
	}
	
	@Override
	public Cruce<T> clone() {
		return new CruceMultipunto<>();
	}

	@Override
	public String toString() {
		return "Cruce multipunto";
	}

	public Integer getnPos() {
		return nPos;
	}

	public void setnPos(Integer nPos) {
		this.nPos = nPos;
	}
}
