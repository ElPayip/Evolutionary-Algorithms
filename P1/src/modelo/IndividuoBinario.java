package modelo;

import java.util.ArrayList;
import java.util.List;

import modelo.genes.Gen;
import modelo.genes.GenBinario;

public abstract class IndividuoBinario<T> extends Individuo<T> {

	public IndividuoBinario(T[] mins, T[] maxs, T[] precs) {
		super(mins, maxs, precs);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<T> getValores() {
		List<T> valores = new ArrayList<>();
		for (Gen<T> g : cromosoma)
			valores.add(g.getFenotipo());
		return null;
	}

	@Override
	public void muta() {
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings("unchecked")
	protected List<Boolean> getCromosomaBinario() {
		List<Boolean> alelos = new ArrayList<>();
		for (Gen<T> g : cromosoma) {
			alelos.addAll(((GenBinario<Boolean>) g).getGenotipo());
		}
		return alelos;
	}
}
