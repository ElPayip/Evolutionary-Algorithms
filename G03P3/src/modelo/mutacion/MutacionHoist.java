package modelo.mutacion;

import java.util.List;
import java.util.Random;

import modelo.genes.Gen;
import modelo.genes.GenNodo;
import vista.ConfigPanel.Option;

public class MutacionHoist<T> implements Mutacion<T> {

	@Override
	public void mutar(List<Gen<T>> crom) {
		int pos = new Random().nextInt(crom.size());
		GenNodo<T> nodo = (GenNodo<T>) crom.get(pos);
		
		nodo.setPadre(null);
		crom.removeAll(crom);
		crom.addAll(nodo.getPreorder());
	}

	public Mutacion<T> clone(){
		return new MutacionHoist<>();
	}
	
	@Override
	public String toString() {
		return "Hoist";
	}
	
	@Override
	public <O> List<Option<O>> getExtraOpts() {
		return null;
	}
}
