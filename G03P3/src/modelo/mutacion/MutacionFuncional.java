package modelo.mutacion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import modelo.genes.Gen;
import modelo.genes.GenNodo;
import vista.ConfigPanel.Option;

public class MutacionFuncional<T> implements Mutacion<T> {

	@Override
	public void mutar(List<Gen<T>> crom) {
		List<Gen<T>> funcs = new ArrayList<>();
		for (Gen<T> g : crom)
			if (!((GenNodo<T>) g).isTerminal()) funcs.add(g);
		
		int pos = new Random().nextInt(funcs.size());
		funcs.get(pos).setRandomVal();
	}
	
	@Override
	public <O> List<Option<O>> getExtraOpts() {
		return null;
	}
	
	public Mutacion<T> clone(){
		return new MutacionFuncional<>();
	}
	
	@Override
	public String toString() {
		return "Funcional";
	}
}
