package modelo.mutacion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import modelo.genes.Gen;
import modelo.genes.GenNodo;
import vista.ConfigPanel.Option;

public class MutacionTerminal<T> implements Mutacion<T> {

	@Override
	public void mutar(List<Gen<T>> crom) {
		List<Gen<T>> terms = new ArrayList<>();
		for (Gen<T> g : crom)
			if (((GenNodo<T>) g).isTerminal()) terms.add(g);
		
		int pos = new Random().nextInt(terms.size());
		((GenNodo<T>) terms.get(pos)).setRandomVal(true);
	}

	@Override
	public <O> List<Option<O>> getExtraOpts() {
		return null;
	}

	public Mutacion<T> clone(){
		return new MutacionTerminal<>();
	}
	
	@Override
	public String toString() {
		return "Terminal";
	}
}
