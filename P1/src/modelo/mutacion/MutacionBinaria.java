package modelo.mutacion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MutacionBinaria implements Mutacion<Integer> {

	@Override
	public List<Integer> mutar(List<Integer> crom) {
		List<Integer> nuevo = new ArrayList<>(crom);
		int idx = new Random().nextInt(crom.size());
		
		nuevo.set(idx, (nuevo.get(idx) + 1) % 2);
		return nuevo;
	}
}
