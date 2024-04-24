package vista;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;

import javax.naming.OperationNotSupportedException;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import modelo.AlgGenetico;
import modelo.Cortacesped;
import modelo.Cortacesped.Casilla;
import modelo.CortacespedGramatica;
import modelo.fitness.Accion;
import modelo.fitness.FitJardin;
import modelo.fitness.FitJardinGramatica;
import modelo.genes.GenNodoJardin;
import modelo.genes.GenNodoJardin.Coord;
import modelo.individuo.Individuo;

public class RecorridoPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private static final int TAM_CELDA = 30;
	
	private List<List<Casilla>> jardin;
	private Individuo<Accion> mejor;
	private FitJardinVista fit;
	private Integer maxPasos;
	private static boolean omitir = false;
	private boolean gram = false;
	
	private static JSpinner delay;

	public RecorridoPanel(AlgGenetico<?> alg) {
		if (alg.getClass() == Cortacesped.class) {
			jardin = ((Cortacesped) alg).getJardin();
			mejor = ((Cortacesped) alg).getMejor().clone();
			maxPasos = ((Cortacesped) alg).getMaxPasos();
		}
		else if (alg.getClass() == CortacespedGramatica.class) {
			jardin = ((CortacespedGramatica) alg).getJardin();
			mejor = new FitJardinGramatica(((CortacespedGramatica) alg).getJardin(), 
							((CortacespedGramatica) alg).getMaxPasos(),
							((CortacespedGramatica) alg).getMaxWraps()).fromCodones(((CortacespedGramatica) alg).getMejor().clone().getValores());
			maxPasos = ((CortacespedGramatica) alg).getMaxPasos();
			gram = true;
		}
		else
			throw new UnsupportedOperationException("Recorrido panel debe recibir una clase Cortacesped");
		
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
		fit = new FitJardinVista(jardin, maxPasos, (JardinTableModel) tablero.getModel());
		
		JCheckBox boxOmitir = new JCheckBox("Omitir animaciones");
		boxOmitir.setOpaque(false);
		boxOmitir.setSelected(omitir);
		boxOmitir.addActionListener((e) -> {
			omitir = !omitir;
		});
		if (delay == null) {
			delay = new JSpinner();
			delay.setValue(50);
		}
		JPanel panelParams = new JPanel();
		panelParams.setLayout(new BoxLayout(panelParams, BoxLayout.Y_AXIS));
		panelParams.setMaximumSize(new Dimension(50,70));
		panelParams.setOpaque(false);
		panelParams.add(boxOmitir);
		panelParams.add(new JLabel("Delay:"));
		panelParams.add(delay);
		
		JPanel panelRecorrido = new JPanel();
		panelRecorrido.setLayout(new BoxLayout(panelRecorrido, BoxLayout.X_AXIS));
		panelRecorrido.add(Box.createHorizontalStrut(20));
		panelRecorrido.add(tablero);
		panelRecorrido.add(Box.createHorizontalStrut(20));
		panelRecorrido.add(panelParams);
		panelRecorrido.add(Box.createHorizontalGlue());
		panelRecorrido.setOpaque(false);

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setOpaque(false);
		if (gram) this.add(new JLabel(mejor.toString()));
		this.add(panelRecorrido);
	}
	
	public void reproducir() {
		fit.eval(mejor);
	}

	
	private class FitJardinVista extends FitJardin {

		private JardinTableModel tabla;

		public FitJardinVista(List<List<Casilla>> jardin, int maxPasos, JardinTableModel tabla) {
			super(jardin, maxPasos);
			this.tabla = tabla;
		}
		
		@Override
		public List<List<Casilla>> copiarJardin() {
			List<List<Casilla>> copia = super.copiarJardin();
			tabla.setJardin(copia);
			tabla.fireTableDataChanged();
			return copia;
		}
		
		@Override
		protected Coord recorrer(GenNodoJardin arbol, List<List<Casilla>> copiaJardin, Estado estado)
				throws OperationNotSupportedException {
			Coord c = super.recorrer(arbol, copiaJardin, estado);
			if (!omitir)
				try {
					Thread.sleep((int) delay.getValue());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			tabla.actualizar(estado.fila(), estado.columna(), estado.orientacion());
			return c;
		}
	}
	
	private class JardinTableModel extends AbstractTableModel {

		private static final long serialVersionUID = 1L;
		
		private List<List<Casilla>> copiaJardin;
		private int fila = 4, col = 4, orientacion = 0;
		
		public JardinTableModel(List<List<Casilla>> jardin) {
			this.copiaJardin = jardin;
		}
		
		public void setJardin(List<List<Casilla>> jardin) {
			this.copiaJardin = jardin;
		}
		
		public void actualizar(int fila, int col, int orient) {
			this.fila = fila;
			this.col = col;
			this.orientacion = orient;
			this.fireTableDataChanged();
		}
		
		@Override
		public int getRowCount() {
			return copiaJardin.size();
		}

		@Override
		public int getColumnCount() {
			return copiaJardin.get(0).size();
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			if (fila == rowIndex && col == columnIndex)
				switch (orientacion) {
				case 0: return "/█\\";
				case 1: return "<█";
				case 2: return "\\█/";
				case 3: return "█>";
				}
			
			switch (jardin.get(rowIndex).get(columnIndex)) {
			case FLORES:
				return ":▒:";
			case ROCA:
				return " /¯\\";
			default:
				break;
			}
			
			switch (copiaJardin.get(rowIndex).get(columnIndex)) {
			case CESPED:
				return " : · :";
			default:
				break;
			}
			
			return "";
		}
		
		@Override 
		public boolean isCellEditable(int row, int col) { 
			return false; 
		}
	}
	
	private class JardinRenderer extends DefaultTableCellRenderer {
		private static final long serialVersionUID = 1L;

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			JLabel lbl = ((JLabel)super.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,column));
			Casilla actual = ((JardinTableModel) table.getModel()).copiaJardin.get(row).get(column);
			
			switch(actual) {
			case ROCA:
				lbl.setBackground(new Color(100,100,100));
				break;
			case CORTADO:
				lbl.setBackground(new Color(100,255,100));
				break;
			default:
				lbl.setBackground(new Color(0,180,0));
				break;
			}
			
			switch(jardin.get(row).get(column)) {
			case FLORES:
				lbl.setForeground(actual == Casilla.CORTADO ? new Color(100,150,100) : Color.MAGENTA);
				break;
			default:
				lbl.setForeground(Color.BLACK);
				break;
			}
			
			lbl.setFont(new Font(null, Font.PLAIN, 14));
			return lbl;
		}
	}
}
