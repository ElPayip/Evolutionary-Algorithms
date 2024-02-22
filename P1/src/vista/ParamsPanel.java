package vista;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import modelo.factorias.FactoriaAlgGenetico;
import vista.ConfigPanel.ConfigListener;

public class ParamsPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	FactoriaAlgGenetico factoria;
	private boolean datosValidos = false;

	public ParamsPanel(FactoriaAlgGenetico factoria) {
		this.factoria = factoria;
		initGUI();
	}
	
	private void initGUI() {
		ConfigPanel<FactoriaAlgGenetico> config = new ConfigPanel<FactoriaAlgGenetico>();
		
		factoria.configurar(config);
		config.setTarget(factoria);
		config.initialize();
		
		final String textoTodoValido = "Todos los campos OK";
		final String textoHayErrores = "Hay errores en algunos campos";
		final JLabel valido = new JLabel(textoHayErrores);
		// este evento se lanza cada vez que la validez cambia
		config.addConfigListener(new ConfigListener() {
			@Override
			public void configChanged(boolean isConfigValid) {
				valido.setText(isConfigValid ? textoTodoValido: textoHayErrores);		
				datosValidos = isConfigValid;
			}
		});
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		this.add(Box.createVerticalStrut(10));
		this.add(new JLabel("Parámetros:"));
		this.add(Box.createVerticalStrut(20));
		this.add(config);
		this.add(Box.createVerticalStrut(20));
		this.add(Box.createVerticalGlue());
		this.add(valido);
	}
	
	public boolean isConfigValid() {
		return datosValidos;
	}
}
