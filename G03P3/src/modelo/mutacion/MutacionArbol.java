package modelo.mutacion;

import java.util.List;

import modelo.genes.Gen;
import vista.ConfigPanel.Option;

public class MutacionArbol<T> implements Mutacion<T> {

	@Override
	public void mutar(List<Gen<T>> crom) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public <O> List<Option<O>> getExtraOpts() {
		return null;
	}

	public Mutacion<T> clone(){
		return new MutacionArbol<>();
	}
}
