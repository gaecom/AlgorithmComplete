package org.yousharp.pointatoffer.string;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.util.logging.resources.logging;

/**
 * 问题描述：
 * 输入一个字符串，将其中的空格都替换为%20
 * User: Daniel
 * Date: 13-12-8
 * Time: 上午9:39
 */
public class StringReplace {
	private static Logger logger = LoggerFactory.getLogger(StringReplace.class);

	public static char[] replaceBlanks(char[] original) {
		if (original == null || original.length == 0) {
			return original;
		}

		// find the number of blanks in original
		int numOfBlanks = 0;
		for (char c: original) {
			if (c == ' ') {
				numOfBlanks ++;
			}
		}
		if (numOfBlanks == 0) {
			return original;
		}
		int newSize = original.length + numOfBlanks * 2;
		char[] newString = new char[newSize];

		// replace core
		int j = newSize - 1;
		for (int i = original.length - 1; i >= 0; i--) {
			if (original[i] == ' ') {
				newString[j--] = '0';
				newString[j--] = '2';
				newString[j--] = '%';
			} else {
				newString[j--] = original[i];
			}
		}
		return newString;
	}

	/**
	 * test
	 *
	 * @param args
	 */
	public static void main(String[] args) {

		char[] original = "a b c".toCharArray();
		logger.info("{}", replaceBlanks(original));

	}
}

/**
 * 考察点：如果可以利用辅助数组，很简单，依次替换即可；如果必须在原串上操作，则根据空格的数量
 * 计算新串的容量，然后在原串上倒序复制即可。
 * 因为Java中字符串不是以\0结尾，如果允许，使用库函数String.replaceAll()或者使用StringBuilder.
 */