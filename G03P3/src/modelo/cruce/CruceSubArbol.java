package modelo.cruce;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import modelo.genes.Gen;
import modelo.genes.GenNodo;
import utils.Pair;
import vista.ConfigPanel.Option;

public class CruceSubArbol<T> implements Cruce<T> {

	@Override
	public Pair<List<Gen<T>>, List<Gen<T>>> cruzar(List<Gen<T>> crom1, List<Gen<T>> crom2) {
		
		List<Gen<T>> hijo1 = new ArrayList<>(((GenNodo<T>) crom1.get(0).clone()).getPreorder());
		List<Gen<T>> hijo2 = new ArrayList<>(((GenNodo<T>) crom2.get(0).clone()).getPreorder());
		
		if (hijo1.size() <= 1 || hijo2.size() <= 1)
			return new Pair<>(hijo1, hijo2);
		
		Random rand = new Random();
		int pos1 = rand.nextInt(1, hijo1.size()), pos2 = rand.nextInt(1, hijo2.size());
		
		GenNodo<T> inter1 = (GenNodo<T>) hijo1.get(pos1), inter2 = (GenNodo<T>) hijo2.get(pos2), aux;
		
		hijo1.removeAll(inter1.getPreorder());
		hijo2.removeAll(inter2.getPreorder());
		
		hijo1.addAll(pos1, inter2.getPreorder());
		hijo2.addAll(pos2, inter1.getPreorder());
		
		if (inter1.getPadre() != null) {
			pos1 = inter1.getPadre().getHijos().indexOf(inter1);
			inter1.getPadre().getHijos().remove(pos1);
			inter1.getPadre().getHijos().add(pos1, inter2);
		}
		
		if (inter2.getPadre() != null) {
			pos2 = inter2.getPadre().getHijos().indexOf(inter2);
			inter2.getPadre().getHijos().remove(pos2);
			inter2.getPadre().getHijos().add(pos2, inter1);
		}
		
		aux = inter1.getPadre();
		inter1.setPadre(inter2.getPadre());
		inter2.setPadre(aux);
		
		return new Pair<>(hijo1, hijo2);
	}
	
	@Override
	public <O> List<Option<O>> getExtraOpts() {
		return null;
	}
	
	@Override
	public Cruce<T> clone() {
		return new CruceSubArbol<>();
	}
	
	@Override
	public String toString() {
		return "Cruce de subárboles";
	}
}
