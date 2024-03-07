package modelo.individuo;

import java.util.List;

import modelo.genes.Gen;
import modelo.genes.GenBinReal;

public class IndividuoBinarioReal extends Individuo<Double> {
	
	public IndividuoBinarioReal(IndividuoBinarioReal otro) {
		super(otro);
	}

	public IndividuoBinarioReal(List<Gen<Double>> cromosoma) {
		super(cromosoma);
	}

	public IndividuoBinarioReal(Double[] mins, Double[] maxs, Double prec) {
		super(mins, maxs, prec);
	}

	@Override
	protected Gen<Double> generarGen(Double min, Double max, Double prec) {
		return new GenBinReal(min, max, prec);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Individuo<Double> createInstance(List<? extends Gen<Double>> crom) {
		try {
			return new IndividuoBinarioReal((List<Gen<Double>>) crom);
		} catch (ClassCastException e) {
			throw new IllegalArgumentException("Para createInstance de IndividuoBinarioReal son necesarios GenBinReal");
		}
	}

	@Override
	public Individuo<Double> clone() {
		return new IndividuoBinarioReal(this);
	}
}
