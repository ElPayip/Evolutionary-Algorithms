package modelo.genes;

import java.util.List;

public class GenBinReal extends GenBinario<Double> {
	
	private int size;
	private double step;

	public GenBinReal(GenBinReal gen) {
		super(gen);
		this.size = gen.size;
		this.step = gen.step;
	}
	
	public GenBinReal(Double min, Double max, Double precision) {
		super(min, max, precision);
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
		if (size <= 0)
			calcSize();
		return size;
	}

	@Override
	public Gen<Double> clone() {
		return new GenBinReal(this);
	}
}
