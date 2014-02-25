package org.yousharp.pointatoffer.array;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * add two members without using +, -, *, /
 * User: Daniel
 * Date: 14-1-14
 * Time: 下午9:30
 */
public class AdditionWithoutOperator {
	private static Logger logger = LoggerFactory.getLogger(AdditionWithoutOperator.class);

	/**
	 * give two numbers, printout their sum without using +,-,*,/
	 * @param firstNum
	 * @param secondNum
	 * @return
	 */
	public int add(int firstNum, int secondNum) {
		int bitXor = 0;
		int bitAnd = 0;
		while (0 != secondNum) {
			// get sum without carry
			bitXor = firstNum ^ secondNum;
			// get carry
			bitAnd = firstNum & secondNum;
			firstNum = bitXor;
			// carry should be shifted left
			secondNum = bitAnd << 1;
		}
		return firstNum;
	}

	public static void main(String[] args) {
		AdditionWithoutOperator addInstance = new AdditionWithoutOperator();
		int a = 10;
		int b = 45;

		logger.info("a + b: {}", addInstance.add(a, b));
	}
}

/**
 * 思路：不能使用+，-，*，/，那只能从位运算上考虑。
 *  正常的加法运算可以分为两步，第一步相加，不考虑进位，如45+38，得到73；
 *  第二步，仅考虑进位，如上述进位为1，在十位上；第三步，第一步和第二步的结果相加，
 *  得到83，即为最后的结果。
 *  在位上，也是同样的思路：先按位相加，不考虑进位，其实就是位的异或运算；然后按位求
 *  进位，即是与运算，在这两步的结果相加之前，需要将进位左移一位，因为进位总是向高一位
 *  进的。
 *
 */