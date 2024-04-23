package modelo.individuo;

import java.util.List;

import modelo.genes.Gen;
import modelo.genes.GenEntero;

public class IndividuoEntero extends Individuo<Integer> {

	public IndividuoEntero() {
	}

	public IndividuoEntero(IndividuoEntero otro) {
		super(otro);
	}

	public IndividuoEntero(Integer[] mins, Integer[] maxs, Integer prec) {
		super(mins, maxs, prec);
	}

	public IndividuoEntero(List<Gen<Integer>> cromosoma) {
		super(cromosoma);
	}

	@Override
	protected Gen<Integer> generarGen(Integer min, Integer max, Integer prec) {
		return new GenEntero(min, max, prec);
	}

	@Override
	public Individuo<Integer> createInstance(List<Gen<Integer>> crom) {
		return new IndividuoEntero(crom);
	}

	@Override
	public Individuo<Integer> clone() {
		return new IndividuoEntero(this);
	}
}
