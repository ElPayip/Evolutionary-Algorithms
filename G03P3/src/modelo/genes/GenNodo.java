package modelo.genes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public abstract class GenNodo<T> extends Gen<T> {
	
	GenNodo<T> padre;
	List<GenNodo<T>> hijos = new ArrayList<>();
	int terminal = 0;
	
	public GenNodo(GenNodo<T> padre, boolean terminal) {
		this.padre = padre;
		setRandomVal(terminal);
	}
	
	public GenNodo(GenNodo<T> padre) {
		setRandomVal();
	}

	@SuppressWarnings("unchecked")
	@Override
	protected T randomVal() {
		if (terminal > 0)
			return randomNodo(terminales());
		else if (terminal < 0)
			return randomNodo(noTerminales());
		else {
			List<T> aux = new ArrayList<>(Arrays.asList(noTerminales()));
			aux.addAll(Arrays.asList(terminales()));
			return randomNodo((T[]) aux.toArray());
		}
	}
	
	public void setRandomVal(boolean terminal) {
		if (terminal) this.terminal = 1;
		else  		  this.terminal = -1;
		setRandomVal();
	}
	
	private T randomNodo(T[] terms) {
		int i = new Random().nextInt(terms.length);
		return terms[i];
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
	
	public List<Gen<T>> getPreorder() {
		List<Gen<T>> preorder = new ArrayList<>();
		preorder.add(this);
		
		for (GenNodo<T> g : hijos)
			preorder.addAll(g.getPreorder());
		return preorder;
	}
	
	public void setHijos(List<GenNodo<T>> hijos) {
		this.hijos = hijos;
	}
	
	public List<GenNodo<T>> getHijos() {
		return hijos;
	}
	
	public void setPadre(GenNodo<T> padre) {
		this.padre = padre;
	}
	
	public GenNodo<T> getPadre() {
		return padre;
	}
	
	public boolean isTerminal() {
		return getAridad() == 0;
	}
	
	public abstract int getAridad();
	
	public abstract GenNodo<T> createInstance(GenNodo<T> padre);
	
	public abstract GenNodo<T> createInstance(GenNodo<T> padre, boolean terminal);
	
	abstract T[] terminales();

	abstract T[] noTerminales();
}
