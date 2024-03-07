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

	@SuppressWarnings("unchecked")
	@Override
	protected Individuo<Double> createInstance(List<? extends Gen<Double>> crom) {
		try {
			return new IndividuoReal((List<Gen<Double>>) crom);
		} catch (ClassCastException e) {
			throw new IllegalArgumentException("Para createInstance de IndividuoReal son necesarios GenReal");
		}
	}

	@Override
	public Individuo<Double> clone() {
		return new IndividuoReal(this);
	}
}
