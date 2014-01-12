package org.yousharp.problems.string;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * given a string, print all the permutations
 * for example: abc, acb, bca, bac, cba, cab
 * User: Daniel
 * Date: 13-12-29
 * Time: 上午8:10
 */
public class StringPermutation {
	private static Logger logger = LoggerFactory.getLogger(StringPermutation.class);

	/**
	 * two parts: the first char and the remaining string, the permutation of which
	 * can by reached by recursion.
	 * swap the first char with each char in the current string
	 *
	 * @param string
	 * @param index
	 */
	private void permOne(char[] string, int index) {
		// it is a permutation
		if (index == string.length) {
			logger.info("perm1: {}", string);
		} else {
			for (int i = index; i < string.length; i++) {
				// swap the first char with each char in the string
				char charBak = string[index];
				string[index] = string[i];
				string[i] = charBak;

				// assume that the string begins with index+1 completely permutation
				permOne(string, index + 1);

				// swap back
				char charBak2 = string[index];
				string[index] = string[i];
				string[i] = charBak2;
			}
		}
	}

	/**
	 * this solution come from Princeton course:
	 * http://introcs.cs.princeton.edu/java/23recursion/Permutations.java.html
	 *
	 * @param prefix
	 * @param string
	 */
	public void permTwo(String prefix, String string) {
		int length = string.length();
		if (0 == length) {
			logger.info("perm2: {}", prefix);
		} else {
			for (int i = 0; i < length; i++) {
				permTwo(prefix + string.charAt(i), string.substring(0, i) + string.substring(i + 1, length));
			}
		}
	}

	public static void main(String[] args) {
		StringPermutation sp = new StringPermutation();

		String string = "abcd";
		char[] charArray = string.toCharArray();

//		sp.permOne(charArray, 0);
		sp.permTwo("", string);

	}

}
