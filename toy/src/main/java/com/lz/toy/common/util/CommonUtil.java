package com.lz.toy.common.util;

import org.apache.commons.text.RandomStringGenerator;

import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 通用工具类
 *
 * @author zhanglei
 */
public class CommonUtil {

    private final static Pattern pat = Pattern.compile("-");
    private final static RandomStringGenerator randomStringGenerator = new RandomStringGenerator.Builder()
            .withinRange('a', 'z')
            .build();
    private final static RandomStringGenerator build = new RandomStringGenerator.Builder()
            .withinRange(0, 9)
            .build();

    /**
     * md5加密
     *
     * @param s 加密字符
     * @return String
     */
    public static String MD5(String s) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (byte byte0 : md) {
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String randomAlphanumeric(int size) {

        return randomStringGenerator.generate(size);
    }

    public static String randomNumeric(int size) {
        return build.generate(size);
    }


    /**
     * 获得两个日期之间的所有月份
     * @param minDate
     * @param maxDate
     * @param format
     *              格式化为年月
     * @return
     * @throws ParseException
     */
    public static List<String> getMonthBetween(String minDate, String maxDate, String format) throws ParseException {
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        ArrayList<String> result = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat(format);//格式化为年月

        Calendar min = Calendar.getInstance();
        Calendar max = Calendar.getInstance();

        min.setTime(date.parse(minDate));
        min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);

        max.setTime(date.parse(maxDate));
        max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

        Calendar curr = min;
        while (curr.before(max)) {
            result.add(sdf.format(curr.getTime()));
            curr.add(Calendar.MONTH, 1);
        }

        return result;
    }

    /****
     * 传入具体日期 ，相加（减）月份，返回具体日期。
     *
     * @param date
     *            日期(2014-04-20)
     * @param month
     *            月份从0到11，正数为日期相加，负数为日期相减
     * @param format
     *              格式化为年月日
     * @return 如：2017-10-08
     * @throws ParseException
     */
    public static String subMonth(String date,int month, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date dt = sdf.parse(date);
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(dt);

        rightNow.add(Calendar.MONTH, month);
        Date dt1 = rightNow.getTime();
        String reStr = sdf.format(dt1);

        return reStr;
    }


    /**
     * 获得两个日期之间的所有天数
     * @param minDate
     * @param maxDate
     * @param format
     *              格式化为年月
     * @return
     * @throws ParseException
     */
    public static List<String> getDayBetween(String minDate, String maxDate, String format) throws ParseException {
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        ArrayList<String> result = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat(format);//格式化为年月

        Calendar min = Calendar.getInstance();
        Calendar max = Calendar.getInstance();

        min.setTime(date.parse(minDate));
        //min.add(Calendar.DAY_OF_YEAR, 1);

        max.setTime(date.parse(maxDate));
        //max.set(max.get(Calendar.MONTH), max.get(Calendar.DATE), 2);

        Calendar curr = min;
        while (curr.before(max)|| curr.equals(max)){
            result.add(sdf.format(curr.getTime()));
            curr.add(Calendar.DAY_OF_YEAR, 1);
        }

        return result;
    }
    /****
     * 传入具体日期 ，相加（减）天数，返回具体日期。
     *
     * @param date
     *            日期(2014-04-20)
     * @param days
     *            日期从0到31，正数为日期相加，负数为日期相减
     * @param format
     *              格式化为年月日
     * @return 如：2017-10-08
     * @throws ParseException
     */
    public static String subDay(String date,int days, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date dt = sdf.parse(date);
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(dt);

        rightNow.add(Calendar.DATE, days);
        Date dt1 = rightNow.getTime();
        String reStr = sdf.format(dt1);

        return reStr;
    }

