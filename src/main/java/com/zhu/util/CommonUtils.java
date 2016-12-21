package com.zhu.util;

import org.apache.commons.beanutils.BeanUtils;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

public class CommonUtils
{
    
    public static Integer defaultRandomStrLength = new Integer(32);
    
    /**
     * 分隔符 ,
     */
    public static final String SPLIT = ",";
    
    /**
     * 分隔符 .
     */
    public static final String SPLIT_POINT = "\\.";
    
    /**
     * 分隔符 :
     */
    public static final String SPLIT_COLON = ":";
    
    /**
     * 分隔符 ;
     */
    public static final String SPLIT_SEMICOLON = ";";
    
    /**
     * 分隔符 \
     */
    public static final String SPLIT_BACKSLASH = "\\\\";
    
    /**
     * 分割符 /
     */
    public static final String SPLIT_SLASH = "/";
    
    /**
     * 分隔符 |
     */
    public static final String SPLIT_LINE = "\\|";
    
    /**
     * 分隔符?
     */
    public static final String SPLIT_QUESTION = "\\?";
    
    /**
     * NOKIA告警级别*符号代表三种告警级别
     */
    public static final String NOKIA_ALARM_LOGO = "*";
    
    /**
     * 获得NOKIA的告警级别是否合理
     */
    public static boolean isIncludeNokiaAlarmLeve(String leve)
    {
        int index = leve.indexOf(NOKIA_ALARM_LOGO);
        return index>=0 ? true :false;
    }
    
    /**
     * 修改如果字符串为NULL 则让字符穿为空串
     */
    public static String nullToString(String arg)
    {
        if(CommonUtils.isNull(arg)||arg.equalsIgnoreCase("null"))
        {
            return "";
        }
        else
        {
            return arg;
        }
    }
    
    /**
     * 分解字符串为字符数组 。
     * 
     * @param value
     *            指定字符串
     * @param split
     *            切分字符串。
     * @return 字符串数组。
     */
    public static String[] stringToArraySplit(String value,String split)
    {
        String[] result = new String[0];
        
        if(isNotNull(value)&&split!=null)
        {
            result = value.split(split);
        }
        
        return result;
    }
    
    /**
     * 根据指定的字符从字符串中提取0到指定字符索引位置的字符串
     * 
     * @param value
     *            字符串
     * @param split
     *            指定字符
     * @return 提取后的字符穿
     */
    public static String extractionStringToIndex(String value,String split)
    {
        String result = "";
        if(isNull(value)&&isNull(split))
        {
            return result;
        }
        int index = value.indexOf(split);
        
        if(index<=0)
        {
            return value;
        }
        return value.substring(0,index);
        
    }
    
    /**
     * Long[] 转成分隔形式的字符串
     * 
     * @param ls
     * @return
     */
    public static String longsToString(Long[] ls)
    {
        StringBuffer sb = new StringBuffer();
        if(ls!=null&&ls.length>0)
        {
            for(int i = 0;i<ls.length;i++)
            {
                Long l = ls[i];
                sb.append(l.toString());
                sb.append(SPLIT);
            }
            // 去掉最后一个 分隔符，返回
            String str = sb.substring(0,sb.length()-1);
            return str;
        }
        return "";
    }
    
    /**
     * Object[] 转成分隔形式的字符串
     * 
     * @param ls
     * @return
     */
    public static String objectsToString(Object[] ls)
    {
        StringBuffer sb = new StringBuffer();
        if(ls!=null&&ls.length>0)
        {
            for(int i = 0;i<ls.length;i++)
            {
                Object l = ls[i];
                sb.append(l.toString());
                sb.append(SPLIT);
            }
            // 去掉最后一个 分隔符，返回
            String str = sb.substring(0,sb.length()-1);
            return str;
        }
        return "";
    }
    
