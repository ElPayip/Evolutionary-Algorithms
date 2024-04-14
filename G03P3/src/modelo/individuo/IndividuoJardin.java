package modelo.individuo;

import java.util.List;

import modelo.genes.Accion;
import modelo.genes.Gen;
import modelo.genes.GenNodoJardin;

public class IndividuoJardin extends Individuo<Accion> {
	
	public IndividuoJardin() {
		super();
	}

	public IndividuoJardin(Individuo<Accion> otro) {
		super(otro);
	}

	public IndividuoJardin(List<Gen<Accion>> cromosoma) {
		super(cromosoma);
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
}