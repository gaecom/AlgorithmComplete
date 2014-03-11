package org.yousharp.pointatoffer.array;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 问题：给定数组，调整数组中的数字，使得所有的奇数都位于数组的前部分，所有的偶数都位于数组的
 * 后部分。
 * User: lingguo
 * Date: 14-3-11
 * Time: 下午10:46
 */
public class MakeOddBeforeEven {
	private static Logger logger = LoggerFactory.getLogger(MakeOddBeforeEven.class);

	/**
	 * traverse the array using two pointers, the first one points to the first number of
	 * the array and the second one points to the last number of the array. traverse from
	 * both side.
	 * @param data
	 * @param length
	 */
	public static void adjust(int[] data, int length) {
		int first = 0;
		int last = length - 1;
		while (first <= last) {
			// find the first even number
			while (isOdd(data[first])) {
				first++;
			}
			// find the first odd number
			while (!isOdd(data[last])) {
				last--;
			}
			// swap odd and even number
			if (first <= last) {
				int tmp = data[first];
				data[first] = data[last];
				data[last] = tmp;
			}
		}
	}

	// check if the number is odd
	public static boolean isOdd(int num) {
		if ((num & 0x01) == 1) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		int[] data = new int[] {3, 2, 6, 9, 1, 23, 42};
		MakeOddBeforeEven.adjust(data, data.length);
		logger.info("{}", Arrays.toString(data));
	}
}

/**
 * 思路：使用两个指针，分别指向数组的首元素和尾元素，首指针判断当前元素是否尾奇数，尾元素判断当前元素
 * 是否尾偶数，如果达到交换的条件，则奇偶互换。这里应该将判断逻辑抽取为独立的函数。
 */