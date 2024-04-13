package modelo;

public enum CategoriaCrom {

	BINARIO, 		// Los genes se codifican en binario
	REAL,			// Los genes se tratan como números reales
	PERMUTACION,	// El cromosoma es una permutación de un conjunto fijo de genes
	ARBOL,			// El cromosoma es un árbol con genes somo nodos
	GENERICA		// Los genes pueden tener cualquier tipo de valor
}
