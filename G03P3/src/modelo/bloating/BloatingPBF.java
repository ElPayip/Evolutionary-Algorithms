package modelo.bloating;

import java.util.List;

import modelo.individuo.Individuo;
import vista.ConfigPanel.Option;

public class BloatingPBF implements ControlBloating {

	@Override
	public <T> void controlar(List<Individuo<T>> poblacion) {
		// TODO Auto-generated method stub
	}
	
	public ControlBloating clone() {
		return new BloatingPBF();
	}

	@Override
	public <T> List<Option<T>> getExtraOpts() {
		return null;
	}
}
