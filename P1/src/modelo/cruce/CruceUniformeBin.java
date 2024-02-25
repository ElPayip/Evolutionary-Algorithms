package modelo.cruce;

import java.util.List;

import modelo.genes.Gen;
import utils.Pair;
import vista.ConfigPanel.Option;

public class CruceUniformeBin<T> implements Cruce<T>{

	@Override
	public Pair<List<? extends Gen<T>>, List<? extends Gen<T>>> cruzar(List<? extends Gen<T>> crom1,
			List<? extends Gen<T>> crom2) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String toString() {
		return "Cruce uniforme (binario)";
	}
	
	@Override
	public <O> List<Option<O>> getExtraOpts() {
		return null;
	}
	
	@Override
	public Cruce<T> clone() {
		return new CruceUniformeBin<>();
	}
	
}
