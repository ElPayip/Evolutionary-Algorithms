package modelo.bloating;

import java.util.ArrayList;
import java.util.List;

import modelo.individuo.Individuo;
import vista.ConfigPanel.Option;

public class BloatingPBF implements ControlBloating {

	@Override
	public <T> void controlar(List<Individuo<T>> poblacion) {
		
		List<Double> sizesL = new ArrayList<Double>(poblacion.size());
		List<Double> fitnessL = new ArrayList<Double>(poblacion.size());
		
		for (Individuo<T> ind : poblacion) {
			sizesL.add((double) ind.getValores().size()); //Casteo para poder reutilizar la función media
			fitnessL.add(ind.getFitness());
		}
		
		double k = covarianza(sizesL, fitnessL) / varianza(sizesL);
		
		for(Individuo<T> ind : poblacion) {
			double fitnessNuevo = ind.getFitness() + k * ind.getValores().size();
			ind.setFitness(fitnessNuevo);
		}
	}
	
	private double covarianza(List<Double> sizesL, List<Double> fitnessL) {
		double suma = 0;
		double mediaSizes = media(sizesL), mediaFitness = media(fitnessL);
		int n = sizesL.size();
		
		for (int i = 0; i < n; i++) {
            suma += (sizesL.get(i) - mediaSizes) * (fitnessL.get(i) - mediaFitness);
        }
		
		return suma / (n - 1);
	}
	
	private double varianza(List<Double> sizesL) {
		double suma = 0;
        double media = media(sizesL);
        int n = sizesL.size();

        for (int i = 0; i < n; i++) {
            suma += Math.pow(sizesL.get(i) - media, 2);
        }

        return suma / (n - 1);
	}
	
	private double media(List<Double> poblacion) {
		double suma = 0;
		int n = poblacion.size();
		
		for (int i = 0; i < poblacion.size(); ++i) {
			suma += poblacion.get(i);
		}
		
		return suma / n;
	}
	
	public ControlBloating clone() {
		return new BloatingPBF();
	}

	@Override
	public <T> List<Option<T>> getExtraOpts() {
		return null;
	}
}
