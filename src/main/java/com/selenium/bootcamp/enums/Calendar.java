package com.selenium.bootcamp.enums;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

public enum Calendar {
    JAN(1, 31),
    FEB(2, 28),
    MAR(3, 31),
    APR(4, 30),
    MAY(5, 31),
    JUN(6, 30),
    JUL(7, 31),
    AUG(8, 31),
    SEP(9, 30),
    OCT(10, 31),
    NOV(11, 30),
    DEC(12, 31),
    MONTH(13, 12),
    YEAR(14, 1),
    DAY(15, 1);

    private static final Logger logger = LogManager.getLogger(Calendar.class.getName());
    private final int monthIndex;
    private final int numberOfDays;

    Calendar(int index, int daysInAMonth) {
        this.monthIndex = index;
        this.numberOfDays = daysInAMonth;
    }

    public int getMonthIndex() {
        return monthIndex;
    }

    /**
     * To get number of days in a month falls in a specific year
     *
     * @param year Year for which to count the days
     * @return Number of days for a selected month
     */
    public int getNumberOfDays(int year) {
        if (getMonthIndex() == 2) {
            if (isLeapYear(year)) {
                return 29;
            } else {
                return numberOfDays;
            }
        } else {
            return numberOfDays;
        }
    }

    /**
     * To check whether a particular year is a leap year or not
     *
     * @param year year to check for
     * @return <code>true</code> if the year is leap year, <code>false</code> otherwise
     */
    private boolean isLeapYear(int year) {
        boolean leap = false;

        if (year % 4 == 0) {
            if (year % 100 == 0) {
                // year is divisible by 400, hence the year is a leap year
                leap = year % 400 == 0;
            } else
                leap = true;
        } else
            leap = false;

        if (leap)
            logger.info(year + " is a leap year.");
        else
            logger.info(year + " is not a leap year.");
        return leap;
    }

    public enum Week {
        SUNDAY("Sunday"),
        MONDAY("Monday"),
        TUESDAY("Tuesday"),
        WEDNESDAY("Wednesday"),
        THURSDAY("Thursday"),
        FRIDAY("Friday"),
        SATURDAY("Saturday");

        private static final Map<String, Week> enumMAP;

        static {
            Map<String, Week> mainNavMap = Arrays
                    .stream(values())
                    .collect(toMap(cg -> cg.text, e -> e));

            enumMAP = Collections.unmodifiableMap(mainNavMap);
        }

        private final String text;

        Week(String innerText) {
            this.text = innerText;
        }

        public static Week getEnum(String linkText) {
            return enumMAP.get(linkText);
        }

        public String getName() {
            return text;
        }
    }
}
