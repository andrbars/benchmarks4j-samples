package org.andrbars.benchmarks4j.samples;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.andrbars.benchmarks4j.Benchmark;
import org.andrbars.benchmarks4j.Benchmarks;

/**
 * Multithread fibonacci
 *
 * @author andrbars
 */
public class sample3
{

	private static final Benchmark main = Benchmarks.get("fibonacci");

	public static void main(String[] args)
		throws InterruptedException, ExecutionException
	{
		int n = 8;
		int nt = 40;

		ExecutorService e = Executors.newFixedThreadPool(nt);

		Benchmarks.setAutoLogging(false);

		Callable<Long> work = ()
			->
			{
				long f = 0;

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
				return f;
		};

		ArrayList<Future<Long>> workResults = new ArrayList<>();
		for (int i = 0; i < nt; i++)
		{
			workResults.add(e.submit(work));
		}

		for (int i = 0; i < workResults.size(); i++)
		{
			workResults.get(i).get();
		}

		e.shutdown();

		System.out.println(
			Benchmarks.asString(true) + "\n"
			+ Benchmarks.asStringTree(true));
	}

	private static long fibonacci(int n)
	{
		Benchmark fibonacciBenchmark = Benchmarks.get("fibonacci-" + n);

		Benchmarks.start(fibonacciBenchmark);
		try
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
		finally
		{
			Benchmarks.stop(fibonacciBenchmark);
		}
	}
}
