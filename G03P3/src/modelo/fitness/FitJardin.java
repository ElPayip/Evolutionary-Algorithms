package modelo.fitness;

import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import modelo.Cortacesped.Casilla;
import modelo.genes.Accion;
import modelo.genes.Accion.Coord;
import modelo.genes.GenNodoJardin;
import modelo.individuo.Individuo;
import modelo.individuo.IndividuoArbol;

public class FitJardin implements Fitness<Accion> {
	
	Integer fitness;
	List<List<Casilla>> jardin;
	private class Estado {
		private int orientacion = 0, fila = 4, col = 4;
		public void gira() {
			orientacion++;
		}
		public void avanza() {
			switch (orientacion) {
			case 0: col = (col - 1) % jardin.get(0).size(); break;
			case 1: fila = (fila - 1) % jardin.size(); break;
			case 2: col = (col + 1) % jardin.get(0).size(); break;
			case 3: fila = (fila + 1) % jardin.size(); break;
			}
		}
		public void setPos(int f, int c) {
			fila = f;
			col = c;
		}
	}
	
	public FitJardin(List<List<Casilla>> jardin) {
		this.jardin = jardin;
	}

	@Override
	public double eval(Individuo<Accion> ind) {
		fitness = 0;
		List<List<Casilla>> copiaJardin = new ArrayList<>();
		for (List<Casilla> lista : jardin)
			copiaJardin.add(new ArrayList<>(lista));
		
		try {
			recorrer(((IndividuoArbol) ind).getRaiz(), copiaJardin, new Estado());
		} catch (OperationNotSupportedException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	private Coord recorrer(GenNodoJardin arbol, List<List<Casilla>> copiaJardin, Estado estado) throws OperationNotSupportedException {
		Coord c = new Coord(0,0);
		switch (arbol.getValor()) {
		case AVANZA:
			estado.avanza();
			break;
		case IZQUIERDA:
			estado.gira();
			break;
		case CONST:
			c = arbol.getValor().getCoord();
			break;
		case SALTA:
			c = recorrer(arbol.getHijos().get(0), copiaJardin, estado);
			estado.setPos(c.fila(), c.columna());
			break;
		case SUMA: 
			for (GenNodoJardin g : arbol.getHijos()) {
				Coord co = recorrer(g, copiaJardin, estado);
				c = new Coord((c.fila() + co.fila()) % jardin.size(), 
							  (c.columna()+ co.columna()) % jardin.size());
			}
			break;
		case PROGN:
			for (GenNodoJardin g : arbol.getHijos())
				c = recorrer(g, copiaJardin, estado);
			break;
		default:
			break;
		} 
		
		if (copiaJardin.get(estado.fila).get(estado.col) == Casilla.CESPED) {
			copiaJardin.get(estado.fila).set(estado.col, Casilla.CORTADO);
			fitness++;
		}
		return c;
	}
}
