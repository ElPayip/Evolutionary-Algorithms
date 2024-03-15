package modelo.genes;

public abstract class Gen<T> implements Cloneable {
	
	protected T valor;
	protected T min, max;
	protected T precision;
	
	public Gen() {
	}
	
	public Gen(Gen<T> gen) {
		valor = gen.valor;
		min = gen.min;
		max = gen.max;
		precision = gen.precision;
	}
	
	public Gen(T min, T max, T precision) {
		this.min = min;
		this.max = max;
		this.precision = precision;
		setRandomVal();
	}
	
	public T getValor() {
		return valor;
	}
	
	public void setValor(T val) {
		valor = val;
	}
	
	public void setRandomVal() {
		valor = randomVal();
	}
	
	@Override
	public String toString() {
		return "Gen: " + valor.toString();
	}
	
	protected abstract T randomVal();
	
	public abstract Gen<T> clone();
}
