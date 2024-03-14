package modelo.cruce;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import modelo.genes.Gen;
import utils.Pair;
import vista.ConfigPanel.IntegerOption;
import vista.ConfigPanel.Option;

public class CruceOXOP<T> implements Cruce<T> {
	
	private Integer nPos;
	
	public CruceOXOP() {
		nPos = 2;
	}

	public CruceOXOP(Integer nPos) {
		this.nPos = nPos;
	}

	@Override
	public Pair<List<Gen<T>>, List<Gen<T>>> cruzar(List<Gen<T>> crom1, List<Gen<T>> crom2) {
		List<Gen<T>> hijo1 = new ArrayList<>(crom1); Collections.fill(hijo1, null);
		List<Gen<T>> hijo2 = new ArrayList<>(crom2); Collections.fill(hijo2, null);
		
		// Cálculo del hijo 1
		Random rand = new Random();
		List<Integer> posiciones = new ArrayList<Integer>(nPos);
		int pos;
		
		for (int i = 0; i < nPos; ++i) {
			do {
				pos = rand.nextInt(crom1.size());
			} while(posiciones.contains(pos));
			posiciones.add(pos);
		}
		posiciones.sort(null);
		
		List<Gen<T>> ciudades = new ArrayList<>(nPos);
		for (int i = 0; i < nPos; ++i) {
			ciudades.add(crom1.get(posiciones.get(i)));
		}
		
		List<Integer> posCiudades = new ArrayList<Integer>(nPos);
		for (int i = 0; i < nPos; ++i) {
			posCiudades.add(crom2.indexOf(ciudades.get(i).clone()));
		}
		
		int j = 0;
		for (int i = 0; i < hijo1.size(); ++i) {
			if(posCiudades.contains(i)) {
				hijo1.set(i, crom2.get(i).clone());
			}
			else {
				hijo1.set(i, ciudades.get(j).clone());
				j++;
			}
		}
		
		// Cálculo del hijo 2
		rand = new Random();
		posiciones = new ArrayList<Integer>(nPos);
		
		for (int i = 0; i < nPos; ++i) {
			do {
				pos = rand.nextInt(crom2.size());
			} while(posiciones.contains(pos));
			posiciones.add(pos);
		}
		posiciones.sort(null);
		
		ciudades = new ArrayList<>(nPos);
		for (int i = 0; i < nPos; ++i) {
			ciudades.add(crom2.get(posiciones.get(i)));
		}
		
		posCiudades = new ArrayList<Integer>(nPos);
		for (int i = 0; i < nPos; ++i) {
			posCiudades.add(crom1.indexOf(ciudades.get(i).clone()));
		}
		
		j = 0;
		for (int i = 0; i < hijo1.size(); ++i) {
			if(posCiudades.contains(i)) {
				hijo2.set(i, crom1.get(i).clone());
			}
			else {
				hijo2.set(i, ciudades.get(j).clone());
				j++;
			}
		}
		
		return new Pair<>(hijo1, hijo2);
	}
	
	@Override
	public Cruce<T> clone() {
		return new CruceOXOP<>();
	}
	
	@Override
	public <O> List<Option<O>> getExtraOpts() {
		List<Option<O>> extras = new ArrayList<>();
		extras.add(new IntegerOption<O>("posiciones", "posiciones", "nPos", 1, 100));
		return extras;
	}
	
	@Override
	public String toString() {
		return "OXOP";
	}
	
	public Integer getnPos() {
		return nPos;
	}

	public void setnPos(Integer nPos) {
		this.nPos = nPos;
	}
}
