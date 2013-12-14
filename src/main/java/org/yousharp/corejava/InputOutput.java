package org.corejava;

import java.io.*;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.Utils.YouConstants;

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

	/**
	 * BufferedReader: used to get input before Java 5
	 */
	public static void bufferedReaderInput() {
		// read from console
		InputStreamReader inStream = new InputStreamReader(System.in);
		BufferedReader consoleBufferedReader = null;
		String lineInConsole = null;
		try {
			consoleBufferedReader = new BufferedReader(inStream);
			while ((lineInConsole = consoleBufferedReader.readLine()) != null) {
				logger.info("line in console: {}", lineInConsole);
			}
		} catch (IOException ex) {
			logger.info("io ex", ex);
		} finally {
			try {
				if (null != inStream) {
					inStream.close();
				}
				if (null != consoleBufferedReader) {
					consoleBufferedReader.close();
				}
			} catch (IOException ex) {
				logger.info("io ex", ex);
			}
		}

		// read from file
		BufferedReader fileBufferedReader = null;
		File file = null;
		String lineInFile = null;
		FileReader fileReader = null;
		try {
			file = new File(YouConstants.INPUT_FILE_PATH);
			fileReader = new FileReader(file);
			fileBufferedReader = new BufferedReader(fileReader);
			while ((lineInFile = fileBufferedReader.readLine()) != null) {
				logger.info("lineInConsole in file: {}", lineInFile);
			}
		} catch (IOException ex) {
			try {
				if (null != fileReader) {
					fileReader.close();
				}
				if (null != fileBufferedReader) {
					fileBufferedReader.close();
				}
			} catch (IOException ex2) {
				logger.info("io ex", ex2);
			}
		}

	}


	public static void main(String... args) {
//		InputOutput.scannerInput();

		InputOutput.bufferedReaderInput();
	}
}
