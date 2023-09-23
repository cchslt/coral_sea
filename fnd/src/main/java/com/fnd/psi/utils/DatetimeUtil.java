package com.fnd.psi.utils;

import cn.hutool.core.date.DateUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.*;


public class DatetimeUtil {
    private static final long TIME_ZONE_MILLIS = getTimeZoneMillis();
    private static final long FIRST_DAY_MILLIS = 24 * 60 * 60 * 1000 + TIME_ZONE_MILLIS;

    /**
     * 所有的DateTimeFormat中value组成的formatMap,静态资源
     */
    private static final Map<String, SimpleDateFormat> FORMAT_MAP = getFormatMap(DatetimeFormat.values());
    public static final String PATTERN_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    /**
     * 获取当前日期时间的时区的时间毫秒数与实际时间的毫秒数相隔的毫秒数
     * createdDatetime 2014年8月23日 下午7:47:23
     *
     * @return long
     */
    public static long getTimeZoneMillis() {
        return TimeZone.getDefault().getOffset(System.currentTimeMillis());
    }

    /**
     * 获取所有format组成的Map
     * createdDatetime 2014年8月25日 上午11:14:44
     *
     * @param values
     * @return Map
     */
    private static Map<String, SimpleDateFormat> getFormatMap(
            DatetimeFormat[] values) {
        Map<String, SimpleDateFormat> formatMap = new HashMap<String, SimpleDateFormat>();

        for (DatetimeFormat format : values) {
            String formatValue = format.getFormat();
            formatMap.put(formatValue, new SimpleDateFormat(formatValue));
        }

        return formatMap;
    }

    /**
     * 获取SimpleDateFormat by format
     * createdDatetime 2014年8月25日 上午11:25:57
     *
     * @param format 日期格式
     * @return SimpleDateFormat
     */
    private static SimpleDateFormat getFormat(String format) {
        return FORMAT_MAP.containsKey(format) ? FORMAT_MAP.get(format) : new SimpleDateFormat(format);
    }

