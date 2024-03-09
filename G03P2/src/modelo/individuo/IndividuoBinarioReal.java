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

	@Override
	public Individuo<Double> createInstance(List<Gen<Double>> crom) {
		return new IndividuoBinarioReal(crom);
	}

	@Override
	public Individuo<Double> clone() {
		return new IndividuoBinarioReal(this);
	}
}
