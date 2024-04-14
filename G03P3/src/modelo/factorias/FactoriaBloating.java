package modelo.factorias;

import java.util.ArrayList;
import java.util.List;

import modelo.bloating.BloatingTarpeian;
import modelo.bloating.ControlBloating;

public class FactoriaBloating {

	private List<ControlBloating> controles;
	
	public FactoriaBloating() {
		controles = new ArrayList<>();
		controles.add(new BloatingTarpeian());
		//TODO
	}
	
	public ControlBloating[] getControlesBloating() {
		return controles.toArray(new ControlBloating[0]);
	}
}