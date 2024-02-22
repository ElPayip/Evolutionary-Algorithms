package modelo;

import java.util.ArrayList;
import java.util.List;

import vista.ConfigPanel.InnerOption;
import vista.ConfigPanel.Option;

public interface Configurable {

	public default <T> List<Option<T>> configuracion(String campo) {
		List<Option<T>> opts = new ArrayList<>();
		opts.add(new InnerOption<T,Configurable>(
	  			  toString(),
	  			  toString(),
	  			  campo,
	  			  getClass()));
		
		List<Option<T>> extras = getExtraOpts();
		if (extras != null)
			opts.addAll(extras);
		return opts;
	}
	
	public <T> List<Option<T>> getExtraOpts();
}
