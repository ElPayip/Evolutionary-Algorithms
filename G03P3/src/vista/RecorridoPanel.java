package vista;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;

import javax.naming.OperationNotSupportedException;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import modelo.Cortacesped;
import modelo.Cortacesped.Casilla;
import modelo.fitness.FitJardin;
import modelo.genes.Accion;
import modelo.genes.GenNodoJardin;
import modelo.genes.GenNodoJardin.Coord;
import modelo.individuo.Individuo;

public class RecorridoPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private static final int TAM_CELDA = 30;
	
	private List<List<Casilla>> jardin;
	private Individuo<Accion> mejor;
	private FitJardinVista fit;

	public RecorridoPanel(Cortacesped alg) {
		jardin = alg.getJardin();
		mejor = alg.getMejor();
		
		initGUI();
	}
	
	private void initGUI() {
		JTable tablero = new JTable(new JardinTableModel(jardin));
		tablero.setGridColor(Color.BLACK);
		tablero.setDefaultRenderer(Object.class, new JardinRenderer());
		tablero.addComponentListener(new ComponentAdapter(){
		    @Override
		    public void componentResized(ComponentEvent e){
		        tablero.setRowHeight(TAM_CELDA);
		        for (int i = 0; i < tablero.getColumnCount(); i++){
		        	tablero.getColumnModel().getColumn(i).setMaxWidth(TAM_CELDA);
		        }
		    }
		});
		tablero.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		fit = new FitJardinVista(jardin, (JardinTableModel) tablero.getModel());
		
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.add(Box.createHorizontalStrut(20));
		this.add(tablero);
		this.add(Box.createHorizontalGlue());
		this.setOpaque(false);
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
		public List<List<Casilla>> copiarJardin() {
			List<List<Casilla>> copia = super.copiarJardin();
			tabla.setJardin(copia);
			return copia;
		}
		
		@Override
		protected Coord recorrer(GenNodoJardin arbol, List<List<Casilla>> copiaJardin, Estado estado)
				throws OperationNotSupportedException {
			Coord c = super.recorrer(arbol, copiaJardin, estado);
			try {
				Thread.sleep(20);
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
		private int fila = 4, col = 4;
		
		public JardinTableModel(List<List<Casilla>> jardin) {
			this.jardin = jardin;
		}
		
		public void setJardin(List<List<Casilla>> jardin) {
			this.jardin = jardin;
		}
		
		public void actualizar(int fila, int col) {
			this.fila = fila;
			this.col = col;
			this.fireTableDataChanged();
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
				return "  O";
			return " ";
		}
		
		@Override 
		public boolean isCellEditable(int row, int col) { 
			return false; 
		}
	}
	
	private static class JardinRenderer extends DefaultTableCellRenderer {
		private static final long serialVersionUID = 1L;

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			JLabel lbl = ((JLabel)super.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,column));
			switch(((JardinTableModel) table.getModel()).jardin.get(row).get(column)) {
			case CESPED:
				lbl.setBackground(new Color(0,150,0));
				break;
			case CORTADO:
				lbl.setBackground(new Color(100,255,100));
				break;
			default:
				break;
			}
			return lbl;
		}
	}
}
