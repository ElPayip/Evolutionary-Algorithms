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
		for (List<Integer> perm : permutaciones(idx, nPos, 0)) {
			
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
	
	private List<List<Integer>> permutaciones(List<Integer> lista, int pos, Integer val) {
		List<List<Integer>> sol = new ArrayList<>();
		if (pos == lista.size()) {
			for (Integer i : lista)
				sol.addAll(permutaciones(lista, pos-1, i));
		}
		else if (pos == 0) {
			List<Integer> perm = new ArrayList<>();
			perm.add(val);
			sol.add(perm);
		}
		else {
			for (Integer i : lista) {
				List<List<Integer>> perms = permutaciones(lista, pos-1, i);
				for (List<Integer> perm : perms)
					perm.add(val);
				sol.addAll(perms);
			}
		}
		return sol;
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