    /**
     * 类似于System.currentTimeMillis获取当前日期的毫秒数
     * createdDatetime 2014年8月23日 下午7:47:31
     *
     * @return long
     */
    public static long currentDateMillis() {
        // todayDatetime
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());
        // today date year month day
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }

    /**
     * 日期时间形式的字符串转化为时间毫秒数（若仅有时间，则日期为今天）
     * createdDatetime 2014年8月23日 下午3:48:48
     *
     * @param dateTimeString
     * @param format
     * @return long
     */
    public static long dateTimeStringToLong(String dateTimeString, String format) {
        try {
            long result = getFormat(format).parse(dateTimeString).getTime();
            if (isTimeString(dateTimeString, result)) {
                result = result + currentDateMillis() + TIME_ZONE_MILLIS;
            }
            return result;
        } catch (Exception e) {
            throw new RuntimeException("dateTimeString illegal,dateTimeString=" + dateTimeString + "," + e.getMessage(), e);
        }
    }

    private static boolean isTimeString(String dateTimeString, long result) {
        return !dateTimeString.contains("-") && result < FIRST_DAY_MILLIS;
    }

    /**
     * 线程安全
     * 日期时间形式的字符串转化为时间毫秒数（若仅有时间，则日期为今天）
     * createdDatetime 2014年8月23日 下午4:54:32
     *
     * @param dateTimeString
     * @param format
     * @return long
     */
    public static synchronized long dateTimeStringToLong(String dateTimeString, DatetimeFormat format) {
        try {
            long result;
            if (DatetimeFormat.INTERNAL_DATE_TIME_MILLIS_ZONE_COLON_SEPARATOR_FORMAT.equals(format)) {
                int indexOfAdd = dateTimeString.indexOf("+");
                String suffix = dateTimeString.substring(indexOfAdd + "+".length());
                result = getFormat(DatetimeFormat.INTERNAL_DATE_TIME_MILLIS_ZONE_FORMAT.getFormat()).parse(dateTimeString.substring(0, indexOfAdd) + "+" + suffix.replace(":", "")).getTime();
            } else if (DatetimeFormat.INTERNAL_DATE_TIME_ZONE_COLON_SEPARATOR_FORMAT.equals(format)) {
                int indexOfAdd = dateTimeString.indexOf("+");
                String suffix = dateTimeString.substring(indexOfAdd + "+".length());
                result = getFormat(DatetimeFormat.INTERNAL_DATE_TIME_ZONE_FORMAT.getFormat()).parse(dateTimeString.substring(0, indexOfAdd) + "+" + suffix.replace(":", "")).getTime();
            } else {
                result = getFormat(format.getFormat()).parse(dateTimeString).getTime();
            }
            if (isTimeString(dateTimeString, result)) {
                result = result + currentDateMillis() + TIME_ZONE_MILLIS;
            }
            return result;
        } catch (Exception e) {
            throw new RuntimeException("dateTimeString illegal,dateTimeString=" + dateTimeString + ",format=" + format.getFormat() + "," + e.getMessage(), e);
        }
    }

    /**
     * 时间毫秒数转化为标准时间没有秒数字符串
     * createdDatetime 2014年8月23日 下午3:51:39
     *
     * @param minseconds
     * @param format
     * @return String
     */
    public static String longToDateTimeString(long minseconds, DatetimeFormat format) {
        Date date = new Date(minseconds);
        if (DatetimeFormat.INTERNAL_DATE_TIME_MILLIS_ZONE_COLON_SEPARATOR_FORMAT.equals(format)) {
            String s = getFormat(DatetimeFormat.INTERNAL_DATE_TIME_MILLIS_ZONE_FORMAT.getFormat()).format(date);
            int indexOfAdd = s.indexOf("+");
            String suffix = s.substring(indexOfAdd + "+".length());
            return s.substring(0, indexOfAdd) + "+" + suffix.substring(0, 2) + ":" + suffix.substring(2);
        }
        return getFormat(format.getFormat()).format(date);
    }

    /**
     * 时间毫秒数转化为标准时间没有秒数字符串
     * createdDatetime 2014年8月23日 下午3:51:39
     *
     * @param minseconds
     * @param format
     * @return String
     */
    public static String longToDateTimeString(long minseconds, String format) {
        Date date = new Date(minseconds);
        return getFormat(format).format(date);
    }

    /**
     * 线程安全
     * 一种时间格式字符串转化为另一种时间格式字符串
     * createdDatetime 2014年8月25日 上午11:42:56
     *
     * @param source
     * @param sourceFormat
     * @param targetFormat
     * @return String
     */
    public static synchronized String stringToString(String source, DatetimeFormat sourceFormat, DatetimeFormat targetFormat) {
        try {
            Date date;
            if (DatetimeFormat.INTERNAL_DATE_TIME_MILLIS_ZONE_COLON_SEPARATOR_FORMAT.equals(sourceFormat)) {
                int indexOfAdd = source.indexOf("+");
                String suffix = source.substring(indexOfAdd + "+".length());
                date = getFormat(DatetimeFormat.INTERNAL_DATE_TIME_MILLIS_ZONE_FORMAT.getFormat()).parse(source.substring(0, indexOfAdd) + "+" + suffix.replace(":", ""));
            } else if (DatetimeFormat.INTERNAL_DATE_TIME_ZONE_COLON_SEPARATOR_FORMAT.equals(sourceFormat)) {
                int indexOfAdd = source.indexOf("+");
                String suffix = source.substring(indexOfAdd + "+".length());
                date = getFormat(DatetimeFormat.INTERNAL_DATE_TIME_ZONE_FORMAT.getFormat()).parse(source.substring(0, indexOfAdd) + "+" + suffix.replace(":", ""));
            } else {
                date = getFormat(sourceFormat.getFormat()).parse(source);
            }
            if (DatetimeFormat.INTERNAL_DATE_TIME_MILLIS_ZONE_COLON_SEPARATOR_FORMAT.equals(targetFormat)) {
                String s = getFormat(DatetimeFormat.INTERNAL_DATE_TIME_MILLIS_ZONE_FORMAT.getFormat()).format(date);
                int indexOfAdd = s.indexOf("+");
                String suffix = s.substring(indexOfAdd + "+".length());
                return s.substring(0, indexOfAdd) + "+" + suffix.substring(0, 2) + ":" + suffix.substring(2);
            } else if (DatetimeFormat.INTERNAL_DATE_TIME_ZONE_COLON_SEPARATOR_FORMAT.equals(targetFormat)) {
                String s = getFormat(DatetimeFormat.INTERNAL_DATE_TIME_ZONE_FORMAT.getFormat()).format(date);
                int indexOfAdd = s.indexOf("+");
                String suffix = s.substring(indexOfAdd + "+".length());
                return s.substring(0, indexOfAdd) + "+" + suffix.substring(0, 2) + ":" + suffix.substring(2);
            }
            return getFormat(targetFormat.getFormat()).format(date);
        } catch (Exception e) {
            throw new RuntimeException("source illegal,source=" + source + ",sourceFormat=" + sourceFormat.getFormat() + ",targetFormat=" + targetFormat.getFormat() + "," + e.getMessage(), e);
        }
    }

    /**
     * 一种时间格式字符串转化为另一种时间格式字符串
     * createdDatetime 2014年8月25日 上午11:43:47
     *
     * @param source
     * @param sourceFormat
     * @param targetFormat
     * @return String
     */
    public static String stringToString(String source, String sourceFormat, String targetFormat) {
        try {
            return getFormat(targetFormat).format(getFormat(sourceFormat).parse(source));
        } catch (Exception e) {
            throw new RuntimeException("source illegal,source=" + source + "," + e.getMessage(), e);
        }
    }

    /**
     * 一种时间格式字符串转化为标准时间格式化字符串
     * createdDatetime 2014年8月25日 上午11:45:55
     *
     * @param source       日期字符串
     * @param sourceFormat 日期格式
     * @return String
     */
    public static String stringToStandaredString(String source, String sourceFormat) {
        return stringToString(source, sourceFormat, DatetimeFormat.STANDARED_DATE_TIME_FORMAT.getFormat());
    }

    /**
     * 一种时间格式字符串转化为标准时间格式化字符串
     * createdDatetime 2014年8月25日 上午11:46:26
     *
     * @param source       日期字符串
     * @param sourceFormat 日期格式
     * @return String
     */
    public static String stringToStandaredString(String source, DatetimeFormat sourceFormat) {
        return stringToString(source, sourceFormat, DatetimeFormat.STANDARED_DATE_TIME_FORMAT);
    }

    /**
     * 标准时间格式化字符串转化为一种时间格式字符串
     * createdDatetime 2014年8月26日 上午10:29:05
     *
     * @param source       日期字符串
     * @param targetFormat 日期格式
     * @return String
     */
    public static String standaredStringToString(String source, String targetFormat) {
        return stringToString(source, DatetimeFormat.STANDARED_DATE_TIME_FORMAT.getFormat(), targetFormat);
    }

    /**
     * 标准时间格式化字符串转化为一种时间格式字符串
     * createdDatetime 2014年8月26日 上午10:30:25
     *
     * @param source       日期字符串
     * @param targetFormat 日期格式
     * @return String
     */
    public static String standaredStringToString(String source, DatetimeFormat targetFormat) {
        return stringToString(source, DatetimeFormat.STANDARED_DATE_TIME_FORMAT, targetFormat);
    }


    /**
     * 获取指定日期的毫秒数
     * createdDatetime 2014年9月17日 下午10:47:31
     *
     * @param date 日期
     * @return long
     */
    public static long currentDateMillis(Date date) {
        SimpleDateFormat format = getFormat(DatetimeFormat.STANDARED_DATE_FORMAT.getFormat());
        try {
            return format.parse(format.format(date)).getTime();
        } catch (ParseException e) {
            throw new RuntimeException("currentDateMillis exception occured," + e.getMessage(), e);
        }
    }

    /**
     * 获取指定日期的毫秒数
     * createdDatetime 2014年9月17日 下午10:47:31
     *
     * @param date 日期
     * @return long
     */
    public static long currentDateMillis(String date) {
        SimpleDateFormat format = getFormat(DatetimeFormat.STANDARED_DATE_FORMAT.getFormat());
        try {
            return format.parse(date).getTime();
        } catch (ParseException e) {
            throw new RuntimeException("currentDateMillis exception occured," + e.getMessage(), e);
        }
    }

    /**
     * 获取指定日期的秒数
     * createdDatetime 2014年9月1日 下午7:47:31
     *
     * @param date 日期
     * @return Integer
     */
    public static Integer currentDateSecs(Date date) {
        try {
            return Integer.parseInt(currentDateMillis(date) / 1000 + "");
        } catch (NumberFormatException e) {
            throw new RuntimeException("currentDateSecs exception occured," + e.getMessage(), e);
        }
    }

    /**
     * 获取当前日期时间的秒数
     * createdDatetime 2014年9月1日 下午7:47:31
     *
     * @return Integer
     */
    public static Integer currentDateSecs() {
        try {
            return Integer.parseInt(System.currentTimeMillis() / 1000 + "");
        } catch (NumberFormatException e) {
            throw new RuntimeException("currentDateSecs exception occured," + e.getMessage(), e);
        }
    }

    /**
     * 获取指定增加或减少月数的指定日的日期
     *
     * @param date  指定日期
     * @param month 增加或减少的月数（负数为减少，0为当月）
     * @param day   指定日（不能为0或负数）
     * @return Date
     */
    public static Date getFirstday_LastMonth(Date date, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if (month != 0) {
            calendar.add(Calendar.MONTH, month);
        }
        if (day > 0) {
            calendar.set(Calendar.DAY_OF_MONTH, day);
        }
        Date theDate = calendar.getTime();
        return theDate;
    }

    /**
     * 获取今天的日期号
     *
     * @return Integer
     */
    public static Integer getDay() {
        GregorianCalendar gc = new GregorianCalendar();
        return gc.get(Calendar.DATE);
    }

    /**
     * 获取指定时间的日期号
     *
     * @return Integer
     */
    public static Integer getDay(Date date) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        return gc.get(Calendar.DATE);
    }

    /**
     * date字符串转换为 date类型
     *
     * @param dateString
     * @return
     */
    public static Date stringToDate(String dateString) {
        SimpleDateFormat sdf = null;
        if (dateString.length() == 19) {
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        } else if (dateString.length() == 16) {
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        } else if (dateString.length() == 13) {
            sdf = new SimpleDateFormat("yyyy-MM-dd HH");
        } else if (dateString.length() == 10) {
            sdf = new SimpleDateFormat("yyyy-MM-dd");
        } else if (dateString.length() == 7) {
            sdf = new SimpleDateFormat("yyyy-MM");
        }

        try {
            return sdf.parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException("currentDateSecs exception occured," + e.getMessage(), e);
        }
    }

    /**
     * 将当前日期转换为 指定类型的字符串
     *
     * @param targetFormat
     * @return
     */
    public static String getTodayToString(DatetimeFormat targetFormat) {
        SimpleDateFormat format = getFormat(targetFormat.getFormat());
        return format.format(new Date());
    }

    /**
     * 获取当前年份
     *
     * @return
     */
    public static int getYear() {
        GregorianCalendar gc = new GregorianCalendar();
        int retCode = gc.get(Calendar.YEAR);
        return retCode;
    }

    /**
     * 获取指定时间的当前年份
     *
     * @return
     */
    public static int getYear(Date date) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        int retCode = gc.get(Calendar.YEAR);
        return retCode;
    }

    /**
     * 获取当前月份
     *
     * @return
     */
    public static int getMonth() {
        GregorianCalendar gc = new GregorianCalendar();
        int retCode = gc.get(Calendar.MONTH) + 1;
        return retCode;
    }

    /**
     * 获取指定时间的月份
     *
     * @return
     */
    public static int getMonth(Date date) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        int retCode = gc.get(Calendar.MONTH) + 1;
        return retCode;
    }

    /**
     * 获取当前月份
     *
     * @return
     */
    public static String getMonthForString() {
        String retCode = Integer.toString(getMonth());
        if (retCode.length() == 1) {
            retCode = "0" + retCode;
        }
        return retCode;
    }

    /**
     * 获取指定时间的月份
     *
     * @return
     */
    public static String getMonthForString(Date date) {
        String retCode = Integer.toString(getMonth(date));
        if (retCode.length() == 1) {
            retCode = "0" + retCode;
        }
        return retCode;
    }

    /**
     * 获取当前小时
     *
     * @return
     */
    public static int getHour() {
        GregorianCalendar gc = new GregorianCalendar();
        return gc.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取指定时间的小时
     *
     * @return
     */
    public static int getHour(Date date) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        return gc.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取当前分钟+秒
     *
     * @return 0101
     */
    public static String getMinAndSec() {
        GregorianCalendar gc = new GregorianCalendar();
        String min = Integer.toString(gc.get(Calendar.MINUTE));
        String sec = Integer.toString(gc.get(Calendar.SECOND));
        if (min.length() == 1) {
            min = "0" + min;
        }
        if (sec.length() == 1) {
            sec = "0" + sec;
        }
        return min + sec;
    }

    /**
     * 获取当前秒+毫秒后两位
     *
     * @return
     */
    public static String getSecAndmsec() {
        GregorianCalendar gc = new GregorianCalendar();
        String sec = Integer.toString(gc.get(13));
        String msec = Integer.toString(gc.get(14));
        if (sec.length() == 1) {
            sec = "0" + sec;
        }

        if (msec.length() == 1) {
            msec = "0" + msec;
        }
        if (msec.length() == 3) {
            msec = msec.substring(1, 3);
        }
        return msec + sec;
    }

    /**
     * 获取指定时间的分钟+秒
     *
     * @return 0101
     */
    public static String getMinAndSec(Date date) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        String min = Integer.toString(gc.get(Calendar.MINUTE));
        String sec = Integer.toString(gc.get(Calendar.SECOND));
        if (min.length() == 1) {
            min = "0" + min;
        }
        if (sec.length() == 1) {
            sec = "0" + sec;
        }
        return min + sec;
    }

    public static String getDateToString(Date date, String targetFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(targetFormat);
        ;

        return sdf.format(date);

    }

    /**
     * 周开始日期.
     *
     * @param date
     * @param timezone
     * @return
     */
    public static Date beginWeek(Date date, Integer timezone) {
        Date zoneTime = DateTimeZoneConvertUtils.serverTimeTransfer(date, timezone);
        Date start = DateUtil.beginOfWeek(zoneTime);
        return DateTimeZoneConvertUtils.timeZoneTransfer(start, timezone, DateTimeZoneConvertUtils.SERVER_TIME_ZONE);
    }

    /**
     * 周结束日期.
     *
     * @param date
     * @param timezone
     * @return
     */
    public static Date endWeek(Date date, Integer timezone) {
        Date zoneTime = DateTimeZoneConvertUtils.serverTimeTransfer(date, timezone);
        Date end = DateUtil.endOfWeek(zoneTime);
        return DateTimeZoneConvertUtils.timeZoneTransfer(end, timezone, DateTimeZoneConvertUtils.SERVER_TIME_ZONE);
    }

    /**
     * 获取指定时区的时间系统当月开始时间。
     *
     * @param date     时间
     * @param timezone 目标时区
     * @return Date
     */
    public static Date beginMonth(Date date, Integer timezone) {
        // 转为当前国家的时间
        Date zoneTime = DateTimeZoneConvertUtils.serverTimeTransfer(date, timezone);
        Date startDate = DateUtil.beginOfMonth(zoneTime);
        return DateTimeZoneConvertUtils.timeZoneTransfer(startDate, timezone, DateTimeZoneConvertUtils.SERVER_TIME_ZONE);
    }

    /**
     * 获取指定时区的时间系统当月结束时间。
     *
     * @param date     时间
     * @param timezone 目标时区
     * @return Date
     */
    public static Date endMonth(Date date, Integer timezone) {
        // 转为当前国家的时间
        Date zoneTime = DateTimeZoneConvertUtils.serverTimeTransfer(date, timezone);
        Date endDate = DateUtil.endOfMonth(zoneTime);
        return DateTimeZoneConvertUtils.timeZoneTransfer(endDate, timezone, DateTimeZoneConvertUtils.SERVER_TIME_ZONE);
    }


    public static Date getDateBeforeTime(Long beforeTime) {
        // 转为当前国家的时间
        Long time = System.currentTimeMillis();
        return new Date(time - beforeTime);
    }

    public static ZonedDateTime parseZoneTime(String text, String pattern, ZoneId zoneId) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern).withZone(zoneId);
        TemporalAccessor parse = dateTimeFormatter.parse(text);
        return ZonedDateTime.from(parse);
    }

    /**
     * 解析时间，默认采用yyyy-MM-dd HH:mm:ss模式<br/>
     * 例如按照上海时区解析：DatetimeUtil.parseZoneTime(makeInventoryTime, SimbaZone.ASIA_SHANGHAI)
     *
     * @param text      时间字符串
     * @param simbaZone SimbaZone类中常量
     * @return
     */
    public static Date parseZoneTime(String text, String simbaZone) {
        return Date.from(parseZoneTime(text, PATTERN_YYYY_MM_DD_HH_MM_SS, ZoneId.of(simbaZone)).toInstant());
    }


}
