package modelo.genes;

import java.util.List;

public class GenBinReal extends GenBinario<Double> {
	
	private int size;
	private double step;

	public GenBinReal(GenBinario<Double> gen) {
		super(gen);
		calcSize();
	}
	
	public GenBinReal(Double min, Double max, Double precision) {
		super(min, max, precision);
		calcSize();
	}
	
	private void calcSize() {
		size = (int) Math.ceil(Math.log10(((max - min) / precision) + 1) / Math.log10(2));
		step = (max - min) / (Math.pow(2, size) - 1);
	}

	@Override
	protected Double decode(List<Integer> als) {
		String str = "";
		for (int a : als)
			str += a;
		return min + step * Integer.parseInt(str, 2);
	}

	@Override
	public int getSize() {
		return size;
	}

	@Override
	protected Gen<Double> clonar() {
		return new GenBinReal(this);
	}
}
