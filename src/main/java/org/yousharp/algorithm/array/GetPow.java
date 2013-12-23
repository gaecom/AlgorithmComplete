package org.yousharp.algorithm.array;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 计算 pow(base, exponent)，即base的exponent次方；
 * 忽略大数问题，不得使用库函数
 * User: Daniel
 * Date: 13-12-18
 * Time: 下午11:30
 */
public class GetPow {
	private static Logger logger = LoggerFactory.getLogger(GetPow.class);

	/**
	 * get the value of pow(base, exponent)
	 * @param base
	 * @param exponent
	 * @return
	 */
	private double getPowValue(double base, int exponent) {
		// be aware of zero divisor
		if ((Double.compare(base, 0.00) == 0) && (exponent < 0)) {
			return 0.0;
		}

		// get result by loop, you can do it by recursion too
		double result = 1.0;
		for (int i = 0; i < Math.abs(exponent); i++) {
			result *= base;
		}

		// if exponent is less than 0
		if (exponent < 0) {
			result = 1 / result;
		}
		return result;
	}

	/**
	 * test
	 * @param args
	 */
	public static void main(String[] args) {
		GetPow getPow = new GetPow();

		logger.info("{}", getPow.getPowValue(0.0, -2));
		logger.info("{}", getPow.getPowValue(4.5, 5));
		logger.info("{}", getPow.getPowValue(4.5, -5));
	}

}
