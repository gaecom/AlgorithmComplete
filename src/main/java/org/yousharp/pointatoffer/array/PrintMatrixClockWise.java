package org.yousharp.pointatoffer.array;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 问题描述：输入一个矩阵（二维数组），按照顺时针的方向打印矩阵；
 *
 * User: lingguo
 * Date: 14-3-25
 * Time: 下午9:34
 */
public class PrintMatrixClockWise {

	private static Logger logger = LoggerFactory.getLogger(PrintMatrixClockWise.class);

	/**
	 * print the matrix clockwise.
	 * divide the process into four steps: left->right, top->bottom, right->left and bottom->top,
	 * we nee an extra matrix to mark if the current element is visited.
	 *
	 * @param matrix    the matrix to be printed clockwise
	 * @param rows  number of rows of the matrix
	 * @param cols  number of columns of the matrix
	 */
	public static void print(int[][] matrix, int rows, int cols) {
		// all flags are initialized to false, meaning not visited
		boolean[][] visited = new boolean[rows][cols];
		for (int i = 0; i < rows; i ++) {
			for (int j = 0; j < cols; j++) {
				visited[i][j] = false;
			}
		}

		int i = 0;
		int j = -1;
		int k = 0;
		// loop through the matrix
		while (k++ < rows * cols) {
			// path: left to right
			// we try to check if the next element is accessible, if yes, visit it and mark it
			while (j + 1 < cols && !visited[i][j+1]) {
				logger.info("{} ", matrix[i][++j]);
				visited[i][j] = true;
			}
			// path: top to bottom
			while (i + 1 < rows && !visited[i+1][j]) {
				logger.info("{} ", matrix[++i][j]);
				visited[i][j] = true;
			}

			// path: right to left
			while (j - 1 >= 0 && !visited[i][j-1]) {
				logger.info("{} ", matrix[i][--j]);
				visited[i][j] = true;
			}
			// path: bottom to top
			while (i - 1 >= 0 && !visited[i-1][j]) {
				logger.info("{} ", matrix[--i][j]);
				visited[i][j] = true;
			}
		}
	}

	public static void main(String[] args) {
		int[][] matrix = new int[][] {
				{1, 2, 3, 4},
				{5, 6, 7, 8},
				{9, 10, 11, 12},
				{13, 14, 15, 16}};

		PrintMatrixClockWise.print(matrix, 4, 4);
	}
}
