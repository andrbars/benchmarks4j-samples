package org.andrbars.benchmarks4j.samples;

import org.andrbars.benchmarks4j.Benchmark;
import org.andrbars.benchmarks4j.Benchmarks;

/**
 *
 * @author andrbars
 */
public class sample1
{

	private static final Benchmark main = Benchmarks.get("fibonacci");

	public static void main(String[] args)
	{
		long f;
		int n = 8;

		Benchmarks.setAutoLogging(false);

		Benchmarks.start(main);
		try
		{
			f = fibonacci(n);
		}
		finally
		{
			Benchmarks.stop(main);
		}

		System.out.println("fibonacci(" + n + ")=" + f);
		System.out.println(Benchmarks.asString(true));
	}

	private static long fibonacci(int n)
	{
		if (n == 0)
		{
			return 1;
		}
		if (n == 1)
		{
			return 1;
		}
		return fibonacci(n - 1) + fibonacci(n - 2);
	}

}
