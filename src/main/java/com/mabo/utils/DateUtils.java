package com.mabo.utils;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Component
public class DateUtils {

    /**
     * 默认日期格式 //Timestamp format must be yyyy-mm-dd hh:mm:ss[.fffffffff]
     */
    private static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 数据库中默认年月日格式
     */
    private static final String DEFAULT_FORMAT_DB = "yyyyMMdd";

    /**
     * 默认构造函数
     */
    private DateUtils() {
    }

    /**
     * @param format 时间格式
     * @return 得到当前时间的指定格式
     * @yhcip:title 返回指定格式的当前时间
     * @yhcip:desc 返回指定格式的当前时间 getCurrentTime("yyyy-MM-dd") 返回 2007-04-29
     * @author Administrator
     */
    public static String getCurrentDate(String format) {
        String returnStr = null;
        SimpleDateFormat f = new SimpleDateFormat(format);
        Date date = new Date();
        returnStr = f.format(date);
        return returnStr;
    }

    /**
     * 日期转字符串
     * @author wk.li
     * @date 2020/1/12 17:57
     * @param date
     * @return java.lang.String
     */
    public static String getDateToStr(Date date, String format) {
        SimpleDateFormat f = new SimpleDateFormat(format);
        String returnStr = f.format(date);
        return returnStr;
    }

    public static class TimeFormat {
        public static final String HH_MM_SS_COLON = "HH:mm:ss";
        public static final String HH_MM_SS_MILSEC_COLON = "HH:mm:ss.SSS";
        public static final String HH_MM_SS_CHINESE = "HH时mm分ss秒";
        public static final String YYYY_MM_DD_CHINESE = "yyyy年MM月dd日";
        public static final String YYYY_MM_DD_LINE = "yyyy-MM-dd";
        public static final String YYYY_MM_DD_SLASH = "yyyy/MM/dd";
        public static final String YYYY_MM_DD_BACKSLASH = "yyyy\\MM\\dd";
        public static final String YYYY_MM_DD_NONE = "yyyyMMdd";
        public static final String YYYY_MM = "yyyyMM";
        public static final String YYYY = "yyyy";
        public static final String YYYY_MM_DD_HH_MM_SS_CHINESE = "yyyy年MM月dd日 HH时mm分ss秒";
        public static final String YYYY_MM_DD_HH_MM_SS_LINE = "yyyy-MM-dd HH:mm:ss";
        public static final String YYYY_MM_DD_HH_MM_SS_SLASH = "yyyy/MM/dd HH:mm:ss";
        public static final String YYYY_MM_DD_HH_MM_SS_BACKSLASH = "yyyy\\MM\\dd HH:mm:ss";
        public static final String YYYY_MM_DD_HH_MM_SS_NONE = "yyyyMMdd HH:mm:ss";
        public static final String YYYY_MM_DD_HH_MM_SS_BUS = "yyyyMMddHHmmss";
        public static final String YYYY_MM_DD_HH_MM_SS_MILSEC_LINE = "yyyy-MM-dd HH:mm:ss.SSS";
        public static final String YYYY_MM_DD_HH_MM_SS_MILSEC_SLASH = "yyyy/MM/dd HH:mm:ss.SSS";
        public static final String YYYY_MM_DD_HH_MM_SS_MILSEC_BACKSLASH = "yyyy\\MM\\dd HH:mm:ss.SSS";
        public static final String YYYY_MM_DD_HH_MM_SS_MILSEC_NONE = "yyyyMMdd HH:mm:ss.SSS";

    }

    /**
     * 两时间关系判断构件
     * 时间相等返回0，time1大于time2返回1，time1小于time2返回-1
     *
     * @param time1
     * @param time2
     * @return
     */
    public static int checkTime(Date time1, Date time2) {
        return time1.compareTo(time2);
    }


    /**
     * 将月数转换成年数
     *
     * @param month
     * @return
     */
    public static double monthToYear(int month) {
        return month / 12D;
    }

