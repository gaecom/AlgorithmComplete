package org.yousharp.pointatoffer.array;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 在旋转的数组中寻找最小值：有一个递增数组，将其前k个元素移到数组的末尾，
 * 求旋转后的数组的最小值。如 5, 8, 9, 2, 3可以看成是2, 3, 5, 8, 9的旋转。
 *
 * User: lingguo
 * Date: 14-3-1
 * Time: 下午8:53
 */
public class FindMinimumInRotatedArray {
	private static Logger logger = LoggerFactory.getLogger(FindMinimumInRotatedArray.class);

	/**
	 * find the minimum number of the rotation array based on the idea of
	 * binary search;
	 *
	 * @param data
	 * @param start
	 * @param end
	 * @return
	 */
	public static int binarySearchImpl(int[] data, int start, int end) {
		// this is the condition to exit
		if (start + 1 == end) {
			return data[end];
		}
		// get the middle number
		int mid = (start + end) >> 1;
		// the minimum locates on the right part of the middle number
		if (data[mid] > data[start]) {
			start = mid;
		// the minimum locates on the left part of the middle
		} else if (data[mid] < data[start]) {
			end = mid;
		} else if (data[mid] == data[start]) {
			// if the middle equals the start and the end, we have to search by traverse
			if (data[mid] == data[end]) {
				int min = data[start];
				for (int i = start; i <= end; i++) {
					if (data[i] < min) {
						min = data[i];
					}
				}
				return min;
			}
		}
		// search the new part of the array for the minimum
		return binarySearchImpl(data, start, end);
	}

	/**
	 * for test
	 * @param args
	 */
	public static void main(String[] args) {
		int[] data = {1, 0, 1, 1, 1};
		int min = FindMinimumInRotatedArray.binarySearchImpl(data, 0, data.length - 1);
		logger.info("min: {}", min);
	}
}

/**
 * 思路：遍历数组当然能找到最小的元素，但是在当前条件下，未必是最优的方法。
 * 因为数组的两部分分别有序，因此可以借鉴二分查找的思路。如果中间元素比首元素大，
 * 则最小元素应该在右部分，如果中间元素比首元素小，则最小元素应该在左部分；如果中间元素等于
 * 首元素，此时如果中间元素也等于尾元素，则无法确定最小元素的位置，此时需要顺序查找。
 *
 */