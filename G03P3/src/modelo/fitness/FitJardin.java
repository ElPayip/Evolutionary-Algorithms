package modelo.fitness;

import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import modelo.Cortacesped.Casilla;
import modelo.genes.GenNodo;
import modelo.genes.GenNodoJardin;
import modelo.genes.GenNodoJardin.Coord;
import modelo.individuo.Individuo;
import modelo.individuo.IndividuoJardin;

public class FitJardin implements Fitness<Accion> {
	
	Integer fitness, count, maxCount = 100;
	List<List<Casilla>> jardin;
	protected class Estado {
		private Coord old;
		private int orientacion = 0, fila = 4, col = 4;
		public void gira() {
			orientacion = (orientacion + 1) % 4;
		}
		public void avanza() {
			save();
			int nFilas = jardin.size(), nCols = jardin.get(0).size();
			switch (orientacion) {
			case 0: fila = (nFilas + fila - 1) % nFilas; break;
			case 1: col = (nCols + col - 1) % nCols; break;
			case 2: fila = (fila + 1) % nFilas; break;
			case 3: col = (col + 1) % nCols; break;
			}
		}
		public void salta(int f, int c) {
			save();
			fila = (fila + f) % jardin.size();
			col = (col + c) % jardin.size();
		}
		public int fila() {return fila;}
		public int columna() {return col;}
		public int orientacion() {return orientacion;}
		private void save() {old = new Coord(fila, col);}
		public void undo() {fila = old.fila(); col = old.columna();}
		public Coord frente() { 
			Coord aux = old, nuevo;
			avanza(); nuevo = new Coord(fila, col);
			undo(); old = aux;
			return nuevo;
		}
	}
	
	public FitJardin(List<List<Casilla>> jardin, int maxPasos) {
		this.jardin = jardin;
		maxCount = maxPasos;
	}

	@Override
	public double eval(Individuo<Accion> ind) {
		fitness = count = 0;
		List<List<Casilla>> copiaJardin = copiarJardin();
		
		try {
			Estado estado = new Estado();
			int abort = 10, countAnt = count;
			while (count < maxCount && abort > 0) {
				recorrer(((IndividuoJardin) ind).getRaiz(), copiaJardin, estado);
				if (count == 0) break;
				if (count == countAnt) abort--;	// Cuenta atrás para considerar que está en un bucle infinito
				else {
					abort = 10;
					countAnt = count;
				}
			}
		} catch (OperationNotSupportedException | IndexOutOfBoundsException e) {
			e.printStackTrace();
		}
		return fitness;
	}
	
	public List<List<Casilla>> copiarJardin() {
		List<List<Casilla>> copiaJardin = new ArrayList<>();
		for (List<Casilla> lista : jardin)
			copiaJardin.add(new ArrayList<>(lista));
		return copiaJardin;
	}
	
	protected Coord recorrer(GenNodoJardin arbol, List<List<Casilla>> copiaJardin, Estado estado) throws OperationNotSupportedException {
		Coord c = new Coord(0,0);
		boolean corta = false;
		switch (arbol.getValor()) {
		case AVANZA:
			estado.avanza();
			corta = true;
			count++;
			break;
		case IZQUIERDA:
			estado.gira();
			count++;
			break;
		case DERECHA:
			estado.gira();
			estado.gira();
			estado.gira();
			count++;
			break;
		case CONST:
			c = arbol.getCoord();
			break;
		case SALTA:
			c = recorrer((GenNodoJardin) arbol.getHijos().get(0), copiaJardin, estado);
			estado.salta(c.fila(), c.columna());
			corta = true;
			count++;
			break;
		case SUMA: 
			for (GenNodo<Accion> g : arbol.getHijos()) {
				Coord co = recorrer((GenNodoJardin) g, copiaJardin, estado);
				c = new Coord((c.fila() + co.fila()) % jardin.size(), 
							  (c.columna()+ co.columna()) % jardin.size());
			}
			break;
		case PROGN:
			for (GenNodo<Accion> g : arbol.getHijos())
				c = recorrer((GenNodoJardin) g, copiaJardin, estado);
			break;
		case IF_DIRTY:
			Coord frente = estado.frente();
			if (copiaJardin.get(frente.fila()).get(frente.columna()).getValor() > 0) 		// Comprueba si cortar la casilla le aporta fitness
				c = recorrer((GenNodoJardin) arbol.getHijos().get(0), copiaJardin, estado); // Es importante que sea así, puesto que hay más casillas cortables aparte del césped
			else 
				c = recorrer((GenNodoJardin) arbol.getHijos().get(1), copiaJardin, estado);
			break;
		default:
			break;
		} 
		Casilla actual = copiaJardin.get(estado.fila).get(estado.col);
		if (!actual.isTransitable())
			estado.undo();
		else if (corta && actual.isCortable()) {
			copiaJardin.get(estado.fila).set(estado.col, Casilla.CORTADO);
			fitness += actual.getValor();
		}
		
		return c;
	}
}
