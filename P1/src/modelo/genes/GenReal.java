package modelo.genes;

import java.util.Random;

public class GenReal extends Gen<Double> {
	
	private Random rand = new Random();
	
	public GenReal(GenReal gen) {
		super(gen);
	}

	public GenReal(Double min, Double max) {
		super(min, max, 0.0);
	}

	@Override
	protected Double randomVal() {
		return min + (max - min) * rand.nextDouble();
	}

	@Override
	protected Gen<Double> clonar() {
		return new GenReal(this);
	}

}
