package modelo.cruce;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import modelo.genes.Gen;
import utils.Pair;
import vista.ConfigPanel.IntegerOption;
import vista.ConfigPanel.Option;

public class CruceOXPP<T> implements Cruce<T> {
	
	private Integer nPos;
	
	public CruceOXPP() {
		nPos = 2;
	}

	public CruceOXPP(Integer nPos) {
		this.nPos = nPos;
	}

	@Override
	public Pair<List<Gen<T>>, List<Gen<T>>> cruzar(List<Gen<T>> crom1, List<Gen<T>> crom2) {
		List<Gen<T>> hijo1 = new ArrayList<>(crom1); Collections.fill(hijo1, null);
		List<Gen<T>> hijo2 = new ArrayList<>(crom2); Collections.fill(hijo2, null);
		
		Random rand = new Random();
		int maxPos = -1;
		for (int i = 0; i < nPos; ++i) {
			int pos = rand.nextInt(crom1.size());
			while (hijo1.get(pos) != null) pos = rand.nextInt(crom1.size());
			
			hijo1.set(pos, crom2.get(pos).clone());
			hijo2.set(pos, crom1.get(pos).clone());
			if (pos > maxPos) maxPos = pos;
		}
		
		int i = (maxPos + 1) % crom1.size(),
			j = (maxPos + 1) % crom1.size();
		while (i != maxPos && j != maxPos) {
			if (hijo1.get(j) != null) j = (j + 1) / crom1.size();
			else if (hijo1.contains(crom1.get(i))) i = (i + 1) / crom1.size();
			else hijo1.set(j, crom1.get(i).clone());
		}

		i = (maxPos + 1) % crom1.size();
		j = (maxPos + 1) % crom1.size();
		while (i != maxPos && j != maxPos) {
			if (hijo2.get(j) != null) j = (j + 1) / crom1.size();
			else if (hijo2.contains(crom1.get(i))) i = (i + 1) / crom1.size();
			else hijo2.set(j, crom1.get(i).clone());
		}
		
		return new Pair<>(hijo1, hijo2);
	}
	
	@Override
	public Cruce<T> clone() {
		return new CruceOXPP<>(nPos);
	}

	@Override
	public <O> List<Option<O>> getExtraOpts() {
		List<Option<O>> extras = new ArrayList<>();
		extras.add(new IntegerOption<O>("posiciones", "posiciones", "nPos", 1, 100));
		return extras;
	}

	@Override
	public String toString() {
		return "OXPP";
	}

	public Integer getnPos() {
		return nPos;
	}

	public void setnPos(Integer nPos) {
		this.nPos = nPos;
	}
}
