package practice.java;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * User: Daniel
 * Date: 13-12-7
 * Time: 下午9:27
 */
public class Slf4jTest {
	private static Logger logger = LoggerFactory.getLogger(Slf4jTest.class);

	public static void main(String... args) {
		Map<String, Object> nameMap = new HashMap<String, Object>();
		nameMap.put("name", "daniel");
		String name = "nkcoder";
		logger.info("hello, {}", nameMap);
		logger.info("hello, {}", name);
		logger.info("hi, {}", 3);
	}
}
