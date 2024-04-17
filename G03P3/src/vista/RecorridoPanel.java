package vista;

import java.util.List;

import javax.naming.OperationNotSupportedException;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import modelo.Cortacesped;
import modelo.Cortacesped.Casilla;
import modelo.fitness.FitJardin;
import modelo.genes.Accion;
import modelo.genes.GenNodoJardin;
import modelo.genes.GenNodoJardin.Coord;
import modelo.individuo.Individuo;

public class RecorridoPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private List<List<Casilla>> jardin;
	private Individuo<Accion> mejor;
	private FitJardinVista fit;

	public RecorridoPanel(Cortacesped alg) {
		jardin = alg.getJardin();
		mejor = alg.getMejor();
		
		initGUI();
		reproducir();
	}
	
	private void initGUI() {
		JTable tablero = new JTable(new JardinTableModel(jardin));
		fit = new FitJardinVista(jardin, (JardinTableModel) tablero.getModel());
	}
	
	public void reproducir() {
		fit.eval(mejor);
	}

	
	private static class FitJardinVista extends FitJardin {

		private JardinTableModel tabla;

		public FitJardinVista(List<List<Casilla>> jardin, JardinTableModel tabla) {
			super(jardin);
			this.tabla = tabla;		
		}
		
		@Override
		protected Coord recorrer(GenNodoJardin arbol, List<List<Casilla>> copiaJardin, Estado estado)
				throws OperationNotSupportedException {
			Coord c = super.recorrer(arbol, copiaJardin, estado);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			tabla.actualizar(estado.fila(), estado.columna());
			return c;
		}
	}
	
	private static class JardinTableModel extends AbstractTableModel {

		private static final long serialVersionUID = 1L;
		
		private List<List<Casilla>> jardin;
		private int fila, col;

		public JardinTableModel(List<List<Casilla>> jardin) {
			this.jardin = jardin;
		}
		
		public void actualizar(int fila, int col) {
			this.fila = fila;
			this.col = col;
		}
		
		@Override
		public int getRowCount() {
			return jardin.size();
		}

		@Override
		public int getColumnCount() {
			return jardin.get(0).size();
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			if (fila == rowIndex && col == columnIndex)
				return "Pito";
			return " ";
		}
		
		@Override 
		public boolean isCellEditable(int row, int col) { 
			return false; 
		}
	}
}
