package modelo.genes;

import java.util.Random;

import modelo.fitness.Accion;

public class GenNodoJardin extends GenNodo<Accion> {
	
	private static int nFilas, nCols;
	public static class Coord {
		int fila, col;
		public Coord(int f, int c) {
			fila = f;
			col = c;
		}
		public int fila() {return fila;}
		public int columna() {return col;}
		public int modulo() {return (int) Math.sqrt(Math.pow(fila, 2) + Math.pow(col, 2)); }
		@Override
		public String toString() {
			return String.format("%d,%d", fila, col);
		}
	}
	private Coord coord;
	
	public GenNodoJardin(GenNodoJardin padre, boolean terminal) {
		super(padre, terminal);
		coord = new Coord(new Random().nextInt(nFilas), new Random().nextInt(nCols));
	}
	
	public GenNodoJardin(GenNodoJardin padre) {
		super(padre);
		coord = new Coord(new Random().nextInt(nFilas), new Random().nextInt(nCols));
	}
	
	public GenNodoJardin(GenNodoJardin padre, Accion accion) {
		super(padre);
		coord = new Coord(new Random().nextInt(nFilas), new Random().nextInt(nCols));
		valor = accion;
	}

	@Override
	public int getAridad() {
		return valor.getAridad();
	}

	@Override
	public GenNodo<Accion> createInstance(GenNodo<Accion> padre) {
		return new GenNodoJardin((GenNodoJardin) padre);
	}

	@Override
	public GenNodo<Accion> createInstance(GenNodo<Accion> padre, boolean terminal) {
		return new GenNodoJardin((GenNodoJardin) padre, terminal);
	}

	@Override
	Accion[] terminales() {
		return Accion.terminales();
	}

	@Override
	Accion[] noTerminales() {
		return Accion.noTerminales();
	}
	
	@Override
	public String toString() {
		String str = "(";
		str += valor == Accion.CONST ? coord.toString() : valor.toString();
		for (GenNodo<Accion> g : hijos)
			str += " " + g.toString();
		str += ")";
		return str;
	}
	
	public static void setDominio(int filas, int cols) {
		nFilas = filas;
		nCols = cols;
	}

	public Coord getCoord() {
		return coord;
	}

	@Override
	public Gen<Accion> clone() {
		GenNodoJardin nuevo = (GenNodoJardin) super.clone();
		nuevo.coord = new Coord(coord.fila, coord.col);
		return nuevo;
	}
}
