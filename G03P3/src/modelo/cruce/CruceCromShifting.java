package modelo.cruce;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import modelo.CategoriaCrom;
import modelo.factorias.FactoriaCruce;
import modelo.genes.Gen;
import utils.Pair;
import vista.ConfigPanel.BooleanOption;
import vista.ConfigPanel.Option;
import vista.ConfigPanel.StrategyOption;

public class CruceCromShifting<T> implements Cruce<T> {
	
	private Cruce<T> cruce = new CrucePMX<>();
	private Boolean distDespl = false;

	@Override
	public Pair<List<Gen<T>>, List<Gen<T>>> cruzar(List<Gen<T>> crom1, List<Gen<T>> crom2) {
		Random rand = new Random();
		int sh1 = rand.nextInt(crom1.size()), sh2;
		if (distDespl) sh2 = rand.nextInt(crom1.size());
		else sh2 = sh1;
		
		List<Gen<T>> hijo1 = new ArrayList<>(crom1),
					 hijo2 = new ArrayList<>(crom2);
		Collections.rotate(hijo1, sh1);
		Collections.rotate(hijo2, sh2);
		
		Pair<List<Gen<T>>,List<Gen<T>>> hijos = cruce.cruzar(hijo1, hijo2);

		Collections.rotate(hijos.getFirst(), crom1.size() - sh1);
		Collections.rotate(hijos.getSecond(), crom1.size() - sh2);
		return hijos;
	}
	
	@Override
	public Cruce<T> clone() {
		return new CruceCromShifting<>();
	}

	@Override
	public <O> List<Option<O>> getExtraOpts() {
		List<Option<O>> extras = new ArrayList<>();
		extras.add(new StrategyOption<O>(
				"metodo de cruce", 
				"metodo de cruce", 
				"cruce",
				new FactoriaCruce().getCruces(CategoriaCrom.PERMUTACION)));
		extras.add(new BooleanOption<O>(
				"distinto desplazamiento", 
				"distinto desplazamiento", 
				"distDespl"));
		return extras;
	}

	@Override
	public String toString() {
		return "CS";
	}

	public Cruce<T> getCruce() {
		return cruce;
	}

	public void setCruce(Cruce<T> cruce) {
		this.cruce = cruce;
	}

	public Boolean getDistDespl() {
		return distDespl;
	}

	public void setDistDespl(Boolean distDespl) {
		this.distDespl = distDespl;
	}
}
