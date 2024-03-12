package modelo.cruce;

import java.util.List;

import modelo.genes.Gen;
import utils.Pair;
import vista.ConfigPanel.Option;

public class CruceOXOP<T> implements Cruce<T> {

	@Override
	public Pair<List<Gen<T>>, List<Gen<T>>> cruzar(List<Gen<T>> crom1, List<Gen<T>> crom2) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Cruce<T> clone() {
		return new CruceOXOP<>();
	}
	
	@Override
	public <O> List<Option<O>> getExtraOpts() {
		return null;
	}
	
	@Override
	public String toString() {
		return "OXOP";
	}
}
