package modelo.cruce;

import java.util.List;

import modelo.Individuo;

public interface Cruce<T> {
    public void cruzar (List<Individuo<T>> individuos);
}
