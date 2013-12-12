package practice.corejava;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import practice.Utils.YouConstants;

/**
 * input and output methods
 * User: Daniel
 * Date: 13-12-12
 * Time: 上午7:58
 */
public class InputOutput {
	private static Logger logger = LoggerFactory.getLogger(InputOutput.class);

	/**
	 * Scanner: util class for input since Java 5, can read input from console and file
	 * methods: hasNext(), hasNextLong(), hasNextLine()
	 */
	private static void scannerInput() {
		// console scanner
		Scanner stdScanner = new Scanner(System.in);
		// default delimiter is blank
		stdScanner.useDelimiter("\n");
		while (stdScanner.hasNextLong()) {
			logger.info("{}", stdScanner.nextLong());
		}
		if (null != stdScanner) {
			stdScanner.close();
		}

		// file scanner
		File file = null;
		Scanner fileScanner = null;
		try {
			file = new File(YouConstants.INPUT_FILE_PATH);
			fileScanner = new Scanner(file);
			while (fileScanner.hasNextLine()) {
				logger.info("{}", fileScanner.nextLine());
			}
		} catch (FileNotFoundException e) {
			logger.info("error", e);
		} finally {
			if (null != fileScanner) {
				fileScanner.close();
			}
		}
	}


	public static void main(String... args) {
		InputOutput.scannerInput();
	}
}
