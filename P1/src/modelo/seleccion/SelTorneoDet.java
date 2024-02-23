package modelo.seleccion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import modelo.individuo.Individuo;
import vista.ConfigPanel.IntegerOption;
import vista.ConfigPanel.Option;

public class SelTorneoDet implements Seleccion {
    private Integer k;

	@Override
	public <T> List<Individuo<T>> seleccionar(List<Individuo<T>> individuos) {
		if (k == null)
			throw new RuntimeException("Hay que inicializar SelTorneoDet con setK");

    if (k < 2 || k > 3)
      throw new IllegalArgumentException("El tamaño del torneo debe ser 2 o 3");
		
		Random rnd = new Random();
		List<Individuo<T>> supervs = new ArrayList<>(individuos.size());
		
		for (int i = 0; i < individuos.size(); ++i) {
            List<Individuo<T>> torneo = new ArrayList<>();
            for (int j = 0; j < k; ++j) {
                int idx = rnd.nextInt(individuos.size());
                torneo.add(individuos.get(idx));
            }
            Individuo<T> seleccionado = null;
            double fitnessSel = Double.MIN_VALUE;
            for(int j = 0; j < k; ++j){
                if(torneo.get(j).getFitness() > fitnessSel){
                    seleccionado = torneo.get(j);
                    fitnessSel = torneo.get(j).getFitness();
                }
            }
            supervs.add(seleccionado);
        }
		return supervs;
	}
	
	public void setK(Integer k) {
		this.k = k;
	}
	
	public Integer getK() {
		return k;
	}

	@Override
	public String getName() {
		return "Torneo determinístico";
	}

	@Override
	public <T> List<Option<T>> getExtraOpts() {
		List<Option<T>> extras = new ArrayList<>();
		extras.add(new IntegerOption<T>("k", "k", "k", 0, 100));
		return extras;
	}
}
