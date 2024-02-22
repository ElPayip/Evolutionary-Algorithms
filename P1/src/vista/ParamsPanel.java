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
		
		factoria.configurar(config);
	}
}
