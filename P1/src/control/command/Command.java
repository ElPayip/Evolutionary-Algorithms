package control.command;

import modelo.AlgGenetico;

public interface Command {

	public void ejecutar(AlgGenetico<?> alg);
}
