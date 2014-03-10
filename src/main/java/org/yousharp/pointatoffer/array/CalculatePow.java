package org.yousharp.pointatoffer.array;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 计算 pow(base, exponent)，即base的exponent次方；
 * 忽略大数问题，不得使用库函数
 * User: Daniel
 * Date: 13-12-18
 * Time: 下午11:30
 */
public class CalculatePow {
	private static Logger logger = LoggerFactory.getLogger(CalculatePow.class);

	/**
	 * get the value of pow(base, exponent)
	 *
	 * @param base
	 * @param exponent
	 * @return
	 */
	private double getPowValue(double base, int exponent) {
		if (doubleEqual(base, 0.0) == 0) {
			if (exponent < 0) {
				logger.info("error input");
				return -1;
			}
			return 1.0;
		}

		int absExponent = exponent;
		if (exponent < 0) {
			absExponent = -exponent;
		}

		double result = powWithUnsigned(base, absExponent);
		return result;
	}

	/**
	 * if exponent is even, b^e = b^(e/2) * b^(e/2)
	 * if exponent is odd, b^e = b^(e/2) * b^(e/2) * b
	 * this is more efficient than loop solution
	 * @param base
	 * @param exponent
	 * @return
	 */
	private double powWithUnsigned(double base, int exponent) {
		if (exponent == 1) {
			return base;
		}
		double result = powWithUnsigned(base, exponent >> 1);
		result *= result;
		if ((exponent & 0x1) == 1) {
			result *= base;
		}
		return result;
	}

	/**
	 * compare double to zero, be careful
	 * @param d1
	 * @param d2
	 * @return
	 */
	private int doubleEqual(double d1, double d2) {
		if ((d1 - d2) < 0.000001 && (d1 - d2) > -0.000001) {
			return 0;
		}
		return -1;
	}



	/**
	 * test
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		CalculatePow getPow = new CalculatePow();

		logger.info("{}", getPow.getPowValue(0.0, -2));
		logger.info("{}", getPow.getPowValue(4.5, 5));
		logger.info("{}", getPow.getPowValue(4.5, -5));
	}

}
