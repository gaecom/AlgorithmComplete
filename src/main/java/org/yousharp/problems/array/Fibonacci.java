package org.yousharp.problems.array;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 问题描述：
 * 求Fibonacci序列之和：
 * |--    0 (n = 0)
 * f(n) = |--    1 (n = 1)
 * |--    f(n-1) + f(n-2) (n >= 2)
 * User: Daniel
 * Date: 13-12-17
 * Time: 上午8:03
 */
public class Fibonacci {
	private static Logger logger = LoggerFactory.getLogger(Fibonacci.class);

	/**
	 * recursive method according to the definition
	 *
	 * @param n
	 * @return
	 */
	private long recursiveMethod(int n) {
		if (0 == n) {
			return 0;
		}
		if (1 == n) {
			return 1;
		}
		return (recursiveMethod(n - 1) + recursiveMethod(n - 2));
	}

	/**
	 * iterative method
	 * to get f(n), we need to iterate n-1 times
	 *
	 * @param n
	 * @return
	 */
	private long iterativeMethod(int n) {
		if (0 == n) {
			return 0;
		}
		if (1 == n) {
			return 1;
		}

		long f0 = 0;
		long f1 = 1;
		long f = 0;
		for (int i = 0; i < n - 1; i++) {
			f = f0 + f1;
			f0 = f1;
			f1 = f;
		}
		return f;
	}

	/**
	 * the coefficient follows a rule
	 * f(n) = f(n-1) + f(n-2) = (1+1)f(n-2) + f(n-2)
	 * = (2+1)f(n-2) + 2f(n-3) = ...
	 * which means: f(n) = af(x) + bf(x-1) = (a+b)f(x-1) + bf(x-2)
	 *
	 * @param n
	 * @return
	 */
	private long coefficientMethod(int n) {
		if (0 == n) {
			return 0;
		}
		if (1 == n) {
			return 1;
		}

		// change the coefficient
		long a = 1, b = 1;
		for (int i = 0; i < n - 2; i++) {
			long tmp = a;
			a = a + b;
			b = tmp;
		}
		return a;
	}

	public static void main(String[] args) {
		Fibonacci fibonacci = new Fibonacci();
		int n = 20;
//		logger.info("recursive result = {}", fibonacci.recursiveMethod(n));
		logger.info("iterative result = {}", fibonacci.iterativeMethod(n));
		logger.info("coefficient result = {}", fibonacci.coefficientMethod(n));
	}
}

/**
 *  思路：三种方法：
 *  1. 直接根据定义递归，这是自顶向下的思考方式；
 *  2.  采用自底向上的思考方式，先求f2，再求f3，...，直到fn
 *  3.   仍然是自顶向下，根据系数的规律，每向下一级，两个系数的变化都是有规律的。 *
 *  说明：递归虽然简单，但是n较大时，容易栈溢出。
 */