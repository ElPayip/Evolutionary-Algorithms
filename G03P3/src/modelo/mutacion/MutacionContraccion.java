package modelo.mutacion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import modelo.genes.Gen;
import modelo.genes.GenNodo;
import vista.ConfigPanel.Option;

public class MutacionContraccion<T> implements Mutacion<T> {

	@Override
	public void mutar(List<Gen<T>> crom) {
		List<GenNodo<T>> noTerms = new ArrayList<>();
		for (Gen<T> g : crom)
			if (!((GenNodo<T>) g).isTerminal()) noTerms.add((GenNodo<T>) g);
		
		GenNodo<T> old = noTerms.get(new Random().nextInt(noTerms.size())), nuevo = old.createInstance(old.getPadre(), true);
		int pos = crom.indexOf(old);
		
		old.getPadre().getHijos().set(old.getPadre().getHijos().indexOf(old), nuevo);
		crom.removeAll(old.getInorden());
		crom.add(pos, nuevo);
	}

	public Mutacion<T> clone(){
		return new MutacionContraccion<>();
	}

	@Override
	public <O> List<Option<O>> getExtraOpts() {
		return null;
	}
}
