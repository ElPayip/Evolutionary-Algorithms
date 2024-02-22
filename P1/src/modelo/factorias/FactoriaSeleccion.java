package modelo.factorias;

import java.util.ArrayList;
import java.util.List;

import modelo.seleccion.SelRuleta;
import modelo.seleccion.SelTorneoProb;
import modelo.seleccion.SelTruncamiento;
import modelo.seleccion.Seleccion;

public class FactoriaSeleccion {

	private List<Seleccion> selecciones;
	
	public FactoriaSeleccion() {
		selecciones = new ArrayList<>();
		selecciones.add(new SelRuleta());
		selecciones.add(new SelTruncamiento());
		selecciones.add(new SelTorneoProb());
	}
	
	public List<Seleccion> getSelecciones() {
		return selecciones;
	}
}
