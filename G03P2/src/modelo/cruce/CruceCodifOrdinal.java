package modelo.cruce;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import modelo.CategoriaCrom;
import modelo.factorias.FactoriaCruce;
import modelo.genes.Gen;
import modelo.genes.GenEntero;
import utils.Pair;
import vista.ConfigPanel.Option;
import vista.ConfigPanel.StrategyOption;

public class CruceCodifOrdinal<T> implements Cruce<Comparable<T>> {
	
	private Cruce<Integer> cruce;
	
	public CruceCodifOrdinal() {}

	public CruceCodifOrdinal(Cruce<Integer> cruce) {
		this.cruce = cruce;
	}

	@Override
	public Pair<List<Gen<Comparable<T>>>, List<Gen<Comparable<T>>>> cruzar(List<Gen<Comparable<T>>> crom1, List<Gen<Comparable<T>>> crom2) {
		List<Gen<Comparable<T>>> lista = new LinkedList<Gen<Comparable<T>>>(crom1), lista1, lista2;
		lista.sort(new Comparator<>() {
			@SuppressWarnings("unchecked")
			@Override
			public int compare(Gen<Comparable<T>> o1, Gen<Comparable<T>> o2) {
				return o1.getValor().compareTo((T) o2.getValor());
			}
		});
		lista1 = new LinkedList<>(lista);
		lista2 = new LinkedList<>(lista);
		
		List<Gen<Integer>> ord1 = new ArrayList<>(), 
					  ord2 = new ArrayList<>();
		
		for (int i = 0; i < crom1.size(); ++i) {
			ord1.add(new GenEntero(lista1.indexOf(crom1.get(i))));
			lista1.remove(crom1.get(i));
			ord2.add(new GenEntero(lista1.indexOf(crom2.get(i))));
			lista2.remove(crom1.get(i));
		}
		
		Pair<List<Gen<Integer>>,List<Gen<Integer>>> newOrds = cruce.cruzar(ord1, ord2);
		ord1 = newOrds.getFirst();
		ord2 = newOrds.getSecond();

		List<Gen<Comparable<T>>> hijo1 = new ArrayList<>(),
								 hijo2 = new ArrayList<>();
		for (int i = 0; i < crom1.size(); ++i) {
			hijo1.add(lista.get(ord1.get(i).getValor()).clone());
			hijo2.add(lista.get(ord2.get(i).getValor()).clone());
		}
		
		return new Pair<>(hijo1,hijo2);
	}
	
	@Override
	public Cruce<Comparable<T>> clone() {
		return new CruceCodifOrdinal<>(cruce);
	}

	@Override
	public <O> List<Option<O>> getExtraOpts() {
		List<Option<O>> extras = new ArrayList<>();
		extras.add(new StrategyOption<O>(
				"metodo de cruce", 
				"metodo de cruce", 
				"cruce",
				new FactoriaCruce().getCruces(CategoriaCrom.GENERICA)));
		return extras;
	}

	@Override
	public String toString() {
		return "CO";
	}

	public Cruce<Integer> getCruce() {
		return cruce;
	}

	public void setCruce(Cruce<Integer> cruce) {
		this.cruce = cruce;
	}
}