    /**
     * 字符串转换成日期 如果转换格式为空，则利用默认格式进行转换操作
     *
     * @param str    字符串
     * @param format 日期格式
     * @return 日期
     * @throws ParseException
     */
    public static Date str2Date(String str, String format) {
        if (null == str || "".equals(str)) {
            return null;
        }
        // 如果没有指定字符串转换的格式，则用默认格式进行转换
        if (null == format || "".equals(format)) {
            format = DEFAULT_FORMAT;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = sdf.parse(str);
            return date;
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 日期转换为字符串
     *
     * @param date   日期
     * @param format 日期格式
     * @return 字符串
     */
    public static String date2Str(Date date, String format) {
        if (null == date) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static String date2Str(Date date) {
        if (null == date) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_FORMAT);
        return sdf.format(date);
    }

    /**
     * 时间戳转换为字符串
     *
     * @param time
     * @return
     */
    public static String timestamp2Str(Timestamp time) {
        Date date = null;
        if (null != time) {
            date = new Date(time.getTime());
        }
        return date2Str(date, DEFAULT_FORMAT);
    }

    /**
     * 字符串转换时间戳
     *
     * @param str
     * @return
     */
    public static Timestamp str2Timestamp(String str) {
        Date date = str2Date(str, DEFAULT_FORMAT);
        if (null == date) {
            return null;
        } else {
            return new Timestamp(date.getTime());
        }
    }


    /**
     * 字符串转换时间戳
     *
     * @param str
     * @param format
     * @return
     */
    public static Timestamp str2Timestamp(String str, String format) {
        Date date = str2Date(str, format);
        if (null != date) {
            return new Timestamp(date.getTime());
        } else {
            return null;
        }
    }

    /**
     * 打印日期字符格式（yyyy-MM-dd hh:mm:ss）
     *
     * @param longValue
     * @return
     */
    public static String long2Str(Long longValue) {
        SimpleDateFormat format = new SimpleDateFormat(DEFAULT_FORMAT);
        return format.format(parseDate(longValue));
    }

    public static Timestamp long2Timestamp(Long longValue) {
        return new Timestamp(longValue);
    }

    /**
     * long解析为java.util.Date
     *
     * @param longValue
     * @return
     */
    public static Date parseDate(long longValue) {
        return new Date(longValue);
    }

    /**
     * 判断两个java.util.Date是否为同一天
     *
     * @param date1
     * @param date2
     * @return
     */
    @SuppressWarnings("deprecation")
    public static boolean isSameDay(Date date1, Date date2) {
        return date1.getDate() == date2.getDate();
    }

    /**
     * 计算两个long（秒）之间相差的天数
     *
     * @param longValue1
     * @param longValue2
     * @return
     */
    public static int getBetweenDays(long longValue1, long longValue2) {
        long betweenDays = ((longValue1 - longValue2) / 1000) / (60 * 60 * 24);
        return Math.abs((int) betweenDays);
    }

    /**
     * @param time1
     * @param time2
     * @return
     * @Description: 计算2个日期Str的天数
     * @update: 2014-1-8 by huangyh
     */
    public static int getBetweenDays(String time1, String time2) {
        long betweenDays = 0;
        SimpleDateFormat ft = new SimpleDateFormat(TimeFormat.YYYY_MM_DD_LINE);
        try {
            Date date1 = ft.parse(time1);
            Date date2 = ft.parse(time2);
            betweenDays = date1.getTime() - date2.getTime();
            betweenDays = betweenDays / 1000 / 60 / 60 / 24;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return Math.abs((int) betweenDays);
    }

    /**
     * sql.Date转化为sql.Timestamp
     *
     * @param date
     * @return
     */
    public static Timestamp convertDate2Timestamp(Date date) {
        return new Timestamp(date.getTime());
    }

    /**
     * @param date
     * @param days
     * @return
     * @Description: 获取下一天
     * @update: 2016年1月5日 by huangyh
     */
    @SuppressWarnings("static-access")
    public static Timestamp getNextDay(Timestamp date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, days);// 把日期往后增加一天.整数往后推,负数往前移动
        return convertDate2Timestamp(calendar.getTime());
    }

    /**
     * @param date1
     * @param date2
     * @param format
     * @return
     * @Description: 比较两个日期的大小
     * @update: 2014年8月4日 by huangyh
     */
    public static int compareDate(String date1, String date2, String format) {

        DateFormat df = new SimpleDateFormat(format);
        try {
            Date dt1 = df.parse(date1);
            Date dt2 = df.parse(date2);
            if (dt1.getTime() > dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    /**
     * 将数字转为日期格式（主要用于处理数据库中日期为NUMBER的查询）
     *
     * @param value
     * @param format
     * @return
     */
    public static Date integer2Date(Integer value, String format) {
        if (value == null) {
            return null;
        }
        String str = String.valueOf(value);
        if (format == null || "".equals(format)) {
            format = DEFAULT_FORMAT_DB;
        }
        return str2Date(str, format);
    }

    /**
     * 时间转换为integer
     *
     * @param date
     * @param format
     * @return
     */
    public static Integer date2Integer(Date date, String format) {
        String str = date2Str(date, format);
        Integer value = Integer.valueOf(str);
        return value;
    }



    /**
     * 获取某个月的最后一天
     *
     * @param year  年
     * @param month 月
     * @return
     */
    public static String getLastDayOfMonth(int year, int month, String format) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, maxDay);
        return date2Str(calendar.getTime(), format);
    }

    public static Date getMonthEnd(String date, String format) {
        Calendar calendar = Calendar.getInstance();
        if (format == null || format.length() <= 0) {
            format = TimeFormat.YYYY_MM;
        }
        calendar.setTime(str2Date(date, format));
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        return calendar.getTime();
    }

    /***
     *〈获取当前月第一天〉
     * @return
     * @see  [类、类#方法、类#成员]
     */
    public static String getMonthOfDayFirst() {
        SimpleDateFormat format = new SimpleDateFormat(TimeFormat.YYYY_MM_DD_LINE);
        String firstDay = "";
        // 获取前月的第一天
        Calendar cal_1 = Calendar.getInstance();
        cal_1.add(Calendar.MONTH, 0);
        cal_1.set(Calendar.DAY_OF_MONTH, 1);
        firstDay = format.format(cal_1.getTime());

        return firstDay;
    }

    /***
     *〈获取当前日期〉
     * @return
     * @see  [类、类#方法、类#成员]
     */
    public static String getMonthToday() {
        SimpleDateFormat format = new SimpleDateFormat(TimeFormat.YYYY_MM_DD_LINE);
        String firstDay = "";
        // 获取前月的第一天
        Calendar cal = Calendar.getInstance();
        firstDay = format.format(cal.getTime());

        return firstDay;
    }






    /**
     * Date增减day
     *
     * @param date 基础日期
     * @param day  增减天数
     * @return
     * @
     */
    public static Date addDay(Date date, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, day);
        return calendar.getTime();
    }

    /**
     * Date增减month
     *
     * @param date  基础日期
     * @param month 增减月数
     * @return
     * @
     */
    public static Date addMonth(Date date, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, month);
        return calendar.getTime();
    }



    /**
     * 计算两个日期之间相差多少天
     *
     * @param one
     * @param second
     * @return
     */
    public static int compareDate(Date one, Date second) {
        Date temp;
        if (one.after(second)) { //日期one大于日期second
            temp = one;
            one = second;
            second = temp;
        }

        Calendar calOne = Calendar.getInstance();
        calOne.setTime(one);

        Calendar calSecond = Calendar.getInstance();
        calSecond.setTime(second);

        int dayOne = calOne.get(Calendar.DAY_OF_YEAR);
        int daySecond = calSecond.get(Calendar.DAY_OF_YEAR);

        int yearOne = calOne.get(Calendar.YEAR);
        int yearSecond = calSecond.get(Calendar.YEAR);

        if (yearOne != yearSecond) { //不同年
            int timeDistance = 0;
            for (int i = yearOne; i < yearSecond; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) {    //闰年
                    timeDistance += 366;

                } else {    //不是闰年
                    timeDistance += 365;

                }
            }

            return timeDistance + (daySecond - dayOne);
        } else {    //不同年

            return daySecond - dayOne;
        }

    }

