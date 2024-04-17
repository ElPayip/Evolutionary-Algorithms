package modelo.mutacion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import modelo.genes.Gen;
import modelo.genes.GenNodo;
import modelo.inicializaciones.IniCreciente;
import vista.ConfigPanel.Option;

public class MutacionExpansion<T> implements Mutacion<T> {

	@Override
	public void mutar(List<Gen<T>> crom) {
		List<GenNodo<T>> terms = new ArrayList<>();
		for (Gen<T> g : crom)
			if (((GenNodo<T>) g).isTerminal()) terms.add((GenNodo<T>) g);
		
		GenNodo<T> old = terms.get(new Random().nextInt(terms.size()));
		List<Gen<T>> nuevo = new IniCreciente<T>().generar(old.createInstance(old.getPadre(), false), crom.size());
		int pos = crom.indexOf(old);
		
		old.getPadre().getHijos().set(old.getPadre().getHijos().indexOf(old), (GenNodo<T>) nuevo.get(0).clone());
		crom.remove(old);
		crom.addAll(pos, nuevo);
	}
	
	@Override
	public <O> List<Option<O>> getExtraOpts() {
		return null;
	}
	
	public Mutacion<T> clone(){
		return new MutacionExpansion<>();
	}
	
	@Override
	public String toString() {
		return "Expansión";
	}
}
