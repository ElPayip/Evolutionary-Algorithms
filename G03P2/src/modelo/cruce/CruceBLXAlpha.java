package modelo.cruce;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import modelo.genes.Gen;
import utils.Pair;
import vista.ConfigPanel.DoubleOption;
import vista.ConfigPanel.Option;

public class CruceBLXAlpha implements Cruce<Double> {

	private double alpha;
	
	public CruceBLXAlpha() {
		alpha = 0.5;
	}

	@Override
	public Pair<List<Gen<Double>>, List<Gen<Double>>> cruzar(List<Gen<Double>> crom1, List<Gen<Double>> crom2) {
		
		List<Gen<Double>> h1 = new ArrayList<>();
		List<Gen<Double>> h2 = new ArrayList<>();
		for (Gen<Double> g : crom1)
			h1.add(g.clone());
		for (Gen<Double> g : crom2)
			h2.add(g.clone());
		
		Random r = new Random();
		
		for (int i = 0; i < h1.size(); ++i) {
			Double cMax = Math.max(h1.get(i).getValor(), h2.get(i).getValor()),
				   cMin = Math.min(h1.get(i).getValor(), h2.get(i).getValor());
			Double I = cMax - cMin;
			
			h1.get(i).setValor(r.nextDouble() * ((cMax + I * alpha) - (cMin + I * alpha)));
			h2.get(i).setValor(r.nextDouble() * ((cMax + I * alpha) - (cMin + I * alpha)));
		}
		
		return new Pair<>(h1, h2);
	}
	
	@Override
	public String toString() {
		return "Cruce BLXAlpha";
	}
	
	@Override
	public <T> List<Option<T>> getExtraOpts() {
		List<Option<T>> extras = new ArrayList<>();
		extras.add(new DoubleOption<T>("alpha", "alpha", "alpha", 0, 1000));
		return extras;
	}
	
	public double getAlpha() {
		return alpha;
	}

	public void setAlpha(double alpha) {
		this.alpha = alpha;
	}

	@Override
	public Cruce<Double> clone() {
		return new CruceBLXAlpha();
	}

}
