package org.yousharp.pointatoffer.string;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * convert a String to int
 * User: Daniel
 * Date: 14-1-14
 * Time: 上午8:03
 */
public class StringToInt {
	private static Logger logger = LoggerFactory.getLogger(StringToInt.class);

	// max and min value of int type
	private final double INT_MAX = Math.pow(2, 31) - 1;
	private final double INT_MIN = -Math.pow(2, 31);

	/**
	 * input a string, and convert it to an integer
	 * @param str   the string to be converted
	 * @return  the integer of the string
	 */
	public int str2Int(String str) {
		if (null == str || str.isEmpty()) {
			logger.info("param error");
			return -1;
		}

		// the sign, the first char to convert
		boolean isPositive = true;
		int begin = 0;

		// check sign
		if ('+' == str.charAt(0)) {
			begin++;
		} else if ('-' == str.charAt(0)) {
			begin++;
			isPositive = false;
		}

		int i = 0;
		long num = 0;
		// convert each char to number
		for (i = begin; i < str.length(); i++) {
			if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
				num = 10 * num + str.charAt(i) - '0';
				// overflow
				if (num > INT_MAX || -num < INT_MIN) {
					logger.info("the value of num is overflow");
					return -1;
				}
			} else {
				break;
			}
		}

		// if convert successfully
		if (i == str.length()) {
			if (!isPositive) {
				num = -num;
			}
			return (int) num;
		}
		return -1;
	}

	/**
	 * for test
	 * @param args
	 */
	public static void main(String[] args) {
		StringToInt stringToInt = new StringToInt();
		String s1 = "435436";
		String s2 = "-43652";
		String s3 = "+234266";
		String s4 = "385948395797298472934729847398";
		String s5 = "-79375397549375398759384759354793";

		logger.info("s1: {}, s2: {}, s3: {}, s4: {}, s5: {}", stringToInt.str2Int(s1), stringToInt.str2Int(s2),
				stringToInt.str2Int(s3), stringToInt.str2Int(s4), stringToInt.str2Int(s5));
	}
}

/**
 *  将字符串转换为整数，主要是需要注意一些特殊情况：
 *  1. 字符串中不只是包含数字字符，包含其它特殊字符怎么处理。
 *  2. 如果以'+', '-'号开头；
 *  3. 溢出的处理；
 *  标准的库函数strToInt还包含很多其它特殊处理，如：
 *  1. 空格；
 *
 *
 *
 *
 *
 */