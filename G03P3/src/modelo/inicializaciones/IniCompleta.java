package modelo.inicializaciones;

import java.util.List;

import modelo.genes.Gen;
import modelo.individuo.Individuo;
import vista.ConfigPanel.Option;

public class IniCompleta<T> implements Inicializacion<T> {
	
	public IniCompleta() {
		
	}

	@Override
	public List<Individuo<T>> init(int n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <O> List<Option<O>> getExtraOpts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setInstances(Individuo<T> ind, Gen<T> gen) {
		// TODO Auto-generated method stub
		
	}

}
