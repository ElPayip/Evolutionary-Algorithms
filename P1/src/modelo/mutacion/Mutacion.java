package modelo.mutacion;

import java.util.List;

import modelo.genes.Gen;

public interface Mutacion {

	public <T> void mutar(List<? extends Gen<T>> crom);
}
