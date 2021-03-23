/*
 * Created on 26.10.2015
 */
package ch.fhnw.algd1.simplearraylist.amortisation;

import java.util.List;

/**
 * @author Wolfgang Weck
 */
public class Measure {
	public static void main(String[] args) {
		measure(new SimpleArrayListConstFactor<>(), 10_000_000, "const factor");
		System.gc();
		measure(new SimpleArrayListConstFactor<>(), 100_000_000, "const factor");
		System.gc();
		measure(new SimpleArrayListConstDelta<>(), 100_000, "const delta");
		System.gc();
		measure(new SimpleArrayListConstDelta<>(), 1_000_000, "const delta");
		System.gc();
		System.out.println();
		measure(new SimpleArrayListConstFactor2<>(), 10_000_000, "const factor");
		System.gc();
		measure(new SimpleArrayListConstFactor2<>(), 100_000_000, "const factor");
		System.gc();
		measure(new SimpleArrayListConstDelta2<>(), 100_000, "const delta");
		System.gc();
		measure(new SimpleArrayListConstDelta2<>(), 1_000_000, "const delta");
		System.gc();
	}

	private static void measure(List<?> l, int n, String name) {
		long t = System.nanoTime();
		for (int i = 0; i < n; i++)
			l.add(i, null);
		t = System.nanoTime() - t;
		System.out.printf("%s adding %,d elements: %f ms, %f μs per element\n", name, n, (double)t / 1_000_000,
				(double)t / n / 1000);
	}
}