    /**
     * Collection\<String\> 转成分隔形式的字符串
     * 
     * @param strs
     * @return
     */
    public static String strCollectionToStringSplit(Collection strs)
    {
        StringBuffer sb = new StringBuffer();
        if(strs!=null&&!strs.isEmpty())
        {
            Iterator itr = strs.iterator();
            while(itr.hasNext())
            {
                try
                {
                    String item = (String)itr.next();
                    sb.append(item.toString());
                    sb.append(SPLIT);
                }
                catch(Exception ex)
                {
                    
                }
            }
            // 去掉最后一个 分隔符，返回
            String str = sb.substring(0,sb.length()-1);
            return str;
        }
        return "";
    }
    
    /**
     * Collection 转成分隔形式的字符串
     * 
     * @param objs
     * @param propertyName
     * @return
     */
    public static String objCollectionToStringSplit(Collection objs,String propertyName)
    {
        StringBuffer sb = new StringBuffer();
        if(objs!=null&&!objs.isEmpty())
        {
            Iterator itr = objs.iterator();
            while(itr.hasNext())
            {
                try
                {
                    Object item = itr.next();
                    String itemProperty = BeanUtils.getProperty(item,propertyName);
                    sb.append(itemProperty);
                    sb.append(SPLIT);
                }
                catch(Exception ex)
                {
                    
                }
            }
            // 去掉最后一个 分隔符，返回
            String str = sb.substring(0,sb.length()-1);
            return str;
        }
        return "";
    }
    
    /**
     * Collection\<Long\> 转成分隔形式的字符串
     * 
     * @param ls
     * @return
     */
    public static String longsToString(Collection ls)
    {
        StringBuffer sb = new StringBuffer();
        if(ls!=null&&!ls.isEmpty())
        {
            Iterator itr = ls.iterator();
            while(itr.hasNext())
            {
                try
                {
                    Long l = (Long)itr.next();
                    sb.append(l.toString());
                    sb.append(SPLIT);
                }
                catch(Exception ex)
                {
                    ex.printStackTrace();
                }
            }
            // 去掉最后一个 分隔符，返回
            String str = sb.substring(0,sb.length()-1);
            return str;
        }
        return "";
    }
    
    /**
     * 分隔形式的字符串转成Long[]
     * 
     * @param str
     * @return
     */
    public static Long[] stringsToLong(String strSplit)
    {
        if(strSplit!=null&&strSplit.trim().length()>0)
        {
            String[] strItems = strSplit.split(SPLIT);
            List longList = new ArrayList();
            for(int i = 0;i<strItems.length;i++)
            {
                String strItm = strItems[i];
                try
                {
                    Long longItem = toLong(strItm);
                    longList.add(longItem);
                }
                catch(Exception ex)
                {
                    
                }
                
            }
            Long[] ls = new Long[longList.size()];
            longList.toArray(ls);
            return ls;
        }
        return new Long[] {};
    }
    
    /**
     * 分隔形式的字符串转成Long[]
     * 
     * @param str
     * @param split
     * @return
     */
    public static Long[] stringsToLong(String str,String split)
    {
        if(str!=null&&str.trim().length()>0)
        {
            String[] strItems = str.split(split);
            List longList = new ArrayList();
            for(int i = 0;i<strItems.length;i++)
            {
                String strItm = strItems[i];
                try
                {
                    Long longItem = toLong(strItm);
                    longList.add(longItem);
                }
                catch(Exception ex)
                {
                    
                }
                
            }
            Long[] ls = new Long[longList.size()];
            longList.toArray(ls);
            return ls;
        }
        return new Long[] {};
    }
    
    /**
     * 分隔形式的字符串转化成List\<Long\>
     * 
     * @param str
     * @return
     */
    public static List stringsToLongList(String str,String split)
    {
        if(str!=null&&str.trim().length()>0)
        {
            String[] strItems = str.split(split);
            List longList = new ArrayList();
            for(int i = 0;i<strItems.length;i++)
            {
                String strItm = strItems[i];
                try
                {
                    Long longItem = toLong(strItm);
                    longList.add(longItem);
                }
                catch(Exception ex)
                {
                    ex.printStackTrace();
                }
                
            }
            return longList;
        }
        return Collections.EMPTY_LIST;
    }
    
