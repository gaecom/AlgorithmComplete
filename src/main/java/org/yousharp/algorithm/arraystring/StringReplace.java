package org.yousharp.algorithm.arraystring;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 问题描述：
 * 输入一个字符串，将其中的空格都替换为%20
 * User: Daniel
 * Date: 13-12-8
 * Time: 上午9:39
 */
public class StringReplace {
	private static Logger logger = LoggerFactory.getLogger(StringReplace.class);

	private static final char BLANK_CHAR = ' ';
	private static final String BLANK_STRING = " ";
	private static final String REPLACE_STRING = "%20";

	/**
	 * replace using StringBuilder
	 * @param src
	 * @return
	 */
	private static String replaceByBuilder(String src) {
		if (StringUtils.isEmpty(src)) {
			return null;
		}
		StringBuilder dest =new StringBuilder();
		for (char c: src.toCharArray()) {
			if (BLANK_CHAR == c) {
				dest.append(REPLACE_STRING);
			} else {
				dest.append(c);
			}
		}
		return dest.toString();
	}

	/**
	 * replace using java library
	 * @param src
	 * @return
	 */
	private static String replaceByLibrary(String src) {
		if (StringUtils.isEmpty(src)) {
			return null;
		}

		String dest = src.replaceAll(BLANK_STRING, REPLACE_STRING);
		return dest;
	}

	/**
	 * test
	 * @param args
	 */
	public static void main(String[] args) {
		String schoolName = "Nankai University and Tianjin University";
		String replaceByBuilder = replaceByBuilder(schoolName);
		String replaceByLibrary = replaceByLibrary(schoolName);
		logger.info("replaceByBuilder: {}, replaceByLibrary: {}", replaceByBuilder, replaceByLibrary);
	}
}

/**
 * 考察点：如果可以利用辅助数组，很简单，依次替换即可；如果必须在原串上操作，则根据空格的数量
 * 计算新串的容量，然后在原串上倒序复制即可。
 * 因为Java中字符串不是以\0结尾，所以这里采用StringBuilder 或者利用库函数，很方便实现。
 */