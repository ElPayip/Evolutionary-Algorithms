package modelo.genes;

import java.util.ArrayList;
import java.util.List;

public abstract class GenNodo<T> extends Gen<T> {
	
	GenNodo<T> padre;
	List<GenNodo<T>> hijos;
	int terminal = 0;
	
	public GenNodo(GenNodo<T> padre, boolean terminal) {
		this.padre = padre;
		if (terminal)
			this.terminal = 1;
		else 
			this.terminal = -1;
		setRandomVal();
	}
	
	public GenNodo(GenNodo<T> padre) {
		setRandomVal();
	}

	@Override
	public Gen<T> clone() {
		GenNodo<T> nuevo = createInstance(padre);
		nuevo.valor = valor;
		nuevo.terminal = terminal;
		nuevo.hijos = new ArrayList<>();
		for (GenNodo<T> g : hijos)
			nuevo.hijos.add((GenNodo<T>) g.clone());
		return nuevo;
	}
	
	public void setHijos(List<GenNodo<T>> hijos) {
		this.hijos = hijos;
	}
	
	public List<GenNodo<T>> getHijos() {
		return hijos;
	}
	
	public boolean isTerminal() {
		return getAridad() == 0;
	}
	
	public abstract int getAridad();
	
	abstract GenNodo<T> createInstance(GenNodo<T> padre);
}
