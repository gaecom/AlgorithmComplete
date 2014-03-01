package org.yousharp.pointatoffer.array;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 问题描述：一个二维数组，每一行从左到右自增有序，每一列从上到下自增有序；
 * 完成一个函数，输入一个二维数组和一个整数，在该数组中查找该整数是否存在。
 * 2 5 7 9 10
 * 3 8 9 11 13
 * 5 9 12 15 20
 * 9 11 32 40 50
 * User: Daniel
 * Date: 13-12-5
 * Time: 下午11:16
 */
public class SearchInTwoDimensionArray {
	/**
	 * search function
	 *
	 * @param array  the two dimension array
	 * @param row    the row of the array
	 * @param column the column of the array
	 * @param key    the key to search for in the array
	 * @return
	 */
	private static boolean searchInTwoDimensionArray(int[][] array, int row, int column, int key) {
		boolean isFound = false;
		if (null == array || 0 == array.length || row <= 0 || column <= 0) {
			return isFound;
		}

		// core of the algorithm
		int x = row - 1, y = 0;
		while (x >= 0 && y <= column - 1) {
			if (array[x][y] == key) {       // found
				isFound = true;
				return isFound;
			} else if (array[x][y] < key) {     // the current column is ignored
				y++;
			} else {            // the current row is ignored
				x--;
			}
		}
		return isFound;
	}

	public static void main(String[] args) {
		int[][] array = {{2, 5, 7, 9, 10}, {3, 8, 9, 11, 13}, {5, 9, 12, 15, 20}, {9, 11, 32, 40, 50}};
		int row = 4, column = 5;
		Set<Integer> keys = new LinkedHashSet<Integer>();
		keys.add(10);
		keys.add(50);
		keys.add(80);

		for (int key : keys) {
			boolean isFound = searchInTwoDimensionArray(array, row, column, key);
			System.out.println(isFound);
		}
	}
}

/**
 * 思路：以数组的左下角（或右上角）为基准，如果key比该值大，则当前列可以忽略；
 * 如果key比该值小，则当前行可以忽略；这样每次都会去掉一行或者一列，时间复杂度为O(n).
 */