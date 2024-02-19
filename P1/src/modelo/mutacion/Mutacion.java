package modelo.mutacion;

import java.util.List;

import modelo.genes.Gen;

public interface Mutacion<T> {

	public void mutar(List<? extends Gen<T>> crom);
}
