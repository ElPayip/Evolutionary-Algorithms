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
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		return valor.equals(((GenEntero) obj).valor);
	}
	
	public boolean equals(GenEntero obj) {
		return valor.equals(obj.valor);
	}
}
