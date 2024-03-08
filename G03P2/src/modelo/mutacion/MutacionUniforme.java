package modelo.mutacion;

import java.util.List;
import java.util.Random;

import modelo.genes.Gen;
import vista.ConfigPanel.Option;

public class MutacionUniforme implements Mutacion<Double> {

	@Override
	public void mutar(List<Gen<Double>> crom) {
		int idx = new Random().nextInt(crom.size());
		crom.get(idx).setRandomVal();
	}

	@Override
	public <O> List<Option<O>> getExtraOpts() {
		return null;
	}

	public Mutacion<Double> clone(){
		return new MutacionUniforme();
	}
}
