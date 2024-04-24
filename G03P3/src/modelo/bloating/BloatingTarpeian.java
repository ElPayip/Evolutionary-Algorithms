package modelo.bloating;

import java.util.List;
import java.util.Random;

import modelo.individuo.Individuo;
import vista.ConfigPanel.Option;

public class BloatingTarpeian implements ControlBloating {

	@Override
	public <T> void controlar(List<Individuo<T>> poblacion) {
		double media = 0;
		for (Individuo<T> i : poblacion)
			media += i.getValores().size();
		media /= poblacion.size();
		
		Random rand = new Random();
		for (Individuo<T> i : poblacion)
			if (i.getValores().size() > media && rand.nextDouble() < 0.5)
				i.setFitness(0);
	}

	public ControlBloating clone() {
		return new BloatingTarpeian();
	}

	@Override
	public <T> List<Option<T>> getExtraOpts() {
		return null;
	}
	
	@Override
	public String toString() {
		return "Tarpeian";
	}
}
