package modelo.mutacion;

import java.util.List;

import modelo.Configurable;
import modelo.genes.Gen;

public interface Mutacion<T>  extends Configurable, Cloneable{

	public void mutar(List<Gen<T>> crom);
	
	public Mutacion<T> clone();
}
