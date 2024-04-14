package modelo.mutacion;

import java.util.List;
import java.util.Random;

import modelo.genes.Gen;
import modelo.genes.GenNodo;
import modelo.inicializaciones.IniCreciente;
import vista.ConfigPanel.Option;

public class MutacionSubarbol<T> implements Mutacion<T> {

	@Override
	public void mutar(List<Gen<T>> crom) {
		int pos = new Random().nextInt(crom.size());
		GenNodo<T> nodo = (GenNodo<T>) crom.get(pos);
		List<Gen<T>> arbol = new IniCreciente<T>().generar(nodo.createInstance(nodo.getPadre(), false), crom.size());

		crom.removeAll(nodo.getInorden());
		crom.addAll(pos, arbol);
	}

	public Mutacion<T> clone(){
		return new MutacionSubarbol<>();
	}

	@Override
	public <O> List<Option<O>> getExtraOpts() {
		return null;
	}
}
