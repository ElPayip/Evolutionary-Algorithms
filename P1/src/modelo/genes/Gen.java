package modelo.genes;

public abstract class Gen<T> {
	
	protected T valor;
	protected T min, max;
	protected T precision;
	
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
	
	protected abstract T randomVal();
	
	protected abstract Gen<T> clonar();
}
