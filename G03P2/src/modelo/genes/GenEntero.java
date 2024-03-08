package modelo.genes;

public class GenEntero extends Gen<Integer> {

	public GenEntero(Gen<Integer> gen) {
		super(gen);
	}

	public GenEntero(Integer val) {
		valor = val;
	}

	@Override
	protected Integer randomVal() {
		return null;
	}

	@Override
	public Gen<Integer> clone() {
		return new GenEntero(this);
	}
}