    /**
     * 获取日期的下月第一天
     * @param date 日期
     * @param sdf 格式
     * @return
     */
    public static String nextMonthFirstDay(Date date,SimpleDateFormat sdf){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, 1);
        c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
        c.set(Calendar.HOUR_OF_DAY,0);
        c.set(Calendar.MINUTE,0);
        c.set(Calendar.SECOND,0);
        return sdf.format(c.getTime());
    }

    /**
     * 获取时间相隔（months）之后的第一天
     * @param date
     * @param months
     * @return
     */
    public static Date nextFewMonth(Date date,Integer months){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, months);
        c.set(Calendar.DAY_OF_MONTH,1);
        c.set(Calendar.HOUR_OF_DAY,0);
        c.set(Calendar.MINUTE,0);
        c.set(Calendar.SECOND,0);
        return c.getTime();
    }


    /**
     * 获取当年的第一天
     * @return
     */
    public static Date getCurrYearFirst(){
        Calendar currCal=Calendar.getInstance();
        int currentYear = currCal.get(Calendar.YEAR);
        return getYearFirst(currentYear);
    }

    /**
     * 获取当年的最后一天
     * @return
     */
    public static Date getCurrYearLast(){
        Calendar currCal=Calendar.getInstance();
        int currentYear = currCal.get(Calendar.YEAR);
        return getYearLast(currentYear);
    }

    /**
     * 获取某年第一天日期
     * @param year 年份
     * @return Date
     */
    public static Date getYearFirst(int year){
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        Date currYearFirst = calendar.getTime();
        return currYearFirst;
    }

    /**
     * 获取某年最后一天日期
     * @param year 年份
     * @return Date
     */
    public static Date getYearLast(int year){
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        calendar.set(Calendar.HOUR_OF_DAY,23);
        calendar.set(Calendar.MINUTE,59);
        calendar.set(Calendar.SECOND,59);
        Date currYearLast = calendar.getTime();

        return currYearLast;
    }

    /**
     * 获得该月第一天
     * @param year
     * @param month
     * @return
     */
    public static Date getFirstDayOfMonth(int year,int month){
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR,year);
        //设置月份
        cal.set(Calendar.MONTH, month-1);
        //获取某月最小天数
        int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最小天数
        cal.set(Calendar.DAY_OF_MONTH, firstDay);
        cal.set(Calendar.HOUR_OF_DAY,0);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);
        Date firstDayOfMonth = cal.getTime();
        return firstDayOfMonth;
    }

    /**
     * 获得该月最后一天
     * @param year
     * @param month
     * @return
     */
    public static Date getLastDayOfMonth(int year,int month){
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR,year);
        //设置月份
        cal.set(Calendar.MONTH, month-1);
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        cal.set(Calendar.HOUR_OF_DAY,23);
        cal.set(Calendar.MINUTE,59);
        cal.set(Calendar.SECOND,59);
        Date lastDayOfMonth = cal.getTime();
        return lastDayOfMonth;
    }

    /**
     * 获取给定日期月份的最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastdayOfMonth(Date date) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);// someDate 为你要获取的那个月的时间
        ca.set(Calendar.DAY_OF_MONTH, 1);
        ca.add(Calendar.MONTH, 1);
        ca.add(Calendar.DAY_OF_MONTH, -1);
        ca.set(Calendar.HOUR_OF_DAY,23);
        ca.set(Calendar.MINUTE,59);
        ca.set(Calendar.SECOND,59);
        // 最后一天
        Date lastDate = ca.getTime();
        return lastDate;
    }


    /**
     * 获取给定日期月份的第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstdayOfMonth(Date date) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);// someDate 为你要获取的那个月的时间
        ca.set(Calendar.DAY_OF_MONTH, 1);
        ca.set(Calendar.HOUR_OF_DAY,0);
        ca.set(Calendar.MINUTE,0);
        ca.set(Calendar.SECOND,0);
        // 第一天
        Date firstDate = ca.getTime();
        return firstDate;
    }

    /**
     * 获取某天最后时刻
     * @param date
     * @return
     */
    public static Date getEndTimeOfDay(Date date){
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.set(Calendar.HOUR_OF_DAY,23);
        ca.set(Calendar.MINUTE,59);
        ca.set(Calendar.SECOND,69);
        Date time = ca.getTime();
        return time;
    }


}
