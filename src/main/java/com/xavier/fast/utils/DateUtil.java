//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.xavier.fast.utils;

import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateUtil {
    public DateUtil() {
    }

    public static boolean isBefore(Date configDate, Date entryDate) {
        boolean bool = entryDate.before(configDate);
        return bool;
    }

    public static boolean isAfter(Date configDate, Date entryDate) {
        boolean bool = entryDate.after(configDate);
        return bool;
    }

    public static Date convertStr2Date(String str) {
        Date date = null;

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            date = sdf.parse(str);
        } catch (ParseException var3) {
            date = null;
        }

        return date;
    }

    public static Date convertStr3Date(String str) {
        Date date = null;

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = sdf.parse(str);
        } catch (ParseException var3) {
            date = null;
        }

        return date;
    }

    public static Date StringToDate(String str) {
        SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM-dd");

        try {
            return formate.parse(str);
        } catch (Exception var3) {
            return null;
        }
    }

    public static Date increaseDate(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(5, days);
        return cal.getTime();
    }

    public static Date increaseHour(Date date, int hours) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(11, hours);
        return cal.getTime();
    }

    public static Date increaseMinute(Date date, int minutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(12, minutes);
        return cal.getTime();
    }

    public static Date increaseSecond(Date date, int second) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(13, second);
        return cal.getTime();
    }

    public static Date lastDayByMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(5, cal.getActualMaximum(5));
        return cal.getTime();
    }

    public static Integer getMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(2) + 1;
    }

    public static String dateTo8String(Date date) {
        if (date == null) {
            return null;
        } else {
            String dates = null;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            dates = sdf.format(date);
            return dates;
        }
    }

    public static String dateTo14StringCH(Date date) {
        String dates = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
        dates = sdf.format(date);
        return dates;
    }

    public static String dateTo8StringCH(Date date) {
        String dates = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日  HH:mm:ss");
        dates = sdf.format(date);
        return dates;
    }

    public static String dateTo14String(Date date) {
        String dates = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dates = sdf.format(date);
        return dates;
    }

    public static String dateTo12String(Date date) {
        String dates = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dates = sdf.format(date);
        return dates;
    }

    public static String dateTo8String1(Date date) {
        String dates = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        dates = sdf.format(date);
        return dates;
    }

    public static String dateTo8String2(Date date) {
        String dates = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        dates = sdf.format(date);
        return dates;
    }

    public static String dateTo8String3(Date date) {
        String dates = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        dates = sdf.format(date);
        return dates;
    }

    public static String dateToYMD(Date date) {
        String dates = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");
        dates = sdf.format(date);
        return dates;
    }

    public static Integer dateTo8Integer(Date date) {
        String dates = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        dates = sdf.format(date);
        return Integer.valueOf(dates);
    }

    public static Date string8ToDate(String str) {
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        try {
            date = sdf.parse(str);
        } catch (ParseException var4) {
            date = null;
        }

        return date;
    }

    public static boolean validate(String dateString) {
        Pattern p = Pattern.compile("\\d{4}+\\d{1,2}+\\d{1,2}+");
        Matcher m = p.matcher(dateString);
        if (!m.matches()) {
            return false;
        } else {
            int year = Integer.valueOf(dateString.substring(0, 4));
            int month = Integer.valueOf(dateString.substring(4, 6));
            int day = Integer.valueOf(dateString.substring(6, 8));
            if (month >= 1 && month <= 12) {
                int[] monthLengths = new int[]{0, 31, -1, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
                if (isLeapYear(year)) {
                    monthLengths[2] = 29;
                } else {
                    monthLengths[2] = 28;
                }

                int monthLength = monthLengths[month];
                return day >= 1 && day <= monthLength;
            } else {
                return false;
            }
        }
    }

    private static boolean isLeapYear(int year) {
        return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    }

    public static Date now() {
        return new Date();
    }

    public static Date getDate(String year, String month, String day) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String str = year + "-" + month + "-" + day;
        Date date = null;

        try {
            date = sdf.parse(str);
        } catch (ParseException var7) {
            date = null;
        }

        return date;
    }

    public static int countDiffDateDays(Date startDate, Date endDate) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(startDate);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(endDate);
        int day1 = cal1.get(6);
        int day2 = cal2.get(6);
        int year1 = cal1.get(1);
        int year2 = cal2.get(1);
        if (year1 == year2) {
            return day2 - day1;
        } else {
            int timeDistance = 0;

            for(int i = year1; i < year2; ++i) {
                if ((i % 4 != 0 || i % 100 == 0) && i % 400 != 0) {
                    timeDistance += 365;
                } else {
                    timeDistance += 366;
                }
            }

            return timeDistance + (day2 - day1);
        }
    }

    public static long countDiffDateDays2(Date fDate, Date oDate) {
        Calendar aCalendar = Calendar.getInstance();
        aCalendar.setTimeInMillis(0L);
        aCalendar.setTime(fDate);
        fDate = getDate(aCalendar.get(1), aCalendar.get(2), aCalendar.get(6));
        aCalendar.setTime(oDate);
        oDate = getDate(aCalendar.get(1), aCalendar.get(2), aCalendar.get(6));
        long quot = (oDate.getTime() - fDate.getTime()) / 1000L / 60L / 60L / 24L;
        if (quot < 0L) {
            quot = -quot;
        }

        return quot;
    }

    public static Date getDate(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(1, year);
        cal.set(2, month);
        cal.set(6, day);
        cal.set(10, 0);
        cal.set(12, 0);
        cal.set(13, 0);
        cal.set(14, 0);
        return cal.getTime();
    }

    public static Date convertDate2Date(Date date) {
        String dateStr = dateTo8String(date);
        Date adate = convertStr2Date(dateStr);
        return adate;
    }

    public static int getYear(Date date) {
        Calendar aCalendar = Calendar.getInstance();
        aCalendar.setTime(date);
        return aCalendar.get(1);
    }

    public static int getDay(Date date) {
        Calendar aCalendar = Calendar.getInstance();
        aCalendar.setTime(date);
        return aCalendar.get(5);
    }

    public static int compareOnlyDate(Date date1, Date date2) {
        String date1str = dateTo8String(date1);
        Date newDate1 = convertStr2Date(date1str);
        String date2str = dateTo8String(date2);
        Date newDate2 = convertStr2Date(date2str);
        return newDate1.compareTo(newDate2);
    }

    public static boolean compareDate(Long date1, Date date2) {
        if (date1 == null && date2 == null) {
            return true;
        } else if (date1 == null && date2 != null) {
            return false;
        } else if (date1 != null && date2 == null) {
            return false;
        } else {
            return date1 == date2.getTime();
        }
    }

    public static long getBetweenDays(Date date1, Date date2) {
        if (date2.before(date1)) {
            Date date3 = date1;
            date1 = date2;
            date2 = date3;
        }

        long to = date1.getTime();
        long from = date2.getTime();
        return (from - to) / 86400000L;
    }

    public static String dateToFormat(Date date, String format) {
        String dates = null;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        dates = sdf.format(date);
        return dates;
    }

    public static Date getTheDayBefore(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(5, -1);
        Date beforeDate = calendar.getTime();
        return beforeDate;
    }

    public static Date getTheDayAfter(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(5, 1);
        Date afterDate = calendar.getTime();
        return afterDate;
    }

    public static Timestamp increaseDateAndToTimestamp(Date date, Number days) {
        Timestamp timestamp = null;
        if (date == null) {
            return timestamp;
        } else {
            int intDays = days.intValue();
            Date increaseDate = increaseDate(date, intDays);
            timestamp = new Timestamp(increaseDate.getTime());
            return timestamp;
        }
    }

    public static Timestamp dateToTimestamp(Date date) {
        Timestamp timestamp = null;
        if (date == null) {
            return timestamp;
        } else {
            timestamp = new Timestamp(date.getTime());
            return timestamp;
        }
    }

    public static String timestampToSMMdd(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        } else {
            Date date = new Date(timestamp.getTime());
            String dateStr = dateToFormat(date, "MM-dd");
            return dateStr;
        }
    }

    public static String timestampToSYYYYMMdd(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        } else {
            Date date = new Date(timestamp.getTime());
            String dateStr = dateToFormat(date, "yyyy-MM-dd");
            return dateStr;
        }
    }

    public static String timestampTo14String(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        } else {
            Date date = new Date(timestamp.getTime());
            String dateStr = dateTo14String(date);
            return dateStr;
        }
    }

    public static String calcDateDifference(long timeStart, long timeEnd) {
        long between = 0L;
        String returnStr = "";
        between = timeEnd - timeStart;
        long second = 1000L;
        long minute = 60L * second;
        long hour = minute * 60L;
        long day = hour * 24L;
        long month = day * 30L;
        long year = month * 12L;
        if (between >= 0L && between < second) {
            returnStr = "刚刚";
        } else if (second >= 0L && between < minute) {
            returnStr = between / second + "秒前";
        } else if (between >= minute && between < hour) {
            returnStr = between / minute + "分钟前";
        } else if (between >= hour && between < day) {
            returnStr = between / hour + "小时前";
        } else if (between >= day && between < month) {
            returnStr = between / day + "天前";
        } else if (between >= month && between < year) {
            returnStr = between / month + "个月前";
        } else if (between >= year) {
            returnStr = between / year + "年前";
        }

        return returnStr;
    }

    public static Date strToDate(String str) throws Exception {
        return StringUtils.isBlank(str) ? null : (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(str);
    }

    public static String timestamp2String(String str_num, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String date;
        if (str_num.length() == 13) {
            date = sdf.format(new Date(Long.parseLong(str_num)));
            return date;
        } else {
            date = sdf.format(new Date((long)Integer.parseInt(str_num) * 1000L));
            return date;
        }
    }

    public static Date timestamp2Date(String str_num, String format) {
        return str_num.length() == 13 ? new Date(Long.parseLong(str_num)) : new Date((long)Integer.parseInt(str_num) * 1000L);
    }

    public static Timestamp strToTimestamp(String str) throws Exception {
        if (StringUtils.isBlank(str)) {
            return null;
        } else {
            Timestamp timestamp = new Timestamp((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(str).getTime());
            return timestamp;
        }
    }

    public static long toTimePoint(int hour, int minute) {
        Calendar todayStart = Calendar.getInstance();
        todayStart.set(11, hour);
        todayStart.set(12, minute);
        todayStart.set(13, 0);
        todayStart.set(14, 0);
        return todayStart.getTime().getTime();
    }

    public static String toString(Date date, String pattern) {
        if (date != null && !date.equals("")) {
            if (pattern == null) {
                pattern = "yyyy-MM-dd";
            }

            String dateString = "";
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);

            try {
                dateString = sdf.format(date);
            } catch (Exception var5) {
                var5.printStackTrace();
            }

            return dateString;
        } else {
            return "";
        }
    }

    public static Date StringTo10Date(String str) {
        SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        try {
            return formate.parse(str);
        } catch (Exception var3) {
            return null;
        }
    }

    public static Date formatDate(Date date, String pattern){
        pattern = StringUtils.isBlank(pattern) ? "yyyy-MM-dd HH:mm:ss" : pattern;
        if(date == null){
            return null;
        }
        SimpleDateFormat formate = new SimpleDateFormat(pattern);
        try {
            Date result = formate.parse(formate.format(date));
            return result;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
