package modelo.mutacion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import modelo.genes.Gen;
import vista.ConfigPanel.IntegerOption;
import vista.ConfigPanel.Option;

public class MutacionShuffle<T> implements Mutacion<T> {
	
	private Integer nPos = 2;

	@Override
	public void mutar(List<Gen<T>> crom) {
		Random rand = new Random();
		List<Integer> idx = new ArrayList<>();
		for (int i = 0; i < nPos; ++i) {
			int ind = rand.nextInt(nPos);
			while (idx.contains(ind)) ind = rand.nextInt(nPos);
			idx.add(ind);
		}
		
		List<Gen<T>> mezcla = new ArrayList<>(crom), nuevo = new ArrayList<>(crom);
		Collections.shuffle(mezcla);
		Collections.fill(nuevo, null);
		
		for (Integer i : idx)
			nuevo.set(i, mezcla.get(i));
		
		int i = 0, j = 0;
		while (i < crom.size() && j < crom.size()) {
			if (nuevo.get(i) != null) i++;
			else if (nuevo.contains(crom.get(j))) j++;
			else {
				nuevo.set(i, crom.get(j));
				i++;
				j++;
			}
		}
		
		crom.clear();
		crom.addAll(nuevo);
	}

	@Override
	public <O> List<Option<O>> getExtraOpts() {
		List<Option<O>> extras = new ArrayList<>();
		extras.add(new IntegerOption<O>("posiciones", "posiciones", "nPos", 1, 100));
		return extras;
	}

	public Mutacion<T> clone(){
		return new MutacionShuffle<>();
	}

	@Override
	public String toString() {
		return "Shuffle (nueva)";
	}

	public Integer getnPos() {
		return nPos;
	}

	public void setnPos(Integer nPos) {
		this.nPos = nPos;
	}
}