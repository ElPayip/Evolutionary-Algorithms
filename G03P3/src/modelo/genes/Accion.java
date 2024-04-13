package modelo.genes;

import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

public enum Accion {
	
	AVANZA(0), IZQUIERDA(0), CONST(0), SALTA(1), SUMA(2), PROGN(2);
	
	private int aridad;
	private Coord coord;
	public static class Coord {
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
	
	public static Accion coord(int fila, int col) {
		Accion a = CONST;
		a.coord = new Coord(fila, col);
		return a;
	}
	
	public Coord getCoord() throws OperationNotSupportedException {
		if (coord == null)
			throw new OperationNotSupportedException("getCoord sólo disponible para constantes inicializadas");
		return coord;
	}
}
