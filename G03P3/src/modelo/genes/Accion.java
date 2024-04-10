package modelo.genes;

import java.util.ArrayList;
import java.util.List;

public enum Accion {
	
	AVANZA(0), IZQUIERDA(0), CONST(1), SALTA(1), SUMA(2), PROGN(2);
	
	private int aridad;
	private static class Coord {
		int fila, col;
		public Coord(int f, int c) {
			fila = f;
			col = c;
		}
		public int fila() {return fila;}
		public int columna() {return col;}
	}
	
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
