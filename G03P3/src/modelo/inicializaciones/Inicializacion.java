package modelo.inicializaciones;

import java.util.List;

import modelo.individuo.Individuo;

public interface Inicializacion<T> {

	public List<Individuo<T>> init(int n);
}
