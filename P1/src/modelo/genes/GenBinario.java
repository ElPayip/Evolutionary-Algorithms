package modelo.genes;

import java.util.List;

public abstract class GenBinario<T> extends Gen<T> {
	
	private List<Integer> alelos;
	
	public GenBinario(T inicial, T min, T max, T precision) {
		super(inicial, min, max, precision);
	}

	public List<Integer> getGenotipo() {
		return alelos;
	}

	public abstract int getSize();
}
