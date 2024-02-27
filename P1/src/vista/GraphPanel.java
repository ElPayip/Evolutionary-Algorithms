package vista;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.math.plot.Plot2DPanel;

import modelo.individuo.Individuo;

public class GraphPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private Plot2DPanel plot;
	private JLabel individuoLabel;
	private JLabel resultadoLabel;
	
	private String[] nombres = { "Media", "Max Actual", "Max Global" };
	private Color[] colores = { Color.GREEN, Color.RED, Color.BLUE };

	public GraphPanel() {
		initGUI();
	}
	
	private void initGUI() {
		plot = new Plot2DPanel();
		plot.addLegend("SOUTH");
		
		JPanel mejorPanel = new JPanel();
		mejorPanel.setLayout(new BoxLayout(mejorPanel, BoxLayout.Y_AXIS));
		mejorPanel.add(new JLabel("Mejor individuo:"));
		individuoLabel = new JLabel("...");
		resultadoLabel = new JLabel("");
		mejorPanel.add(individuoLabel);
		mejorPanel.add(resultadoLabel);
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(plot);
		this.add(mejorPanel);
		this.setPreferredSize(new Dimension(600, 600));
	}
	
	public void update(List<Double[]> metricas, Individuo<?> mejor) {
		plot.removeAllPlots();
		double[] x = Arrays.stream(IntStream.range(0, metricas.get(0).length).toArray()).asDoubleStream().toArray();
		for (int i = 0; i < metricas.size(); ++i) {
			double[] y = Stream.of(metricas.get(i)).mapToDouble(Double::doubleValue).toArray();
			
			try { Thread.sleep(20);// Esta espera impide excepciones por problemas de concurrencia (aunque son inofensivas)
			} catch (InterruptedException e) { System.out.println("Espera interrumpida"); }
			plot.addLinePlot(nombres[i], colores[i], x, y);
		}
		
		individuoLabel.setText("Valores: "+mejor.getValores().toString());
		resultadoLabel.setText("Solución: "+mejor.getFitness());
	}
}
