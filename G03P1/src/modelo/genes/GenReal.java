package modelo.genes;

import java.util.Random;

public class GenReal extends Gen<Double> {
	
	public GenReal(GenReal gen) {
		super(gen);
	}

	public GenReal(Double min, Double max) {
		super(min, max, 0.0);
	}

	@Override
	protected Double randomVal() {
		return min + (max - min) * new Random().nextDouble();
	}

	@Override
	public Gen<Double> clone() {
		return new GenReal(this);
	}
}
