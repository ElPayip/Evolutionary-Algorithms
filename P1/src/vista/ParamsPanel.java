package vista;

import javax.swing.JPanel;

import modelo.Configurable;
import modelo.factorias.FactoriaAlgGenetico;
import vista.ConfigPanel.DoubleOption;
import vista.ConfigPanel.IntegerOption;
import vista.ConfigPanel.StrategyOption;

public class ParamsPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	FactoriaAlgGenetico factoria;

	public ParamsPanel(FactoriaAlgGenetico factoria) {
		this.factoria = factoria;
		initGUI();
	}
	
	private void initGUI() {
		ConfigPanel<FactoriaAlgGenetico> config = new ConfigPanel<FactoriaAlgGenetico>();
		
		config.addOption(new StrategyOption<FactoriaAlgGenetico>(
					  "algoritmo genetico",
					  "algoritmo genetico",
					  "alg",
					  factoria.getAlgPosibles()));
		for (Configurable c : factoria.getAlgPosibles()) {
			c.configurar(config);
		}
		
		config.addOption(new IntegerOption<>(
					  "número de generaciones", 			// texto a usar como etiqueta del campo
					  "número de generaciones",       		// texto a usar como 'tooltip' cuando pasas el puntero
					  "nGeneraciones",  					// campo (espera que haya un getX y un setX)
					  1, Integer.MAX_VALUE))				// min y max (usa Integer.MIN_VALUE /MAX_VALUE para infinitos)
			  .addOption(new IntegerOption<>(
					  "tamaño de poblacion", 
					  "tamaño de poblacion", 
					  "tamPoblacion", 
					  1, Integer.MAX_VALUE))
			  .addOption(new DoubleOption<>(
					  "probabilidad de cruce",
					  "probabilidad de cruce",
					  "probCruce",
					  0, 1));
	}
}
