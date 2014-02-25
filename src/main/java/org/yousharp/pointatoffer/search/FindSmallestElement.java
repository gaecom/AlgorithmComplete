package org.yousharp.pointatoffer.search;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 问题描述：
 * 一个有序序列，将其前n个元素，移到序列的最后，求新序列的最小值；
 * 比如移动前的序列1，2，3，4，5，6，移动后的序列为3，4，5，1，2，3
 * 求新序列的最小值。
 * User: Daniel
 * Date: 13-12-16
 * Time: 上午8:17
 */
public class FindSmallestElement {
	private static Logger logger = LoggerFactory.getLogger(FindSmallestElement.class);

	/**
	 * it is a binary-search like method.
	 * 1. get the middle element of the array;
	 * 2. compare the middle element to the first (or the last) element
	 * 3. adjust the first (or the last) element
	 *
	 * @param data   the array holding the elements
	 * @param length length of the array
	 * @return
	 */
	private int execute(int[] data, int length) {
		int first = 0;
		int last = length;
		int mid = (first + last) / 2;

		// if zero element is move to the back, then the first element is the smallest
		if (data[first] < data[last]) {
			return data[first];
		}

		// when first + 1 = last, last is the smallest element
		while (first < last - 1) {
			if (data[first] == data[last] && data[first] == data[mid]) {
				return searchByTraverse(data, first, last);     // can be recursive
			}

			if (data[first] <= data[mid]) {  // the smallest element is in the right part
				first = mid;
			} else {        // the smallest element is int left part
				last = mid;
			}
			mid = (first + last) / 2;
		}
		return data[last];
	}

	/**
	 * search for the smallest element by traversing the array
	 *
	 * @param data
	 * @param first
	 * @param last
	 * @return
	 */
	private int searchByTraverse(int[] data, int first, int last) {
		int min = data[first];
		for (int i = first; i <= last; i++) {
			if (data[i] < min) {
				min = data[i];
			}
		}
		return min;
	}

	/**
	 * test
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		FindSmallestElement findSmallestElement = new FindSmallestElement();
		int[] data = new int[]{1, 0, 1, 1, 1};
		int length = data.length;
		logger.info("small: {}", findSmallestElement.execute(data, length));
	}
}

/**
 * 思路：
 * 1. 如果直接遍历数组，复杂度为O(n);
 * 2. 考虑到数组的前部分和后部分分别有序，可以使用二分查找的思路：first指向第一个元素，last指向最后一个元素，取中间元素mid：
 *  2.1 如果first < mid, 说明最小值在mid右侧，更新first = mid;
 *  2.2 如果first > mid，说明最小值在mid左侧，更新last = mid；
 *  2.3 如果first = mid，因为first >= last,则mid >= last；
 *      2.3.1 如果mid > last，则最小值仍在mid右侧，更新first = mid，此时与2.1合并；
 *      2.3.2 如果mid = last，则first = mid = last，无法确定最小值在mid的左侧还是右侧，可以顺序查抄，也可以递归；
 * 3. 当first和last相隔一个元素，即first为前部分有序序列的最后一个元素，而last为后部分有序序列的第一个元素，终止
 *  二分查找，last即为所求。
 */