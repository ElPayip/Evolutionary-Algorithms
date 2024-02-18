package modelo.individuo;

import java.util.ArrayList;
import java.util.List;

import modelo.cruce.Cruce;
import modelo.genes.Gen;
import modelo.genes.GenBinario;
import modelo.mutacion.Mutacion;
import utils.Pair;

public class IndividuoBinario<T> extends Individuo<T,Integer> {

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

	protected List<Integer> getCromosomaBinario() {
		List<Integer> alelos = new ArrayList<>();
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
	public Pair<Individuo<T,Integer>, Individuo<T,Integer>> cruzar(Individuo<T,Integer> otro, Cruce<Integer> cruce) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void muta(Mutacion<Integer> mutacion) {
		// TODO Auto-generated method stub
		
	}
}
