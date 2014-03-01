package org.yousharp.pointatoffer.array;
// import

public class FindNumbersWithSum {
	// private static Logger logger = LoggerFactory.getLogger(FindNumbersWithSum.class);

	public void findSumOne(int[] data, int size, int sum) {
		int start = 0;
		int end = size - 1;

		while (start < end) {
			int thisSum = data[start] + data[end];
			if (thisSum == sum) {
				// logger.info("find pair: {} and {}", data[start], data[end]);
				System.out.println("paired: " + data[start] + ", " + data[end]);
				start++;
				end--;
			} else if (thisSum < sum) {
				start++;
			} else {
				end--;
			}
		}

	}

	public void findContinuosSum(int sum) {
		int start = 1;
		int end = 2;
		while ((start < end) && (end <= (sum + 1) / 2)) {
			int thisSum = 0;
			for (int i = start; i <= end; i++) {
				thisSum += i;
			}
			if (thisSum == sum) {
				System.out.println("got it: " + start + " to " + end);
				start++;
				end++;
			} else if (thisSum > sum) {
				start++;
			} else {
				end++;
			}
		}

	}

	public static void main(String[] args) {
		int[] data = new int[] {2, 3, 5, 8, 9, 11, 23};
		int sum = 13;

		FindNumbersWithSum findInstance = new FindNumbersWithSum();
		findInstance.findSumOne(data, 7, sum);
		findInstance.findContinuosSum(15);

	}
}


