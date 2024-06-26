package modelo.individuo;

import java.util.List;

import modelo.genes.Gen;
import modelo.genes.GenReal;

public class IndividuoReal extends Individuo<Double> {
	
	public IndividuoReal(IndividuoReal otro) {
		super(otro);
	}

	public IndividuoReal(List<Gen<Double>> cromosoma) {
		super(cromosoma);
	}

	public IndividuoReal(Double[] mins, Double[] maxs) {
		super(mins, maxs, 0.0);
	}

	@Override
	protected Gen<Double> generarGen(Double min, Double max, Double prec) {
		return new GenReal(min, max);
	}

	@Override
	public Individuo<Double> createInstance(List<Gen<Double>> crom) {
		return new IndividuoReal(crom);
	}

	@Override
	public Individuo<Double> clone() {
		return new IndividuoReal(this);
	}
}
