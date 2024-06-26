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
		List<Gen<T>> arbol = new IniCreciente<T>().generar(nodo.createInstance(nodo.getPadre(), false), crom.size()/2+1);

		if (nodo.getPadre() != null)
			nodo.getPadre().getHijos().set(nodo.getPadre().getHijos().indexOf(nodo), (GenNodo<T>) arbol.get(0));
		
		if (arbol.size() == 0)
			System.out.println();
		crom.removeAll(nodo.getPreorder());
		crom.addAll(pos, arbol);
	}

	public Mutacion<T> clone(){
		return new MutacionSubarbol<>();
	}

	@Override
	public <O> List<Option<O>> getExtraOpts() {
		return null;
	}
	
	@Override
	public String toString() {
		return "Subárbol";
	}
}
