package modelo.mutacion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import modelo.genes.Gen;
import vista.ConfigPanel.Option;

public class MutacionInversion<T> implements Mutacion<T> {

	@Override
	public void mutar(List<Gen<T>> crom) {
		
		Random rand = new Random();
		int pos1 = rand.nextInt(crom.size()), pos2 = rand.nextInt(crom.size());
		if (pos2 < pos1) {
			int aux = pos1; pos1 = pos2; pos2 = aux;
		}
		
		List<Gen<T>> seg = new ArrayList<Gen<T>>();
		
		for(int i = pos1; i < pos2; ++i) {
			seg.add(crom.get(i));
		}
		Collections.reverse(seg);
		
		for(int i = pos1; i < pos2; ++i) {
			crom.set(i, seg.get(i - pos1));
		}
	}
	
	@Override
	public <O> List<Option<O>> getExtraOpts() {
		return null;
	}
	
	public Mutacion<T> clone(){
		return new MutacionInversion<>();
	}

	@Override
	public String toString() {
		return "Inversion";
	}

}