    /**
     * 分隔形式的字符串转化成List\<Long\>
     * 
     * @param str
     * @return
     */
    public static List stringsToLongList(String str)
    {
        if(str!=null&&str.trim().length()>0)
        {
            String[] strItems = str.split(SPLIT);
            List longList = new ArrayList();
            for(int i = 0;i<strItems.length;i++)
            {
                String strItm = strItems[i];
                try
                {
                    Long longItem = toLong(strItm);
                    longList.add(longItem);
                }
                catch(Exception ex)
                {
                    ex.printStackTrace();
                }
                
            }
            return longList;
        }
        return Collections.EMPTY_LIST;
    }
    
    /**
     * String[] 转化为 Long[]
     * 
     * @param strArray
     * @return
     */
    public static Long[] stringArrayToLongArray(String[] strArray)
    {
        if(strArray!=null&&strArray.length>0)
        {
            Long[] longArray = new Long[strArray.length];
            for(int i = 0;i<strArray.length;i++)
            {
                longArray[i] = toLong(strArray[i]);
            }
            return longArray;
        }
        return new Long[] {};
        
    }
    
    /**
     * Object[] 转化为 Long[]
     * 
     * @param strArray
     * @return
     */
    public static Long[] objectArrayToLongArray(Object[] strArray)
    {
        if(strArray!=null&&strArray.length>0)
        {
            Long[] longArray = new Long[strArray.length];
            for(int i = 0;i<strArray.length;i++)
            {
                longArray[i] = toLong(String.valueOf(strArray[i]));
            }
            return longArray;
        }
        return new Long[] {};
        
    }
    
    /**
     * 纪录用户操作的日志，其格式为user|option|message，其中信息最大允许128Byte.
     * 
     * @param user
     *            当前操作用户的登录帐户名
     * @param option
     *            操作名称,eg 增加帐户，删除帐户等。
     * @param msg
     *            DB发生时的各自段信息。
     * @return 纪录用户操作的日志
     */
    public static String getLogMessage(String user,String option,String msg)
    {
        StringBuffer sbMsg = new StringBuffer();
        
        sbMsg.append(user);
        sbMsg.append(SPLIT_LINE);
        sbMsg.append(option);
        sbMsg.append(SPLIT_LINE);
        sbMsg.append(msg);
        
        return sbMsg.toString();
    }
    
    /**
     * 判断是否是数字(包括小数)
     * 
     * @param str
     *            要判断的字符串
     * @return true 是数字 false 不是数字
     */
    public static boolean isNumeric(String str)
    {
        for(int i = str.length();--i>=0;)
        {
            if(!Character.isDigit(str.charAt(i))&&!".".equals(String.valueOf(str.charAt(i))))
            {
                return false;
            }
        }
        return true;
    }
    
