package modelo;

import modelo.factorias.FactoriaAlgGenetico;
import vista.ConfigPanel;

public interface Configurable {

	public void configurar(ConfigPanel<FactoriaAlgGenetico> config);
}
