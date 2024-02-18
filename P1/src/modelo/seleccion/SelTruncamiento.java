package modelo.seleccion;

import java.util.ArrayList;
import java.util.List;

import modelo.individuo.Individuo;

public class SelTruncamiento implements Seleccion {
	
	private double trunc;
	
	public SelTruncamiento(double trunc) {
		this.trunc = trunc;
	}

	@Override
	public <T,C> List<Individuo<T,C>> seleccionar(List<Individuo<T,C>> individuos) {
		int lim = (int) (trunc * individuos.size());
		List<Individuo<T,C>> supervs = new ArrayList<>(individuos.size());
		for (int i = 0; i < individuos.size(); ++i) 
			supervs.set(i, individuos.get(i % lim));
			
		return supervs;
	}
}
