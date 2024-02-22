import javax.swing.SwingUtilities;

import control.Controller;
import modelo.factorias.FactoriaAlgGenetico;
import vista.MainWindow;

public class Main {

	public static void main(String[] args) {
		FactoriaAlgGenetico fact = new FactoriaAlgGenetico();
		SwingUtilities.invokeLater(() -> new MainWindow(fact));
	}
}
