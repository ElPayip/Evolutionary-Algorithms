package modelo;

import java.util.List;

import modelo.genes.Accion;
import modelo.individuo.Individuo;
import modelo.inicializaciones.Inicializacion;
import vista.ConfigPanel.Option;

public class Cortacesped extends AlgGenetico<Accion> {
	
	private Integer profMax, ancho, alto;
	private Inicializacion<Accion> init;

	@Override
	protected Individuo<Accion> generarIndividuo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CategoriaCrom getCategoria() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AlgGenetico<Accion> clone() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean maximizacion() {
		return true;
	}

	@Override
	public <T> List<Option<T>> getExtraOpts() {
		// TODO Auto-generated method stub
		return null;
	}
}
