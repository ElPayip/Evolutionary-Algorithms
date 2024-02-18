package modelo.cruce;

import java.util.List;
import java.util.Random;

import utils.Pair;

public class CruceMonopunto<T> implements Cruce<T> {
	
	@Override
	public Pair<List<T>,List<T>> cruzar(List<T> crom1, List<T> crom2) {
		if (crom1.size() != crom2.size())
			throw new IllegalArgumentException("Cromosomas de distinto tamaño en cruce");

		int len = crom1.size();
		int punto = (int) (new Random().nextDouble() * len);
		
		List<T> c1 = crom1.subList(0, punto);
		List<T> c2 = crom2.subList(0, punto);
		c1.addAll(crom2.subList(punto, len));
		c2.addAll(crom1.subList(punto, len));
		
		return new Pair<>(c1, c2);
	}
}