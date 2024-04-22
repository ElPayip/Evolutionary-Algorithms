package modelo.genes;

import java.util.Random;

public class GenEntero extends Gen<Integer> {
	
	public GenEntero() {
	}

	public GenEntero(Gen<Integer> gen) {
		super(gen);
	}

	public GenEntero(Integer min, Integer max, Integer precision) {
		super(min, max, precision);
	}

	@Override
	protected Integer randomVal() {
		return new Random().nextInt(min, min + (max-min)/precision) * precision;
	}

	@Override
	public Gen<Integer> clone() {
		return new GenEntero(this);
	}

}