    /**
     * 判断一个数字是否为负数
     * 
     * @param str
     * @return
     */
    public static boolean isMinus(String str)
    {
        
        try
        {
            if(Long.valueOf(str)>0)
            {
                return false;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            
        }
        return true;
    }
    
    /**
     * 判断字符串是否为空对象或空字符串，如果是则返回 True.
     * 
     * @param msg
     *            字符串
     * @return boolean 字符串为空则为True.
     */
    public static boolean isNull(String msg)
    {
        return (msg!=null&&msg.trim().length()>0) ? false :true;
    }
    
    /**
     * 判断字符串是否为空对象或空字符串，如果是则返回 True.
     * 
     * @param msg
     *            字符串
     * @return boolean 字符串为空则为True.
     */
    public static boolean isNullString(String msg)
    {
        boolean flag = false;
        if(null==msg||"".equals(msg)||"null".equalsIgnoreCase(msg))
        {
            flag = true;
        }
        return flag;
    }
    
    /**
     * 判断对象是否为空对象或空字符串，如果是则返回 True.
     * 
     * @param msg
     *            对象
     * @return boolean 对象为空则为True.
     */
    public static boolean isNull(Object msg)
    {
        return (msg!=null&&!msg.equals("")) ? false :true;
    }
    
    /**
     * 判断字符串是否为空对象或空字符串，如果是则返回 False.
     * 
     * @param msg
     *            字符串
     * @return boolean 字符串为空则为False.
     */
    public static boolean isNotNull(String msg)
    {
        return !isNull(msg);
    }
    
    /**
     * @param arreffect
     * @return
     */
    public static List stringsSplitToStringList(String strSplit)
    {
        List strList = new ArrayList();
        if(strSplit!=null&&strSplit.trim().length()>0)
        {
            String[] strItems = strSplit.split(SPLIT);
            for(int i = 0;i<strItems.length;i++)
            {
                String str = strItems[i];
                if(!isNull(str))
                {
                    strList.add(str.trim());
                }
            }
        }
        return strList;
    }
    
    /**
     * 分隔形式的字符串分隔为字符串数组
     * 
     * @param strSplit
     * @return
     */
    public static String[] stringsSplitToStrings(String strSplit)
    {
        List strList = stringsSplitToStringList(strSplit);
        String[] strs = new String[strList.size()];
        strList.toArray(strs);
        return strs;
    }
    
    /**
     * String 转Long
     * 
     * @param str
     * @return
     */
    public static Long toLong(String str)
    {
        try
        {
            if(isNotNull(str))
            {
                long n = Long.parseLong(str.trim());
                return new Long(n);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            Float f = toFloat(str);
            if(f!=null)
            {
                return new Long(f.longValue());
            }
        }
        return null;
    }
    
    /**
     * 将String类型转换为Integer
     */
    public static int toInt(String str)
    {
        try
        {
            
            if(isNotNull(str))
            {
                int context = Integer.parseInt(str);
                return context;
            }
            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return 0;
    }
    
    /**
     * 字符串转数值，如果格式不正确以默认值代替。
     * 
     * @param value
     *            输入字符串
     * @param defValue
     *            默认值
     * @return Long类型。
     */
    public static Long toLong(String value,Long defValue)
    {
        try
        {
            if(isNotNull(value))
            {
                long n = Long.parseLong(value.trim());
                return new Long(n);
            }
        }
        catch(Exception ex)
        {
            
        }
        
        return defValue;
    }
    
    /**
     * String 转Long
     * 
     * @param str
     * @return
     */
    public static Float toFloat(String str)
    {
        try
        {
            if(isNotNull(str))
            {
                float f = Float.parseFloat(str.trim());
                return new Float(f);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }
    
    /**
     * String 转Double
     * 
     * @param str
     * @return
     */
    public static Double toDouble(String str)
    {
        try
        {
            if(isNotNull(str))
            {
                double d = Double.parseDouble(str.trim());
                return new Double(d);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }
    
    /**
     * 往URL后面添加一个参数
     * 
     * @param url
     * @param paramName
     * @param paramValue
     * @return
     */
    public static String urlAddParam(String url,String paramName,String paramValue)
    {
        
        // 判断后面是添加?还是&
        StringBuffer urlSb = new StringBuffer(url);
        char lastchar = url.charAt(url.length()-1);
        String last4str = url.substring(url.length()-4);
        
        // 判断末尾是什么字符
        if(lastchar=='?'||lastchar=='&'||last4str.toUpperCase().equals("&AMP;"))
        {
            
        }
        else
        {
            if(url.lastIndexOf('?')<0)
            {
                urlSb.append("?");
            }
            else
            {
                urlSb.append("&amp;");
            }
        }
        
        // 加参
        urlSb.append(paramName);
        urlSb.append('=');
        urlSb.append(paramValue);
        
        return urlSb.toString();
    }
    
    /**
     * 对密码进行加密
     * 
     * @param 接收输入的密码或字符串
     *            ,进行加密
     */
    public static String encodeByHashCode(String inputString)
    {
        return null;
    }
    
    /**
     * 解密,严整密码是否正确
     * 
     * @param 接收输入参数
     * @return 返回boolean true验证成功 false验证失败
     */
    public static boolean validatePassWord(String inputString)
    {
        return true;
    }
    
    public static String getText(Object obj)
    {
        if(null==obj)
        {
            return "";
        }
        return obj.toString();
    }
    
    /**
     * 获取随机字符串.
     * 
     * @param length
     *            字符串长度.
     * @return 随机字符串
     */
    public static String getRandomString(Integer length)
    {
        if(length==null||length.longValue()==0L)
        {
            length = defaultRandomStrLength;
        }
        
        char chSt = 'A';
        char chEd = 'Z';
        char[] chs = new char[length.intValue()];
        
        for(int i = 0;i<length.intValue();i++)
        {
            double rdDb = Math.random();
            double dr = rdDb*(chEd-chSt);
            chs[i] = (char)(chSt+dr);
        }
        
        String str = new String(chs);
        return str;
    }
    
    /**
     * 判断字符串数组中有无重复无素
     * 
     * @param strs
     * @param trim
     * @return
     */
    public static boolean isArrayHasSaveItem(String[] strs,boolean trim)
    {
        int size = strs.length;
        
        // 去空格
        if(trim)
        {
            for(int k = 0;k<size;k++)
            {
                String str = strs[k];
                if(str==null)
                {
                    return true;
                }
                strs[k] = str.trim();
            }
        }
        
        int kMax = size-1;
        for(int k = 0;k<kMax;k++)
        {
            String strK = strs[k];
            for(int i = k+1;i<size;i++)
            {
                String strI = strs[i];
                if(strK.equals(strI))
                {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * 截取字符串，如果字符串 超过125的长度，则截取保留125长度
     */
    public static String interceptionString(String arg)
    {
        String str = "";
        String string = nullToString(arg);
        int length = string.length();
        if(length>0&&length>125)
        {
            str = string.substring(0,125);
        }
        if(length>0&&length<=125)
        {
            return arg;
        }
        
        return str;
    }
    
    /**
     * 截取字符串，如果字符串 默认为125
     * 
     * @param arg
     * @param length
     * @return
     */
    public static String interceptionString(String arg,Integer length)
    {
        try
        {
            
            if(null==length||length.intValue()<=0)
            {
                return interceptionString(arg);
            }
            else
            {
                String str = "";
                String string = nullToString(arg);
                if(isNull(string))
                {
                    return str;
                }
                
                if(string.length()<=length.intValue())
                {
                    str = string.substring(0,string.length());
                }
                else
                {
                    str = string.substring(0,length.intValue());
                }
                return str;
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return "";
        }
    }
    


    /**
     * 根据当前系统时间获得今天是星期几
     * 
     * @param dt
     * @return 星期几的数字形式
     */
    public static int getWeekValue(Date dt)
    {
        Calendar cal = Calendar.getInstance(Locale.CHINA);
        cal.setTime(dt);
        String a = cal.getTimeZone().getDisplayName(Locale.CHINA);
        int w = cal.get(Calendar.DAY_OF_WEEK)-1;
        if(w<0)
        {
            w = 0;
        }
        return w;
    }
    

    
    /**
     * 数组 转集合
     * 
     * @param objects
     *            数组
     * @return
     */
    public static Collection toCollection(Object[] objects)
    {
        return Arrays.asList(objects);
    }
    
    /**
     * String数组转换成以","分隔的字符串
     * 
     * @param strs
     * @return
     */
    public static String stringArrayToString(String[] strs)
    {
        String str = "";
        if(null!=strs)
        {
            for(int i = 0;i<strs.length;i++)
            {
                str += SPLIT+strs[i];
            }
            if(strs.length>0)
                str = str.substring(1);
        }
        return str;
    }
    
    /**
     * 将集合转成字符串
     * @param list
     * @param split
     * @return
     */
    public static String listToString(List<?> list,String splitstr)
    {
        String str = "";
        if(!CommonUtils.isNullList(list))
        {
            for(int i = 0;i<list.size();i++)
            {
                str = str + list.get(i) + splitstr;
            }
            
            if(str.endsWith(splitstr))
            {
                str = str.substring(0,str.length()-1);
            }
        }
        return str;
    }
    


    public static boolean isAdmin(Long userId)
    {
        if(userId!=null&&userId.longValue()<0)
        {
            return true;
        }
        
        return false;
    }
    
    /**
     * 判断value是否在Strs字符串中(strs是以','分隔的字符串)
     * 
     * @param strs
     * @param value
     * @return
     */
    public static boolean isHaveElement(String strs,String value)
    {
        if(isNull(strs))
            return false;
        if(isNull(value))
            return false;
        String[] temp = strs.split(SPLIT);
        for(int i = 0;i<temp.length;i++)
        {
            if(value.equals(temp[i].trim()))
                return true;
        }
        
        return false;
    }
    

    /**
     * 将String转码——报表导出时的报表名字
     * 
     * @param name
     * @return
     */
    public static String transcodingForExportReportName(String name)
    {
        String result = "";
        try
        {
            
            result = new String(name.getBytes("GBK"), "ISO8859-1")+".xls";
            
        }
        catch(UnsupportedEncodingException e2)
        {
            e2.printStackTrace();
        }
        return result;
    }
    
    /**
     * 将String转码——报表导出时的报表名字图片
     * 
     * @param name
     * @return
     */
    public static String transcodingForExportReportNameJPG(String name)
    {
        String result = "";
        try
        {
            
            result = new String(name.getBytes("GBK"), "ISO8859-1");
            
        }
        catch(UnsupportedEncodingException e2)
        {
            e2.printStackTrace();
        }
        return result;
    }
    
    /**
     * 将String转码——导出文件名称的转码utf-8
     * 
     * @param name
     * @return
     */
    public static String transcodingForExportFileName(String name)
    {
        String result = "";
        try
        {
            
            result = java.net.URLEncoder.encode(name,"utf-8");
            
        }
        catch(UnsupportedEncodingException e2)
        {
            e2.printStackTrace();
        }
        return result;
    }
    
    /**
     * 计算工作量
     * 
     * @param cyear
     *            工作年份
     * @param cmonth
     *            工作月份
     * @param begindate
     *            线路维护开始时间
     * @param enddate
     *            线路维护结束时间
     * @param length
     *            维护线路长度
     * @return 工作量
     */
    public static Double calWorkLoad(int cyear,int cmonth,Date begindate,Date enddate,double length)
    {
        try
        {
            double daycount = (double)calDatePeriod(begindate,enddate);
            double monthcount = (double) DateCalUtils.getMonthDayNum(cyear, cmonth);
            return ((daycount/monthcount)*length);
        }
        catch(Exception e)
        {
            return 0.0d;
        }
    }
    
    /**
     * 计算日期天数
     * 
     * @param begindate
     *            开始时间
     * @param enddate
     *            结束时间
     * @return 天数
     */
    private static int calDatePeriod(Date begindate,Date enddate)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(begindate);
        int bdate = calendar.get(Calendar.DAY_OF_MONTH);
        calendar = Calendar.getInstance();
        calendar.setTime(enddate);
        int edate = calendar.get(Calendar.DAY_OF_MONTH);
        if(edate<bdate)
        {
            return 0;
        }
        else
        {
            return (edate-bdate);
        }
    }
    
    /**
     * 格式化数组(数字或字符串)为SQL in条件中('1','2','3','4')
     * 
     * @param objects
     * @return
     */
    public static String formatArrayToSQLCondition(Object[] objects)
    {
        int size = 0;
        StringBuffer sb = new StringBuffer();
        if(objects!=null&&objects.length>0)
        {
            for(int i = 0;i<objects.length;i++)
            {
                if(i>0)
                {
                    sb.append(",");
                }
                sb.append("'").append(objects[i].toString()).append("'");
            }
        }
        else
        {
            sb.append("");
        }
        
        return sb.toString();
    }
    
    /**
     * 转换为百分比。
     * 
     * @param num
     * @return
     */
    public static String ConvertToPercentage(double num)
    {
        return num!=0.0D ? (double)Math.round(num*10000D)/100D+"%" :"0%";
    }
    
    /**
     * 转换为百分比。
     * 
     * @param num
     * @return
     */
    public static String ConvertToPercentage(Double num)
    {
        return ConvertToPercentage(num!=null ? num.doubleValue() :0.0D);
    }
    
    public static String ConvertToDouble(double num)
    {
        return num!=0.0D ? (new StringBuffer(String.valueOf((double)Math.round(num*10000D)/100D))).toString() :"0";
    }
    
    /**
     * 转换为百分比。
     * 
     * @param num
     * @return
     */
    public static String ConvertToDouble(Double num)
    {
        double tt;
        if(num==null)
        {
            tt = 0.0D;
        }
        else
        {
            tt = num.doubleValue();
        }
        
        return ConvertToDouble(tt);
    }
    
    /**
     * 查询MAP中是否已存在此KEY，如果不存在则新增
     * 
     * @param map
     *            MAP集合
     * @param orgObject
     *            key 可以是orgid或者orgname
     * @param queryObject
     *            查询结果List
     * @return
     */
    public static Map addMapByKey(Map map,Object orgObject,Object queryObject)
    {
        List sTList = null;
        if(map.containsKey(orgObject))
        {
            sTList = (List)map.get(orgObject);
            sTList.add(queryObject);
            map.remove(orgObject);
            map.put(orgObject,sTList);
        }
        else
        {
            sTList = new ArrayList();
            sTList.add(queryObject);
            map.put(orgObject,sTList);
        }
        
        return map;
    }
    
    /**
     * 根据季度获得对应的月数集合
     * 
     * @param quarter
     * @return
     */
    public static Integer[] getMonthsByQuarter(Integer quarter)
    {
        if(quarter==1)
            return new Integer[] {1,2,3};
        else if(quarter==2)
            return new Integer[] {4,5,6};
        else if(quarter==3)
            return new Integer[] {7,8,9};
        else return new Integer[] {10,11,12};
    }
    
    /**
     * 判断集合是否为空
     * 
     * @param objList
     *            要验证的List集合
     * @return boolean Boolean
     */
    public static Boolean isNullList(List<?> objList)
    {
        boolean flag = true;
        if(null!=objList&&objList.size()>0)
        {
            flag = false;
        }
        return flag;
    }
    
    /**
     * 判断Map是否为空
     * 
     * @param map
     *            要验证的map
     * @return boolean Boolean
     */
    public static Boolean isNullMap(Map<?,?> map)
    {
        boolean flag = true;
        if(null!=map&&map.keySet().size()>0)
        {
            flag = false;
        }
        return flag;
    }
    
    /**
     * 判断数组是否为空
     * 
     * @param objs
     *            要验证的数组集合
     * @return boolean Boolean
     */
    public static Boolean isNullArray(Object[] objs)
    {
        boolean flag = true;
        if(null!=objs&&objs.length>0)
        {
            flag = false;
        }
        return flag;
    }
    
    /**
     * 将code格式化显示
     * <p>
     * 例如，参数code 为1L，经格式化后，返回值为"001"
     * </p>
     * <p>
     * 返回 的样式为"001","023","123"，而且参数code,必须是数字，不能为字母或其他字符
     * </p>
     * <P>
     * 参数code被转换成Long类型，代码：Long num = Long.parseLong(code);
     * </p>
     * 
     * @param code
     * @return String
     */
    public static String formatLongCode(Long code)
    {
        String str = "";
        if(null!=code)
        {
            DecimalFormat df = new DecimalFormat("000");
            str = df.format(code);
        }
        return str;
    }
    
    public static String getExceptionStackTrace(Exception e)
    {
        StringBuffer sb = new StringBuffer();
        sb.append(e+"\r");
        StackTraceElement[] trace = e.getStackTrace();
        for(int i = 0;i<trace.length;i++)
            sb.append("\r at "+trace[i]);
        if(e.getCause()!=null)
            sb.append("Caused by: "+e.getCause());
        return sb.toString();
    }
    
    /**
     * 将两个数组合并为一个数据
     * 
     * @return
     */
    public static Long[] mergeArray(Long[] array1,Long[] array2)
    {
        Long[] targetArray = null;
        if(array1!=null&&array2!=null)
        {
            targetArray = new Long[array1.length+array2.length];
            System.arraycopy(array1,0,targetArray,0,array1.length);
            System.arraycopy(array2,0,targetArray,array1.length,array2.length);
        }
        else if(array1!=null&&array2==null)
        {
            targetArray = array1;
        }
        else if(array1==null&&array2!=null)
        {
            targetArray = array2;
        }
        return targetArray;
    }
    
    /**
     * 将传入的数字保留两位小数, 四舍五入.
     * 
     * @param objectValue
     *            需要转换的数字 float或double类型
     * @return Object 数字对象 float或double
     */
    public static String subNumRounding(Object objectValue)
    {
        Object resultObject = null;
        if(!CommonUtils.isNull(objectValue))
        {
            BigDecimal bd = null;
            if(objectValue instanceof Float)
            {
                float floatValue = (Float)objectValue;
                bd = new BigDecimal(floatValue);
                bd = bd.setScale(2,BigDecimal.ROUND_HALF_UP);
                resultObject = bd.floatValue();
            }
            else if(objectValue instanceof Double)
            {
                double doubleValue = (Double)objectValue;
                bd = new BigDecimal(doubleValue);
                bd = bd.setScale(2,BigDecimal.ROUND_HALF_UP);
                resultObject = bd.doubleValue();
            }
            else
            {
                resultObject = objectValue;
            }
        }
        return String.valueOf(resultObject);
    }
    
    /**
     * 将传入的数字保留两位小数, 四舍五入.
     * 
     * @param objectValue
     *            需要转换的数字 float或double类型
     * @return Object 数字对象 float或double
     */
    public static Float subNumThreeRounding(Object objectValue)
    {
        Object resultObject = null;
        if(!CommonUtils.isNull(objectValue))
        {
            BigDecimal bd = null;
            if(objectValue instanceof Float)
            {
                float floatValue = (Float)objectValue;
                bd = new BigDecimal(String.valueOf(floatValue));
                bd = bd.setScale(3,BigDecimal.ROUND_HALF_UP);
                resultObject = bd.floatValue();
            }
            else if(objectValue instanceof Double)
            {
                double doubleValue = (Double)objectValue;
                bd = new BigDecimal(String.valueOf(doubleValue));
                bd = bd.setScale(3,BigDecimal.ROUND_HALF_UP);
                resultObject = bd.doubleValue();
            }
            else
            {
                resultObject = objectValue;
            }
        }
        else
        {
            return null;
        }
        return Float.parseFloat(String.valueOf(resultObject));
    }
    
    /**
     * 添加逗号分隔
     * 
     * @param strs
     * @return
     */
    public static String strsToString(String[] strs)
    {
        StringBuffer sbf = new StringBuffer();
        for(int i = 0;i<strs.length;i++)
        {
            sbf.append(strs[i]).append(SPLIT);
        }
        return sbf.substring(0,sbf.length()-1);
    }
    
    /**
     * 添加逗号分隔
     * 
     * @param strs
     * @return
     */
    public static String strsToString(List list)
    {
        StringBuffer sbf = new StringBuffer();
        for(int i = 0;i<list.size();i++)
        {
            sbf.append(list.get(i).toString()).append(SPLIT);
        }
        return sbf.substring(0,sbf.length()-1);
    }
    
    /**
     * 根据String[]过滤出LIST集合
     * 
     * @param list
     * @param str
     * @return
     */
    public static List listToStrs(List list,String[] strs)
    {
        List backList = new ArrayList();
        for(int i = 0;i<strs.length;i++)
        {
            if(!list.contains(strs[i]))
            {
                backList.add(strs[i]);
            }
        }
        return backList;
    }
    
    /**
     * 合并数组
     */
    public static Long[] arrayToArray(Long[] specId,Long[] railway)
    {
        Long[] result = new Long[specId.length+railway.length];
        System.arraycopy(specId,0,result,0,specId.length);
        System.arraycopy(railway,0,result,specId.length,railway.length);
        return result;
    }

    
}
