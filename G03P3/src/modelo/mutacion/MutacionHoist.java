package modelo.mutacion;

import java.util.List;

import modelo.genes.Gen;
import vista.ConfigPanel.Option;

public class MutacionHoist<T> implements Mutacion<T> {

	@Override
	public <O> List<Option<O>> getExtraOpts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void mutar(List<Gen<T>> crom) {
		// TODO Auto-generated method stub
		
	}

	public Mutacion<T> clone(){
		return new MutacionHoist<>();
	}
	
	@Override
	public String toString() {
		return "Hoist";
	}
}
