package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import modelo.AlgGenetico;
import modelo.factorias.FactoriaAlgGenetico;

public class MainWindow extends JFrame {
    
	private static final long serialVersionUID = 1L;
	
	FactoriaAlgGenetico factoria;
	
	GraphPanel graphPanel;
	JButton bEjecutar;
	Thread thread;

    public MainWindow(FactoriaAlgGenetico factoria) {
        super ("Control de tráfico aéreo");
        this.factoria = factoria;
        initGUI();
    }

    private void initGUI() {
        JPanel mainPanel = new JPanel(new BorderLayout());

        ParamsPanel paramsPanel = new ParamsPanel(this, factoria);
        graphPanel = new GraphPanel();
        
        JPanel panelBotones = new JPanel(new FlowLayout());
        bEjecutar = new JButton("Ejecutar");
        bEjecutar.addActionListener((e) -> {
        	paramsPanel.confirmParams();
        	thread = new Thread(() -> run(factoria.generar()));
        	thread.start();
        });
        bEjecutar.setEnabled(true);
        panelBotones.add(bEjecutar);
        
        mainPanel.add(paramsPanel, BorderLayout.WEST);
        mainPanel.add(graphPanel, BorderLayout.CENTER);
        mainPanel.add(panelBotones, BorderLayout.SOUTH);
        
        setContentPane(mainPanel);
        
        this.pack();
        this.setSize(this.getWidth()+50, 700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    
    public void activarBotones(boolean x) {
    	bEjecutar.setEnabled(x);
    }
    
    private void run(AlgGenetico<?> alg) {
    	activarBotones(false);
    	try {
    		alg.ejecutar();
        	graphPanel.update(alg);
		} catch (Exception ex) {
			//JOptionPane.showInternalMessageDialog(null, ex.getMessage());
			ex.printStackTrace();
		}
    	activarBotones(true);
    }
}
