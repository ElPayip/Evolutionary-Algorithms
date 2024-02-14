package modelo.genes;

public class Gen<T> {
	
	protected T valor;
	protected T min, max;
	protected T precision;
	
	public Gen(T inicial, T min, T max, T precision) {
		valor = inicial;
		this.min = min;
		this.max = max;
		this.precision = precision;
	}
	
	public T getFenotipo() {
		return valor;
	}
}
