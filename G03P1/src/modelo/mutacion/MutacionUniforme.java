package modelo.mutacion;

import java.util.List;
import java.util.Random;

import modelo.genes.Gen;

public class MutacionUniforme implements Mutacion<Double> {

	@Override
	public void mutar(List<? extends Gen<Double>> crom) {
		int idx = new Random().nextInt(crom.size());
		crom.get(idx).setRandomVal();
	}
}
