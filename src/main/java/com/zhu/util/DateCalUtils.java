package com.zhu.util;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateCalUtils
{

	/** yyyy-MM-dd HH:mm:ss **/
	public static String SYS_DEFAUL_PATTERN = "yyyy-MM-dd HH:mm:ss";

	/** yyyy-MM-dd **/
	public static String SYS_DEFAUL_PATTERN2 = "yyyy-MM-dd";

	/** yyyymmdd **/
	public static String SYS_DEFAUL_PATTERN3 = "yyyyMMdd";

	/** yyyymmdd CN**/
	public static String SYS_DEFAUL_PATTERN_CN = "yyyy年MM月dd日";

	public static String[] WEEK_DAY = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
	/**
	 * 日期转换,输入String输出sqlDate
	 * @param date
	 * @return
	 */
	public static Date formatDate(String date){

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date javaDate=null;
		try {
			javaDate = sdf.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Date(javaDate.getTime());

	}
	/**
	 * 计算2个时间之差(小时)保留一位小数
	 *
	 * @param stDate
	 * @param endDate
	 * @return
	 */
	/*public static int getIntDateDayDecimal(Date stDate, Date endDate)
	{
		String sdatestr = DateUtils.toString(stDate, DateUtils.SYS_DEFAUL_PATTERN2);
		String edatestr = DateUtils.toString(endDate, DateUtils.SYS_DEFAUL_PATTERN2);
		stDate = DateUtils.parseStr2Date(sdatestr, DateUtils.SYS_DEFAUL_PATTERN2);
		endDate = DateUtils.parseStr2Date(edatestr, DateUtils.SYS_DEFAUL_PATTERN2);
		Long days = 0l;
		if (stDate == null || endDate == null)
		{
			return days.intValue();
		}
		double startTime = stDate.getTime();
		double endTime = endDate.getTime();
		double offset = endTime - startTime;
		DecimalFormat df = new DecimalFormat("###");
		days = new Long(df.format(offset / (1000 * 60 * 60 * 24)));// 根据时间差（微秒）计算小时差
		// System.out.println(days.intValue());
		return days.intValue();
	}*/

	/**
	 * 计算2个时间之差(天)保留一位小数
	 *
	 * @param stDate
	 * @param endDate
	 * @return
	 */
	public static int getIntDateDayDecimal(Date endDate)
	{

		Date stDate = DateCalUtils.patchDateToAM(new Date());
		Long days = 0l;
		if (stDate == null || endDate == null)
		{
			return days.intValue();
		}
		double startTime = stDate.getTime();
		double endTime = endDate.getTime();
		double offset = endTime - startTime;
		DecimalFormat df = new DecimalFormat("###");
		days = new Long(df.format(offset / (1000 * 60 * 60 * 24)));// 根据时间差（微秒）计算日期差
		System.out.println(days.intValue());
		return days.intValue();
	}

	 /*public static void main(String[] args) {
	 	 Date now = new Date();
		 Date am =patchDateToAM(now);
		 System.out.println(parseDateToString(am,SYS_DEFAUL_PATTERN));
		 System.out.println(parseDateToString(patchDateToMidnight(now),SYS_DEFAUL_PATTERN));
		 System.out.println(parseDateToString(patchDateToMonthStart(now),SYS_DEFAUL_PATTERN));
		 System.out.println(parseDateToString(patchDateToMidnight(patchDateToMonthEnd(now)),SYS_DEFAUL_PATTERN));


	 *//*Double d =
	 DateUtils.getIntDateDayDecimal(DateUtils.parseStr2Date("2013-11-14 12:23:23","yyyy-MM-dd HH:mm:ss"),
	 DateUtils.parseStr2Date("2013-11-14","yyyy-MM-dd"));
	 System.out.println(d);*//*
	 }*/
	/**
	 * 修改日期到参数日期的凌晨(00:00:00)
	 *
	 * @return
	 */
	public static final Date patchDateToAM(Date src)
	{

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(src);
		calendar.set(Calendar.HOUR_OF_DAY, 00);
		calendar.set(Calendar.MINUTE, 00);
		calendar.set(Calendar.SECOND, 00);
		return calendar.getTime();
	}

	/**
	 * 修改日期到参数日期的午夜(23:59:59)
	 * @return
	 */
	public static final Date patchDateToMidnight(Date src)
	{

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(src);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}

	/**
	 * Add by HuangZiJie
	 * 得到当参数周周一的凌晨(00:00:00)
	 * @return
	 */
	public static final Date patchWeekToAM(Date src){
		Calendar calendar=Calendar.getInstance(Locale.CHINA);
		calendar.setTime(src);
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return patchDateToAM(calendar.getTime());
	}

	/**
	 * Add by HuangZiJie
	 * 得到当参数周周末的午夜(23:59:59)
	 * @return
	 */
	public static final Date patchWeekToMidnight(Date src){
		Calendar calendar=Calendar.getInstance(Locale.CHINA);
		calendar.setTime(src);
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		return patchDateToMidnight(calendar.getTime());
	}

	/**
	 * 修改日期到参数日期的凌晨(23:59:59)
	 *
	 * @return
	 */
	public static final Date patchDateToNoon(Date src)
	{

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(src);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}

	/**
	 * 根据日期得到第二天的日期
	 *
	 * @param src
	 * @return
	 */
	public static final Date getTomorrow(Date src)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(src);
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		// Date date = new Date(src.getYear(), src.getMonth(), src.getDate() +
		// 1);
		return calendar.getTime();
	}

	public static final Date moveDay(Date src, int addNum)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(src);
		calendar.add(Calendar.DAY_OF_YEAR, addNum);
		// Date date = new Date(src.getYear(), src.getMonth(), src.getDate() +
		// 1);
		return calendar.getTime();
	}

	/**
	 * 根据日期得到月初的日期 1号00：00：00
	 *
	 * @param src
	 * @return
	 */
	public static final Date patchDateToMonthStart(Date src)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(src);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 根据日期得到月末的日期 30 31 28 29号00：00：00
	 *
	 * @param src
	 * @return
	 */
	public static final Date patchDateToMonthEnd(Date src)
	{
		Date monthSt = patchDateToMonthStart(src);
		Date nextMonthSt = addMonth(monthSt, 1);
		Date monthEnd = moveDay(nextMonthSt, -1);
		return monthEnd;
	}

	/**
	 * 根据日期得到年初的日期 1号00：00：00
	 *
	 * @param src
	 * @return
	 */
	public static final Date patchDateToYearStart(Date src)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(src);
		calendar.set(Calendar.DAY_OF_YEAR, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 判断日期是否在两个时期之间，前后包含
	 *
	 * @param date
	 * @param start
	 * @param end
	 * @return
	 */
	public static final boolean isBetween(Date date, Date start, Date end)
	{
		if (date.before(start) || date.after(end))
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	/**
	 *
	 * 指定时间加/减 N 月后的日期
	 *
	 * @param src
	 *            指定时间
	 * @param montNum
	 *            加/减 N 月
	 * @return
	 */
	public static final Date addMonth(Date src, int montNum)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(src);
		calendar.add(Calendar.MONTH, montNum);
		return calendar.getTime();
	}

	/**
	 * 指定时间加/减 N 天后的日期
	 *
	 * @param date
	 *            指定时间
	 * @param addNum
	 *            加/减 N 天
	 * @return
	 */
	public static Date addDate(Date date, int addNum)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, addNum);
		return calendar.getTime();
	}

	/**
	 * 指定时间加/减 N 小时后的日期
	 *
	 * @param date
	 *            指定时间
	 * @param addNum
	 *            加/减 N 天
	 * @return
	 */
	public static Date addHour(Date date, int addNum)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR, addNum);
		return calendar.getTime();
	}

	/**
	 * 将日期按照pattern进行格式化
	 *
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static final String toString(Date date, String pattern)
	{
		String fmtstr = "";
		if (!CommonUtils.isNull(date))
		{
			if (CommonUtils.isNull(pattern))
			{
				pattern = SYS_DEFAUL_PATTERN;
			}
			SimpleDateFormat df = new SimpleDateFormat(pattern);

			fmtstr = df.format(date);
		}

		return fmtstr;
	}

	/**
	 * 将日期按照yyyy-MM-dd HH:mm:ss进行格式化
	 *
	 * @param date
	 * @param pattern
	 * @return
	 */
	/*public static final String toString(Date date)
	{
		return toString(date, null);
	}*/

	/**
	 * 将日期按照yyyy-MM-dd进行格式化
	 *
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static final String dateToString(Date date)
	{
		return toString(date, SYS_DEFAUL_PATTERN2);
	}

	/**
	 * 将字符串按照pattern格式转成日期类型
	 *
	 * @param dateStr
	 * @param pattern
	 * @return
	 */
	public static final Date parseStr2Date(String dateStr, String pattern)
	{
		if (CommonUtils.isNull(pattern))
		{
			pattern = SYS_DEFAUL_PATTERN;
		}
		SimpleDateFormat dateFormattor = new SimpleDateFormat(pattern);
		try
		{
			return dateFormattor.parse(dateStr);
		}
		catch (ParseException e)
		{
			return null;
		}
	}
	/**
	 * 得到一个月有多少天
	 *
	 * @param year
	 * @param month
	 * @return
	 */
	public static int getMonthDayNum(int year, int month)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, 1);
		Date monthEnd = patchDateToMonthEnd(calendar.getTime());

		calendar.setTime(monthEnd);
		int monthEndDayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
		return monthEndDayOfMonth;
	}

	/**
	 * 修改一个日期对象的某个项的值，设为指定值
	 *
	 * @param dateSrc
	 * @param field
	 * @param value
	 * @return
	 */
	public static Date setDatePart(Date dateSrc, int field, int value)
	{

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(dateSrc);
		calendar.set(field, value);

		return calendar.getTime();
	}

	/**
	 * 修改一个日期对象的某个项的值，增加指定值
	 *
	 * @param dateSrc
	 * @param field
	 * @param value
	 * @return
	 */
	public static Date addDatePart(Date dateSrc, int field, int value)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateSrc);
		calendar.add(field, value);

		return calendar.getTime();
	}

	public static Float getDateHour(Date stDate, Date endDate)
	{

		Float hours = new Float(0);

		if (stDate == null || endDate == null)
		{
			return null;
		}
		long startTime = stDate.getTime();
		long endTime = endDate.getTime();

		long offset = endTime - startTime;

		hours = new Float(offset / (1000 * 60 * 60));// 根据时间差（微秒）计算小时差

		return hours;
	}

	public static Double getDoubleDateHour(Date stDate, Date endDate)
	{
		Double hours = new Double(0);
		if (stDate == null || endDate == null)
		{
			return hours;
		}
		long startTime = stDate.getTime();
		long endTime = endDate.getTime();
		long offset = endTime - startTime;
		hours = new Double(offset / (1000 * 60 * 60));// 根据时间差（微秒）计算小时差
		return hours;
	}

	/**
	 * 计算2个时间之差(分钟)
	 *
	 * @param stDate
	 * @param endDate
	 * @return
	 */
	public static Double getDoubleDateMinute(Date stDate, Date endDate)
	{
		Double hours = new Double(0);
		if (stDate == null || endDate == null)
		{
			return hours;
		}
		long startTime = stDate.getTime();
		long endTime = endDate.getTime();
		long offset = endTime - startTime;
		hours = new Double(offset / (1000 * 60));// 根据时间差（微秒）计算小时差
		return hours;
	}

	/**
	 * 计算2个时间之差(小时)保留一位小数
	 *
	 * @param stDate
	 * @param endDate
	 * @return
	 */
	public static Double getDoubleDateHourDecimal(Date stDate, Date endDate)
	{
		Double hours = new Double(0);
		if (stDate == null || endDate == null)
		{
			return hours;
		}
		double startTime = stDate.getTime();
		double endTime = endDate.getTime();
		double offset = endTime - startTime;
		DecimalFormat df = new DecimalFormat("###.0");
		hours = new Double(df.format(offset / (1000 * 60 * 60)));// 根据时间差（微秒）计算小时差
		return hours;
	}

	public static String getDateLength(Date stDate, Date endDate)
	{
		if (stDate == null || endDate == null)
		{
			return null;
		}
		long stMiniS = stDate.getTime();
		long endMiniS = endDate.getTime();

		long chaL = endMiniS - stMiniS;

		long day = chaL / (24 * 60 * 60 * 1000);
		long hour = (chaL / (60 * 60 * 1000) - day * 24);
		long min = ((chaL / (60 * 1000)) - day * 24 * 60 - hour * 60);

		StringBuffer sb = new StringBuffer();
		sb.append(day);
		sb.append("天");
		sb.append(hour);
		sb.append("小时");
		sb.append(min);
		sb.append("分钟");
		return sb.toString();
	}

	public static Date getHalfYearStart(Date dateSrc)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateSrc);
		int month = calendar.get(Calendar.MONTH);
		// 上半年 从0开始的
		if (month < 6)
		{
			return patchDateToYearStart(dateSrc);
		}
		else
		{
			calendar.set(Calendar.MONTH, 6);
			Date dt = calendar.getTime();
			return patchDateToMonthStart(dt);
		}
	}

	public static Date getHalfYearEnd(Date dateSrc)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateSrc);
		int month = calendar.get(Calendar.MONTH);
		// 上半年 从0开始的
		if (month < 6)
		{
			calendar.set(Calendar.MONTH, 5);
			Date dt = calendar.getTime();
			return patchDateToMonthEnd(dt);
		}
		else
		{
			calendar.set(Calendar.MONTH, 11);
			Date dt = calendar.getTime();
			return patchDateToMonthEnd(dt);
		}
	}

	public static Date getSeasonStart(Date dateSrc)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateSrc);
		int month = calendar.get(Calendar.MONTH);
		// 上半年 从0开始的
		if (month < 3)
		{
			return patchDateToYearStart(dateSrc);
		}
		if (month < 6)
		{
			calendar.set(Calendar.MONTH, 3);
			Date dt = calendar.getTime();
			return patchDateToMonthStart(dt);
		}
		if (month < 9)
		{
			calendar.set(Calendar.MONTH, 6);
			Date dt = calendar.getTime();
			return patchDateToMonthStart(dt);
		}
		calendar.set(Calendar.MONTH, 9);
		Date dt = calendar.getTime();
		return patchDateToMonthStart(dt);
	}

	public static Date getSeasonEnd(Date dateSrc)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateSrc);
		int month = calendar.get(Calendar.MONTH);
		// 上半年 从0开始的
		if (month < 3)
		{
			calendar.set(Calendar.MONTH, 2);
			Date dt = calendar.getTime();
			return patchDateToMonthEnd(dt);
		}
		if (month < 6)
		{
			calendar.set(Calendar.MONTH, 5);
			Date dt = calendar.getTime();
			return patchDateToMonthEnd(dt);
		}
		if (month < 9)
		{
			calendar.set(Calendar.MONTH, 8);
			Date dt = calendar.getTime();
			return patchDateToMonthEnd(dt);
		}
		calendar.set(Calendar.MONTH, 11);
		Date dt = calendar.getTime();
		return patchDateToMonthEnd(dt);
	}

	public static Date getDefaultExceTime(Date nowDate)
	{
		Calendar expecteCal = Calendar.getInstance(Locale.CHINA);
		expecteCal.setTime(nowDate);
		int hour = expecteCal.get(Calendar.HOUR_OF_DAY);
		if (hour - 12 > 0)
		{
			expecteCal.set(Calendar.HOUR_OF_DAY, 17);
			expecteCal.set(Calendar.MINUTE, 0);
			expecteCal.set(Calendar.SECOND, 0);
		}
		else
		{
			expecteCal.set(Calendar.HOUR_OF_DAY, 12);
			expecteCal.set(Calendar.MINUTE, 0);
			expecteCal.set(Calendar.SECOND, 0);
		}
		return expecteCal.getTime();
	}

	/**
	 * 返回最近3年的年份数组
	 *
	 * @return
	 */
	public static int[] getCurrentYear3()
	{
		Calendar cal = Calendar.getInstance();
		int[] year = { cal.get(Calendar.YEAR), cal.get(Calendar.YEAR) - 1, cal.get(Calendar.YEAR) - 2 };

		return year;
	}

	/**
	 * 根据年月 返回比传入年月晚一个月的时间
	 *
	 * @param year
	 * @param month
	 * @return
	 */
	public static Date getDateMonthAdd(int year, int month)
	{
		String time = null;
		if (month == 12)
		{
			time = (year + 1) + "-01-01";

		}
		else
		{
			time = year + "-" + (month + 1) + "-01";
		}
		return DateCalUtils.parseStr2Date(time, "yyyy-MM-dd");
	}

	/**
	 * 根据年月 返回时间
	 *
	 * @param year
	 * @param month
	 * @return
	 */
	public static Date getDateMonth(int year, int month)
	{
		String time = year + "-" + month + "-01";
		return DateCalUtils.parseStr2Date(time, "yyyy-MM-dd");
	}

	/**
	 * 根据传入日期参数，返回当前日期是否在传入日期之后
	 *
	 * @param d
	 *            传入日期
	 * @return
	 */
	public static boolean confirmDate(Date d)
	{
		Date date = new Date();
		if (d.before(date))
		{
			return true;
		}
		return false;
	}


	/**
	 * 得到哪一年
	 *
	 * @param date
	 * @return
	 */
	public static int getYear(Date date)
	{
		Calendar calendar = Calendar.getInstance(Locale.CHINA);
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}


	/**
	 * 得到在一年中的第几天
	 *
	 * @param date
	 * @return
	 */
	public static int getDayOfYear(Date date)
	{
		Calendar calendar = Calendar.getInstance(Locale.CHINA);
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_YEAR);
	}

	/**
	 * 得到在一个周中的第几天
	 * @param date
	 * @return
	 */
	public static int getDayOfWeek(Date date)
	{
		Calendar calendar = Calendar.getInstance(Locale.CHINA);

		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.setTime(date);
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

		return dayOfWeek;
	}

	/**
	 * 得到在一年中的第几周，按周算法来计算，不是按1月1日-1月7日为第一周的计算方法
	 * @param date
	 * @return
	 */
	public static int getWeekOfYear(Date date)
	{
		Calendar calendar = Calendar.getInstance(Locale.CHINA);

		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.setTime(date);
		int weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);

		return weekOfYear;
	}

	/**
	 * 得到在一年中的第几月
	 * @param date
	 * @return
	 */
	public static int getMonthOfYear(Date date)
	{
		Calendar calendar = Calendar.getInstance(Locale.CHINA);
		calendar.setTime(date);
		int monthOfYear = calendar.get(Calendar.MONTH);

		//由于java认定每年的月份从0-11，所以要将值转换一下，+1
		monthOfYear += 1;

		return monthOfYear;
	}

	/**
	 * 根据传入的年月返回时间 Date
	 *
	 * @param year
	 * @param month
	 * @return
	 */
	public static Date getDateByYearAndMonth(int year, int month)
	{
		String time = year + "-" + month + "-01";
		return parseStr2Date(time, "yyyy-MM-dd");
	}

	/**
	 * 根据时间返回时间所在的年
	 *
	 * @param date
	 * @return
	 */
	public static int getYearByDate(Date date)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * 根据时间返回时间所在的月
	 *
	 * @param date
	 * @return
	 */
	public static int getMonthByDate(Date date)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH) + 1;
	}

	/**
	 * 判断两个日期是否是同年同月
	 *
	 * @param date
	 * @param date1
	 * @return
	 */
	public static boolean getCompareDate(Date date, Date date1)
	{
		Calendar calendar = Calendar.getInstance();
		Calendar calendar1 = Calendar.getInstance();
		calendar.setTime(date);
		calendar1.setTime(date1);
		if (calendar.get(Calendar.YEAR) == calendar1.get(Calendar.YEAR) && calendar.get(Calendar.MONTH) == calendar1.get(Calendar.MONTH))
		{
			return true;
		}
		return false;

	}

	/**
	 * 根据时间返回时间所在月的第几天
	 *
	 * @param date
	 * @return
	 */
	public static int getDayByDate(Date date)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	public static String lastTime(int mm, int ww)
	{
		Date date = new Date();
		int year = Integer.parseInt(new SimpleDateFormat("yyyy").format(date));
		int month = Integer.parseInt(new SimpleDateFormat("MM").format(date)) - mm;
		int day = Integer.parseInt(new SimpleDateFormat("dd").format(date)) - ww;

		if (day < 1)
		{
			month -= 1;
			if (month == 0)
			{
				year -= 1;
				month = 12;
			}
			if (month == 4 || month == 6 || month == 9 || month == 11)
			{
				day = 30 + day;
			}
			else if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)
			{
				day = 31 + day;
			}
			else if (month == 2)
			{
				if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0))
					day = 29 + day;
				else
					day = 28 + day;
			}
		}
		String y = year + "";
		String m = "";
		String d = "";
		if (month < 10)
			m = "0" + month;
		else
			m = month + "";
		if (day < 10)
			d = "0" + day;
		else
			d = day + "";

		return y + "-" + m + "-" + d + " 00:00:01";
	}

	/**
	 * 将A日期的年月日和B日期的时分秒组合成为一个日期
	 *
	 * @param dateA
	 * @param dateB
	 * @return
	 */
	public static Date getDateByTwoDate(Date dateA, Date dateB)
	{
		Calendar calendar = Calendar.getInstance();
		Calendar calendarA = Calendar.getInstance();
		Calendar calendarB = Calendar.getInstance();
		calendarA.setTime(dateA);
		calendarB.setTime(dateB);
		calendar.set(calendarA.get(Calendar.YEAR), calendarA.get(Calendar.MONTH), calendarA.get(Calendar.DATE), calendarB.get(Calendar.HOUR_OF_DAY), calendarB.get(Calendar.MINUTE),
				calendarB.get(Calendar.SECOND));
		return calendar.getTime();
	}

	/**
	 * 获取当前日期的上一月的字符串[yyyyMM格式]
	 *
	 * @return
	 */
	public static String getPreMonthStr()
	{
		Calendar calendar = Calendar.getInstance();
		// 前一个月
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) - 1, 1);
		return new SimpleDateFormat("yyyyMM").format(calendar.getTime());
	}

	/**
	 * 获得某年某月的天数
	 *
	 * @param year
	 *            年份
	 * @param month
	 *            月份
	 * @return
	 */
	public static int maxDays(int year, int month)
	{
		int maxDays = 0;
		if (month == 4 || month == 6 || month == 9 || month == 11)
		{
			maxDays = 30;
		}
		else if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)
		{
			maxDays = 31;
		}
		else if (month == 2)
		{
			if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0))
				maxDays = 29;
			else
				maxDays = 28;
		}
		return maxDays;
	}

	/**
	 * 取年月 符合1303格式
	 *
	 * @return
	 */
	public static String getYearAndMonth()
	{
		Calendar cal = Calendar.getInstance();// 使用日历类
		int year = cal.get(Calendar.YEAR);// 得到年
		int month = cal.get(Calendar.MONTH) + 1;// 得到月，因为从0开始的，所以要加1
		if (String.valueOf(month).length() == 2)
		{
			return (String.valueOf(year) + month).substring(2);
		}
		return (String.valueOf(year) + "0" + month).substring(2);
	}

	/**
	 * 根据月数计算所在季度
	 *
	 * @return
	 */
	public static int getQuarterBymonth(int month)
	{
		double t = month / 3.0;
		int d = 0;
		if (t <= 1.0)
		{
			d = 1;
		}
		else if (t <= 2.0)
		{
			d = 2;
		}
		else if (t <= 3.0)
		{
			d = 3;
		}
		else if (t <= 4.0)
		{
			d = 4;
		}
		return d;
	}

	/**
	 * 比较日期大小
	 *
	 * @param dateA
	 * @param dateB
	 * @return
	 */
	public static boolean getDateByTwoDate(String dateA, String dateB)
	{
		Date dA = parseStr2Date(dateA, "yyyy-MM-dd");
		Date dB = parseStr2Date(dateB, "yyyy-MM-dd");
		if (dA.getTime() - dB.getTime() > 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * 比较时间大小
	 *
	 * @param dateA
	 * @param dateB
	 * @return
	 */
	public static boolean getDateBy2Date(String dateA, String dateB)
	{
		Date dA = parseStr2Date(dateA, "yyyy-MM-dd HH:mm:ss");
		Date dB = parseStr2Date(dateB, "yyyy-MM-dd HH:mm:ss");
		if (dA.getTime() - dB.getTime() > 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * 根据传入的日期 得到当前日期周的第一天
	 *
	 * @param time
	 * @return
	 */
	public static String getWeekFirst(Date time)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 设置时间格式
		Calendar cal = Calendar.getInstance();
		cal.setTime(time);
		// 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		if (1 == dayWeek)
		{
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
		int day = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
		String imptimeBegin = sdf.format(cal.getTime());
		return imptimeBegin;
	}

	/**
	 * 根据传入的日期 得到当前日期周的最后一天
	 *
	 * @param time
	 * @return
	 */
	public static String getWeekEnd(Date time)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 设置时间格式
		Calendar cal = Calendar.getInstance();
		cal.setTime(time);
		// 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		if (1 == dayWeek)
		{
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
		int day = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
		cal.add(Calendar.DATE, 6);
		String imptimeEnd = sdf.format(cal.getTime());
		return imptimeEnd;
	}

	/**
	 * 根据当前日期得到一年当中的第几周
	 *
	 * @param time
	 * @return
	 */
	public static int getWeekCountForYear(Date time)
	{
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(time);
		int currentWeekOfYear = c.get(Calendar.WEEK_OF_YEAR);
		return currentWeekOfYear;
	}

	/**
	 * 获得当前时间所在月份的最后一天所在日期<br>
	 *
	 * @return
	 */
	public static String getLastMonthDay(Date time)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(time);
		int year = 0;
		int month = cal.get(Calendar.MONTH);
		int day = cal.getActualMaximum(Calendar.DAY_OF_MONTH); // 结束天数
		month = month + 1;
		if (month == 0)
		{
			year = cal.get(Calendar.YEAR) - 1;
			month = 12;
		}
		else
		{
			year = cal.get(Calendar.YEAR);
		}
		String endDay = "";
		String monthstr = "";
		String daystr = "";
		if (month < 10)
		{
			monthstr = "0" + month;
		}
		else
		{
			monthstr = String.valueOf(month);
		}
		if (day < 10)
		{
			daystr = "0" + day;
		}
		else
		{
			daystr = String.valueOf(day);
		}
		endDay = year + "-" + monthstr + "-" + daystr;
		return endDay;
	}

	/**
	 * 获得两个日期之间月份差值
	 *
	 * @param stDate
	 * @param endDate
	 * @return
	 */
	public static int getBetweenMonth(Date stDate, Date endDate)
	{
		Calendar stcal = Calendar.getInstance();
		Calendar encal = Calendar.getInstance();
		stcal.setTime(stDate);
		encal.setTime(endDate);
		int stmonth = stcal.get(Calendar.MONTH);
		int enmonth = encal.get(Calendar.MONTH);
		int result = 0;
		int styear = stcal.get(Calendar.YEAR);
		int enyear = encal.get(Calendar.YEAR);
		if (styear == enyear)
		{
			result = enmonth - stmonth;// 两个日期相差几个月，即月份差
		}
		else
		{
			result = 12 * (enyear - styear) + enmonth - stmonth;// 两个日期相差几个月，即月份差
		}
		return result;
	}

	/**
	 * 日期类型转换成字符串
	 *
	 * @param date
	 * @param strpatten
	 * @return
	 */
	public static String parseDateToString(Date date, String strpatten)
	{
		String datestr = "";
		SimpleDateFormat format = new SimpleDateFormat(strpatten);
		datestr = format.format(date);
		return datestr;
	}
}
