package modelo.seleccion;

import java.util.ArrayList;
import java.util.List;

import modelo.individuo.Individuo;
import vista.ConfigPanel.DoubleOption;
import vista.ConfigPanel.Option;

public class SelTruncamiento implements Seleccion {
	
	private Double trunc = 0.5;
	
	public SelTruncamiento() {}
	
	public SelTruncamiento(Double trunc) {
		this.trunc = trunc;
	}

	@Override
	public <T> List<Individuo<T>> seleccionar(List<Individuo<T>> individuos) {
		if (trunc == null)
			throw new RuntimeException("Hay que inicializar SelTruncamiento con setTrunc");
		
		int lim = (int) (trunc * individuos.size());
		List<Individuo<T>> supervs = new ArrayList<>(individuos.size());
		for (int i = 0; i < individuos.size(); ++i) 
			supervs.add(individuos.get(i % lim).clone());
			
		return supervs;
	}

	@Override
	public String toString() {
		return "Truncamiento";
	}

	@Override
	public <T> List<Option<T>> getExtraOpts() {
		List<Option<T>> extras = new ArrayList<>();
		extras.add(new DoubleOption<T>("% de truncamiento", "% de truncamiento", "trunc", 0, 100, 100));
		return extras;
	}
	
	public void setTrunc(Double trunc) {
		this.trunc = trunc;
	}
	
	public Double getTrunc() {
		return trunc;
	}
	
	@Override
	public Seleccion clone() {
		return new SelTruncamiento(trunc);
	}
}
