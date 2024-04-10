package modelo.mutacion;

import java.util.List;
import java.util.Random;

import modelo.genes.Gen;
import vista.ConfigPanel.Option;

public class MutacionIntercambio<T> implements Mutacion<T> {

	@Override
	public void mutar(List<Gen<T>> crom) {
		Random rand = new Random();
		int pos1 = rand.nextInt(crom.size()),
			pos2 = rand.nextInt(crom.size()-1);
		if (pos2 >= pos1) pos2++;
		
		Gen<T> aux = crom.get(pos1);
		crom.set(pos1, crom.get(pos2));
		crom.set(pos2, aux);
	}

	@Override
	public <O> List<Option<O>> getExtraOpts() {
		return null;
	}

	public Mutacion<T> clone(){
		return new MutacionIntercambio<>();
	}

	@Override
	public String toString() {
		return "Intercambio";
	}
}
