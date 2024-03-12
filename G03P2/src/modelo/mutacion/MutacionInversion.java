package modelo.mutacion;

import java.util.List;

import modelo.fitness.Fitness;
import modelo.genes.Gen;
import modelo.individuo.Individuo;
import vista.ConfigPanel.Option;

public class MutacionInversion<T> implements Mutacion<T> {

	@Override
	public void mutar(List<Gen<T>> crom) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public <O> List<Option<O>> getExtraOpts() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void update(Fitness<T> fit, Individuo<T> ind) {
		//TODO
	}
	
	public Mutacion<T> clone(){
		return new MutacionInversion<>();
	}

	@Override
	public String toString() {
		return "Inversion";
	}

}
