package modelo;

import java.util.ArrayList;
import java.util.List;

import modelo.cruce.Cruce;
import modelo.genes.Gen;
import modelo.genes.GenBinario;
import utils.Pair;

public class IndividuoBinario<T> extends Individuo<T> {

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

	protected List<Boolean> getCromosomaBinario() {
		List<Boolean> alelos = new ArrayList<>();
		for (Gen<T> g : cromosoma) {
			alelos.addAll(((GenBinario<T>) g).getGenotipo());
		}
		return alelos;
	}

	@Override
	public T getRandomValue(T min, T max, T precision) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pair<Individuo<T>, Individuo<T>> cruzar(Individuo<T> otro, Cruce<? extends Gen<T>> cruce) {
		// TODO Auto-generated method stub
		return null;
	}
}
