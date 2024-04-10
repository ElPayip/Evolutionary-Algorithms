package modelo.genes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GenNodo extends Gen<Accion> {
	
	private GenNodo padre;
	private List<GenNodo> hijos;
	private int terminal = 0;
	
	public GenNodo(GenNodo padre, boolean terminal) {
		this.padre = padre;
		if (terminal)
			this.terminal = 1;
		else 
			this.terminal = -1;
		setRandomVal();
	}
	
	public GenNodo(GenNodo padre) {
		setRandomVal();
	}

	@Override
	protected Accion randomVal() {
		if (terminal > 0)
			return randomTerminal();
		else if (terminal < 0)
			return randomNoTerminal();
		else 
			return randomNodo(Accion.values());
	}

	@Override
	public Gen<Accion> clone() {
		GenNodo nuevo = new GenNodo(padre);
		nuevo.valor = valor;
		nuevo.terminal = terminal;
		nuevo.hijos = new ArrayList<>();
		for (GenNodo g : hijos)
			nuevo.hijos.add((GenNodo) g.clone());
		return nuevo;
	}
	
	private Accion randomTerminal() {
		return randomNodo(Accion.terminales());
	}
	
	private Accion randomNoTerminal() {
		return randomNodo(Accion.noTerminales());
	}
	
	private Accion randomNodo(Accion[] terms) {
		int i = new Random().nextInt(terms.length);
		return terms[i];
	}
}
