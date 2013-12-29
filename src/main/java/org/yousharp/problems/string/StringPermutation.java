package org.yousharp.algorithm.basic.string;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * User: Daniel
 * Date: 13-12-29
 * Time: 上午8:10
 */
public class StringPermutation {
	private static Logger logger = LoggerFactory.getLogger(StringPermutation.class);

	/**
	 * permutation recursively
	 * @param string
	 * @param index
	 */
	private void permutationRecursively (char[] string, int index) {
		// it is a permutation
		if (index == string.length) {
			logger.info("string: {}", string);
		} else {
			for (int i = index; i < string.length; i++) {
				// swap the first char with each char in the string
				char charBak = string[index];
				string[index] = string[i];
				string[i] = charBak;

				// assume that the string begins with index+1 completely permutation
				permutationRecursively(string, index + 1);

				// swap back
				char charBak2 = string[index];
				string[index] = string[i];
				string[i] = charBak2;
			}
		}
	}

	public void permutationByLibrary(char[] string) {

	}

	public static void main(String[] args) {
		StringPermutation sp = new StringPermutation();

		String string = "abcd";
		char[] charArray = string.toCharArray();

		sp.permutationRecursively(charArray, 0);
	}

}
