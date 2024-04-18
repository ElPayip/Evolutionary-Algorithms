package modelo.individuo;

import java.util.List;

import modelo.fitness.Accion;
import modelo.genes.Gen;
import modelo.genes.GenNodo;
import modelo.genes.GenNodoJardin;

public class IndividuoJardin extends Individuo<Accion> {
	
	public IndividuoJardin() {
		super();
	}

	public IndividuoJardin(Individuo<Accion> otro) {
		super(otro);
	}

	public IndividuoJardin(List<Gen<Accion>> cromosoma) {
		this.cromosoma = ((GenNodo<Accion>) cromosoma.get(0).clone()).getPreorder();
	}

	@Override
	protected Gen<Accion> generarGen(Accion min, Accion max, Accion prec) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Individuo<Accion> createInstance(List<Gen<Accion>> crom) {
		return new IndividuoJardin(crom);
	}

	@Override
	public Individuo<Accion> clone() {
		return new IndividuoJardin(this);
	}
	
	public GenNodoJardin getRaiz() {
		return (GenNodoJardin) cromosoma.get(0);
	}
	
	@Override
	public String toString() {
		return cromosoma.get(0).toString();
	}
}
