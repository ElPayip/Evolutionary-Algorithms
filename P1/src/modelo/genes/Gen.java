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
		valor = initRandomVal();
	}
	
	public T getValor() {
		return valor;
	}
	
	public void setValor(T val) {
		valor = val;
	}
	
	protected abstract T initRandomVal();
	
	protected abstract Gen<T> clonar();
}
