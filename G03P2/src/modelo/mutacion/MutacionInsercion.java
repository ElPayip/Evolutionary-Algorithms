package modelo.mutacion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import modelo.fitness.Fitness;
import modelo.genes.Gen;
import modelo.individuo.Individuo;
import vista.ConfigPanel.IntegerOption;
import vista.ConfigPanel.Option;

public class MutacionInsercion<T> implements Mutacion<T> {
	
	private Integer numInserciones = 1;
	
	public MutacionInsercion() {}
	
	public MutacionInsercion(Integer numInserciones) {
		this.numInserciones = numInserciones;
	}
	
	@Override
	public void mutar(List<Gen<T>> crom) {
		
		for(int i = 0; i < numInserciones; ++i){
			Random rand = new Random();
			int pos1 = rand.nextInt(crom.size()),
				pos2 = rand.nextInt(crom.size());
			
			Gen<T> sel = crom.get(pos1);
			
			crom.remove(pos1);
			crom.add(pos2, sel);
		}
	}
	
	public void setInserciones(Integer numInserciones) {
		this.numInserciones = numInserciones;
	}
	
	public Integer getInserciones() {
		return numInserciones;
	}
	
	@Override
	public <O> List<Option<O>> getExtraOpts() {
		List<Option<O>> extras = new ArrayList<>();
		extras.add(new IntegerOption<O>("Inserciones", "Inserciones", "Inserciones", 0, 100));
		return extras;
	}
	
	public Mutacion<T> clone(){
		return new MutacionInsercion<>();
	}

	@Override
	public String toString() {
		return "Insercion";
	}
}
