import javax.swing.SwingUtilities;

import control.Controller;
import control.FactoriaAlgGenetico;
import vista.MainWindow;

public class Main {

	public static void main(String[] args) {
		FactoriaAlgGenetico<Double> fact = new FactoriaAlgGenetico<>();
		Controller control = new Controller(fact);
		SwingUtilities.invokeLater(() -> new MainWindow(control));
	}
}
