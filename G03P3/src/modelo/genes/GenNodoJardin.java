package modelo.genes;

import java.util.Random;

public class GenNodoJardin extends GenNodo<Accion> {
	
	public GenNodoJardin(GenNodoJardin padre, boolean terminal) {
		super(padre, terminal);
	}
	
	public GenNodoJardin(GenNodoJardin padre) {
		super(padre);
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

	@Override
	public int getAridad() {
		return valor.getAridad();
	}

	@Override
	GenNodo<Accion> createInstance(GenNodo<Accion> padre) {
		return new GenNodoJardin((GenNodoJardin) padre);
	}
}
