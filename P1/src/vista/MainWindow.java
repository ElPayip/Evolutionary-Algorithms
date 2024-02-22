package vista;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import modelo.factorias.FactoriaAlgGenetico;

public class MainWindow extends JFrame {
    
	private static final long serialVersionUID = 1L;
	
	FactoriaAlgGenetico factoria;

    public MainWindow(FactoriaAlgGenetico factoria) {
        super ("Programacion Evolutiva - Practica 1");
        this.factoria = factoria;
        initGUI();
    }

    private void initGUI() {
        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel paramsPanel = new ParamsPanel(factoria);
        JPanel graphPanel = new GraphPanel();
        
        mainPanel.add(paramsPanel, BorderLayout.WEST);
        mainPanel.add(graphPanel, BorderLayout.EAST);
        
        setContentPane(mainPanel);
        
        this.setSize(600, 600);
        this.setVisible(true);
    }
}
