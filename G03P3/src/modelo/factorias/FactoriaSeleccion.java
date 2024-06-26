package modelo.factorias;

import java.util.ArrayList;
import java.util.List;

import modelo.seleccion.SelEstocastico;
import modelo.seleccion.SelRanking;
import modelo.seleccion.SelRestos;
import modelo.seleccion.SelRuleta;
import modelo.seleccion.SelTorneoDet;
import modelo.seleccion.SelTorneoProb;
import modelo.seleccion.SelTruncamiento;
import modelo.seleccion.Seleccion;

public class FactoriaSeleccion {

	private List<Seleccion> selecciones;
	
	public FactoriaSeleccion() {
		selecciones = new ArrayList<>();
		selecciones.add(new SelEstocastico());
		selecciones.add(new SelRuleta());
		selecciones.add(new SelTruncamiento());
		selecciones.add(new SelTorneoProb());
		selecciones.add(new SelTorneoDet());
		selecciones.add(new SelRestos());
		selecciones.add(new SelRanking());
	}
	
	public Seleccion[] getSelecciones() {
		Seleccion[] aux = new Seleccion[selecciones.size()];
		return selecciones.toArray(aux);
	}
}
