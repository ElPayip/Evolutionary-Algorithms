package modelo.factorias;

import java.util.List;

import modelo.AlgGenetico;
import modelo.Cortacesped;
import modelo.CortacespedGramatica;
import modelo.cruce.Cruce;
import modelo.inicializaciones.Inicializacion;
import modelo.mutacion.Mutacion;
import modelo.seleccion.Seleccion;
import vista.ConfigPanel;
import vista.ConfigPanel.BooleanOption;
import vista.ConfigPanel.ComplexOption;
import vista.ConfigPanel.DoubleOption;
import vista.ConfigPanel.IntegerOption;
import vista.ConfigPanel.Option;
import vista.ConfigPanel.StrategyOption;

public class FactoriaAlgGenetico {
	
	int nGeneraciones, tamPoblacion;
	double probCruce, probMutacion;

	private FactoriaCruce cruces;
	private FactoriaSeleccion selecciones;
	private FactoriaMutacion mutaciones;
	private FactoriaInicializacion<?> inicializaciones;
	private AlgGenetico<?> alg;
	private AlgGenetico<?>[] algPosibles = {new Cortacesped(), new CortacespedGramatica()};
	
	public FactoriaAlgGenetico() {
		cruces = new FactoriaCruce();
		selecciones = new FactoriaSeleccion();
		mutaciones = new FactoriaMutacion();
		inicializaciones = new FactoriaInicializacion<>();
		
		alg = algPosibles[0];
	}
	
	public AlgGenetico<?> generar() {
		return alg;
	}
	
	public void configurar(ConfigPanel<FactoriaAlgGenetico> config) {
		config.addOption(new StrategyOption<FactoriaAlgGenetico>(
				  "algoritmo genetico",
				  "algoritmo genetico",
				  "alg",
				  algPosibles));
		for (AlgGenetico<?> ag : algPosibles) {
			List<Option<FactoriaAlgGenetico>> agConf = ag.configuracion("alg");
			config.beginInner((ComplexOption<FactoriaAlgGenetico,?>) agConf.get(0));
			
			config.addInner(new IntegerOption<>(
						  "número de generaciones", 		// texto a usar como etiqueta del campo
						  "número de generaciones",       	// texto a usar como 'tooltip' cuando pasas el puntero
						  "nGeneraciones",  				// campo (espera que haya un getX y un setX)
						  1, 1000000))						// min y max (usa Integer.MIN_VALUE /MAX_VALUE para infinitos)
				  .addInner(new IntegerOption<>(
						  "tamaño de poblacion", 
						  "tamaño de poblacion", 
						  "tamPoblacion", 
						  1, 1000000))
				  .addInner(new DoubleOption<>(
						  "probabilidad de cruce",
						  "probabilidad de cruce",
						  "probCruce",
						  0, 1))
				  .addInner(new DoubleOption<>(
						  "probabilidad de mutacion",
						  "probabilidad de mutacion",
						  "probMutacion",
						  0, 1))
				  .addInner(new DoubleOption<>(
						  "% de elitismo",
						  "% de elitismo",
						  "elitismo",
						  0, 100, 100))
				  .addInner(new BooleanOption<>(
						  "escalado",
						  "escalado",
						  "escalado"));
			for (int i = 1; i < agConf.size(); ++i)
				config.addInner(agConf.get(i));
			
			config.addInner(new StrategyOption<FactoriaAlgGenetico>(
					"metodo de inicializacion", 
					"metodo de inicializacion", 
					"inicializacion",
					inicializaciones.getInicializaciones(ag.getCategoria()) ));
			for (Inicializacion<?> inicializacion : inicializaciones.getInicializaciones(ag.getCategoria()))
				innerConfig(config, inicializacion.configuracion("inicializacion"));
			
			config.addInner(new StrategyOption<FactoriaAlgGenetico>(
					"metodo de cruce", 
					"metodo de cruce", 
					"cruce",
					cruces.getCruces(ag.getCategoria()) ));
			for (Cruce<?> cruce : cruces.getCruces(ag.getCategoria()))
				innerConfig(config, cruce.configuracion("cruce"));
			
			config.addInner(new StrategyOption<FactoriaAlgGenetico>(
					"metodo de seleccion", 
					"metodo de seleccion", 
					"seleccion",
					selecciones.getSelecciones() ));
			for (Seleccion seleccion : selecciones.getSelecciones())
				innerConfig(config, seleccion.configuracion("seleccion"));
			
			config.addInner(new StrategyOption<FactoriaAlgGenetico>(
					"metodo de mutacion", 
					"metodo de mutacion", 
					"mutacion",
					mutaciones.getMutaciones(ag.getCategoria()) ));
			for (Mutacion<?> mutacion : mutaciones.getMutaciones(ag.getCategoria()))
				innerConfig(config, mutacion.configuracion("mutacion"));
			
			config.endInner();
		}
		config.endOptions();
	}

	private void innerConfig(ConfigPanel<FactoriaAlgGenetico> config, List<ConfigPanel.Option<FactoriaAlgGenetico>> opts) {
		config.beginInner((ComplexOption<FactoriaAlgGenetico,?>) opts.get(0));
		
		for (int i = 1; i < opts.size(); ++i)
			config.addInner(opts.get(i));
		
		config.endInner();
	}
	
	private void updateAlg() {
		
	}
	
	public AlgGenetico<?> getAlg() {
		return alg;
	}

	public void setAlg(AlgGenetico<?> alg) {
		this.alg = alg;
		updateAlg();
	}

	public int getnGeneraciones() {
		return nGeneraciones;
	}

	public void setnGeneraciones(int nGeneraciones) {
		this.nGeneraciones = nGeneraciones;
	}

	public int getTamPoblacion() {
		return tamPoblacion;
	}

	public void setTamPoblacion(int tamPoblacion) {
		this.tamPoblacion = tamPoblacion;
	}

	public double getProbCruce() {
		return probCruce;
	}

	public void setProbCruce(double probCruce) {
		this.probCruce = probCruce;
	}

	public double getProbMutacion() {
		return probMutacion;
	}

	public void setProbMutacion(double probMutacion) {
		this.probMutacion = probMutacion;
	}
}
