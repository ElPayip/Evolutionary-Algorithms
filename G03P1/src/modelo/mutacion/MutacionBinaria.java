package modelo.mutacion;

import java.util.List;
import java.util.Random;

import modelo.genes.Gen;
import modelo.genes.GenBinario;

public class MutacionBinaria<T> implements Mutacion<T> {

	@SuppressWarnings("unchecked")
	@Override
	public void mutar(List<? extends Gen<T>> crom) {
		try {
			int size = GenBinario.getCromSize((List<GenBinario<T>>) crom);
			int idx = new Random().nextInt(size);
			
			GenBinario.bitFlip((List<GenBinario<T>>) crom, idx);
		}
		catch (ClassCastException e) {
			throw new IllegalArgumentException("MutacionBinaria debe aplicarse sobre GenBinario");
		}
	}
}
