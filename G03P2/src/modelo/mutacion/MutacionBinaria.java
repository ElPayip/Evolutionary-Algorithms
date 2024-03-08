package modelo.mutacion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import modelo.genes.Gen;
import modelo.genes.GenBinario;
import vista.ConfigPanel.Option;

public class MutacionBinaria<T> implements Mutacion<T> {

	@Override
	public void mutar(List<Gen<T>> crom) { //TODO probar
		List<GenBinario<T>> aux = new ArrayList<>();
		for (Gen<T> g : crom) aux.add((GenBinario<T>) g);
		
		int size = GenBinario.getCromSize(aux);
		int idx = new Random().nextInt(size);
		
		GenBinario.bitFlip(aux, idx);
	}

	@Override
	public <O> List<Option<O>> getExtraOpts() {
		return null;
	}

	public Mutacion<T> clone(){
		return new MutacionBinaria<>();
	}
}
