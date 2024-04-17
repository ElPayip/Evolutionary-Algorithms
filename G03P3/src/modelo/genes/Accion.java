package modelo.genes;

import java.util.ArrayList;
import java.util.List;

public enum Accion {
	
	AVANZA(0), IZQUIERDA(0), CONST(0), SALTA(1), SUMA(2), PROGN(2);

	private int aridad;
	
	Accion(int aridad) {
		this.aridad = aridad;
	}
	
	public int getAridad() {
		return aridad;
	}
	
	public static Accion[] terminales() {
		List<Accion> lista = new ArrayList<>();
		for (Accion a : values())
			if (a.aridad == 0) lista.add(a);
		
		return lista.toArray(new Accion[0]);
	}
	
	public static Accion[] noTerminales() {
		List<Accion> lista = new ArrayList<>();
		for (Accion a : values())
			if (a.aridad > 0) lista.add(a);
		
		return lista.toArray(new Accion[0]);
	}
}
