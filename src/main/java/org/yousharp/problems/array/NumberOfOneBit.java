package org.yousharp.problems.array;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 问题描述：
 * 给定一个整数n，求n的二进制表示中，1的个数；
 * User: Daniel
 * Date: 13-12-17
 * Time: 下午9:13
 */
public class NumberOfOneBit {
	private static Logger logger = LoggerFactory.getLogger(NumberOfOneBit.class);

	/**
	 * check every bit in the number
	 *
	 * @param num
	 * @return
	 */
	private int methodOne(int num) {
		int flag = 1;
		int count = 0;
		while (flag != 0) {
			int x = (flag & num);
			if (x != 0) {
				count++;
			}
			flag = (flag << 1);
		}
		return count;
	}

	/**
	 * only check bits that is 1
	 *
	 * @param num
	 * @return
	 */
	private int methodTwo(int num) {
		int count = 0;
		while (0 != num) {
			num = (num & (num - 1));
			count++;
		}
		return count;
	}

	/**
	 * test
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		NumberOfOneBit numberOfOneBit = new NumberOfOneBit();

		int numOne = 9;
		int numTwo = -10;
		int numThree = -30;

		logger.info("{}", numberOfOneBit.methodOne(numTwo));
	}

}

/**
 * 思路：
 * 1. 将n每次右移一位，与1做&操作，但是，如果整数为有符号的负数，可能会陷入无限循环；
 * 2. 将1每次左移一位，与n做&操作，这样，遍历整数的每一位；
 * 3. n与n-1做&操作，每次可以消除n的最低位上的1，且只遍历n中为1的位。
 *
 *
 */
