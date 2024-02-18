package modelo.mutacion;

import java.util.List;

public interface Mutacion<T> {

	public List<T> mutar(List<T> crom);
}
