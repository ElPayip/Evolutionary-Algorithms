package modelo.inicializaciones;

import java.util.List;

import modelo.genes.Gen;

public interface Inicializacion<T> {

	public List<Gen<T>> init(int profundidad);
}
