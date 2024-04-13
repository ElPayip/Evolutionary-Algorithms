package modelo.individuo;

import java.util.List;

import modelo.genes.Accion;
import modelo.genes.Gen;
import modelo.genes.GenNodoJardin;
import modelo.inicializaciones.Inicializacion;

public class IndividuoArbol extends Individuo<Accion> {
	
	public IndividuoArbol() {
		super();
	}

	public IndividuoArbol(Individuo<Accion> otro) {
		super(otro);
	}

	public IndividuoArbol(List<Gen<Accion>> cromosoma) {
		super(cromosoma);
	}

	public IndividuoArbol(int profMax, Inicializacion<Accion> init) {
		cromosoma = init.init(profMax);
	}

	@Override
	protected Gen<Accion> generarGen(Accion min, Accion max, Accion prec) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Individuo<Accion> createInstance(List<Gen<Accion>> crom) {
		return new IndividuoArbol(crom);
	}

	@Override
	public Individuo<Accion> clone() {
		return new IndividuoArbol(this);
	}
	
	public GenNodoJardin getRaiz() {
		return (GenNodoJardin) cromosoma.get(0);
	}
}
