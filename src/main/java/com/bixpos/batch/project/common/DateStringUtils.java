package com.bixpos.batch.project.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateStringUtils {
    // format : yyyy MM dd HH mm ss

    private final static String DEFAULT_FORMAT = "yyyyMMdd";

    ////////////////////////////////////////////////////////////////////////////////
    // Date --> String
    ////////////////////////////////////////////////////////////////////////////////
    public static String getString(Date from, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(from);
    }

    public static String getStringYYYYMMDD(Date from) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DEFAULT_FORMAT);
        return dateFormat.format(from);
    }

    public static String getStringNow(String format) {
        Date from = new Date();
        return getString(from, format);
    }

    public static String getStringNowYYYYMMDD() {
        Date from = new Date();
        return getStringYYYYMMDD(from);
    }

    public static String addDays(Date basisDate, int days, String format) {
        Date from = org.apache.commons.lang3.time.DateUtils.addDays(basisDate, days);
        return getString(from, format);
    }

    public static String getStringYesterday(String format) {
        Date from = new Date();
        return addDays(from, -1, format);
    }

    public static String getStringYesterdayYYYYMMDD() {
        return getStringYesterday(DEFAULT_FORMAT);
    }

    public static String getStringTomorrow(String format) {
        Date from = new Date();
        return addDays(from, +1, format);
    }

    public static String getStringTomorrowYYYYMMDD() {
        return getStringTomorrow(DEFAULT_FORMAT);
    }


    ////////////////////////////////////////////////////////////////////////////////
    // String --> Date
    ////////////////////////////////////////////////////////////////////////////////
    public static Date getDate(String dateString, String format) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.parse(dateString);
    }

    ////////////////////////////////////////////////////////////////////////////////
    // Validation
    ////////////////////////////////////////////////////////////////////////////////
    
    /**
     * 날짜 유효성 체크
     * @param dateString
     * @param format
     * @return
     */
    public static boolean isDate(String dateString, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(dateString);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    ////////////////////////////////////////////////////////////////////////////////
    // Calculate Date
    ////////////////////////////////////////////////////////////////////////////////

    /**
     * 두 날짜 사이의 일수를 반환 합니다.
     *   - 절대값을 반환하며, 올림처리 합니다.
     *     ex) from : 2021-06-11 09:01:02, to : 2021-06-10 10:11:12 ==> 2일
     * @param date1 날짜1
     * @param date2 날짜2
     * @return 일수
     */
    public static int getDays(Date date1, Date date2) {
        long betweenTime = date2.getTime() - date1.getTime();
        long betweenDays = betweenTime / (1000*60*60*24);

        return Math.abs(betweenDays == (int)betweenDays ? (int)betweenDays+1 : (int) Math.ceil(betweenDays));
    }

    /**
     * 두 날짜 사이의 일수를 반환 합니다.
     *   - 절대값을 반환하며, 올림처리 합니다.
     *     ex) from : 2021-06-11 09:01:02, to : 2021-06-10 10:11:12, format : yyyy-MM-dd HH:mm:ss ==> 2일
     * @param dataString1 날짜 문자열1
     * @param dataString2 날짜 문자열2
     * @param format 날짜 문자열 포맷
     * @return 일수
     * @throws ParseException ParseException
     */
    public static int getDays(String dataString1, String dataString2, String format) throws ParseException {
        Date date1 = getDate(dataString1, format);
        Date date2 = getDate(dataString2, format);

        return getDays(date1, date2);
    }

    /**
     * 두 날짜 사이의 일수를 반환 합니다.
     *   - 절대값을 반환하며, 올림처리 합니다.
     *     ex) from : 20210611, to : 20210610 ==> 2일
     * @param dataString1 날짜 문자열1
     * @param dataString2 날짜 문자열2
     * @return 일수
     * @throws ParseException ParseException
     */
    public static int getDaysFromYYYYMMDD(String dataString1, String dataString2) throws ParseException {
        return getDays(dataString1, dataString2, DEFAULT_FORMAT);
    }
    
    
    public static int parseInt(Object object) {
        try {

            return Integer.valueOf(object.toString());
        } catch (Exception e) {
            return 0;
        }
    }

    public static String toString(Object object) {
        try {
            return object.toString();
        } catch (Exception e) {
            return "";
        }
    }
}