    /**
     * 秒转化为天小时分秒字符串
     *
     * @param seconds
     * @return String
     */
    public static String formatSeconds(long seconds) {
        String timeStr = seconds + "秒";
        if (seconds > 60) {
            long second = seconds % 60;
            long min = seconds / 60;
            timeStr = min + "分" + second + "秒";
            if (min > 60) {
                min = (seconds / 60) % 60;
                long hour = (seconds / 60) / 60;
                timeStr = hour + "小时" + min + "分" + second + "秒";
                if (hour > 24) {
                    hour = ((seconds / 60) / 60) % 24;
                    long day = (((seconds / 60) / 60) / 24);
                    timeStr = day + "天" + hour + "小时" + min + "分" + second + "秒";
                }
            }
        }
        return timeStr;
    }


    /**
     * 计算两个期号之间的月数
     *
     * @param issue1
     * @param issue2
     * @return issue1<issue2返回正数       ，       issue1>issue2返回负数
     */
    public static int monthNum(int issue1, int issue2) throws Exception {
        if (issue1 < 0 || issue2 < 0) {
            throw new Exception("比较期号小于零");
        }
        int i = Math.max(issue1, issue2);
        int j = Math.min(issue1, issue2);
        int yyyy = (i / 100) - (j / 100);
        int mm = i % 100 - j % 100;
        if (issue1 <= issue2) {
            return yyyy * 12 + mm;
        } else {
            return -(yyyy * 12 + mm);
        }
    }


