package modelo;

public abstract class Gen<T> {
	
	private T valor;
	private boolean[] alelos;
	private int size;
	
	public Gen(T inicial, int size) {
		valor = inicial;
		this.size = size;
	}
	
	public T getFenotipo() {
		return valor;
	}

	public boolean[] getGenotipo() {
		return alelos;
	}

	public int getSize() {
		return size;
	}
	
	
}
