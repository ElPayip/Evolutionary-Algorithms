package modelo.mutacion;

import java.util.List;
import java.util.Random;

import modelo.genes.Gen;
import vista.ConfigPanel.Option;

public class MutacionUniforme<T> implements Mutacion<T> {

	@Override
	public void mutar(List<Gen<T>> crom) {
		int idx = new Random().nextInt(crom.size());
		crom.get(idx).setRandomVal();
	}

	@Override
	public <O> List<Option<O>> getExtraOpts() {
		return null;
	}

	public Mutacion<T> clone(){
		return new MutacionUniforme<T>();
	}

	@Override
	public String toString() {
		return "Uniforme";
	}
}
