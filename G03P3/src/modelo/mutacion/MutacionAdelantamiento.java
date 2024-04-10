package modelo.mutacion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import modelo.genes.Gen;
import vista.ConfigPanel.IntegerOption;
import vista.ConfigPanel.Option;

public class MutacionAdelantamiento<T> implements Mutacion<T> {
	
	private Integer nPos = 3;

	@Override
	public void mutar(List<Gen<T>> crom) {
		Random rand = new Random();
		List<Integer> idx = new ArrayList<>();
		for (int i = 0; i < nPos; ++i) {
			int ind = rand.nextInt(nPos);
			while (idx.contains(ind)) ind = rand.nextInt(nPos);
			idx.add(ind);
		}
		
		for (Integer i : idx) {
			Gen<T> aux = crom.get(i);
			crom.set(i, crom.get((i+1)%crom.size()));
			crom.set((i+1)%crom.size(), aux);
		}
	}

	@Override
	public <O> List<Option<O>> getExtraOpts() {
		List<Option<O>> extras = new ArrayList<>();
		extras.add(new IntegerOption<O>("posiciones", "posiciones", "nPos", 1, 100));
		return extras;
	}

	public Mutacion<T> clone(){
		return new MutacionAdelantamiento<>();
	}

	@Override
	public String toString() {
		return "Adelantamiento (nueva)";
	}

	public Integer getnPos() {
		return nPos;
	}

	public void setnPos(Integer nPos) {
		this.nPos = nPos;
	}
}
