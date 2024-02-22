import javax.swing.SwingUtilities;

import modelo.factorias.FactoriaAlgGenetico;
import vista.MainWindow;

public class Main {

	public static void main(String[] args) {
		FactoriaAlgGenetico fact = new FactoriaAlgGenetico();
		new MainWindow(fact);
	}
}
