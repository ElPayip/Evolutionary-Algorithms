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

	public FitJardinGramatica(List<List<Casilla>> jardin, int maxPasos) {
		fit = new FitJardin(jardin, maxPasos);
	}

	public Individuo<Accion> fromCodones(List<Integer> codones) {
		return new IndividuoJardin(noTerm(codones, null));
	}

	@Override
	public double eval(Individuo<Integer> ind) {
		return fit.eval(fromCodones(ind.getValores()));
	}
	
	private List<Gen<Accion>> nodo(List<Integer> codones, GenNodo<Accion> padre) {
		List<Gen<Accion>> crom = null;
		int i = codones.get(pos % codones.size()) % 2;
		pos++;
		switch (i) {
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
		int i = codones.get(pos % codones.size()) % 4;
		pos++;
		switch (i) {
		case 0:
			crom.add(new GenNodoJardin((GenNodoJardin) padre, Accion.AVANZA));
			break;
		case 1:
			crom.add(new GenNodoJardin((GenNodoJardin) padre, Accion.IZQUIERDA));
			break;
		case 2:
			crom.add(new GenNodoJardin((GenNodoJardin) padre, Accion.CONST));
			break;
		case 3:
			crom.add(new GenNodoJardin((GenNodoJardin) padre, Accion.DERECHA));
			break;
		}
		return crom;
	}
	
	private List<Gen<Accion>> noTerm(List<Integer> codones, GenNodo<Accion> padre) {
		List<Gen<Accion>> crom = null;
		int i = codones.get(pos % codones.size()) % 5;
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
		List<Gen<Accion>> crom = new ArrayList<>();
		crom.add(new GenNodoJardin((GenNodoJardin) padre, Accion.SALTA));
		crom.addAll(nodo(codones, padre));
		crom.addAll(nodo(codones, padre));
		return crom;
	}
	
	private List<Gen<Accion>> suma(List<Integer> codones, GenNodo<Accion> padre) {
		List<Gen<Accion>> crom = new ArrayList<>();
		crom.add(new GenNodoJardin((GenNodoJardin) padre, Accion.SUMA));
		crom.addAll(nodo(codones, padre));
		crom.addAll(nodo(codones, padre));
		return crom;
	}
	
	private List<Gen<Accion>> progn(List<Integer> codones, GenNodo<Accion> padre) {
		List<Gen<Accion>> crom = new ArrayList<>();
		crom.add(new GenNodoJardin((GenNodoJardin) padre, Accion.PROGN));
		crom.addAll(nodo(codones, padre));
		crom.addAll(nodo(codones, padre));
		return crom;
	}
	
	private List<Gen<Accion>> if_dirty(List<Integer> codones, GenNodo<Accion> padre) {
		List<Gen<Accion>> crom = new ArrayList<>();
		crom.add(new GenNodoJardin((GenNodoJardin) padre, Accion.IF_DIRTY));
		crom.addAll(nodo(codones, padre));
		crom.addAll(nodo(codones, padre));
		return crom;
	}
	
	private List<Gen<Accion>> repeat(List<Integer> codones, GenNodo<Accion> padre) {
		List<Gen<Accion>> crom = new ArrayList<>();
		crom.add(new GenNodoJardin((GenNodoJardin) padre, Accion.REPEAT));
		crom.addAll(nodo(codones, padre));
		crom.addAll(nodo(codones, padre));
		return crom;
	}
}
