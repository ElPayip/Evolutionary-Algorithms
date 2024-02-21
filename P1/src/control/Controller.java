package control;

import control.command.Command;
import modelo.AlgGenetico;

public class Controller {
	
	private FactoriaAlgGenetico<Double> fact;
	private AlgGenetico<?> alg = null;

	public Controller(FactoriaAlgGenetico<Double> fact) {
		this.fact = fact;
	}
	
	public void setAlgGenetico(FuncionAlgGenetico func) {
		alg = fact.generar(func);
	}
	
	public void runCommand(Command com) {
		com.ejecutar(alg);
	}
}