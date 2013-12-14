package org.yousharp.mytest;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * User: Daniel
 * Date: 13-12-7
 * Time: 下午9:27
 */
public class SimpleTest {
	private static Logger logger = LoggerFactory.getLogger(SimpleTest.class);

	/**
	 * test for slf4j and log4j
	 */
	private static void slf4jTest() {
		Map<String, Object> nameMap = new HashMap<String, Object>();
		nameMap.put("name", "daniel");
		String name = "nkcoder";
		logger.info("hello, {}", nameMap);

	}


	private static void envTest() {
		Map<String, String> env = System.getenv();
		for (Map.Entry<String, String> entry: env.entrySet()) {
			logger.info("key: {}, value: {}", entry.getKey(), entry.getValue());
		}
	}


	public static void main(String... args) {
//		SimpleTest.slf4jTest();
//
//		final String src = "The standard Java libraries fail to provide enough methods for " +
//				"manipulation of its core classes.";
//		SimpleTest.stringReplaceTest(src);

//		JodaTime.localDateUtil();
//		JodaTime.localDateTimeUtil();

		SimpleTest.envTest();
	}



}
