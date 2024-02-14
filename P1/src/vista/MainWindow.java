package vista;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import control.Controller;

public class MainWindow extends JFrame {
    
	private static final long serialVersionUID = 1L;
	
	Controller ctrl;

    public MainWindow(Controller ctrl) {
        super ("Programacion Evolutiva - Practica 1");
        this.ctrl = ctrl;
        initGUI();
    }

    private void initGUI() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        //TODO
    }
}
