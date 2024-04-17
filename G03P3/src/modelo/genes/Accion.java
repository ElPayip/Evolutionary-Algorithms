package modelo.genes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.naming.OperationNotSupportedException;

public enum Accion {
	
	AVANZA(0), IZQUIERDA(0), CONST(0), SALTA(1), SUMA(2), PROGN(2);

	private static int nFilas = 8, nCols = 8;
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
		@Override
		public String toString() {
			return String.format("%d,%d", fila, col);
		}
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
	
	public Coord getCoord() throws OperationNotSupportedException {
		if (this != CONST)
			throw new OperationNotSupportedException("getCoord sólo disponible para constantes");
		if (coord == null)
			coord = new Coord(new Random().nextInt(nFilas), new Random().nextInt(nCols));
		return coord;
	}
	
	@Override
	public String toString() {
		if (this == CONST)
			try {
				return getCoord().toString();
			} catch (OperationNotSupportedException e) {
				e.printStackTrace();
			}
		return super.toString();
	}
	
	public static void setDominio(int filas, int cols) {
		nFilas = filas;
		nCols = cols;
	}
}
