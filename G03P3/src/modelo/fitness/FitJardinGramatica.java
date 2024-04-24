package modelo.fitness;

import java.util.ArrayList;
import java.util.List;

import modelo.Cortacesped.Casilla;
import modelo.genes.Gen;
import modelo.genes.GenNodo;
import modelo.genes.GenNodoJardin;
import modelo.individuo.Individuo;
import modelo.individuo.IndividuoJardin;

public class FitJardinGramatica implements Fitness<Integer> {
	
	private static int pos;
	private FitJardin fit;
	private int maxWraps, nFilas, nCols;
	private boolean extras = false;

	public FitJardinGramatica(List<List<Casilla>> jardin, int maxPasos, int maxWraps) {
		fit = new FitJardin(jardin, maxPasos);
		this.maxWraps = maxWraps;
		nFilas = jardin.size();
		nCols = jardin.get(0).size();
	}

	public Individuo<Accion> fromCodones(List<Integer> codones) {
		pos = 0;
		return new IndividuoJardin(noTerm(codones, null));
	}

	@Override
	public double eval(Individuo<Integer> ind) {
		return fit.eval(fromCodones(ind.clone().getValores()));
	}
	
	private List<Gen<Accion>> nodo(List<Integer> codones, GenNodo<Accion> padre) {
		List<Gen<Accion>> crom = null;
		int i = codones.get(pos % codones.size()) % 2;
		pos++;
		if (pos / codones.size() >= maxWraps) i = 0;	// nº de wraps = posicion / nº de codones
		switch (i) {									// (nº de veces que ha dado la vuelta)
		case 0:
			crom = term(codones, padre);
			break;
		case 1:
			crom = noTerm(codones, padre);
			break;
		}
		return crom;
	}
	
	private List<Gen<Accion>> term(List<Integer> codones, GenNodo<Accion> padre) {
		List<Gen<Accion>> crom = new ArrayList<>();
		int i = codones.get(pos % codones.size()) % (extras ? 4 : 3);
		pos++;
		switch (i) {
		case 0:
			crom.add(new GenNodoJardin((GenNodoJardin) padre, Accion.AVANZA));
			break;
		case 1:
			crom.add(new GenNodoJardin((GenNodoJardin) padre, Accion.IZQUIERDA));
			break;
		case 2:
			int fila = codones.get(pos % codones.size()) % nFilas; pos++;
			int col = codones.get(pos % codones.size()) % nCols; pos++;
			crom.add(new GenNodoJardin((GenNodoJardin) padre, fila, col));
			break;
		case 3:
			crom.add(new GenNodoJardin((GenNodoJardin) padre, Accion.DERECHA));
			break;
		}
		return crom;
	}
	
	private List<Gen<Accion>> noTerm(List<Integer> codones, GenNodo<Accion> padre) {
		List<Gen<Accion>> crom = null;
		int i = codones.get(pos % codones.size()) % (extras ? 5 : 3);
		pos++;
		switch (i) {
		case 0:
			crom = salta(codones, padre);
			break;
		case 1:
			crom = suma(codones, padre);
			break;
		case 2:
			crom = progn(codones, padre);
			break;
		case 3:
			crom = if_dirty(codones, padre);
			break;
		case 4:
			crom = repeat(codones, padre);
			break;
		}
		return crom;
	}
	
	private List<Gen<Accion>> salta(List<Integer> codones, GenNodo<Accion> padre) {
		GenNodo<Accion> gen = new GenNodoJardin((GenNodoJardin) padre, Accion.SALTA);
		gen.getHijos().add((GenNodo<Accion>) nodo(codones, padre).get(0));
		return gen.getPreorder();
	}
	
	private List<Gen<Accion>> suma(List<Integer> codones, GenNodo<Accion> padre) {
		GenNodo<Accion> gen = new GenNodoJardin((GenNodoJardin) padre, Accion.SUMA);
		gen.getHijos().add((GenNodo<Accion>) nodo(codones, padre).get(0));
		gen.getHijos().add((GenNodo<Accion>) nodo(codones, padre).get(0));
		return gen.getPreorder();
	}
	
	private List<Gen<Accion>> progn(List<Integer> codones, GenNodo<Accion> padre) {
		GenNodo<Accion> gen = new GenNodoJardin((GenNodoJardin) padre, Accion.PROGN);
		gen.getHijos().add((GenNodo<Accion>) nodo(codones, padre).get(0));
		gen.getHijos().add((GenNodo<Accion>) nodo(codones, padre).get(0));
		return gen.getPreorder();
	}
	
	private List<Gen<Accion>> if_dirty(List<Integer> codones, GenNodo<Accion> padre) {
		GenNodo<Accion> gen = new GenNodoJardin((GenNodoJardin) padre, Accion.IF_DIRTY);
		gen.getHijos().add((GenNodo<Accion>) nodo(codones, padre).get(0));
		gen.getHijos().add((GenNodo<Accion>) nodo(codones, padre).get(0));
		return gen.getPreorder();
	}
	
	private List<Gen<Accion>> repeat(List<Integer> codones, GenNodo<Accion> padre) {
		GenNodo<Accion> gen = new GenNodoJardin((GenNodoJardin) padre, Accion.REPEAT);
		gen.getHijos().add((GenNodo<Accion>) nodo(codones, padre).get(0));
		gen.getHijos().add((GenNodo<Accion>) nodo(codones, padre).get(0));
		return gen.getPreorder();
	}
}
