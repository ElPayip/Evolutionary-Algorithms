package modelo.individuo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.naming.OperationNotSupportedException;

import modelo.genes.Gen;
import modelo.genes.GenEntero;

public class IndividuoPermInt extends Individuo<Integer> {

	public IndividuoPermInt(Individuo<Integer> otro) {
		super(otro);
	}

	public IndividuoPermInt(Integer[] mins, Integer[] maxs, Integer prec) throws OperationNotSupportedException {
		throw new OperationNotSupportedException();
	}

	public IndividuoPermInt(List<Gen<Integer>> cromosoma) {
		super(cromosoma);
	}
	
	public IndividuoPermInt(int vuelos) {
		List<Integer> crom = IntStream.range(0, vuelos).boxed().collect(Collectors.toList());
		Collections.shuffle(crom);
		List<Gen<Integer>> cromosoma = new ArrayList<>();
		for (Integer i : crom)
			cromosoma.add(new GenEntero(i));
		this.cromosoma = cromosoma;
	}

	@Override
	protected Gen<Integer> generarGen(Integer min, Integer max, Integer prec) {
		return null;
	}

	@Override
	public Individuo<Integer> createInstance(List<Gen<Integer>> crom) {
		return new IndividuoPermInt(crom);
	}

	@Override
	public Individuo<Integer> clone() {
		return new IndividuoPermInt(this);
	}

}
