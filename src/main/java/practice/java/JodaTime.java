package practice.java;

import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Joda-Time library
 * User: Daniel
 * Date: 13-12-8
 * Time: 下午5:57
 */
public class JodaTime {
	private static Logger logger = LoggerFactory.getLogger(JodaTime.class);

	/**
	 * LocalDate util
	 */
	public static void localDateUtil() {
		// get today and yesterday date string
		LocalDate now = new LocalDate();
		LocalDate yesterday = now.plusDays(-1);
		logger.info("today: {}", now.toString());       // today: 2013-12-08
		logger.info("yesterday: {}", yesterday.toString("yyyyMMdd"));   // yesterday: 20131207

		// get last Monday and last Sunday
		LocalDate lastMonday = now.plusWeeks(-1).dayOfWeek().withMinimumValue();
		LocalDate lastMonday2 = now.plusWeeks(-1).withDayOfWeek(DateTimeConstants.MONDAY);
		LocalDate lastSunday = now.plusWeeks(-1).dayOfWeek().withMaximumValue();
		LocalDate lastSunday2 = now.plusWeeks(-1).withDayOfWeek(DateTimeConstants.SUNDAY);
		logger.info("last monday: {} and {}", lastMonday, lastMonday2); // last monday: 2013-11-25 and 2013-11-25
		logger.info("last sunday: {} and {}", lastSunday, lastSunday2); // last sunday: 2013-12-01 and 2013-12-01

		// get the first day and last day of the last month
		LocalDate firstDayOfLastMonth = now.plusMonths(-1).dayOfMonth().withMinimumValue();
		LocalDate firstDayOfLastMonth2 = now.plusMonths(-1).withDayOfMonth(1);
		LocalDate lastDayOfLastMonth = now.plusMonths(-1).dayOfMonth().withMaximumValue();
		logger.info("first day of last month: {} and {}", firstDayOfLastMonth, firstDayOfLastMonth2);   // first day of last month: 2013-11-01 and 2013-11-01
		logger.info("last day of last month: {}", lastDayOfLastMonth);  // last day of last month: 2013-11-30
	}

	public static void localDateTimeUtil() {
		LocalDateTime now = new LocalDateTime();
		logger.info("now: {}", now.toString("yyyy-MM-dd HH:mm:ss.SSS"));    // now: 2013-12-08 19:49:43.548

		LocalDateTime afterTenMinutes = now.plusMinutes(10);
		logger.info("after ten minutes: {}", afterTenMinutes);  // 2013-12-08T20:01:18.801

	}


}