    /***
     * 计算当前年龄
     *
     * @param birthDay
     *            出生日期
     * @return
     * @throws Exception
     */
    public static int getAge(Date birthDay) throws Exception {
        if (birthDay == null) {
            throw new Exception("出生日期不能为null");
        }

        int age = 0;

        Date now = new Date();

        SimpleDateFormat format_y = new SimpleDateFormat("yyyy");
        SimpleDateFormat format_M = new SimpleDateFormat("MM");

        String birth_year = format_y.format(birthDay);
        String this_year = format_y.format(now);

        String birth_month = format_M.format(birthDay);
        String this_month = format_M.format(now);

        // 初步，估算
        age = Integer.parseInt(this_year) - Integer.parseInt(birth_year);

        // 如果未到出生月份，则age - 1
        if (this_month.compareTo(birth_month) < 0) {
            age -= 1;
        }
        if (age < 0) { // age小于0，未满一岁
            age = 0;
        }

        return age;
    }

    /**
     * 计算到指定时间内的年龄
     *
     * @param birthDay
     * @param endDate
     * @return
     */
    public static int getAge(Date birthDay, Date endDate) throws Exception {
        if (birthDay == null) {
            throw new Exception("出生日期不能为null");
        }

        int age = 0;

        Date now = endDate;

        SimpleDateFormat format_y = new SimpleDateFormat("yyyy");
        SimpleDateFormat format_M = new SimpleDateFormat("MM");

        String birth_year = format_y.format(birthDay);
        String this_year = format_y.format(now);

        String birth_month = format_M.format(birthDay);
        String this_month = format_M.format(now);

        // 初步，估算
        age = Integer.parseInt(this_year) - Integer.parseInt(birth_year);

        // 如果未到出生月份，则age - 1
        if (this_month.compareTo(birth_month) < 0) {
            age -= 1;
        }
        if (age < 0) { // age小于0，未满一岁
            age = 0;
        }

        return age;
    }
    /**
     * 获取yyyy-MM-dd HH:mm:ss格式的日期对象
     * @return
     * @throws ParseException
     */
    public static  Date getCurrentDate () throws ParseException {
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TimeFormat.YYYY_MM_DD_HH_MM_SS_LINE);
		Date parse = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
		return parse;
    }

    /**
     * 格式校验 yyyy-mm-dd
     * @param str
     * @return
     */
    public static boolean ValidateDate(String str){
        //日期的正则表达式
        String regExp = "^[1-9]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$";
        return str.matches(regExp);
    }



    public static int getAgeByBirth(Date birthDay) throws Exception {
        int age = 0;
        Calendar cal = Calendar.getInstance();
        if (cal.before(birthDay)) { //出生日期晚于当前时间，无法计算
            throw new Exception(
                    "The birthDay is before Now.It's unbelievable!");
        }
        int yearNow = cal.get(Calendar.YEAR);  //当前年份
        int monthNow = cal.get(Calendar.MONTH);  //当前月份
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH); //当前日期
        cal.setTime(birthDay);
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
        age = yearNow - yearBirth;   //计算整岁数
        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) {
                    age--;
                }//当前日期在生日之前，年龄减一
            } else {
                age--;//当前月份在生日之前，年龄减一
            }
        }
        return age;
    }


    /**
     * 将日期格式转换为YYYY-MM-DD
     *
     * @param date
     * @return java.util.Date
     * @author tangdan
     * @date 2020/7/14 20:55
     */
    public static Date changeYearMonthDayDate(Date date) throws Exception{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TimeFormat.YYYY_MM_DD_LINE);
        String dateStr = simpleDateFormat.format(date);
        Date date1;
        try {
            date1 = simpleDateFormat.parse(dateStr);
        } catch (ParseException e) {
            throw new Exception("日期格式转换失败");
        }
        return date1;
    }

}
