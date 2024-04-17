package modelo.genes;

public class GenNodoJardin extends GenNodo<Accion> {
	
	public GenNodoJardin(GenNodoJardin padre, boolean terminal) {
		super(padre, terminal);
	}
	
	public GenNodoJardin(GenNodoJardin padre) {
		super(padre);
	}

	@Override
	public int getAridad() {
		return valor.getAridad();
	}

	@Override
	public GenNodo<Accion> createInstance(GenNodo<Accion> padre) {
		return new GenNodoJardin((GenNodoJardin) padre);
	}

	@Override
	public GenNodo<Accion> createInstance(GenNodo<Accion> padre, boolean terminal) {
		return new GenNodoJardin((GenNodoJardin) padre, terminal);
	}

	@Override
	Accion[] terminales() {
		return Accion.terminales();
	}

	@Override
	Accion[] noTerminales() {
		return Accion.noTerminales();
	}
	
	@Override
	public String toString() {
		String str = "(";
		str += valor.toString();
		for (GenNodo<Accion> g : hijos)
			str += " " + g.toString();
		str += ")";
		return str;
	}
}
