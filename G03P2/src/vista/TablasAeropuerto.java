package vista;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import modelo.Aeropuerto;
import modelo.fitness.FitAeropuerto;
import modelo.individuo.Individuo;
import utils.Pair;

public class TablasAeropuerto extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private int[] pistas;
	private Integer[] aviones;
	private double[] tlas;
	private String[] nombres;
	private int nPistas;

	public TablasAeropuerto(Aeropuerto alg) {
		Individuo<Integer> mejor = alg.getMejor();
		FitAeropuerto fit = (FitAeropuerto) alg.getFitness();
		fit.eval(mejor);
		Pair<int[],double[]> sol = fit.getSolucion();
		pistas = sol.getFirst();
		tlas = sol.getSecond();
		nombres = alg.getNombres().toArray(new String[0]);
		aviones = alg.getMejor().getValores().toArray(new Integer[0]);
		nPistas = alg.getNPistas();
		
		initGUI();
	}
	
	private void initGUI() {
		List<JTable> tablas = new ArrayList<>();
		for (int i = 0; i < nPistas; ++i)
			tablas.add(tablaPista());
		
		for (Integer avion : aviones)
			((AeropuertoTableModel)tablas.get(pistas[avion]).getModel()).addVuelo(avion, nombres[avion], tlas[avion]);
		
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.setAlignmentY(TOP_ALIGNMENT);
		for (int i = 0; i < tablas.size(); ++i) {
			JPanel panelTabla = new JPanel();
			panelTabla.setLayout(new BoxLayout(panelTabla, BoxLayout.Y_AXIS));
			panelTabla.setAlignmentY(TOP_ALIGNMENT);
			panelTabla.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
			panelTabla.add(new JLabel("Pista "+Integer.toString(i+1)));
			panelTabla.add(tablas.get(i));
			this.add(panelTabla);
		}
		this.setBackground(Color.WHITE);
	}
	
	private JTable tablaPista() {
		JTable tabla = new JTable(new AeropuertoTableModel());
		tabla.setDefaultRenderer(Object.class, new AeropuertoRenderer());
		tabla.getTableHeader().setForeground(Color.BLUE);
		return tabla;
	}
	
	private static class AeropuertoTableModel extends AbstractTableModel {
		private static final long serialVersionUID = 1L;

		List<Integer> vuelo = new ArrayList<>();
		List<String> nombre = new ArrayList<>();
		List<Double> tla = new ArrayList<>();
		
		private static final String[] HEADER = { "Vuelo", "Nombre", "TLA" };

		@Override
		public int getRowCount() {
			return vuelo.size();
		}

		@Override
		public int getColumnCount() {
			return HEADER.length;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			switch (columnIndex) {
			case 0: return vuelo.get(rowIndex);
			case 1: return nombre.get(rowIndex);
			case 2: return tla.get(rowIndex);
			default: return null;
			}
		}

		@Override
		public String getColumnName(int col) {
			return HEADER[col];
		}
		
		@Override 
		public boolean isCellEditable(int row, int col) { 
			return false; 
		}
		
		public void addVuelo(int vuelo, String nombre, double tla) {
			this.vuelo.add(vuelo);
			this.nombre.add(nombre);
			this.tla.add(tla);
			
			this.fireTableDataChanged();
		}
	}
	
	private static class AeropuertoRenderer extends DefaultTableCellRenderer {
		private static final long serialVersionUID = 1L;

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			JLabel lbl = ((JLabel)super.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,column));
			if(column < 1) lbl.setForeground(Color.BLUE);
			else if (column < 2) lbl.setForeground(Color.BLACK);
			else lbl.setForeground(Color.RED);
			return lbl;
		}
	}
}
