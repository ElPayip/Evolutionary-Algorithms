package modelo.individuo;

import java.util.List;

import modelo.genes.Gen;

public class IndividuoBinarioReal<T> extends Individuo<T> {

	public IndividuoBinarioReal(T[] mins, T[] maxs, T prec) {
		super(mins, maxs, prec);
	}

	@Override
	protected Gen<T> generarGen(T min, T max, T prec) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Individuo<T> createInstance(List<? extends Gen<T>> crom) {
		// TODO Auto-generated method stub
		return null;
	}
}
