package modelo.mutacion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import modelo.fitness.Fitness;
import modelo.genes.Gen;
import modelo.individuo.Individuo;
import vista.ConfigPanel.IntegerOption;
import vista.ConfigPanel.Option;

public class MutacionHeuristica<T> implements Mutacion<T> {
	
	private Integer nPos;
	
	private Fitness<T> fitness;
	private Individuo<T> ejemplo;
	
	public MutacionHeuristica() {
		nPos = 3;
	}

	public MutacionHeuristica(Integer nPos) {
		this.nPos = nPos;
	}

	@Override
	public void mutar(List<Gen<T>> crom) {
		Random rand = new Random();
		List<Integer> idx = new ArrayList<>();
		for (int i = 0; i < nPos; ++i) {
			Integer pos = rand.nextInt(crom.size());
			while (idx.contains(pos)) pos = rand.nextInt(crom.size());
			idx.add(pos);
		}
		
		double maxFit = -Double.MAX_VALUE;
		List<Gen<T>> mejor = new ArrayList<>(), actual = new ArrayList<>(crom);
		for (List<Integer> perm : permutaciones(idx)) {
			
			for (int i = 0; i < nPos; ++i)
				actual.set(idx.get(i), crom.get(perm.get(i)));
			double fit = fitness.eval(ejemplo.createInstance(actual));
			
			if (fit > maxFit) {
				maxFit = fit;
				mejor = new ArrayList<>(actual);
			}
		}
		
		crom.clear();
		crom.addAll(mejor);
	}
	
	private List<List<Integer>> permutaciones(List<Integer> lista) {
		List<List<Integer>> perms = new ArrayList<>();
		if (lista.size() == 0) {
			perms.add(new ArrayList<>());
			return perms;
		}
		
		for (Integer elem : lista) {
			List<Integer> restantes = new ArrayList<>(lista);
			restantes.remove(elem);
			
			for (List<Integer> perm : permutaciones(restantes)) {
				perm.add(elem);
				perms.add(perm);
			}
		}
		return perms;
	}
	
	@Override
	public void update(Fitness<T> fit, Individuo<T> ind) {
		fitness = fit;
		ejemplo = ind.clone();
	}

	@Override
	public <O> List<Option<O>> getExtraOpts() {
		List<Option<O>> extras = new ArrayList<>();
		extras.add(new IntegerOption<O>("posiciones", "posiciones", "nPos", 1, 100));
		return extras;
	}

	public Mutacion<T> clone(){
		return new MutacionHeuristica<>();
	}

	@Override
	public String toString() {
		return "Heuristica";
	}

	public Integer getnPos() {
		return nPos;
	}

	public void setnPos(Integer nPos) {
		this.nPos = nPos;
	}
}
