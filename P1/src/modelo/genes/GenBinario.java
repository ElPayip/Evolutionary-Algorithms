package modelo.genes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class GenBinario<T> extends Gen<T> {
	
	private List<Integer> alelos;
	
	public GenBinario(GenBinario<T> gen) {
		super(gen);
		alelos = gen.alelos;
	}
	
	public GenBinario(T min, T max, T precision) {
		super(min, max, precision);
	}
	
	@Override
	protected T initRandomVal() {
		alelos = new ArrayList<Integer>(getSize());
		Random rnd = new Random();
		for (int i = 0; i < alelos.size(); ++i)
			alelos.set(i, rnd.nextBoolean() ? 1 : 0);
		
		return getValor();
	}
	
	@Override
	public T getValor() {
		return decode(alelos);
	}
	
	protected abstract T decode(List<Integer> als);

	public abstract int getSize();
	
	public static List<Integer> getCromosomaBinario(List<GenBinario<?>> crom) {
		List<Integer> alelos = new ArrayList<>();
		for (GenBinario<?> g : crom) {
			alelos.addAll(g.alelos);
		}
		return alelos;
	}
	
	public static <G> int getCromSize(List<GenBinario<G>> crom) {
		int size = 0;
		for (GenBinario<G> g : crom) {
			size += g.getSize();
		}
		return size;
	}
	
	public static void bitFlip(GenBinario<?> gen, int pos) {
		gen.alelos.set(pos, (gen.alelos.get(pos) + 1) % 2);
	}
	
	public static <G> void bitFlip(List<GenBinario<G>> genes, int pos) {
		for (GenBinario<G> g : genes)
			if (pos >= g.getSize())
				pos -= g.getSize();
			else {
				bitFlip(g, pos);
				return;
			}
	}
	
	public static <G> List<Integer> getGenotipo(List<GenBinario<G>> genes) {
		List<Integer> alelos = new ArrayList<>();
		for (GenBinario<G> g : genes) {
			alelos.addAll(g.alelos);
		}
		return alelos;
	}
	
	public static <G> List<GenBinario<G>> setGenotipo(List<GenBinario<G>> genes, List<Integer> alelos) {
		int i = 0;
		for (GenBinario<G> g : genes) {
			g.alelos.clear();
			g.alelos.addAll(alelos.subList(i, i+g.getSize()));
			i += g.getSize();
		}
		return genes;
	}
}
