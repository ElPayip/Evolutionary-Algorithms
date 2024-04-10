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
	ConfigPanel<FactoriaAlgGenetico> config;
	MainWindow window;

	public ParamsPanel(MainWindow win, FactoriaAlgGenetico factoria) {
		this.factoria = factoria;
		window = win;
		initGUI();
	}
	
	private void initGUI() {
		config = new ConfigPanel<FactoriaAlgGenetico>();
		
		factoria.configurar(config);
		config.setTarget(factoria);
		config.initialize();
		
		final String textoTodoValido = "Todos los campos OK";
		final String textoHayErrores = "Hay errores en algunos campos";
		final JLabel valido = new JLabel(textoHayErrores);
		valido.setText(config.isAllValid() ? textoTodoValido: textoHayErrores);
		// este evento se lanza cada vez que la validez cambia
		config.addConfigListener(new ConfigListener() {
			@Override
			public void configChanged(boolean isConfigValid) {
				valido.setText(isConfigValid ? textoTodoValido: textoHayErrores);		
				window.activarBotones(isConfigValid);
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
	
	public void confirmParams() {
		config.setTarget(factoria);
		config.copyUpdate();
	}
}
