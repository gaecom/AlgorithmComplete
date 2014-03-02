package org.yousharp.pointatoffer.array;

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
		for (int i = 2; i <= n; i++) {
			f = f0 + f1;
			f0 = f1;
			f1 = f;
		}
		return f;
	}

	/**
	 * the coefficient follows a rule
	 * f(n) = f(n-1) + f(n-2) = (1+1)f(n-2) + f(n-3)
	 * = (2+1)f(n-3) + 2f(n-4) = ...
	 * which means: f(n) = af(x) + bf(x-1) = (a+b)f(x-1) + af(x-2)
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
		long a = 1, b = 0;
		for (int i = 2; i <= n; i++) {
			long tmp = a;
			a = a + b;
			b = tmp;
		}
		return a;
	}

	public static void main(String[] args) {
		Fibonacci fibonacci = new Fibonacci();
		int n = 20;
		logger.info("recursive result = {}", fibonacci.recursiveMethod(n));
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
 *
 *  类似的题目：
 *  1. 青蛙跳台阶：一只青蛙一次可以跳一级台阶，也可以跳2级台阶，请问跳n级台阶，一共有多少中跳法？
 *  思路：n级是f(n)，第一次跳一级，后面n-1级，f(n-1)；第一次跳二级，后面n-2级，f(n-2)；
 *
 *  2. 矩形覆盖：我们可以使用2x1的矩形去横着或者竖着去覆盖更大的矩形。请问，用8个2x1的矩形无重叠第覆盖
 *  一个2x8的大矩形，总共有多少种方法？
 *  思路：覆盖2x8的矩形的方法记为f(8)，如果横着覆盖，剩下的为f(6)，如果竖着覆盖，剩下的为f(7)，所以一共为：
 *  f(8) = f(7) + f(6)
 *
 */