package vista;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.math.plot.Plot2DPanel;

import modelo.AlgGenetico;
import modelo.Cortacesped;
import modelo.individuo.Individuo;

public class GraphPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private Plot2DPanel plot;
	private JTextArea individuoLabel;
	private JLabel resultadoLabel;
	private JPanel individuoPanel;
	
	private String[] nombres = { "Media", "Mejor Actual", "Mejor Global", "Presión selectiva (x10)" };
	private Color[] colores = { Color.GREEN, Color.RED, Color.BLUE, Color.LIGHT_GRAY };

	public GraphPanel() {
		initGUI();
	}
	
	private void initGUI() {
		plot = new Plot2DPanel();
		plot.addLegend("SOUTH");
		
		JPanel mejorPanel = new JPanel();
		mejorPanel.setLayout(new BoxLayout(mejorPanel, BoxLayout.Y_AXIS));
		mejorPanel.add(new JLabel("Mejor individuo:"));
		individuoLabel = new JTextArea("...");
		individuoLabel.setEditable(false);
		
		individuoPanel = new JPanel();
		individuoPanel.setLayout(new BoxLayout(individuoPanel, BoxLayout.Y_AXIS));
		individuoPanel.add(individuoLabel);
		individuoPanel.setBackground(Color.WHITE);
		individuoPanel.setAlignmentX(CENTER_ALIGNMENT);
		
		JScrollPane indPanel = new JScrollPane(individuoPanel);
		indPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		indPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		indPanel.setPreferredSize(new Dimension(800, 300));
		
		resultadoLabel = new JLabel("");
		mejorPanel.add(indPanel);
		mejorPanel.add(resultadoLabel);
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(plot);
		this.add(mejorPanel);
		this.setPreferredSize(new Dimension(600, 900));
	}
	
	public void update(AlgGenetico<?> alg) {
		Individuo<?> mejor = alg.getMejor().clone();
		List<Double[]> metricas = alg.getMetricas();
		plot.removeAllPlots();
		for (int i = 0; i < metricas.get(0).length; ++i)
			metricas.get(3)[i] *= 10;
		double[] x = Arrays.stream(IntStream.range(0, metricas.get(0).length).toArray()).asDoubleStream().toArray();
		for (int i = 0; i < metricas.size(); ++i) {
			double[] y = Stream.of(metricas.get(i)).mapToDouble(Double::doubleValue).toArray();
			
			try { Thread.sleep(37);// Esta espera impide excepciones por problemas de concurrencia (aunque son inofensivas)
			} catch (InterruptedException e) { System.out.println("Espera interrumpida"); }
			plot.addLinePlot(nombres[i], colores[i], x, y);
		}
		
		individuoLabel.setText("    Valores: "+mejor.toString());
		
		individuoPanel.removeAll();
		individuoPanel.add(individuoLabel);
		individuoPanel.add(Box.createVerticalStrut(5));
		RecorridoPanel recorrido = new RecorridoPanel(alg);
		individuoPanel.add(recorrido);
		individuoPanel.add(Box.createVerticalStrut(20));
		resultadoLabel.setText("Fitness: "+mejor.getFitness());
		this.validate();
		recorrido.reproducir();
	}
}
