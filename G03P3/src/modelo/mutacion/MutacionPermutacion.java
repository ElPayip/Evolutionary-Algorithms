package modelo.mutacion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import modelo.genes.Gen;
import modelo.genes.GenNodo;
import vista.ConfigPanel.Option;

public class MutacionPermutacion<T> implements Mutacion<T> {

	@Override
	public void mutar(List<Gen<T>> crom) {
		List<Gen<T>> funcs = new ArrayList<>();
		for (Gen<T> g : crom)
			if (!((GenNodo<T>) g).isTerminal()) funcs.add(g);
		if (funcs.size() == 0)
			return;
		
		int pos = new Random().nextInt(funcs.size());
		GenNodo<T> funcion = (GenNodo<T>) funcs.get(pos);
		
		List<GenNodo<T>> hijos = funcion.getHijos();
		Collections.shuffle(hijos);
	}
	
	@Override
	public <O> List<Option<O>> getExtraOpts() {
		return null;
	}

	public Mutacion<T> clone(){
		return new MutacionPermutacion<>();
	}
	
	@Override
	public String toString() {
		return "Permutación";
	}
}
