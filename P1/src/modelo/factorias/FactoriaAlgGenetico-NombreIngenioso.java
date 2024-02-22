package modelo.factorias;

import modelo.AlgGenetico;
import modelo.CalibracionYPrueba;
import modelo.HolderTable;
import modelo.MichalewiczReal;

public class FactoriaAlgGenetico {

	private AlgGenetico<Double> alg;
	private AlgGenetico<?>[] algPosibles = {new CalibracionYPrueba(),
										 	new HolderTable(),
										 	new MichalewiczReal()
										 	};
	
	public AlgGenetico<?> generar(Class<?> func) {
		return null; //TODO
	}
	
	public AlgGenetico<?>[] getAlgPosibles() {
		return algPosibles;
	}
}
