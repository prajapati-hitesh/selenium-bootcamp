package com.selenium.bootcamp.utillity;

import com.selenium.bootcamp.enums.Calendar;
import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.DateTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class DateUtility {

    public static String getCurrentTimeStampWithFormatAs(String format) {
        return new SimpleDateFormat(format).format(new Date());
    }

    public static String getCurrentTimeStamp() {
        return new SimpleDateFormat("dd-MM-yyyy - HH.mm.ss").format(new Date());
    }

    public static String getDateFormatted(Date date, String dateFormat) {
        return new SimpleDateFormat(dateFormat).format(date);
    }

    /**
     * To check whether the supplied <code>String</code> is of <code>Date</code> or not.
     * <br>
     * It makes use of following Date patterns for comparing. <br>
     *
     * <ul>
     *     <li>dd/MM/yyyy</li>
     *     <li>dd-MM-yyyy</li>
     *     <li>MM-dd-yyyy</li>
     *     <li>MM/dd/yyyy</li>
     *     <li>yyyy-MM-dd</li>
     *     <li>yyyy/MM/dd</li>
     * </ul>
     * <p>
     * Please add more pattern if you want to broaden date patterns.
     *
     * @param date Date as <code>String</code>
     * @return <code>true</code>, if the <code>String</code> matches Date Patterns, <code>false</code> otherwise
     */
    public static boolean isDate(String date) {
        String[] dateParsingPatterns = {"dd/MM/yyyy", "dd-MM-yyyy", "MM-dd-yyyy", "MM/dd/yyyy", "yyyy-MM-dd", "yyyy/MM/dd",
                "dd/MM/yyyy HH:mm:ss", "dd-MM-yyyy HH:mm:ss", "MM-dd-yyyy HH:mm:ss",
                "MM/dd/yyyy HH:mm:ss", "yyyy-MM-dd HH:mm:ss", "yyyy/MM/dd HH:mm:ss"};

        boolean isDate = false;

        try {
            DateUtils.parseDateStrictly(date.trim(), dateParsingPatterns);
            isDate = true;
        } catch (ParseException ignored) {
        }
        return isDate;
    }

    /**
     * To parse a date of type <code>String</code> as {@link Date}
     * <br>
     * It makes use of following Date patterns for comparing. <br>
     *
     * <ul>
     *     <li>dd/MM/yyyy</li>
     *     <li>dd-MM-yyyy</li>
     *     <li>MM-dd-yyyy</li>
     *     <li>MM/dd/yyyy</li>
     *     <li>yyyy-MM-dd</li>
     *     <li>yyyy/MM/dd</li>
     * </ul>
     * <p>
     * Please add more pattern if you want to broaden date patterns.
     *
     * @param dateAsString Date as <code>String</code>
     * @return {@link Date} if provided String is valid date format, <code>null</code> otherwise
     */
    public static Date toDate(String dateAsString) {
        String[] dateParsingPatterns = {"dd/MM/yyyy", "dd-MM-yyyy", "MM-dd-yyyy", "MM/dd/yyyy", "yyyy-MM-dd", "yyyy/MM/dd",
                "dd/MM/yyyy HH:mm:ss", "dd-MM-yyyy HH:mm:ss", "MM-dd-yyyy HH:mm:ss",
                "MM/dd/yyyy HH:mm:ss", "yyyy-MM-dd HH:mm:ss", "yyyy/MM/dd HH:mm:ss"};
        Date asDate = null;
        try {
            asDate = DateUtils.parseDateStrictly(dateAsString.trim(), dateParsingPatterns);
        } catch (ParseException ignored) {
        }
        return asDate;
    }

    /**
     * To convert Date time of string to {@link Date} object
     *
     * @param dateAsString   Date time string to be converted to date Object
     * @param dateTimeFormat Date Time format to use while conversion
     * @return {@link Date}, null otherwise
     */
    public static Date toDate(String dateAsString, String dateTimeFormat) {
        try {
            return new SimpleDateFormat(dateTimeFormat).parse(dateAsString);
        } catch (ParseException pEx) {
            return null;
        }
    }

    /**
     * To get date as a String in a particular date format.
     *
     * @param date       {@link Date} to convert into <code>String</code>
     * @param dateFormat Date format in which to convert {@link Date}
     * @return Date as a String format
     */
    public static String toString(Date date, String dateFormat) {
        return new SimpleDateFormat(dateFormat).format(date);
    }

    /**
     * To get date before X Days, Months or Years as a <code>String</code>
     * with date format passed as an argument to this function
     *
     * @param dateFormat Format in which to get the date
     * @param calendar   {@link Calendar} Months, Years or Days
     * @param interval   value in <code>Integer</code> to get date
     * @return Date as a <code>String</code>
     */
    public static String getDateBefore(String dateFormat, Calendar calendar, int interval) {
        String calculatedDate;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);

        if (calendar == Calendar.DAY) {
            calculatedDate = simpleDateFormat.format(new DateTime().minusDays(interval).toDate());
        } else if (calendar == Calendar.MONTH) {
            calculatedDate = simpleDateFormat.format(new DateTime().minusMonths(interval).toDate());
        } else if (calendar == Calendar.YEAR) {
            calculatedDate = simpleDateFormat.format(new DateTime().minusYears(interval).toDate());
        } else {
            throw new IllegalArgumentException("Date for parameter value is incorrect.");
        }
        return calculatedDate;
    }

    /**
     * To get date before X Days, Months or Years as a <code>Date</code>
     *
     * @param calendar {@link Calendar} Months, Years or Days
     * @param interval value in <code>Integer</code> to get date
     * @return Date before X month, Year or Days as {@link Date} value
     */
    public static Date getDateBefore(Calendar calendar, int interval) {
        Date calculatedDate;

        if (calendar == Calendar.DAY) {
            calculatedDate = new DateTime().minusDays(interval).toDate();
        } else if (calendar == Calendar.MONTH) {
            calculatedDate = new DateTime().minusMonths(interval).toDate();
        } else if (calendar == Calendar.YEAR) {
            calculatedDate = new DateTime().minusYears(interval).toDate();
        } else {
            throw new IllegalArgumentException("Date for parameter value is incorrect.");
        }
        return calculatedDate;
    }

    /**
     * To get date After X Days, Months or Years as a <code>Date</code>
     *
     * @param calendar {@link Calendar} Months, Years or Days
     * @param interval value in <code>Integer</code> to get date
     * @return Date before X month, Year or Days as {@link Date} value
     */
    public static Date getDateAfter(Calendar calendar, int interval) {
        Date calculatedDate;

        if (calendar == Calendar.DAY) {
            calculatedDate = new DateTime().plusDays(interval).toDate();
        } else if (calendar == Calendar.MONTH) {
            calculatedDate = new DateTime().plusMonths(interval).toDate();
        } else if (calendar == Calendar.YEAR) {
            calculatedDate = new DateTime().plusYears(interval).toDate();
        } else {
            throw new IllegalArgumentException("Date for parameter value is incorrect.");
        }
        return calculatedDate;
    }

    /**
     * To get date After X Days, Months or Years as a <code>String</code>
     * with date format passed as an argument to this function
     *
     * @param dateFormat Format in which to get the date
     * @param calendar   {@link DateFor} Months, Years or Days
     * @param interval   value in <code>Integer</code> to get date
     * @return Date as a <code>String</code>
     */
    public static String getDateAfter(String dateFormat, Calendar calendar, int interval) {
        String calculatedDate;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);

        if (calendar == Calendar.DAY) {
            calculatedDate = simpleDateFormat.format(new DateTime().plusDays(interval).toDate());
        } else if (calendar == Calendar.MONTH) {
            calculatedDate = simpleDateFormat.format(new DateTime().plusMonths(interval).toDate());
        } else if (calendar == Calendar.YEAR) {
            calculatedDate = simpleDateFormat.format(new DateTime().plusYears(interval).toDate());
        } else {
            throw new IllegalArgumentException("Date for parameter value is incorrect.");
        }
        return calculatedDate;
    }

    /**
     * To get date after X Day(s), Month(s) or Year(s) using a specific date
     * as a base or start date.
     *
     * @param date       {@link Date} to use as base
     * @param dateFormat Date format in which to return the calculated date
     * @param calendar   {@link Calendar} Days, Months, Years
     * @param interval   value in <code>Integer</code> to get date
     * @return Date as a <code>String</code>
     */
    public static String getDateAfter(Date date, String dateFormat, Calendar calendar, int interval) {
        String calculatedDate;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);

        if (calendar == Calendar.DAY) {
            calculatedDate = simpleDateFormat.format(new DateTime(date).plusDays(interval).toDate());
        } else if (calendar == Calendar.MONTH) {
            calculatedDate = simpleDateFormat.format(new DateTime(date).plusMonths(interval).toDate());
        } else if (calendar == Calendar.YEAR) {
            calculatedDate = simpleDateFormat.format(new DateTime(date).plusYears(interval).toDate());
        } else {
            throw new IllegalArgumentException("Date for parameter value is incorrect.");
        }
        return calculatedDate;
    }

    /**
     * To get date Before X Day(s), Month(s) or Year(s) using a specific date
     * as a base or start date.
     *
     * @param date       {@link Date} to use as base
     * @param dateFormat Date format in which to return the calculated date
     * @param calendar   {@link Calendar} Days, Months, Years
     * @param interval   value in <code>Integer</code> to get date
     * @return Date as a <code>String</code>
     */
    public static String getDateBefore(Date date, String dateFormat, Calendar calendar, int interval) {
        String calculatedDate;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);

        if (calendar == Calendar.DAY) {
            calculatedDate = simpleDateFormat.format(new DateTime(date).minusDays(interval).toDate());
        } else if (calendar == Calendar.MONTH) {
            calculatedDate = simpleDateFormat.format(new DateTime(date).minusMonths(interval).toDate());
        } else if (calendar == Calendar.YEAR) {
            calculatedDate = simpleDateFormat.format(new DateTime(date).minusYears(interval).toDate());
        } else {
            throw new IllegalArgumentException("Date for parameter value is incorrect.");
        }
        return calculatedDate;
    }

    /**
     * To get date after X Day(s), Month(s) or Year(s) using a specific date
     * as a base or start date.
     *
     * @param date     {@link Date} to use as base
     * @param calendar {@link Calendar} Days, Months, Years
     * @param interval value in <code>Integer</code> to get date
     * @return Date before X month, Year or Days as {@link Date} value
     */
    public static Date getDateAfter(Date date, Calendar calendar, int interval) {
        Date calculatedDate;

        if (calendar == Calendar.DAY) {
            calculatedDate = new DateTime(date).plusDays(interval).toDate();
        } else if (calendar == Calendar.MONTH) {
            calculatedDate = new DateTime(date).plusMonths(interval).toDate();
        } else if (calendar == Calendar.YEAR) {
            calculatedDate = new DateTime(date).plusYears(interval).toDate();
        } else {
            throw new IllegalArgumentException("Date for parameter value is incorrect.");
        }
        return calculatedDate;
    }

    /**
     * To get date Before X Day(s), Month(s) or Year(s) using a specific date
     * as a base or start date.
     *
     * @param date     {@link Date} to use as base
     * @param calendar {@link Calendar} Days, Months, Years
     * @param interval value in <code>Integer</code> to get date
     * @return Date before X month, Year or Days as {@link Date} value
     */
    public static Date getDateBefore(Date date, Calendar calendar, int interval) {
        Date calculatedDate;

        if (calendar == Calendar.DAY) {
            calculatedDate = new DateTime(date).minusDays(interval).toDate();
        } else if (calendar == Calendar.MONTH) {
            calculatedDate = new DateTime(date).minusMonths(interval).toDate();
        } else if (calendar == Calendar.YEAR) {
            calculatedDate = new DateTime(date).minusYears(interval).toDate();
        } else {
            throw new IllegalArgumentException("Date for parameter value is incorrect.");
        }
        return calculatedDate;
    }


    public static String getCurrentMonth() {
        LocalDate localDate = LocalDate.now();

        /* Methods that can be used
            localDate.getYear() --> This will return year in int. i.e. 2019,2018 etc
            localDate.getDayOfMonth() --> This will return current date. i.e. 1,2,3,4....31
            localDate.getDayOfWeek() --> This will return day of week. i.e. Monday, Tuesday
            localDate.getDayOfYear() --> This will return day of year for current date. i.e. 311th day for date 7th November 2019
            localDate.getMonth().name() --> This will return the current month as string. i.e. JANUARY, FEBRUARY .... DECEMBER
         */

        return localDate.getMonth().name();
    }

    public static int getCurrentDate() {
        LocalDate localDate = LocalDate.now();

        /* Methods that can be used
            localDate.getYear() --> This will return year in int. i.e. 2019,2018 etc
            localDate.getDayOfMonth() --> This will return current date. i.e. 1,2,3,4....31
            localDate.getDayOfWeek() --> This will return day of week. i.e. Monday, Tuesday
            localDate.getDayOfYear() --> This will return day of year for current date. i.e. 311th day for date 7th November 2019
            localDate.getMonth().name() --> This will return the current month as string. i.e. JANUARY, FEBRUARY .... DECEMBER
         */

        return localDate.getDayOfMonth();
    }

    public static int getCurrentYear() {
        LocalDate localDate = LocalDate.now();

        /* Methods that can be used
            localDate.getYear() --> This will return year in int. i.e. 2019,2018 etc
            localDate.getDayOfMonth() --> This will return current date. i.e. 1,2,3,4....31
            localDate.getDayOfWeek() --> This will return day of week. i.e. Monday, Tuesday
            localDate.getDayOfYear() --> This will return day of year for current date. i.e. 311th day for date 7th November 2019
            localDate.getMonth().name() --> This will return the current month as string. i.e. JANUARY, FEBRUARY .... DECEMBER
         */

        return localDate.getYear();
    }

    public static int getCurrentMonthAsNumber() {
        LocalDate localDate = LocalDate.now();

        /* Methods that can be used
            localDate.getYear() --> This will return year in int. i.e. 2019,2018 etc
            localDate.getDayOfMonth() --> This will return current date. i.e. 1,2,3,4....31
            localDate.getDayOfWeek() --> This will return day of week. i.e. Monday, Tuesday
            localDate.getDayOfYear() --> This will return day of year for current date. i.e. 311th day for date 7th November 2019
            localDate.getMonth().name() --> This will return the current month as string. i.e. JANUARY, FEBRUARY .... DECEMBER
         */

        return localDate.getMonthValue();
    }

    public enum DateFor {
        MONTH,
        DAYS,
        YEARS
    }
}
