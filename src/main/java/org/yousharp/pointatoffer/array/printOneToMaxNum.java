package org.yousharp.pointatoffer.array;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 描述：打印从1到最大的n位数，比如n为4，输出1，2，3一直到
 * 最大的4位数，即9999；
 *
 * User: lingguo
 * Date: 14-3-3
 * Time: 下午10:47
 */

public class PrintOneToMaxNum {
	private static Logger logger = LoggerFactory.getLogger(PrintOneToMaxNum.class);

	/**
	 *   方法一：过字符串模拟整数的加1操作：在最后一位上加一，同时记录进位，从低位向高位遍历，遇到没有进位或者
	 *   在最高位有进位，退出。
	 * @param data  保存整数的字符串，初始化各位都为‘0’
	 * @param length   字符串组的长度
	 * @return
	 */
	public static boolean addByOne(char[] data, int length) {
		boolean isOverflow = false;
		int carry = 0;
		for (int i = length - 1; i >= 0; i--) {
			int sum = data[i] - '0' + carry;
			// the last bit, add one
			if (i == length - 1) {
				sum++;
			}
			// carry out occurs
			if (sum >= 10) {
				if (i == 0) {
					isOverflow = true;  // overflow
					break;
				}
				// not overflow, alter data and carry
				data[i] = '0';
				carry = 1;
			} else {
				// no carry out, alter data
				data[i] = (char)(sum + '0');
				break;
			}
		}
		return isOverflow;
	}

	/**
	 *  方法一：环模拟加1操作，知道发生溢出，则遍历了所有的整数
	 * @param data  保存字符串数字的数组，初始化各位均为'0'
	 * @param length  数组的长度
	 */
	public static void stringMethod(char[] data, int length) {
		while (addByOne(data, length) == false) {
			System.out.println(Arrays.toString(data));
		}
	}

	/**
	 *  方法二：使用数组模拟，用递归的策略，假设除第一位之外的n-1位数已经准备好，则只需要将第一位
	 * 从0到9遍历一遍即可。
	 *
	 * @param data
	 * @param length
	 * @param index
	 */
	public static void recursionMethod(int[] data, int length, int index) {
		if (index == length) {
			printNumber(data, length);
			return;
		}
		for (int i = 0; i <= 9; i++) {
			data[index] = i;
			recursionMethod(data, length, index + 1);
		}
	}

	/**
	 *  方法二：果遍历到最后一位，打印该数字，注意去掉数字前缀0；
	 * @param data
	 * @param length
	 */
	public static void printNumber(int[] data, int length) {
		boolean zeroPrefix = true;
		for (int i = 0; i < length; i++) {
			if (zeroPrefix == true) {
				if (data[i] != 0) {
					zeroPrefix = false;
					System.out.print(data[i]);
				}
			} else {
				System.out.print(data[i]);
			}
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int length = 2;
		int[] data = new int[length];
//		PrintOneToMaxNum.recursionMethod(data, length, 0);

		char[] str = new char[] {'0', '0'};
		PrintOneToMaxNum.stringMethod(str, 2);
	}
}
