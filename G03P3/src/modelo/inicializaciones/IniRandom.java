package modelo.inicializaciones;

import java.util.ArrayList;
import java.util.List;

import modelo.genes.Gen;
import modelo.individuo.Individuo;
import vista.ConfigPanel.Option;

public class IniRandom<T> implements Inicializacion<T> {
	
	Individuo<T> indEj; 
	Gen<T> genEj;

	@Override
	public List<Individuo<T>> init(int n) {
		List<Individuo<T>> poblacion = new ArrayList<>();
		for (int i = 0; i < n; ++i)
			poblacion.add(indEj.createInstance(generarCrom()));
		return poblacion;
	}
	
	private List<Gen<T>> generarCrom() {
		List<Gen<T>> crom = new ArrayList<>();
		for (int i = 0; i < indEj.getValores().size(); ++i) {
			Gen<T> nuevo = genEj.clone(); 
			nuevo.setRandomVal();
			crom.add(nuevo);
		}
		return crom;
	}

	@Override
	public void setInstances(Individuo<T> ind, Gen<T> gen) {
		indEj = ind;
		genEj = gen;
	}

	@Override
	public <O> List<Option<O>> getExtraOpts() {
		return null;
	}
	
	@Override
	public Inicializacion<T> clone() {
		return new IniRandom<>();
	}
	
	@Override
	public String toString() {
		return "Random";
	}
}
