package org.biz.utility.date;

//import sun.util.calendar.ZoneInfo;

import javax.xml.datatype.*;
import java.text.*;
import java.util.*;
import org.biz.app.ui.util.StringUtility;


public class DateAndTimeUtility {

  public static final int yyyyMMddHHmmss = 1;
  public static final int yyyyMMddHHmm = 2;
  public static final int yyyyMMddHH = 3;
  public static final int yyyyMMdd = 4;
  public static final int yyMMddHHmmss = 5;
  public static final int HHmmss = 6;
  public static final int HHmm = 7;
  public static final int HH = 8;
  public static final int HH_mm_ss = 9;
  public static final int HH_mm = 10;
  public static final int dd_MM_yyyy = 11;
  public static final int dd_MM_yy = 12;
  public static final int dd_MM_yyyy_HH_mm_ss = 13;
  public static final int dd_MM_yyyy_HH_mm = 14;
  public static final int yyyy = 15;
  public static final int DD_HH_MM = 16;
  public static final int DD_HH = 17;
  public static final int yyyy_MM_dd_HH_mm = 18;
  public static final int ddMMyyyy = 19;
  public static final int yyyy_MM_dd = 20;
  public static final int yyyyMMddHHmmssSS = 21;
  public static final int yyyy_MM_dd_HH_mm_ss = 22;
  public static final int HH_mm_AM_PM = 23;
  public static final int mm_ss = 24;
  public static final int yyyyMM = 25;
  public static final int yyyy_MM_ddTHH_mm_ss = 26;
  public static final int ddMMyyyy_HHmm = 27;
  public static final int dd_MM_yyyy_SLASHED = 28;
  public static final int MMM_dd = 29;
  public static final int mm = 30;

  public static final int DAYS = 1;
  public static final int MONTHS = 2;
  public static final int YEARS = 3;
  public static final int HOURS_IN_A_DAY = 24;

  public static final int MINUTES_IN_A_DAY = HOURS_IN_A_DAY * 60;
  public static final int SECONDS_IN_A_DAY = MINUTES_IN_A_DAY * 60;
  public static String NL = "\n";
  public static String pathSeparator = "/";
  public static int DAYS_PER_WEEK = 7;

  //constants for defining commonly used units of time
  public static final int SECOND_IN_MS = 1000;              //a second, represented in milliseconds
  public static final int MINUTE_IN_SECOND = 60;            //a minute, represented in seconds
  public static final int MINUTE_IN_MS = 60 * 1000;         //a minute, represented in milliseconds
  public static final int HOUR_IN_MS = 60 * MINUTE_IN_MS;   //an hour, represented in milliseconds
  public static final int HOUR_IN_MINUTES = 60;             //an hour, represented in minutes
  public static final int DAY_IN_HOURS = 24;                //a day, represented in hours
  public static final int DAY_IN_MS = DAY_IN_HOURS * HOUR_IN_MS;            //a day, represented in milliseconds
  public static final int DAY_IN_MINUTES = DAY_IN_HOURS * HOUR_IN_MINUTES;  //a day, represented in minutes

  public static final String TIME_STRING_REGEX = "\\d\\d:\\d\\d:\\d\\d"; // regular expression to deine the HH:MM:SS format

  private static TimeZone oldTz = null; //default timezone as set on VM before being overwritten by param value
  /**
   * Constructor of MGD class
   */
  public DateAndTimeUtility() {
  }

  /**
   * Returns true if the given format denotes a time format
   * @param format int
   * @return boolean
   */
  public static boolean isTimeFormat (int format) {
    return (HHmmss == format || HHmm == format || HH == format || HH_mm_ss == format || HH_mm == format || HH_mm_AM_PM == format || mm_ss == format || mm == format);
  }


  /**
   * Returns the current date and time formatted as �yyyyMMddHHmmss�.
   * If any case an exception ocurred "" will be return
   * <p><b>Examples:</b>
   * <br>getTimeStamp() returns "20051227100345"
   *
   * @return current timestamp
   */
  public static String getTimeStamp() {
    return getTimeStamp(new java.util.Date(), yyyyMMddHHmmss);
  }

  /**
   * Returns the current date and time formatted according to the requested format.
   * <br>In case the format is not recognized then the default format will be taken yyyyMMddHHmmSS.
   * <p><b>Examples:</b>
   * <br>getTimeStamp(yyyyMMddHHmm) returns "200512271003"
   * <br>getTimeStamp(yyyyMMdd) returns "20051227"
   * <br>getTimeStamp(yyyy) returns "2005"
   * <br>getTimeStamp(HHmmss) returns "100345"
   * <br>getTimeStamp(HH_mm_ss) returns "10:03:45"
   * <br>getTimeStamp(dd_MM_yyyy) returns "27-12-2005"
   *
   * @param format constant denoting a timestamp format
   * @return current timestamp
   */
  public static String getTimeStamp(int format) {
    return getTimeStamp(new java.util.Date(), format);
  }

  /**
   * Convenience method for getTimeStamp(Date d, int format).
   * Same effect as calling getTimeStamp(Date d, dd_MM_yyyy)
   * @param d Date
   * @return timestamp
   */
  public static String getTimeStamp(Date d) {
    return getTimeStamp(d, dd_MM_yyyy);
  }

  /**
   * Returns the given date and time formatted according to the requested format.
   * <br>In case the format is not recognized then the default format will be taken yyyyMMddHHmmSS.
   * <br>In case the date equals 30/11/0002 then this is interpreted as an Oracle zero date and the zero date is returned.
   * <p><b>Examples:</b>
   * <br>getTimeStamp(14-03-2006 00:00:00, yyyyMMddHHmm) returns "200603140000"
   * <br>getTimeStamp(14-03-2006 00:00:00, yyyyMMdd) returns "20060314"
   * <br>getTimeStamp(14-03-2006 00:00:00, HHmmss) returns "000000"
   * <br>getTimeStamp(14-03-2006 00:00:00, dd_MM_yyyy) returns "14-03-2006"
   * <br>getTimeStamp(30-11-0002 00:00:00, dd_MM_yyyy) returns "00-00-0000"
   * <br>getTimeStamp(00-00-0000 15:12:10, HHmm) returns "1512"
   * <br>getTimeStamp(00-00-0000 15:12:10, yyyyMMddHHmm) returns "000000001512"
   * <br>getTimeStamp(00-00-0000 15:12:10, yyyyMMdd) returns "00000000"
   * <br>getTimeStamp(00-00-0000 15:12:10, HH_mm) returns "15:12"
   * <br>getTimeStamp(00-00-0000 15:12:10, HH_mm_AM_PM) returns "03:12 PM" 
   * <br>getTimeStamp(14-03-2006 16:23:50, yyyyMMddHHmm) returns "200603141623"
   * <br>getTimeStamp(14-03-2006 16:23:50, yyyyMMdd) returns "20060314"
   * <br>getTimeStamp(14-03-2006 16:23:50, HHmmss) returns "162350"
   * <br>getTimeStamp(14-03-2006 16:23:50, dd_MM_yyyy) returns "14-03-2006"
   * <br>getTimeStamp(14-03-2006 16:23:50, dd_MM_yyyy__HH_mm_SS) returns "14-03-2006  16:12:50"
   * <br>getTimeStamp(14-03-2006 16:23:50, MMM_dd) returns "Mar 14"
   * @param d a Date object
   * @param format constant denoting a timestamp format
   * @return formatted date
   */
  public static String getTimeStamp(Date d, int format) {
    if (d != null) {
      GregorianCalendar gc1 = null;
      SimpleDateFormat inputFormat = null;
      try {
        gc1 = new GregorianCalendar();
        gc1.clear();
        gc1.setTime(d);
        switch (format) {
          case yyyyMMddHHmmss:
            inputFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            break;
          case yyyyMMddHHmm:
            inputFormat = new SimpleDateFormat("yyyyMMddHHmm");
            break;
          case yyyyMMddHH:
            inputFormat = new SimpleDateFormat("yyyyMMddHH");
            break;
          case yyyyMMdd:
            inputFormat = new SimpleDateFormat("yyyyMMdd");
            break;
          case yyyyMM:
            inputFormat = new SimpleDateFormat("yyyyMM");
            break;
          case yyMMddHHmmss:
            inputFormat = new SimpleDateFormat("yyMMddHHmmss");
            break;
          case HHmmss:
            inputFormat = new SimpleDateFormat("HHmmss");
            break;
          case HHmm:
            inputFormat = new SimpleDateFormat("HHmm");
            break;
          case HH:
            inputFormat = new SimpleDateFormat("HH");
            break;
          case HH_mm_ss:
            inputFormat = new SimpleDateFormat("HH:mm:ss");
            break;
          case HH_mm:
            inputFormat = new SimpleDateFormat("HH:mm");
            break;
          case HH_mm_AM_PM:
            inputFormat = new SimpleDateFormat("hh:mm a");
            break;
          case dd_MM_yyyy:
            inputFormat = new SimpleDateFormat("dd-MM-yyyy");
            break;
          case dd_MM_yy:
            inputFormat = new SimpleDateFormat("dd-MM-yy");
            break;
          case dd_MM_yyyy_HH_mm_ss:
            inputFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            break;
          case dd_MM_yyyy_HH_mm:
            inputFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            break;
          case yyyy:
            inputFormat = new SimpleDateFormat("yyyy");
            break;
          case yyyy_MM_dd_HH_mm:
            inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            break;
          case ddMMyyyy:
            inputFormat = new SimpleDateFormat("ddMMyyyy");
            break;
          case yyyy_MM_dd:
            inputFormat = new SimpleDateFormat("yyyy-MM-dd");
            break;
          case yyyyMMddHHmmssSS:
            inputFormat = new SimpleDateFormat("yyyyMMddHHmmssSS");
            break;
          case yyyy_MM_dd_HH_mm_ss:
            inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            break;
          case yyyy_MM_ddTHH_mm_ss:
            inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            break;
          case ddMMyyyy_HHmm:
            inputFormat = new SimpleDateFormat("ddMMyyyy HHmm");
            break;
          case dd_MM_yyyy_SLASHED:
            inputFormat = new SimpleDateFormat("dd/MM/yyyy");
            break;
          case MMM_dd:
            inputFormat = new SimpleDateFormat("MMM dd");
            break;
          case mm:
            inputFormat = new SimpleDateFormat("m");
            break;
          default:
            inputFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            break;
        }
      }
      catch (Exception ex) {
        //This "try catch" is to catch the file not found Exception that throws
        //from "GregorianCalendar" at run time for the first time.
        //Do not throw any NewMlineExceptions but keep this empty
      }
      return inputFormat.format(gc1.getTime());
    }
    else
      return "00-00-0000";
  }





  /**
   * Returns the given date and time formatted according to the requested format.
   * <br>In case the date is null then this is interpreted as an Oracle zero date and the zero date is returned.
   * <br>In case the time value is empty then the given date with emptified time values will be returned.
   * <p><b>Examples:</b>
   * <br>getTimeStamp(14-03-2006 00:00:00, "10:00" , yyyyMMddHHmm) returns "200603141000"
   * <br>getTimeStamp(14-03-2006 15:12:00, "10:00" , yyyyMMddHHmm) returns "200603141000"
   * <br>getTimeStamp(14-03-2006 15:12:00, "10:00" , yyyyMMdd) returns "20060314"
   * <br>getTimeStamp(14-03-2006 15:12:00, "10:00" , yyyyMMdd) returns "20060314"
   * <br>getTimeStamp(14-03-2006 15:12:00, "10:00:55" , yyMMddHHmmss) returns "20060314100055"
   * <br>getTimeStamp(14-03-2006 15:12:00, "" , yyyyMMddHHmm) returns "200603140000"
   * @param date Date object
   * @param time String value which denotes time
   * @param format constant denoting a timestamp format
   * @return formatted date string
   */
  public static String getTimeStamp(Date date, String time, int format) {
    if (date != null) {
      long datems = getDateWithZeroTime(date).getTime();
      long timems = timeToSeconds(time) * 1000;
      return getTimeStamp(new Date(datems + timems), format);
    }
    else
      return "00-00-0000";
  }

  /**
   * Accepts a string that is expected to represent a date and returns the Date object of it.
   * If the string does not represent a valid date then null is returned.
   * @param str String
   * @param format a string denoting the format mask of the first argument.
   * @return Date
   */
  public static Date stringToDate(String str, String format) {
    try {
      if (!StringUtility.isEmptyString(str)) {
        SimpleDateFormat input = new SimpleDateFormat(format);
        input.setLenient(false);
        return input.parse(str);
      }
      else {
        return null;
      }
    }
    catch (ParseException ex) {
      return null;
    }
  }


  /**
   * Accepts a string that is expected to represent a date and returns the Date object of it.
   * <br>If the string does not represent a valid date then null is returned
   * <p><b>Examples:</b>
   * <br>StringToDate("14-03-2006") returns Date (14-03-2006)
   * <br>StringToDate("30-02-2006") returns null <b>attention<b>
   * <br>StringToDate("14.03.2006") returns null
   * <br>StringToDate(null) returns null
   * <br>StringToDate("") returns null
   *
   * @param dateString representation of the date as a string formatted as yyyyMMddHHmmss ,
   * yyyyMMdd , yyyyMMddHHmm ,  dd/mm/yyyy , dd-mm-yyyy , dd/mm/yyyy HH:mm:ss , dd-mm-yyyy HH:mm:dd
   * @return Date value of the string or null
   */

  public static Date stringToDate(String str) {
    try {
      if (!StringUtility.isEmptyString(str)) {
        String format = "";
        if (str.matches("\\d{14}")) {
          format = "yyyyMMddHHmmss";
        }
        if (str.matches("\\d{13}")) {
          format = "yyyyMMddHHmmss";
          str += "0";
        }
        else if (str.matches("\\d{12}")) {
          format = "yyyyMMddHHmm";
        }
        else if (str.matches("\\d{11}")) {
          format = "yyyyMMddHHmm";
          str += "0";
        }
        else if (str.matches("\\d{10}")) {
          format = "yyyyMMddHH";
        }
        else if (str.matches("\\d{9}")) {
          format = "yyyyMMddHH";
          str += "0";
        }
        else if (str.matches("\\d{8}")) {
          format = "yyyyMMdd";
        }
        else if (str.matches("\\d{2}\\-\\d{2}\\-\\d{4}")) {
          format = "dd-MM-yyyy";
        }
        else if (str.matches("\\d{4}\\-\\d{2}\\-\\d{2}")) {
          format = "yyyy-MM-dd";
        }
        else if (str.matches("\\d{2}/\\d{2}/\\d{4}")) {
          format = "dd/MM/yyyy";
        }
        else if (str.matches("\\d{2}/\\d{2}/\\d{2}")) {
          format = "dd/MM/yy";
        }
        else if (str.matches("\\d{2}/\\d{2}/\\d{4}\\s\\d{2}:\\d{2}:\\d{2}")) {
          format = "dd/MM/yyyy HH:mm:ss";
        }
        else if (str.matches("\\d{2}/\\d{2}/\\d{4}\\s\\d{2}:\\d{2}")) {
          format = "dd/MM/yyyy HH:mm";
        }
        else if (str.matches("\\d{4}/\\d{2}/\\d{2}")) {
          format = "yyyy/MM/dd";
        }
        else if (str.matches("\\d{4}/\\d{2}/\\d{2}\\s\\d{2}:\\d{2}:\\d{2}")) {
          format = "yyyy/MM/dd HH:mm:ss";
        }
        else if (str.matches("\\d{4}/\\d{2}/\\d{2}\\s\\d{2}:\\d{2}")) {
          format = "yyyy/MM/dd HH:mm";
        }
        else if (str.matches("\\d{2}\\-\\d{2}\\-\\d{4}\\s\\d{2}:\\d{2}:\\d{2}")) {
          format = "dd-MM-yyyy HH:mm:ss";
        }
        else if (str.matches("\\d{2}\\-\\d{2}\\-\\d{4}\\s\\d{2}:\\d{2}")) {
          format = "dd-MM-yyyy HH:mm";
        }
        else if (str.matches("\\d{4}\\-\\d{2}\\-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}")) {
          format = "yyyy-MM-dd HH:mm:ss";
        }
        else if (str.matches("\\d{4}\\-\\d{2}\\-\\d{2}\\s\\d{2}:\\d{2}")) {
          format = "yyyy-MM-dd HH:mm";
        }

        SimpleDateFormat input = new SimpleDateFormat(format);
        input.setLenient(false);
        return input.parse(str);
      }
      else {
        return null;
      }
    }
    catch (ParseException ex) {
      return null;
    }
  }

   public static boolean isSameFormat(String str, int format){
      switch(format){
        case yyyyMMddHHmmss:
          return str.matches("\\d{14}");

        case yyyyMMddHHmm:
         return str.matches("\\d{12}");

        case yyyyMMddHH:
          return str.matches("\\d{10}");

        case yyyyMMdd:
          return str.matches("\\d{8}");

        case dd_MM_yyyy:
          return str.matches("\\d{2}\\-\\d{2}\\-\\d{4}")|| str.matches("\\d{2}/\\d{2}/\\d{4}");

        case yyyy_MM_dd:
          return str.matches("\\d{4}\\-\\d{2}\\-\\d{2}") || str.matches("\\d{4}/\\d{2}/\\d{2}");

        case dd_MM_yy:
          return str.matches("\\d{2}/\\d{2}/\\d{2}");

        case HH_mm_ss:
          return str.matches("\\d{2}:\\d{2}:\\d{2}");

        case HH_mm:
          return str.matches("\\d{2}/\\d{2}") || str.matches("\\d{2}:\\d{2}");

        case dd_MM_yyyy_HH_mm_ss:
          return str.matches("\\d{2}/\\d{2}/\\d{4}\\s\\d{2}:\\d{2}:\\d{2}") || str.matches("\\d{2}\\-\\d{2}\\-\\d{4}\\s\\d{2}:\\d{2}:\\d{2}");

        case dd_MM_yyyy_HH_mm:
          return str.matches("\\d{2}/\\d{2}/\\d{4}\\s\\d{2}:\\d{2}") || str.matches("\\d{2}\\-\\d{2}\\-\\d{4}\\s\\d{2}:\\d{2}");

        case yyyy_MM_dd_HH_mm_ss:
          return str.matches("\\d{4}/\\d{2}/\\d{2}\\s\\d{2}:\\d{2}:\\d{2}") || str.matches("\\d{4}\\-\\d{2}\\-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}");

        case yyyy_MM_dd_HH_mm:
          return str.matches("\\d{4}/\\d{2}/\\d{2}\\s\\d{2}:\\d{2}") || str.matches("\\d{4}\\-\\d{2}\\-\\d{2}\\s\\d{2}:\\d{2}");

        default:
//          ProgrammerAlert.notify("Undefined format : " + format) ;
          return false;
      }
   }


   /**
    * Check if the given timeString belongs to either HH:MM:SS or HH:MM formats and the values valid return true, else return false
    * ("15:03:40") returns true
    * ("15:03:80") returns false
    * ("15:03:0") returns false
    * ("") returns false
    * ("15:40") returns true
    * ("15:80") returns false
    * ("15:0") returns false
    * NumberUtility cannot be used as we need to identify the non numeric characters and when found does not mean it can be equal to 0.
    * @param timeString
    * @return
    */
  public static boolean validateTimeString(String timeString){
    if (timeString == null) return false;
    if ((timeString.length() == 8 && StringUtility.countSpecifiedChar(timeString, ':') == 2) || (timeString.length() == 5 && StringUtility.countSpecifiedChar(timeString, ':') == 1)){
        ArrayList<String> values = StringUtility.split(timeString, ":");
        int hours = 0; int minutes = 0; int seconds = 0;
        try {
           switch (values.size()){
             case 3:
               hours   = Integer.parseInt(values.get(0));
               minutes = Integer.parseInt(values.get(1));
               seconds = Integer.parseInt(values.get(2));
               break;
             case 2:
               hours   = Integer.parseInt(values.get(0));
               minutes = Integer.parseInt(values.get(1));
               break;
           }
        return (hours >= 1 || hours < 24) && (minutes >= 0 || minutes < 60) && (seconds >= 0 || seconds < 60);
      }
      catch (NumberFormatException ex) {
        return false;
      }
    }
    else
       return false;
  }

  /**
   * Takes a date and returns the number of years that passed compared to the current date hence the age.
   * <br>The birth date must be a valid date in the past otherwise 0 is returned.
   * <p><b>Examples:</b>
   * <br>getAge(14-03-1962) returns 44 in case the current date is 14-03-2006
   * <br>getAge(14-03-1962) returns 44 in case the current date is 20-02-2007
   * <br>getAge(14-03-1962) returns 43 in case the current date is 13-03-2006
   * <br>getAge(14-03-2008) returns 0 in case the current date < 14-03-2008
   * <br>getAge(null) returns 0 whatever the current date is
   *
   * @param d a Date object representing a birth date
   * @return integer value denoting the age
   */
  public static int getAge(java.util.Date d) {
    if (checkPeriodValidity(d, new java.util.Date()) != true)
      return 0;
    else
      return getDateDifference(d, new java.util.Date(), YEARS);
  }

 /**
   * Returns the number of seconds of the given time string which is formatted as hh:mm:ss or hh:mm or hh.
   * <br>In case an invalid string is passed then 0 is returned.
   * <p><b>Examples:</b>
   * <br>timeToSeconds ("10:00:01") returns 36001
   * <br>timeToSeconds ("10:00") returns 36000
   * <br>timeToSeconds ("10") returns 36000
   * <br>timeToSeconds ("") returns 0
   * <br>timeToSeconds (null) returns 0
   *
   * @param time string in hh:mm:ss or hh:mm or hh format
   * @return long number of seconds
   */
  public static long timeToSeconds(String time) {
    if (StringUtility.isEmptyString(time))
      return 0;
    if (!StringUtility.isValidString(time))
      return 0;
    long hh = 0, mm = 0, ss = 0;
    try {
       if (time.length() < 3){
            hh = Long.parseLong(time.substring(0, time.length() - 1));
       }else{
            hh = Long.parseLong(time.substring(0, 2));
       }
      if (time.length() >= 5)
        mm = Long.parseLong(time.substring(3, 5));
      if (time.length() == 8)
        ss = Long.parseLong(time.substring(6, 8));
      return hh * 3600 + mm * 60 + ss;
    }
    catch (NumberFormatException ex) {
      return 0;
    }
  }

  /**
   * Returns the number of minutes of the given time string which is formatted as hh:mm:ss or hh:mm or hh.
   * <br>In case an invalid string is passed then 0 is returned.
   * <p><b>Examples:</b>
   * <br>timeToSeconds ("10:00:01") returns 600.0167
   * <br>timeToSeconds ("10:00") returns 600
   * <br>timeToSeconds ("10") returns 600
   * <br>timeToSeconds ("") returns 0
   * <br>timeToSeconds (null) returns 0
   *
   * @param time string in hh:mm:ss or hh:mm or hh format
   * @return long number of seconds
   */

  public static long timeToMinutes(String time){
    return timeToSeconds(time)/60;
  }


  /**
   * Returns the given number of seconds as a time string formatted as specified by the format parameter.
   * <br>The formats that are allowed are HHmmSS, HHmm, HH, HH_mm_SS, HH_mm
   * <br>In case the format is not recognized then the default format HHmmSS is taken
   * <p><b>Examples:</b>
   * <br>secondsToTime(100,HH_mm_SS) returns "00:01:40"
   * <br>secondsToTime(60,HH_mm_SS) returns "00:01:00"
   * <br>secondsToTime(360000,HH_mm_SS) returns "100:00:00" <b>attention length</b>
   * <br>secondsToTime(0,HH_mm_SS) returns "00:00:00"
   * <br>secondsToTime(60,HHmm) returns "0001"
   * <br>secondsToTime(60,0) returns "000100"
   *
   * @param timeInSecs duration in seconds
   * @param format constant denoting a time format
   * @return time in hh:mm:ss format
   */
  public static String secondsToTime(long timeInSecs, int format) {
    String hh, mm, ss, timeFormat;
    long hrs = timeInSecs / 3600;
    long mins = timeInSecs % 3600 / 60;
    long secs = timeInSecs % 60;
    hh = StringUtility.addPrefixZerosForFixedLength(String.valueOf(hrs), 2);
    mm = StringUtility.addPrefixZerosForFixedLength(String.valueOf(mins) + "", 2);
    ss = StringUtility.addPrefixZerosForFixedLength(String.valueOf(secs) + "", 2);
    switch (format) {
      case HHmmss:
        timeFormat = hh + mm + ss;
        break;
      case HHmm:
        timeFormat = hh + mm;
        break;
      case HH:
        timeFormat = hh;
        break;
      case HH_mm_ss:
        timeFormat = hh + ":" + mm + ":" + ss;
        break;
      case HH_mm:
        timeFormat = hh + ":" + mm;
        break;
      case mm_ss:
        timeFormat = mm + ":" + ss;
        break;
      default:
        timeFormat = hh + mm + ss;
        break;
    }
    return timeFormat;
  }

  /**
   * Takes a date and returns the number of the week day which is a digit between 1...7
   * <br>where Sunday = 1, Monday = 2 � Saturday = 7
   * <br>0 is returned in case the given date is not valid.
   * <p><b>Examples:</b>
   * <br>getDayNumber(14-03-2006) returns 3
   * <br>getDayNumber(12-03-2006) returns 1
   * <br>getDayNumber(null) returns 0
   *
   * @param d a Date object
   * @return integer value denoting the number of the week (0 in case of an error)
   */
  public static int getDayNumber(Date d) {
    return getValue(d, Calendar.DAY_OF_WEEK);
  }

  /**
   * Takes a date and returns the number of the week day which is a digit between 1...7
   * <br>counting 0 as the startDay
   * <br>0 is returned in case the given date or startDay is not valid.
   * <br>EXPECTS: startDay between 1 and 7; representing Sunday,Monday.. to Saturday
   * <p><b>Examples:</b>
   * <br>getDayNumber(06-04-2009, Calendar.SUNDAY) returns 2 for Monday
   * <br>getDayNumber(06-04-2009, Calendar.MONDAY) returns 1 for Monday
   * <br>getDayNumber(06-04-2009, Calendar.TUESDAY) returns 7 for Monday
   * <br>getDayNumber(06-04-2009, 8) returns 0
   * <br>getDayNumber(null) returns 0
   *
   * @param d a Date object
   * @return integer value denoting the number of the week (0 in case of an error)
   */
  public static int getDayNumber(Date d, int startDay) {
    int dayNumber = getValue(d, Calendar.DAY_OF_WEEK);
    if(dayNumber<=0 || startDay<1 || startDay>7)
      return 0;

    if(dayNumber>=startDay)
      return dayNumber-startDay+1;

    return DAYS_PER_WEEK - startDay + 1 + dayNumber;
  }






  /**
   * Takes a date and returns the year part of it.
   * For example 14/03/2008 returns 2008
   * null date returns 0
   * @param d Date
   * @return int
   */
  public static int getYear(Date d) {
    return getValue(d, Calendar.YEAR);
  }

  /**
   * Takes a date and returns month year part of it.
   * For example 14/03/2008 returns 3
   * null date returns 0
   * Attention : January is returned as 1 and NOT 0
   *             December is returned as 12 and NOT 11
   * @param d Date
   * @return int
   */
  public static int getMonth(Date d) {
    return getValue(d, Calendar.MONTH);
  }

  /**
   * Takes a date and returns day part of it.
   * For example 14/03/2008 returns 14
   * null date returns 0
   * @param d Date
   * @return int
   */
  public static int getDay(Date d) {
    return getValue(d, Calendar.DAY_OF_MONTH);
  }

  /**
   * Returns a list of all dates of the month containing the given date.
   * @param date
   * @return
   */
  public static List<Date> getDatesForMonth(Date date) {
    Date d1 = getFirstDateOfMonth(getMonth(date), getYear(date));
    Date d2 = getLastDateOfMonth(getMonth(date), getYear(date));
    
    Date dateToAdd = d1;
    List<Date> list = new ArrayList<Date>();
    
    while (dateToAdd.before(d2) || dateToAdd.equals(d2)) {
      list.add(dateToAdd);
      dateToAdd = addValue(dateToAdd, Calendar.DAY_OF_MONTH, 1);
    }

    return list;
  }

  /**
   * Returns comma separated date list in a single line
   * @param dateList
   * @return String
   */
  public static String getUniqueDateString(List<Date> dateList) {
    StringBuilder str = new StringBuilder();
    if (dateList != null) {
      for (Date date: dateList) {
        if (str.length() > 0)
          str.append(", ");
        str.append(DateAndTimeUtility.getTimeStamp(date));
      }
    }
    return str.toString();
  }

  /**
   * Adds the specified (signed) amount to the given time field to given date.
   * Int values from Calendar such as Calendar.YEAR,Calendar.MONTH,etc...
   * are acceptable as field values.
   * To substract the amount from date,the amount value should be in negative.
   * <p><b>Examples:</b>
   * <br>addValue(14-03-2006,Calendar.YEAR,1) returns 14-03-2007
   * <br>addValue(14-03-2006,Calendar.MONTH,-1) returns 14-02-2006
   * @param d
   * @param field
   * @param amount
   * @return Date
   */
  public static Date addValue(Date d, int field, int amount) {
    if (d == null)
      return null;
    GregorianCalendar gc = new GregorianCalendar();
    gc.setTime(d);
    gc.add(field, amount);
    return gc.getTime();
  }

  /**
   * Sets the time field with the given value to given date.
   * Int values from Calendar such as Calendar.YEAR,Calendar.MONTH,etc...
   * are acceptable as field values.
   * <p><b>Examples:</b>
   * <br>setValue(14-03-2006,Calendar.YEAR,2007) returns 14-03-2007
   * <br>setValue(14-03-2006,Calendar.MONTH,5) returns 14-05-2006
   * @param d
   * @param field
   * @param value
   * @return Date
   */
  public static Date setValue(Date d, int field, int value) {
    if (d == null)
      return null;
    // Programmer is expecting to get january by parsing value 1.
    // But GregorianCalendar is setting to february.So we should decrement
    // the value Incase the field is MONTH.
    if (field == Calendar.MONTH) {
      value--;
    }
    GregorianCalendar gc = new GregorianCalendar();
    gc.setTime(d);
    gc.set(field, value);
    return gc.getTime();
  }

  /**
   * Gets the value for a given time field of given date.
   * Int values from Calendar such as Calendar.YEAR,Calendar.MONTH,etc...
   * are acceptable as field values.
   * <p><b>Examples:</b>
   * <br>getValue(14-03-2006,Calendar.YEAR) returns 2006
   * <br>getValue(14-03-2006,Calendar.MONTH) returns 3
   * @param d
   * @param field
   * @return int
   */
  public static int getValue(Date d, int field) {
    if (d == null)
      return -1;
    GregorianCalendar gc = new GregorianCalendar();
    gc.setTime(d);
    // Programmer is expecting to get value 1 for month january.
    // But GregorianCalendar is returning 0.So we should increment
    // the value Incase the field is MONTH.
    if (field == Calendar.MONTH) {
      return gc.get(field) + 1;
    }
    return gc.get(field);
  }

  /**
   * Returns the difference between two dates in days, months or years.
   * <br>If the given unit is undefined or the dates are invalid then 0 is returned
   * <br>Depending on the chronology of the dates the difference can be positive or a negative value.
   * <br>startDate <= endDate => positive value
   * <br>startDate > endDate => negative value
   * <p><b>Examples:</b>
   * <br>getDateDifference (14-03-1962, 14-03-2006, DateAndTimeUtility.YEARS) returns 44
   * <br>getDateDifference (14-03-2006, 14-03-1962, DateAndTimeUtility.MONTHS) returns -528
   * <br>getDateDifference (14-03-2006, 14-03-1962, 0) returns 0
   * <br>getDateDifference (14-03-1962, 14-03-1962, DateAndTimeUtility.DAYS) returns 0
   * <br>getDateDifference (null, 14-03-1962, 2) returns -1
   * @param startDate Date
   * @param endDate Date
   * @param unit int defines the unit of the difference (1 = days, 2 = months, 3 = years).
   * @return int denoting the difference in units between the given dates (0 in case of error or equal dates)
   */
  public static int getDateDifference(Date startDate, Date endDate, int unit) {
    if (startDate == null || endDate == null)
      return -1;
    else {      
      GregorianCalendar gcStart = new GregorianCalendar();
      gcStart.setTime(getDateWithZeroTime(startDate));
      long startDateTimeInMill = gcStart.getTimeInMillis() + gcStart.getTimeZone().getOffset(gcStart.getTimeInMillis());

      GregorianCalendar gcEnd = new GregorianCalendar();
      gcEnd.setTime(getDateWithZeroTime(endDate));
      long endDateTimeInMill  = gcEnd.getTimeInMillis() + gcEnd.getTimeZone().getOffset(gcEnd.getTimeInMillis());

      GregorianCalendar gc = new GregorianCalendar();
      gc.setTimeInMillis(Math.abs(endDateTimeInMill - startDateTimeInMill));
      int dateDiff = 0;
      switch (unit) {
        case DAYS:
          dateDiff = (int) (gc.getTimeInMillis() / (24 * 60 * 60 * 1000));
          break;
        case MONTHS:
          dateDiff = ( (gc.get(gc.YEAR) - 1970) * 12) + gc.get(gc.MONTH);
          break;
        case YEARS:
          dateDiff = ( (gc.get(gc.YEAR) - 1970));
          break;
      }
      if (startDateTimeInMill > endDateTimeInMill)
        return -dateDiff;
      else
        return dateDiff;
    }
  }

  /**
   * Returns the new date which equals the given date + given number of days
   * <br>The number of days can be a negative value
   * <br>If the given date is invalid then null is returned
   * <p><b>Examples:</b>
   * <br>calculateDate(14-03-2006, 0) returns Date (14-03-2006)
   * <br>calculateDate(14-03-2006, 1) returns Date (15-03-2006)
   * <br>calculateDate(14-03-2006, 18) returns Date (01-04-2006)
   * <br>calculateDate(null, 0) returns null
   * @param startDate Date
   * @param noOfDays int
   * @return Date = Date(startDate + noOfDays)
   */
  public static Date calculateDate(Date startDate, int noOfDays) {
    if (startDate == null)
      return null;
    else {
      GregorianCalendar gc = new GregorianCalendar();
      gc.setTimeInMillis(startDate.getTime());
      gc.add(Calendar.DATE,  noOfDays);
      return gc.getTime();
    }
  }

  /**
   * Accepts a time stamp and number of days the date should be altered to.
   * The number of days will be added or subtracked from the time stamp creating a new stamp with the new date.
   * <br> changeDateOfTimeStamp(20090102101112, 5) returns 20090107101112
   * <br> changeDateOfTimeStamp(20090102101112, 0) returns 20090102101112
   * <br> changeDateOfTimeStamp(20090102101112,-1) returns 20090101101112
   * <br> changeDateOfTimeStamp(0102101112,-1) returns 0102101112
   * <br> changeDateOfTimeStamp(null,-1) returns null
   *
   * @param Stime stamp String
   * @param noOfDays int
   * @return String
   */
  public static String changeDateOfTimeStamp(String timeStamp, int noOfDays) {
    if(!isValidTimeStamp(timeStamp)) return timeStamp;
    if (noOfDays == 0) return timeStamp;
    Date currentOPDate = stringToDate(timeStamp);
    Date modifiedOPDATE = calculateDate(currentOPDate, noOfDays);
    return getTimeStamp(modifiedOPDATE, yyyyMMddHHmmss);
  }


  /**
   * Changes the date of the timeStamp to the one of the date passed.
   * incase the date is null or the passed timeStamp is not valid will return the timeStamp as recived.
   * incase there is no date difference will return the timeStamp as recived
   * <br>changeDateOfTimeStamp(20090102101112, 14-01-2009) returns 20090114101112
   * <br>changeDateOfTimeStamp(20090102101112, 02-01-2009) returns 20090102101112
   * <br>changeDateOfTimeStamp(22322324344, 02-01-2009)    returns 22322324344
   * <br>changeDateOfTimeStamp(20090102101112, null)       returns 20090102101112
   *
   * @param timeStamp String
   * @param date Date
   * @return String
   */
  public static String changeDateOfTimeStamp(String timeStamp, Date date){
    if(date == null) return timeStamp;
    if(!isValidTimeStamp(timeStamp)) return timeStamp;

    Date timeStampDate = stringToDate(timeStamp);
    timeStampDate      = setValue(timeStampDate, Calendar.DAY_OF_MONTH, getValue(date, Calendar.DAY_OF_MONTH));
    timeStampDate      = setValue(timeStampDate, Calendar.MONTH, getValue(date, Calendar.MONTH));
    timeStampDate      = setValue(timeStampDate, Calendar.YEAR, getValue(date, Calendar.YEAR));

    return getTimeStamp(timeStampDate, yyyyMMddHHmmss);
  }


  /**
   * Returns true if startDate <= endDate
   * <br>false in all other cases or when at least one of the dates is invalid
   * <p><b>Examples:</b>
   * <br>checkPeriodValidity(14-03-2006,14-03-2006) returns true
   * <br>checkPeriodValidity(14-03-2006,15-03-2006) returns true
   * <br>checkPeriodValidity(15-03-2006,14-03-2006) returns false
   * <br>checkPeriodValidity(null,14-03-2006) returns false
   * @param startDate Date
   * @param endDate Date
   * @return boolean true if startDate <= endDate
   */
  public static boolean checkPeriodValidity(Date startDate, Date endDate) {
    return (getDateDifference(startDate, endDate, DAYS) >= 0);
  }

  //-----------added new methods ---2006-05-04------------------//

  /**
   * Returns the given date String(yyyyMMddHHmmss) formatted according to the requested format.
   * <br>In case the format is not recognized then the default format will be taken yyyyMMddHHmmss.
   * If the string does not represent "yyyyMMddHHmmss" then empty string is returned
   * If the format does not represent a valid format then empty string is returned
   * if the string is null then return ""
   * if the string is null then return ""
   * <p><b>Examples:</b>
   * <br>transformDateString(20060102084535, yyyyMMddHHmmss) returns "20060102084535"
   * <br>transformDateString(20060102084535, yyyyMMddHHmm) returns "200601020845"
   * <br>transformDateString(20060102084535, yyyyMMddHH) returns "2006010208"
   * <br>transformDateString(20060102084535, yyyyMMdd) returns "20060102"
   * <br>transformDateString(20060102084535, yyMMddHHmmss) returns "060102084535"
   * <br>transformDateString(20060102084535, HHmmss) returns "084535"
   * <br>transformDateString(20060102084535, dd_MM_yyyy) returns "02_01_2006"
   * <br>transformDateString(20060102084535, HHmm) returns "0845"
   * <br>transformDateString(20060102084535, yyyyMMddHHmm) returns "200601020845"
   * <br>transformDateString(20060102084535, yyyyMMdd) returns "20060102"
   * <br>transformDateString(20060102084535, HH_mm) returns "08_45"
   * <br>transformDateString(20060102084535, yyyyMMddHHmm) returns "200601020845"
   * <br>transformDateString(20060102084535, HHmmss) returns "020845"
   * <br>transformDateString(20060102084535, dd_MM_yyyy) returns "02_01_2006"
   * <br>transformDateString(20060102084535, dd_MM_yyyy__HH_mm_SS) returns "02_01_2006 08_45_35"
   * <br>transformDateString(20060102084535, yyyy_MM_dd__HH_mm_SS) returns "2006_01_02 08_45_35"
   * <br>transformDateString(20060102, yyyy_MM_dd) returns "2006-01-02"
   * <br>transformDateString(20060102, dd_MM_yyyy) returns "2006-01-02"
   * <br>transformDateString(02/01/2006, dd_MM_yyyy) returns "2006-01-02"
   * <br>transformDateString(02-01-2006, dd_MM_yyyy) returns "2006-01-02"
   * <br>transformDateString("", dd_MM_yyyy__HH_mm_SS) returns "00-00-0000"
   * <br>transformDateString(null, dd_MM_yyyy__HH_mm_SS) returns "00-00-0000"
   * @param dateString String
   * @param format int
   * @return String
   */
  public static String transformDateString(String dateString, int format) {
    if (StringUtility.isEmptyString(dateString))
      return getZeroDate();
    try {
      Date currDate = stringToDate(dateString);
      return getTimeStamp(currDate, format);
    }
    catch (Exception ex) {
      return getZeroDate();
    }
  }

  /**
   * Convert the given date to milliseconds
   * <p><b>Examples:</b>
   * <br>dateToSeconds(new GregorianCalendar(1980, Calendar.SEPTEMBER, 12).getTime()); return "337545000"
   * @param dat Date
   * @return long
   */
  public static long dateToSeconds(java.util.Date dat) {
    return dat.getTime() / 1000;
  }

  /**
   * Convert seconds values to a Timestamp
   * <p><b>Examples:</b>
   * <br>   secondsToDate(337545000) returns "Fri Sep 12 00:00:00 GMT+05:30 1980"
   * <br>   secondsToDate(-99999999l) returns "Mon Oct 31 19:43:21 GMT+05:30 1966"
   *
   * @param seconds long
   * @return Date
   */
  public static Date secondsToDate(long seconds) {
    GregorianCalendar gc = new GregorianCalendar();
    gc.clear();
    gc.setTimeInMillis(seconds * 1000);
    return gc.getTime();
  }

  /**
   * Returns the new accumulated time values of both the arguments
   * The startDate will be taken as the date without time (zero time).
   * If the given date is null then 0 is returned
   *
   * @param startDate Date
   * @param timeInMs time value in milliseconds
   * @return long
   */
  public static long calculateTime(Date startDate, long timeInMs) {
    if (startDate == null)
      return 0;
    else {
      long startDateInMilli = getDateWithZeroTime(startDate).getTime();
      return startDateInMilli + timeInMs;
    }
  }

  /**
   * Returns the new accumulated time values of both the arguments
   * The startDate will be taken as the date with max time (23:59:59:999).
   * If the given date is null then 0 is returned
   *
   * @param startDate Date
   * @param timeInMs time value in milliseconds
   * @return long
   */
  public static long calculateTimeFromMaxTime(Date startDate, long timeInMs) {
    if (startDate == null)
      return 0;
    else {
      long startDateInMilli = getDateWithMaxTime(startDate).getTime();
      return startDateInMilli + timeInMs;
    }
  }

    /**
   * Returns the new accumulated time values of both the arguments
   * The startDate will be taken as the current date with the current time.
   * If the given date is null then 0 is returned
   *
   * @param startDate Date
   * @param timeInMs time value in milliseconds
   * @return long
   */
  public static long calculateTimeFromCurrentTime(Date startDate, long timeInMs) {
    if (startDate == null)
      return 0;
    else {
      long startDateInMilli = startDate.getTime();
      return startDateInMilli + timeInMs;
    }
  }

  /**
   * Returns the given date and time to milliseconds.
   * The startDate will be taken as the date without time (zero time).
   * The time is expected to be expressed in a time format as supported by this class.
   * @param startDate Date
   * @param time String
   * @return long
   */
  public static long calculateTime(Date startDate, String time) {
    if (startDate == null || time == null)
      return 0;
    else
      return calculateTime(startDate, (timeToSeconds(time) * 1000));
  }


  /**
   * Check if given time stamp is valid or not.Return true if the time stamp is valid.
   * Return false if the time stamp is not valid or empty or null
   * <br>isValidTimeStamp("29/02/2004") returns true (leap year)
   * <br>isValidTimeStamp("19800912101010") return true
   * <br>isValidTimeStamp("19800912") return true
   * <br>isValidTimeStamp("19801412") return false
   * <br>isValidTimeStamp("12/21/1980") return false
   * <br>isValidTimeStamp("29/02/2005") returns false (not a leap year)
   * <br>isValidTimeStamp(null) returns false
   * <br>isValidTimeStamp("") returns false
   *
   * @param dateString representation of the date as a string formatted as yyyyMMddHHmmss ,
   * yyyyMMdd , yyyyMMddHHmm ,  dd/mm/yyyy , dd-mm-yyyy , dd/mm/yyyy HH:mm:ss , dd-mm-yyyy HH:mm:dd
   * @return boolean
   */
  public static boolean isValidTimeStamp(String dateString) {
    if (StringUtility.isEmptyString(dateString))
      return false;
    GregorianCalendar gc = new GregorianCalendar();
    gc.setLenient(false);
    try {
      gc.setTime(stringToDate(dateString));
    }
    catch (Exception ex) {
      return false;
    }
    return true;
  }

  /**
   * Check if the given date string is valid or not. Return true if the string is valid.
   * Return false if the date stirng is not valid or empty or null
   * The zero date is considered a valid one.
   * <br>isValidTimeStamp("29/02/2004") returns true (leap year)
   * <br>isValidTimeStamp("19800912101010") returns false
   * <br>isValidTimeStamp("00-00-0000") returns true
   * <br>isValidTimeStamp("19801412") returns false
   * <br>isValidTimeStamp("12/21/1980") returns false
   * <br>isValidTimeStamp("29/02/2005") returns false (not a leap year)
   * <br>isValidTimeStamp(null) returns false
   * <br>isValidTimeStamp("") returns false
   * @param dateString String
   * @return boolean
   */
  public static boolean isValidDateString(String dateString) {
    String format;
    if (StringUtility.isEmptyString(dateString))
      return false;
    else if (isZeroDate(dateString))
      return true;
    else {
      if (dateString.matches("\\d{2}\\-\\d{2}\\-\\d{4}"))
        format = "dd-MM-yyyy";
      else if (dateString.matches("\\d{2}/\\d{2}/\\d{4}"))
        format = "dd/MM/yyyy";
      else return false;
      SimpleDateFormat input = new SimpleDateFormat(format);
      input.setLenient(false);
      GregorianCalendar gc = new GregorianCalendar();
      gc.setLenient(false);
      try {
        gc.setTime(input.parse(dateString));
      }
      catch (Exception ex) {
        return false;
      }
      return true;
    }
  }

  /**
   * Returns true in case the passed string represents the zero date 00-00-0000 or 00-00-0000
   * An empty string is also a zero date string
   * @param theDate String
   * @return boolean
   */
  public static boolean isZeroDate(String dateString) {
    return (StringUtility.isEmptyString(dateString))
           || (dateString.equalsIgnoreCase("00/00/0000"))
           || (dateString.equalsIgnoreCase("00/00/0000 00:00:00"))
           || (dateString.equalsIgnoreCase("00/00/0000 00:00"))

           || (dateString.equalsIgnoreCase("0000/00/00"))
           || (dateString.equalsIgnoreCase("0000/00/00 00:00:00"))
           || (dateString.equalsIgnoreCase("0000/00/00 00:00"))

           || (dateString.equalsIgnoreCase("00-00-0000"))
           || (dateString.equalsIgnoreCase("00-00-0000 00:00:00"))
           || (dateString.equalsIgnoreCase("00-00-0000 00:00"))

           || (dateString.equalsIgnoreCase("0000-00-00"))
           || (dateString.equalsIgnoreCase("0000-00-00 00:00:00"))
           || (dateString.equalsIgnoreCase("0000-00-00 00:00"));
  }

  /**
   * Returns true in case the passed string represents the zero time 00:00:00 or 00:00
   * An empty string is also a zero date string
   * @param timeString String
   * @return boolean
   */
  public static boolean isZeroTime(String timeString) {
    return (StringUtility.isEmptyString(timeString))
           || (timeString.equalsIgnoreCase("00:00:00"))
           || (timeString.equalsIgnoreCase("00:00"));
  }


  /**
   * Returns true when the given date is the zero Date.
   * @param theDate Date
   * @return boolean
   */
  public static boolean isZeroDate(Date theDate) {
    if(theDate == null) return true;
    else
      return isSameDate (getDateWithZeroTime (theDate), stringToDate (getZeroDate()));
  }

  /**
   * Returns the sum of the two given time values with the given format.
   * <br>The formats that are allowed are HHmmSS, HHmm, HH, HH_mm_SS, HH_mm
   * <br>In case the format is not recognized then the default format HHmmSS is taken
   * <p><b>Examples:</b>
   * <br>addTimes("10:00","01:00",HH_mm) returns "11:00"
   * <br>addTimes("10:00","01:00:50",HH_mm_SS) returns "11:00:50"
   * @param time1
   * @param time2
   * @param format
   * @return String
   */
  public static String addTimes(String time1, String time2,int format) {
    long totalS = timeToSeconds(time1) + timeToSeconds(time2);
    return secondsToTime(totalS, format);
  }

  public static String reduceTimes(String time1, String time2, int format) {
    long totalS = timeToSeconds(time1) - timeToSeconds(time2);
    return secondsToTime(totalS, format);
  }

  /**
   * Given time in milliseconds will be added with given timestamp date.
   * The result date will be returned.
   *
   * <b>Examples:</b>
   * <br>addTime("19800912101010",1000) returns Date (1980-09-12-10-10-11)
   * @param timeStamp
   * @param timeInMS - time in milliseconds
   * @return Date
   */
  public static Date addTime(String timeStamp, long timeInMS) {
    Date date = stringToDate(timeStamp);
    return addValue(date, Calendar.MILLISECOND, (int) timeInMS);
  }

  /**
   * Given time in milliseconds will be added with given timestamp date.
   * The result date will be returned in given format as a string.
   *
   * <b>Examples:</b>
   * <br>addTime("19800912101010",1000,yyyyMMddHHmmss) returns "19800912101011"
   * @param timeStamp
   * @param timeInMS - time in milliseconds
   * @param format
   * @return String
   */
  public static String addTime(String timeStamp, long timeInMS, int format) {
    return getTimeStamp(addTime(timeStamp, timeInMS), format);
  }

  /**
   * Returns the first date of the specified week of the specified year. First week of the year is set to be
   * the first week that has atleast 4 days
   * <br>   weekNumberToDate(6,new GregorianCalendar(2006, Calendar.MAY, 7).getTime())
   *                                         return "Mon Feb 06 00:00:00 IST 2006"
   * <br>   weekNumberToDate(6,null)return ""
   *
   * @param weekNo specified week no
   * @param refDate year for which the week no is specified
   * @return date object corresponding to the first date of the specified week
   * Mapped to MGD.getFirstDateInWeek
   */
  public static Date weekNumberToDate(int weekNo, Date refDate) {
    if (refDate == null)
      return null;
    Date myDate = null;
    int currentWeekNo = 0;
    GregorianCalendar gc = new GregorianCalendar();
    gc.clear();
    gc.setTime(refDate);
    gc.setMinimalDaysInFirstWeek(4);
    gc.setFirstDayOfWeek(gc.MONDAY);
    while (gc.get(gc.DAY_OF_WEEK) != gc.MONDAY) {
      gc.add(gc.DATE, -1);
    }
    currentWeekNo = gc.get(gc.WEEK_OF_YEAR);
    if (currentWeekNo == 53)
      currentWeekNo = 0;

    gc.add(gc.DATE, (weekNo - currentWeekNo) * 7);
    myDate = gc.getTime();
    return myDate;
  }

  /**
   * Returns the first date of the specified week of the specified year.
   * First day of the week is considered as Monday.
   * First week of the year is set to be the first week that has atleast 4 days
   *
   * <br>   weekNumberToDate(1,2009) return Date(29/12/2008)
   * <br>   weekNumberToDate(2,2009) return Date(05/01/2009)
   * @param weekNo
   * @param year
   * @return date object corresponding to the first date of the specified week
   */
  public static Date getFirstDateOfWeek(int weekNo,int year){
    GregorianCalendar gc = new GregorianCalendar();
    gc.clear();
    gc.setFirstDayOfWeek(Calendar.MONDAY);
    gc.setMinimalDaysInFirstWeek(4);
    gc.set(Calendar.YEAR,year);
    gc.set(Calendar.WEEK_OF_YEAR, weekNo);
    gc.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
    return gc.getTime();
  }

  /**
   * Returns the last date of the specified week of the specified year.
   * First day of the week is considered as Monday.
   * Note :- getFirstDateOfWeek(int weekNo,int year) method has specified the first day of the week as Monday.
   *
   * First week of the year is set to be the first week that has at least 4 days
   *
   * <br>   weekNumberToDate(1,2009) return Date(29/12/2008)
   * <br>   weekNumberToDate(2,2009) return Date(05/01/2009)
   * @param weekNo
   * @param year
   * @return date object corresponding to the last date of the specified week
   */
  public static Date getLastDateOfWeek(int weekNo, int year) {
    GregorianCalendar gc = new GregorianCalendar();
    gc.clear();
    gc.setFirstDayOfWeek(Calendar.MONDAY);
    gc.setMinimalDaysInFirstWeek(4);
    gc.set(Calendar.YEAR, year);
    gc.set(Calendar.WEEK_OF_YEAR, weekNo);
    gc.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
    return gc.getTime();
  }

  /**
   * Returns the first date of current week
   * @return Date
   */
  public static Date getFirstDateOfCurrentWeek() {
    return getFirstDateOfWeek(dateToWeekNumber(getCurrentDate()), getCurrentYear());
  }

  /**
   * Returns the last date of current week
   * @return Date
   */
  public static Date getLastDateOfCurrentWeek() {
    return getLastDateOfWeek(dateToWeekNumber(getCurrentDate()), getCurrentYear());
  }

  /**
   * Returns the first date of current year
   * @return Date
   */
  public static Date getFirstDateOfCurrentYear() {
    return getFirstDateOfYear(getCurrentYear());
  }

  /**
   * Returns the last date of current year
   * @return Date
   */
  public static Date getLastDateOfCurrentYear() {
    return getLastDateOfYear(getCurrentYear());
  }



  /**
   * Returns the first date of given year
   * @return Date
   */
  public static Date getFirstDateOfYear(int year) {
    GregorianCalendar gc = new GregorianCalendar();
    gc.clear();
    gc.set(Calendar.DAY_OF_YEAR, 1);
    gc.set(Calendar.YEAR, year);
    return gc.getTime();
  }

  /**
   * Returns the last date of given year
   * @return Date
   */
  public static Date getLastDateOfYear(int year) {
    GregorianCalendar gc = new GregorianCalendar();
    gc.clear();
    gc.set(Calendar.DAY_OF_YEAR, 1);
    gc.set(Calendar.YEAR, (year + 1));
    gc.add(Calendar.DATE, -1);
    return gc.getTime();
  }

  /**
   * Returns first date of given month
   * @param month
   * @param year
   * @return Date
   */
  public static Date getFirstDateOfMonth(int month, int year) {
    // Month is decremented by 1.because programmer is using to
    // 1 to january.But GregorianCalendar is expecting 0;
    month = month - 1;

    GregorianCalendar gc = new GregorianCalendar();
    gc.clear();
    gc.set(Calendar.YEAR, year);
    gc.set(Calendar.MONTH, month);
    gc.set(Calendar.DAY_OF_MONTH, 1);
    return gc.getTime();
  }

  /**
   * Returns lase date of given month
   * @param month
   * @param year
   * @return Date
   */
  public static Date getLastDateOfMonth(int month, int year) {

    // Month is decremented by 1.because programmer is using to
    // 1 to january.But GregorianCalendar is expecting 0;
    month = month - 1;

    GregorianCalendar gc = new GregorianCalendar();
    gc.clear();
    gc.set(Calendar.YEAR, year);
    gc.set(Calendar.MONTH, (month + 1));
    gc.set(Calendar.DAY_OF_MONTH, 1);
    gc.add(Calendar.DATE, -1);
    return gc.getTime();
  }

  /**
   * Returns no of weeks in given year.
   * First day of the week is considered as Monday.
   * First week of the year is set to be the first week that has atleast 4 days
   *
   * <br>   weeksForYear(2008) returns 52
   * <br>   weeksForYear(2009) returns 53
   * @param year
   * @return int
   */
  public static int weeksForYear(int year) {
    GregorianCalendar gc = new GregorianCalendar();
    gc.clear();
    gc.setFirstDayOfWeek(Calendar.MONDAY);
    gc.setMinimalDaysInFirstWeek(4);
    gc.set(Calendar.YEAR, year);
    return gc.getActualMaximum(Calendar.WEEK_OF_YEAR);
  }

  /**
   * Gets the week number (of year) of the given date
   * <p><b>Examples:</b>
   * <br>   dateToWeekNumber(new GregorianCalendar(2005, Calendar.DECEMBER, 28).getTime()) returns 52
   * <br>   dateToWeekNumber(new GregorianCalendar(2004, Calendar.DECEMBER, 28).getTime()) returns 53
   * <br>   dateToWeekNumber(null) return -1
   *
   * @param inputDate Date object
   * @return week number
   */

  public static int dateToWeekNumber(Date inputDate) {
    if (inputDate == null)
      return -1;
    int weekOfyear = 0;
    GregorianCalendar gc = new GregorianCalendar();
    gc.clear();
    gc.setTime(inputDate);
    gc.setMinimalDaysInFirstWeek(4);
    gc.setFirstDayOfWeek(java.util.Calendar.MONDAY);
    weekOfyear = gc.get(gc.WEEK_OF_YEAR);
    return weekOfyear;
  }

  /**
   * Formats the string value from Oracle to the dd-mm-yyyy format. In the DB it is stored as yyyymmdd
   * <p><b>Examples:</b>
   * <br>   birthDateToDateString("20051225") returns "25-12-2005"
   * <br>   birthDateToDateString("2005") return "00-00-0000"
   * <br>   birthDateToDateString("25112005") "00-00-0000"
   * <br>   birthDateToDateString(null) return "00-00-0000"
   * <br>   birthDateToDateString("") return "00-00-0000"
   * @param bDate string holding date in yyyymmdd format
   * @return a string representation of the given date in dd-mm-yyyy format
   */

  public static String birthDateToDateString(String bDate) {
    return transformDateString(bDate, DateAndTimeUtility.dd_MM_yyyy);
  }

  /**
   * Gets the date in dd/mm/yyyy format and formats it in yyyymmdd to be stored in an Oracle Database.
   * <p><b>Examples:</b>
   * <br>   dateToBDate("25/12/2005") returns "20051225"
   * <br>   dateToBDate("20051225") returns "00000000"
   * <br>   dateToBDate(null) returns "0000000"
   * <br>   dateToBDate("") returns "0000000"
   *
   * @param myDate the date in dd/mm/yyyy format
   * @return date in yyyymmdd format
   */
  public static String dateToBDate(String myDate) {
    if (!isValidTimeStamp(myDate))
      return "00000000";
    else {
      String year = StringUtility.substring(myDate, 6, 4);
      String month = StringUtility.substring(myDate, 3, 2);
      String date = StringUtility.substring(myDate, 0, 2);

      return year + month + date;
    }
  }

  /**
   * returns a string given the number of minutes in a specified format
   * <p><b>Examples:</b>
   * <br>   TimeString(2880,DD_HH_MM) returns "02:00:00"
   * <br>   TimeString(10,DD_HH_MM) returns "00:00:10"
   * <br>   TimeString(61,DD_HH) returns "00:01"
   * <br>   TimeString(2880,HH_MM) returns "48:00"
   * <br>   TimeString(2881,HH_MM) returns "48:01"

   * @param minutes long
   * @param format int
   * @return String
   */
  public static String TimeString(long minutes, int format) {
    int dd = 0, hh = 0, mm = 0;
    String result = "", days = "", hrs = "", min = "";
    dd = (int) minutes / 1440;
    hh = (int) ( (minutes % 1440) / 60);
    mm = (int) ( (minutes) - (dd * 1440 + hh * 60));
    days = StringUtility.addPrefixZerosForFixedLength(String.valueOf(dd), 2);
    hrs = StringUtility.addPrefixZerosForFixedLength(String.valueOf(hh), 2);
    min = StringUtility.addPrefixZerosForFixedLength(String.valueOf(mm), 2);
    switch (format) {
      case DD_HH_MM:
        result = days + ":" + hrs + ":" + min;
        break;
      case DD_HH:
        result = days + ":" + hrs;
        break;
      case HH_mm:
        hh = dd * 24 + hh;
        hrs = StringUtility.addPrefixZerosForFixedLength(String.valueOf(hh), 2);
        result = hrs + ":" + min;
        break;
      default:
        result = days + ":" + hrs + ":" + min;
        break;
    }
    return result;
  }


  /**
   * Returns true when the given date is the current date
   * @param theDate Date
   * @return boolean true when same date
   */
  public static boolean isCurrentDate (Date theDate) {
    return isSameDate(theDate, getCurrentDate());
  }

  /**
   * Return true when the given date is a future date when compared with today
   * @param theDate
   * @return
   */
  public static boolean isFutureDate(Date theDate) {
      return isAfterDate(theDate, getCurrentDate());
  }

  /**
   * Return true when the given date is a past date when compared with today
   * @param theDate
   * @return
   */
  public static boolean isPastDate(Date theDate) {
    return isBeforeDate(theDate, getCurrentDate());
  }

  /**
   * Checks if given date objects are the same with out considering the time
   * if firstDate or secondDate is null return false
   * <br>   isSameDate(new Date(1980,9,12), new Date(1980,9,12)) returns true
   * <br>   isSameDate(new Date(1980,9,12,10,20,20), new Date(1980,9,12,10,22,21)) returns true
   * <br>   isSameDate(new Date(1980,9,12),new Date(1980,9,13)) returns false
   * <br>   isSameDate(null,new Date(1980,9,13)) returns false
   * @param firstDate Date
   * @param secondDate Date
   * @return boolean
   */
  public static boolean isSameDate(Date firstDate, Date secondDate) {
    if (firstDate == null || secondDate == null)
      return false;
    else {
      String strFirstDate = getTimeStamp(firstDate, dd_MM_yyyy);
      String strSecondDate = getTimeStamp(secondDate, dd_MM_yyyy);
      return (strFirstDate.compareTo(strSecondDate) == 0);
    }
  }


  /**
   * Checks if given date objects are the same considering the hours and minutes
   * if firstDate or secondDate is null return false
   * <br>   isSameMinute(new Date(1980,9,12), new Date(1980,9,12)) returns true
   * <br>   isSameMinute(new Date(1980,9,12,10,20,20), new Date(1980,9,12,10,22,21)) returns false
   * <br>   isSameMinute(new Date(1980,9,12),new Date(1980,9,13)) returns false
   * <br>   isSameMinute(null,new Date(1980,9,13)) returns false
   * @param firstDate Date
   * @param secondDate Date
   * @return boolean
   */
  public static boolean isSameMinute(Date firstDate, Date secondDate) {
    if (firstDate == null || secondDate == null)
      return false;
    else {
      String strFirstDate = getTimeStamp(firstDate, dd_MM_yyyy_HH_mm);
      String strSecondDate = getTimeStamp(secondDate, dd_MM_yyyy_HH_mm);
      return (strFirstDate.compareTo(strSecondDate) == 0);
    }
  }

  /**
   * Returns true if given timestamp values are belongs to same date.
   * <p><b>Examples:</b>
   * <br>   isSameDate("200603141000","200603141100") returns true
   * <br>   isSameDate("200603141000","200603151100") returns false
   * @param timeStamp1
   * @param timeStamp2
   * @return boolean
   */
  public static boolean isSameDate(String timeStamp1, String timeStamp2) {
    Date date1 = stringToDate(timeStamp1);
    Date date2 = stringToDate(timeStamp2);
    return isSameDate(date1, date2);
  }

  /**
   * Checks whether the given timestamps are valid timestamps and equal or not
   *
   * <p><b>Examples:</b>
   * <br>   isSameTimestamp("201209111000","201209111000") returns true
   * <br>   isSameTimestamp("201209101000","201209111000") returns false
   *
   * @param timeStamp1
   * @param timeStamp2
   * @return - boolean
   */
  public static boolean isSameTimestamp(String timeStamp1, String timeStamp2) {
    if (!isValidTimeStamp(timeStamp1) || !isValidTimeStamp(timeStamp2)) return false;

    return StringUtility.isSameString(timeStamp1, timeStamp2);
  }

  /**
    * Checks whether the given timestamps are having the same time, irrespective of the date
    *
    * <p><b>Examples:</b>
    * <br>   isSameTime("201209111000","201209111000") returns true
    * <br>   isSameTime("201209101000","201209111000") returns true
    * <br>   isSameTime("201209111201","201209111000") returns false
    * <br>   isSameTime("201209101100","201209111000") returns false
    *
    * @param timeStamp1
    * @param timeStamp2
    * @return - boolean
    */
  public static boolean isSameTime(String timeStamp1, String timeStamp2){
    long time1 = DateAndTimeUtility.timeToMinutes(DateAndTimeUtility.transformDateString(timeStamp1, DateAndTimeUtility.HH_mm));
    long time2 = DateAndTimeUtility.timeToMinutes(DateAndTimeUtility.transformDateString(timeStamp2, DateAndTimeUtility.HH_mm));
    return time1 == time2;
  }

  private Date setFormat(String format) {
    try {
      if (!StringUtility.isValidString(format))
        return null;
      if (StringUtility.isEmptyString(format))
        return null;
      SimpleDateFormat input = new SimpleDateFormat(format);
      input.setLenient(false);
      return input.parse(format);
    }
    catch (ParseException ex) {
      return null;
    }
  }

  /**
   * Converts the given timestamp to milliseconds
   * <p><b>Examples:</b>
   * <br>   tsToMillisSeconds("19820331122011") returns 386405411000
   * <br>   tsToMillisSeconds("00000000000000") returns 0
   * <br>   tsToMillisSeconds("") returns 0
   * <br>   tsToMillisSeconds(null) returns 0
   *
   * @param timestamp timestamp in "yyyyMMDDHHmmss" format
   * @return milliseconds
   */
  public static long tsToMillisSeconds(String timestamp, boolean considerDST) {
    long timeInMillis = 0;
    Date newDate = stringToDate(timestamp);
    if (newDate != null) {
      timeInMillis = newDate.getTime();
      
      if (!considerDST) {
        timeInMillis += TimeZone.getDefault().getOffset(timeInMillis);
      }
    }
    return timeInMillis;
  }
  
  public static long tsToMillisSeconds(String timestamp) {
    return tsToMillisSeconds(timestamp, true);
  }

  /**
   * Convert the given date to milliseconds
   *  - Return zero incase null is given
   * 
   * @param dat Date
   * @return long
   */
  public static long dateToMiliSeconds(java.util.Date date) {
    if (date == null) return 0;

    return date.getTime();
  }

  /**
   * Convert seconds values to a Timestamp
   * <p><b>Examples:</b>
   * <br>   secondsToTS(337545000) returns "19800912000000"
   * <br>   secondsToTS(-99999999l) returns "19661031194321"
   *
   * @param seconds long
   * @return timestamp in yyyyMMddHHmmss format
   */
  public static String secondsToTS(long seconds) {
    Date result = secondsToDate(seconds);
    return getTimeStamp(result, DateAndTimeUtility.yyyyMMddHHmmss);
  }


  /**
     * Convert milli seconds values to a Timestamp
     * <p><b>Examples:</b>
     * <br>   milliSecondsToTS(337545000000L) returns "19800912000000"
     * <br>   milliSecondsToTS(-99999999l) returns "19691231014321"
     *
     * @param milli seconds long
     * @return timestamp in yyyyMMddHHmmss format
     */
    public static String milliSecondsToTS(long milliSeconds) {
      return secondsToTS(milliSeconds/1000);
    }

    /**
     * Convert milli second to minutes
     * @param milliSeconds long
     * @return  long minutes
     */
    public static long milliSecondsToMinutes(long milliSeconds){
      return (milliSeconds/DateAndTimeUtility.MINUTE_IN_MS);
    }

  /**
   * Convert minute to milliseconds
   * @param long minutes
   * @return long milliSeconds
   */
  public static long minutesToMilliSeconds(long minutes) {
    return (minutes * DateAndTimeUtility.MINUTE_IN_MS);
  }

    /**
     * Convert milli seconds values to a Date
     * <p><b>Examples:</b>
     * <br>   milliSecondsToDate(337545000000) returns "Fri Sep 12 00:00:00 GMT+05:30 1980"
     * <br>   milliSecondsToDate(-99999999l) returns "Wed Dec 31 01:43:21 GMT+05:30 1969"

     * @param milli seconds long
     * @return Date
     */
    public static java.util.Date milliSecondsToDate(long milliSeconds) {
      return secondsToDate(milliSeconds / 1000);
    }

    /**
     * Convert milli seconds values to a time string format.
     * Expects the milliSeconds parameter to be a representation of a full date (as returned Date object's getTime() method)
     * In case the milliSeconds don't express a full date then the system will correctly return a time but with a special trick.
     * The trick is needed as apparently 0 milliseconds actually mean still zero date in 1970 but with GMT time difference. So we will
     * use as a dummy date the current date with zero time.
     * Only time formats are supported else ??:?? is returned
     *
     * <p><b>Examples:</b>
     * <br>   milliSecondsToTimeString(337545000000) returns "Fri Sep 12 00:00:00 GMT+05:30 1980"
     * <br>   milliSecondsToDate(-99999999l) returns "Wed Dec 31 01:43:21 GMT+05:30 1969"

     * @param seconds long
     * @return Date
     */
    public static String milliSecondsToTimeString(long milliSeconds, int format) {
      if (isTimeFormat (format)) {
        if (milliSeconds <= 24*60*60*1000) { // time less then 24 hours
          milliSeconds = getDateWithZeroTime(new Date()).getTime() + milliSeconds;
        }
        return getTimeStamp(new Date(milliSeconds), format);
      }
      else
        return "??:??";
    }

    /**
     * Return current Date in Date format
     * @return Date
     */
    public static java.util.Date getCurrentDate(){
     return new Date();
   }

  /**
   * Convert minutes to a time string format.
   * @param minutes
   * @param format
   * @return - String
   */
  public static String minutesToTimeString(long minutes, int format) {
    return milliSecondsToTimeString(minutesToMilliSeconds(minutes), format);
  }

  /**
   * Return tomorrow in Date format
   * @return Date
   */
  public static java.util.Date getTomorrow() {
    return calculateDate(getCurrentDate(), 1);
  }

  /**
   * Return yesterday in Date format
   * @return Date
   */
  public static java.util.Date getYesterday() {
    return calculateDate(getCurrentDate(), -1);
  }

  /**
    * Returns the current year as an int
    * @return int
    */
   public static int getCurrentYear() {
     GregorianCalendar gc = new GregorianCalendar();
     return gc.get(gc.YEAR);
   }

   /**
    * Returns current date (date,month,year fields). All other fields are set to zero (hour,min,sec,milisec).
    * @author Kasun Ranasinghe (17/9/06)
    * @return Date
    */
   public static java.util.Date getCurrentDateWithZeroTime() {
     return getDateWithZeroTime(new Date());
   }

   /**
    * Returns date (date,month,year fields). All other fields are set to zero
    * @param date Date
    * @return Date
    */
   public static java.util.Date getDateWithZeroTime(Date date) {
     if(date == null) return null;
     GregorianCalendar gc = new GregorianCalendar();
     gc.setTimeInMillis(date.getTime());
     //gc.clear(Calendar.HOUR);
     //gc.clear(Calendar.HOUR_OF_DAY);
     //Clearing one of the fields doesn't reset the hour of day value of this Calendar. Use set(Calendar.HOUR_OF_DAY, 0) to reset the hour value.
     gc.set(Calendar.HOUR_OF_DAY, 0);
     gc.clear(Calendar.MINUTE);
     gc.clear(Calendar.SECOND);
     gc.clear(Calendar.MILLISECOND);
     return gc.getTime();
   }

  /**
   * Returns date (date,month,year fields). All other fields are set to maximum
   * @param date Date
   * @return Date
   */
  public static java.util.Date getDateWithMaxTime(Date date) {
    GregorianCalendar gc = new GregorianCalendar();
    gc.setTimeInMillis(date.getTime());
    gc.set(Calendar.HOUR, 23);
    gc.set(Calendar.HOUR_OF_DAY, 23);
    gc.set(Calendar.MINUTE, 59);
    gc.set(Calendar.SECOND, 59);
    gc.set(Calendar.MILLISECOND, 999);
    return gc.getTime();
  }

  /**
   * Return 00-00-0000 Added by Inoka
   */
  public static String getZeroDate() {
    return getTimeStamp(null, dd_MM_yyyy);
  }

   /**
    * Return true when date1 is before date2
    * otherwise return false
    * if date1 OR date2 is null return false
    * <p><b>Examples:</b>
    * <br>isBeforeDate (new GregorianCalendar(2006,Calendar.JUNE, 17, 18, 10).getTime(),
                        new GregorianCalendar(2006,Calendar.JUNE, 17, 18, 11).getTime()) returns false
    * <br>isBeforeDate (new GregorianCalendar(1980,Calendar.SEPTEMBER, 11, 10, 10, 10).getTime(),
                        new GregorianCalendar(1980,Calendar.SEPTEMBER, 12, 10, 10, 10).getTime())) returns true
    * <br>isBeforeDate (new GregorianCalendar(2006,Calendar.JUNE, 16, 24, 60).getTime(),
                        new GregorianCalendar(2006,Calendar.JUNE, 17, 18, 11).getTime())) returns false
    * <br>isAfterDate(new GregorianCalendar(2006,Calendar.JUNE, 17, 18, 11).getTime(),null)return false
    * <br>isAfterDate(null,new GregorianCalendar(2006,Calendar.JUNE, 17, 18, 10).getTime())return false
    * <br>isAfterDate(null,null)return false
    * @param date1 Date
    * @param date2 Date
    * @return boolean
    */
   public static boolean isBeforeDate(Date date1, Date date2) {
     if (date1 == null || date2 == null)return false;
     Date date1WithZeroTime = getDateWithZeroTime(date1);
     Date date2WithZeroTime = getDateWithZeroTime(date2);
     return date1WithZeroTime.before(date2WithZeroTime);
   }

   /**
    * Return true when date1 is after date2
    * otherwise return false
    * if date1 OR date2 is null return false
    * <p><b>Examples:</b>
    * <br>isAfterDate(new GregorianCalendar(1980,Calendar.SEPTEMBER, 12, 10, 10, 10).getTime(),
                      new GregorianCalendar(1980,Calendar.SEPTEMBER, 11, 10, 10, 10).getTime()))return true
    * <br>isAfterDate(new GregorianCalendar(1980,Calendar.SEPTEMBER, 11, 10, 10, 10).getTime(),
                      new GregorianCalendar(1980,Calendar.SEPTEMBER, 12, 10, 10, 10).getTime()))return false
    * <br>isAfterDate(new GregorianCalendar(2006,Calendar.JUNE, 17, 18, 11).getTime(),
                      new GregorianCalendar(2006,Calendar.JUNE, 17, 18, 10).getTime())return false
    * <br>isAfterDate(new GregorianCalendar(2006,Calendar.JUNE, 17, 18, 11).getTime(),null)return false
    * <br>isAfterDate(null,new GregorianCalendar(2006,Calendar.JUNE, 17, 18, 10).getTime())return false
    * <br>isAfterDate(null,null)return false

    * @param date1 Date
    * @param date2 Date
    * @return boolean
    */
   public static boolean isAfterDate(Date date1, Date date2) {
     if (date1 == null || date2 == null)return false;
     Date date1WithZeroTime = getDateWithZeroTime(date1);
     Date date2WithZeroTime = getDateWithZeroTime(date2);
     return date1WithZeroTime.after(date2WithZeroTime);
   }

   /**
    * Return true when timeStamp1 is before timeStamp2
    * otherwise return false
    * The difference between isBeforeDate and isBeforeTimeStamp
    * isBeforeDate      -> Considering only date values (zero time values)
    * isBeforeTimeStamp -> Considering time value as well.
    *
    * <b>Examples:</b>
    * <br>isBeforeTimeStamp("200603130000","200603140000") returns true.
    * <br>isBeforeTimeStamp("200603140000","200603130000") returns false.
    * @param timeStamp1
    * @param timeStamp2
    * @return boolean
    */
   public static boolean isBeforeTimeStamp(String timeStamp1, String timeStamp2) {
     if (!DateAndTimeUtility.isValidTimeStamp(timeStamp1) || !DateAndTimeUtility.isValidTimeStamp(timeStamp2))
       return false;
     Date date1 = stringToDate(timeStamp1);
     Date date2 = stringToDate(timeStamp2);
     return date1.before(date2);
   }

   /**
    * Return true when timeString1 is before timeString2
    * otherwise return false
    * The difference between isBeforeTime and isBeforeTimeStamp
    * isBeforeTime      -> Considering only time values
    * isBeforeTimeStamp -> Considering both date and time values
    *
    * <b>Examples:</b>
    * <br>isBeforeTime("10:30:00","09:00:00") returns false.
    * <br>isBeforeTime("10:30:00","11:30:00") returns true.
    * <br>isBeforeTime("10:30","09:00") returns false.
    * <br>isBeforeTime("10:30","11:30") returns true.
    * <br>isBeforeTime("20130315140000","20130315130000") returns false.
    * <br>isBeforeTime("20130315140000",null) returns false.
    * <br>isBeforeTime(null,"10:30") returns false.
    * <br>isBeforeTime(null,"") returns false.
    * @param timeString1
    * @param timeString2
    * @return boolean
    */
   public static boolean isBeforeTime(String timeString1, String timeString2) {
     if (!DateAndTimeUtility.validateTimeString(timeString1) || !DateAndTimeUtility.validateTimeString(timeString2))
       return false;
     long time1 = timeToSeconds(timeString1);
     long time2 = timeToSeconds(timeString2);
     return time1 < time2;
   }

   /**
    * Return true when timeStamp1 is after timeStamp2
    * otherwise return false
    * The difference between isAfterDate and isAfterTimeStamp
    * isAfterDate      -> Considering only date values (zero time values)
    * isAfterTimeStamp -> Considering time value as well.
    *
    * <b>Examples:</b>
    * <br>isAfterTimeStamp("200603130000","200603140000") returns false.
    * <br>isAfterTimeStamp("200603140000","200603130000") returns true.
    * @param timeStamp1
    * @param timeStamp2
    * @return boolean
    */
   public static boolean isAfterTimeStamp(String timeStamp1, String timeStamp2) {
    if (StringUtility.isEmptyString(timeStamp1) || StringUtility.isEmptyString(timeStamp2))
      return false;
    Date date1 = stringToDate(timeStamp1);
    Date date2 = stringToDate(timeStamp2);
     if(date1 == null || date2 == null) return false;

    return date1.after(date2);
   }

   /**
    * Return true when timeString1 is after timeString2
    * otherwise return false
    * The difference between isAfterTime and isAfterTimeStamp
    * isAfterTime      -> Considering only time values
    * isAfterTimeStamp -> Considering both date and time values
    *
    * <b>Examples:</b>
    * <br>isAfterTime("10:30:00","09:00:00") returns true.
    * <br>isAfterTime("10:30:00","11:30:00") returns false.
    * <br>isAfterTime("10:30","09:00") returns true.
    * <br>isAfterTime("10:30","11:30") returns false.
    * <br>isAfterTime("20130315140000","20130315130000") returns false.
    * <br>isAfterTime("20130315140000",null) returns false.
    * <br>isAfterTime(null,"10:30") returns false.
    * <br>isAfterTime(null,"") returns false.
    * @param timeString1
    * @param timeString2
    * @return boolean
    */
   public static boolean isAfterTime(String timeString1, String timeString2) {
     if (!DateAndTimeUtility.validateTimeString(timeString1) || !DateAndTimeUtility.validateTimeString(timeString2))
       return false;
     long time1 = timeToMinutes(timeString1);
     long time2 = timeToMinutes(timeString2);
     return time1 > time2;
   }

   /**
    * Returns true when given point is inbetween the given interval.
    * Incase the point is equal to start or end time, then also true will be returned.
    * <b>Examples:</b>
    * <br>isIntervalContaintainingPoint("200603131030","200603131130","200603131100") returns true.
    * <br>isIntervalContaintainingPoint("200603131030","200603131130","200603131200") returns false.
    *
    * @param intervalStart - interval start time in timestamp
    * @param intervalStop - interval stop time in timestamp
    * @param point - point in timestamp
    * @return boolean
    */
   public static boolean isIntervalContaintainingPoint(String intervalStart, String intervalStop, String point, boolean validateOnTime) {
    if (StringUtility.isEmptyString(intervalStart) || StringUtility.isEmptyString(intervalStop) || StringUtility.isEmptyString(point))
      return false;
    Date intervalStartDate = stringToDate(intervalStart);
    Date intervalStopDate = stringToDate(intervalStop);
    Date pointInDate = stringToDate(point);

    return isIntervalContaintainingPoint(intervalStartDate, intervalStopDate, pointInDate, validateOnTime, false);
  }

  /**
   * Convenient method for isIntervalContaintainingPoint(String intervalStart, String intervalStop, String point, boolean validateOnTime)
   *
   * @param intervalStart
   * @param intervalStop
   * @param point
   * @return - boolean
   */
  public static boolean isIntervalContaintainingPoint(String intervalStart, String intervalStop, String point) {
    return isIntervalContaintainingPoint(intervalStart, intervalStop, point, true);
  }

  /**
   * Returns true when given point is inbetween the given interval.
   *  - Incase the point is equal to start or end time, then also true will be returned. This will be done based
   *    on the validateOnTime flag
   *    If validateOnTime flag has been set to false, Then method will not check for euqality of the start or stop with the 
   *    given point
   *
   * @param intStart - Date
   * @param intStop  - Date
   * @param point    - Date
   * @param validateOnTime
   * @return boolean 
   */
  public static boolean isIntervalContaintainingPoint(Date intStart, Date intStop, Date point, boolean validateOnTime, boolean considerOnlyDate) {
    if (considerOnlyDate) {
      intStart = DateAndTimeUtility.getDateWithZeroTime(intStart);
      intStop = DateAndTimeUtility.getDateWithZeroTime(intStop);
      point = DateAndTimeUtility.getDateWithZeroTime(point);
    }

    if (intStart == null || intStop == null || point == null) {
      return false;
    }
    else if (point.after(intStart) && point.before(intStop)) { // inside the interval
      return true;
    }
    else if (validateOnTime && (point.equals(intStart) || point.equals(intStop))) { // equal to start or stop
      return true;
    }
    else {
      return false;
    }
  }

  /**
   * Convenient method for isIntervalContaintainingPoint(Date intStart, Date intStop, Date point, boolean validateOnTime)
   *
   * @param intStart
   * @param intStop
   * @param point
   * @return - boolean
   */
  public static boolean isIntervalContaintainingPoint(Date intStart, Date intStop, Date point) {
    return isIntervalContaintainingPoint(intStart, intStop, point, true, false);
  }

  /**
   * Checks if the given date falls within the specified bounds (inclusive of the bounds).
   * Only the date value is considered, disregarding the time value.
   * @param startBound
   * @param endBound
   * @param toCheck
   * @return
   */
  public static boolean isDateWithinBounds(Date startBound, Date endBound, Date toCheck) {
    return isIntervalContaintainingPoint(startBound, endBound, toCheck, true, true);
  }
  /**
   * Returns whether the given date is in-between the current week.
   *  - Returns true, when the given date is in-between the current week. This include the
   *  - First date of the week and Last date of the week as well.
   *  - All the other cases will be false.
   *
   * @param date
   * @return - boolean (true when the given date is in-between the current week)
   */
  public static boolean isFallInCurrentWeek(Date date) {
    if (date == null) return false;

    int currentWeekNo = dateToWeekNumber(getCurrentDate());
    int currentYear = getCurrentYear();

    return isIntervalContaintainingPoint(getFirstDateOfWeek(currentWeekNo, currentYear), getLastDateOfWeek(currentWeekNo, currentYear), getDateWithZeroTime(date));
  }

  /**
   * Returns whether the given date is in-between the given week.
   *  - Returns true, when the given date is in-between the given week. This include the
   *  - First date of the week and Last date of the week as well.
   *  - All the other cases will be false.
   *
   * @param date
   * @param weekNo
   * @param year
   * @return - boolean (true when the given date is in-between the given week)
   */
  public static boolean isFallInWeek(Date date, int weekNo, int year) {
    if (date == null || weekNo == 0 || year == 0) return false;

    return isIntervalContaintainingPoint(getFirstDateOfWeek(weekNo, year), getLastDateOfWeek(weekNo, year), getDateWithZeroTime(date));
  }

  /**
   * Checks whether the given first pair of interval is intersecting with the second pair of interval
   *
   * Note :- This method will accept time string WITHOUT date part.
   *         Time string which support HH:MM:SS and HH:MM
   *
   * <b>Examples:</b>
   * <br>isIntersectingTimestring("10:30:00","11:30:00","09:30:00", "10:45:00", TRUE) returns true.
   * <br>isIntersectingTimestring("10:30:00","11:30:00","11:15:00", "12:30:00", TRUE) returns true.
   * <br>isIntersectingTimestring("10:30:00","11:30:00","10:45:00", "11:15:00", TRUE) returns true.
   * <br>isIntersectingTimestring("10:30:00","11:30:00","09:30:00", "12:30:00", TRUE) returns true.
   * <br>isIntersectingTimestring("10:30:00","11:30:00","08:30:00", "09:15:00", TRUE) returns false.
   * <br>isIntersectingTimestring("10:30:00","11:30:00","11:31:00", "13:15:00", TRUE) returns false.
   *
   * <br>isIntersectingTimestring("10:30:00","11:30:00","09:30:00", "10:29:59", TRUE) returns false.
   * <br>isIntersectingTimestring("10:30:00","11:30:00","09:30:00", "10:30:01", TRUE) returns true.
   *
   * <br>isIntersectingTimestring("10:30:00","11:30:00","09:30:00", "10:30:00", TRUE) returns true.
   * <br>isIntersectingTimestring("10:30:00","11:30:00","09:30:00", "10:30:00", FALSE) returns false.
   * <br>isIntersectingTimestring("10:30:00","11:30:00","", "10:30:00", TRUE) returns false.
   * <br>isIntersectingTimestring("10:30:00",null,"09:30:00", "", FALSE) returns false.
   *
   * Note:- For the above examples when the timestring is passed in the format of HH:MM,
   *        returns the same result as above 
   *
   * @param intervalStartTimestring
   * @param intervalStopTimestring
   * @param pointStartTimestring
   * @param pointStopTimestring
   * @param validateOnTime
   * @return - boolean (TRUE when intersecting, Else returns FALSE)
   */
  public static boolean isIntersectingTimestring(String intervalStartTimestring, String intervalStopTimestring, String pointStartTimestring, String pointStopTimestring, boolean validateOnTime) {
    if (!DateAndTimeUtility.validateTimeString(intervalStartTimestring) ||
        !DateAndTimeUtility.validateTimeString(intervalStopTimestring) ||
        !DateAndTimeUtility.validateTimeString(pointStartTimestring) ||
        !DateAndTimeUtility.validateTimeString(pointStopTimestring)) return false;

    Date currentDate = DateAndTimeUtility.getCurrentDateWithZeroTime();
    String intervalStart = DateAndTimeUtility.getTimeStamp(currentDate, intervalStartTimestring, DateAndTimeUtility.yyyyMMddHHmmss);
    String intervalStop = DateAndTimeUtility.getTimeStamp(currentDate, intervalStopTimestring, DateAndTimeUtility.yyyyMMddHHmmss);
    String pointStart = DateAndTimeUtility.getTimeStamp(currentDate, pointStartTimestring, DateAndTimeUtility.yyyyMMddHHmmss);
    String pointStop = DateAndTimeUtility.getTimeStamp(currentDate, pointStopTimestring, DateAndTimeUtility.yyyyMMddHHmmss);

    return DateAndTimeUtility.isIntersecting(intervalStart, intervalStop, pointStart, pointStop, validateOnTime);
  }

  /**
   * Checks whether the given first pair of interval is intersecting with the second pair of interval
   * <b>Examples:</b>
   * <br>isIntersecting("20120725103000","20120725113000","20120725093000", "20120725104500", TRUE) returns true.
   * <br>isIntersecting("20120725103000","20120725113000","20120725111500", "20120725123000", TRUE) returns true.
   * <br>isIntersecting("20120725103000","20120725113000","20120725104500", "20120725111500", TRUE) returns true.
   * <br>isIntersecting("20120725103000","20120725113000","20120725093000", "20120725123000", TRUE) returns true.
   * <br>isIntersecting("20120725103000","20120725113000","20120725083000", "20120725091500", TRUE) returns false.
   * <br>isIntersecting("20120725103000","20120725113000","20120725113100", "20120725131500", TRUE) returns false.
   *
   * <br>isIntersecting("20120725103000","20120725113000","20120725093000", "20120725102959", TRUE) returns false.
   * <br>isIntersecting("20120725103000","20120725113000","20120725093000", "20120725103001", TRUE) returns true.
   *
   * <br>isIntersecting("20120725103000","20120725113000","20120725093000", "20120725103000", TRUE) returns true.
   * <br>isIntersecting("20120725103000","20120725113000","20120725093000", "20120725103000", FALSE) returns false.
   * <br>isIntersecting("20120725103000","20120725113000","", "20120725103000", TRUE) returns false.
   * <br>isIntersecting("20120725103000",null,"20120725093000", "", FALSE) returns false.
   *
   * Incase invalid timestamp is given returns false;
   *
   * IMPORTANT:- If the given intervalStart equals to pointStart AND intervalStop equals to pointStop means that
   *             method considers the time as intersect
   *             This means same duration with same start time
   *
   * @param intervalStart
   * @param intervalStop
   * @param pointStart
   * @param pointStop
   * @return - boolean (TRUE when intersecting, Else returns FALSE)
   */
  public static boolean isIntersecting(String intervalStart, String intervalStop, String pointStart, String pointStop, boolean validateOnTime) {
    if (!isValidTimeStamp(intervalStart) || !isValidTimeStamp(intervalStop) || !isValidTimeStamp(pointStart) || !isValidTimeStamp(pointStop)) return false;

    return (isSameTimestamp(intervalStart, pointStart) && isSameTimestamp(intervalStop, pointStop)) ||
           isIntervalContaintainingPoint(intervalStart, intervalStop, pointStart, validateOnTime) ||
           isIntervalContaintainingPoint(intervalStart, intervalStop, pointStop, validateOnTime) ||
           isIntervalContaintainingPoint(pointStart, pointStop, intervalStart, validateOnTime) ||
           isIntervalContaintainingPoint(pointStart, pointStop, intervalStop, validateOnTime);
  }
//
   /**
     * Return the date which the value is the smallest among the given if both the dates are null then null will be return else a valid date will be given
     * <p>
     * <b>Examples:</b> getMinimumValuedDate(null,null) return null getMinimumValuedDate(null,14/10/2006) return 14/10/2006 getMinimumValuedDate(13/10/2006,null) return 13/10/2006 getMinimumValuedDate(13/10/2006,14/10/2006) return 13/10/2006 getMinimumValuedDate(20/10/2006,19/10/2006) return 19/10/2006 getMinimumValuedDate(14/10/2006,14/10/2006) return 14/10/2006
     * @param date1 Date
     * @param date2 Date
     * @return Date
     */
   public static Date getMinimumDate(Date date1, Date date2) {
     if(date1==null && date2==null)
       return null;
     else if((date2==null && date1!=null) || date1.before(date2))
       return date1;
     else
       return date2;
   }

   /**
    * Formats the date argument in the MLine date format ("dd/MM/yyyy")
    * <p><b>Examples:</b>
    * <br>   DateAndTimeUtility.formatDateStrToMLineFormat("31/08/1982", "dd/mm/yyyy") returns �31/01/1982�
    * <br>   DateAndTimeUtility.formatDateStrToMLineFormat("31-08-1982", "dd-mm-yyyy") returns �31/01/1982�
    * <br>   DateAndTimeUtility.formatDateStrToMLineFormat("31-08-1982", "dd/mm/yyyy") returns empty String
    *
    * @param date string representation of the date
    * @param format string representing the format of the date argument
    * @return Formatted string. Returns empty string if exception occurs
    */
   public static String formatDateStrToMLineFormat(String date, String format) {
     try {
       SimpleDateFormat sdf = new SimpleDateFormat(format);
       Date d = sdf.parse(date);
       SimpleDateFormat mlinesdf = new SimpleDateFormat("dd/MM/yyyy");
       return mlinesdf.format(d);
     }
     catch (ParseException ex) {
       return "";
     }
   }

   /**
   * A problem was encountered when stringToDate was reused elsewhere with
   * different formats as it used to always convert the format to lowercase.
   * However, it worked with the DIM methods. Therefore, this new method was
   * temporarily created to be used with the DIM. Later on, please find the
   * references of stringToDate and dimStringToDate and generalize it.
   * @param date String
   * @param format String e.g. yyyyMMddHHmmss
   * @return Date
   *
   * @todo please refer to the method comment.
   */
  public static Date dimStringToDate(String date, String format) {
    try {
      SimpleDateFormat sdf = new SimpleDateFormat(format);
      return sdf.parse(date);
    }
    catch (Exception ex) {
      return null;
    }
  }

  /**
   * Return Minutes in milliseconds
   * @param Minutes long
   * @return long
   */
  public static long getMinutesInMiliSeconds(long mints){
    return mints * 60 * 1000;
  }


  /**
   * Returns the seconds since last midnight
   * @return int
   */
  public static int getSecondsSinceMidnight () {
    long midnightInmsec = getCurrentDateWithZeroTime().getTime();
    return (int)((System.currentTimeMillis() - midnightInmsec)/1000);
  }

  public static long milliSecondsToMillisSinceMidnight (long milliseconds) {
    GregorianCalendar gc = new GregorianCalendar();
    gc.setTimeInMillis(milliseconds);
    //gc.clear(Calendar.HOUR);
    //gc.clear(Calendar.HOUR_OF_DAY);
    //Clearing one of the fields doesn't reset the hour of day value of this Calendar. Use set(Calendar.HOUR_OF_DAY, 0) to reset the hour value.
    gc.set(Calendar.HOUR_OF_DAY, 0);
    gc.clear(Calendar.MINUTE);
    gc.clear(Calendar.SECOND);
    gc.clear(Calendar.MILLISECOND);
    long midnightOfTheGivenDay = gc.getTimeInMillis();
    return milliseconds - midnightOfTheGivenDay;
  }

  public static int milliSecondsToSecondsSinceMidnight (long milliseconds) {
    return (int)DateAndTimeUtility.milliSecondsToMillisSinceMidnight (milliseconds)/1000;
  }


  public static long hoursToMIlliseconds(long hours){
    return   DateAndTimeUtility.minutesToMilliSeconds(hours*60);
  }

  /**
   * Returns days in milliseconds
   * @param days - int
   * @return - long
   */
  public static long daysToMilliseconds(int days) {
    return (long) days * DAY_IN_MS;
  }

  /**
   * Returns a string when a date object is passed
   * to be used in  sql in case of maxdate and mindate
   * @param date Date
   * @return String
   */

  public static String[] getSQLbetweenForTimestamp(Date date) {
    String dateString = getTimeStamp(date, DateAndTimeUtility.yyyyMMddHHmmss);
    return getSQLbetweenForTimestamp(dateString);
  }

  /**
   * Returns a string when a string date representaion  is passed
   * to be used in  sql in case of maxdate and mindate
   * @param date Date
   * @return String
   */
  public static String[] getSQLbetweenForTimestamp(String strTimeStamp) {
    String [] minmax = new String [2];

    if (!StringUtility.isEmptyString(strTimeStamp) && strTimeStamp.length() > 7) {
      String strDate = strTimeStamp.substring(0, 8);
      minmax [0] = StringUtility.addPostFixString(strDate, "000000");
      minmax [1] = StringUtility.addPostFixString(strDate, "235959");
    }
    return minmax;
  }


  /**
   * Get XMLGregorianCalendar from a date value
   * If date null or invalid return null
   * @param dateValue
   * @return XMLGregorianCalendar
   */
  public static XMLGregorianCalendar getXMLGregorianCalendar(Date dateValue) {
    if(dateValue == null){
      return null;
    }
    GregorianCalendar gcal = new GregorianCalendar();
    gcal.setTime(dateValue);
    XMLGregorianCalendar xgcal = null;
    try {
      xgcal = DatatypeFactory.newInstance()
      .newXMLGregorianCalendar(gcal);
    }
    catch (DatatypeConfigurationException e) {
      return null;
    }
    return xgcal;
  }

  /**
   * Get date value from XMLGregorianCalendar
   * if invalid return null;
   * @param xmlCalendar
   * @return Date
   */
  public static Date getDate(XMLGregorianCalendar xmlCalendar){
    if(xmlCalendar == null){
      return null;
    }
    GregorianCalendar gcal =  xmlCalendar.toGregorianCalendar();
    if(gcal == null){
      return null;
    }
    return gcal.getTime();
  }


  /**
   * Returns the number of minutes of the given time string which is formatted as DD:HH:MM.
   * <br>DD represents no of days.
   * <br>In case an invalid string is passed then 0 is returned.
   * <p><b>Examples:</b>
   * <br>getTimeValue ("01:02:10") returns 1570
   * <br>getTimeValue ("02:10:00") returns 3480
   * <br>getTimeValue ("1:06:25") returns 0
   * <br>getTimeValue ("10") returns 0
   * <br>getTimeValue ("") returns 0
   * <br>getTimeValue (null) returns 0
   *
   * @param dateFormat - representation of the time as a string formatted as DD:HH:MM
   * @return long number of minutes
   */
  public static long getTimeValue(String dateFormat) {
    if(StringUtility.isEmptyString(dateFormat) || dateFormat.length() != 8) return 0;
    int days = 0;
    int hrs  = 0;
    int mins = 0;
    days = Integer.parseInt(dateFormat.substring(0, 2));
    hrs = Integer.parseInt(dateFormat.substring(3, 5));
    mins = Integer.parseInt(dateFormat.substring(6, 8));

    int timeInMinutes = (days * 1440) + (hrs * 60) + mins;

    return timeInMinutes;
  }

  /*
  * This method returns difference between two timestamps in milliseconds.
  * @param  String timestamp1, String timestamp2.
  * @return long 
  * returns number of milliseconds between timestamp1 and timestamp2.
  * <br>("201007080000","201007083000") returns 300000.
  * <br>("201007083000","201007080000") returns -300000.
  */
  public static long getTimestampDifference(String timestamp1, String timestamp2, boolean considerDST) {
    long time1 = tsToMillisSeconds(timestamp1, considerDST);
    long time2 = tsToMillisSeconds(timestamp2, considerDST);
    return  (time1-time2);
  }
  
  public static long getTimestampDifference(String timestamp1, String timestamp2) {
    return getTimestampDifference(timestamp1, timestamp2, true);
  }

  public static long getTimestampDifferenceInMinutes(String timestamp1, String timestamp2){
    return getTimestampDifference(timestamp1, timestamp2) / MINUTE_IN_MS;
  }

  /**
   * Returns number of milliseconds from the given date to currentTimeMillis()
   *  - Zero will be returned in-case null is given
   * 
   * @param date
   * @return
   */
  public static long getMilliSecondsSince(Date date) {
    if (date == null) return 0;

    return System.currentTimeMillis() - dateToMiliSeconds(date);
  }

  /**
   * Added test main method
   * @param arg
   */
  public static void main(String arg[]) {
    int week = 11;
    int year = 2010;
    System.out.println(" First date of week " + week + " of year " + year + " --> " + DateAndTimeUtility.getTimeStamp(getFirstDateOfWeek(week, year), DateAndTimeUtility.dd_MM_yyyy));

    String strDate1 = "14-03-2006";
    String strDate2 = "15-03-2006";
    Date date1 = stringToDate(strDate1);
    Date date2 = stringToDate(strDate2);
    System.out.println(" Date difference between " + strDate1 + " and " + strDate2 + " --> " + getDateDifference(date1, date2, DAYS));


    int noOfDays = 3;
    String strDate = "14-03-2006";
    Date date = stringToDate(strDate);
    System.out.println(" Add " + noOfDays + " days from " + strDate + "  --> " + DateAndTimeUtility.getTimeStamp(calculateDate(date, noOfDays), DateAndTimeUtility.dd_MM_yyyy));
  }

  public static void setDefaultTimeZone(String tzone) {
//    TimeZone tz = ZoneInfo.getTimeZone(tzone);
//    if (tz != null) {
////      Tracer.print(Tracer.SYS_MSG, "Setting default timezone as identified by parameter : " + tzone);
//      oldTz = TimeZone.getDefault(); //keep old value for later display for super user
//      TimeZone.setDefault(tz);
//    }
//    else {
////      Tracer.print(Tracer.SYS_MSG, "Timezone cannot be identified for given parameter : " + tzone);
//    }
  }


  /**
   * reruns the time matching the lower time slot for the passed in time
   *
   * NOTE : timeSlots (ArrayList) can be created using the method  DateAndTimeUtility.buildTimeSlotSequence (timeInterval)
   *
   * @param timeInMinutes
   * @param slots
   * @return
   */
  public static String getLowerAdjacentTimeFromTimeSlots(long timeInMinutes, ArrayList<Integer> timeSlots) {
    return getAdjacentTimeRelatedToTimeSlots(timeInMinutes, timeSlots, true);
  }

  /**
   * reruns the time matching the upper time slot for the passed in time
   *
   * NOTE : timeSlots (ArrayList) can be created using the method  DateAndTimeUtility.buildTimeSlotSequence (timeInterval)
   *
   * @param timeInMinutes
   * @param slots
   * @return
   */
  public static String getUpperAdjacentTimeFromTimeSlots(long timeInMinutes, ArrayList<Integer> timeSlots) {
    return getAdjacentTimeRelatedToTimeSlots(timeInMinutes, timeSlots, false);
  }

  /**
   * This method expects the time and the time slot collection to be passed in.
   * the method will find out the closest time slot for the passed in time. This will be either the closest earlier time slot
   * or closest past time according to the param adjacentTimeFloor true or false
   *
   * NOTE : timeSlots (ArrayList) can be created using the method  DateAndTimeUtility.buildTimeSlotSequence (timeInterval)
   *
   * TECHNICAL : when the closest slot is found the method will calculate how much time is needed for the passed in time to come up to the slot time,
   *             this time will be added if the user requests the ceiling value and reduced if the user requests the floor value to match the
   *             nearest time slot value
   * <p/>
   *
   * e.g :
   * getClosestTime(10:07,[15,30,45,60],true)  -----> 10:15
   * getClosestTime(10:07,[15,30,45,60],false) -----> 10:00
   *
   * getClosestTime(10:15,[15,30,45,60],true)  -----> 10:30
   * getClosestTime(10:16,[15,30,45,60],false) -----> 10:15
   *
   * @param timeInMinutes long
   * @param slots ArrayList
   * @param adjacentTimeFloor boolean   (indicates whether the time should be rounded up or down)
   * @return newTime String
   */
  private static String getAdjacentTimeRelatedToTimeSlots(long timeInMinutes, ArrayList<Integer> slots, boolean adjacentTimeFloor) {
    if (slots != null || timeInMinutes >= 0) {
      int minutes = Integer.parseInt(DateAndTimeUtility.minutesToTimeString(timeInMinutes, DateAndTimeUtility.mm));
      int closestSlotTime = 0;
      int position = 0;
      boolean closestTopTimeFound = false;
      ArrayList<Integer> timeSlots = (ArrayList) slots.clone();
      if (adjacentTimeFloor) {
        Collections.reverse(timeSlots);
      }
      for (Integer slotTimeMins : timeSlots) {
        position++;
        if (slotTimeMins != null && !closestTopTimeFound && (!adjacentTimeFloor ? slotTimeMins >= minutes : slotTimeMins <= minutes)) {
          closestSlotTime = slotTimeMins;
          closestTopTimeFound = true;
        }
      }
      if (closestTopTimeFound) {
//        Tracer.print(Tracer.CODE_LOGIC, "[" + position + "]th time Slot : " + closestSlotTime + " minutes");
        int increment = 0;
        if (adjacentTimeFloor) {
          increment = minutes - closestSlotTime;
        }
        else {
          increment = closestSlotTime - minutes;
        }
//        Tracer.print(Tracer.CODE_LOGIC, "Adjacent time-Interval to current " + minutes + " minute(s) is " + closestSlotTime + " minutes");
//        Tracer.print(Tracer.CODE_LOGIC, "Time " + (adjacentTimeFloor ? "decrement" : "increment") + " needed : " + increment + " minutes");

        String newTime;
        if (adjacentTimeFloor) {
          newTime = DateAndTimeUtility.reduceTimes(DateAndTimeUtility.minutesToTimeString(timeInMinutes, DateAndTimeUtility.HH_mm), DateAndTimeUtility.minutesToTimeString(increment, DateAndTimeUtility.HH_mm), DateAndTimeUtility.HH_mm);
        }
        else {
          newTime = DateAndTimeUtility.addTimes(DateAndTimeUtility.minutesToTimeString(timeInMinutes, DateAndTimeUtility.HH_mm), DateAndTimeUtility.minutesToTimeString(increment, DateAndTimeUtility.HH_mm), DateAndTimeUtility.HH_mm);
        }

//        Tracer.print(Tracer.CODE_LOGIC, "Time changed from " + DateAndTimeUtility.minutesToTimeString(timeInMinutes, DateAndTimeUtility.HH_mm) + " to : " + newTime);
        return newTime;
      }
      else {
//        Tracer.print(Tracer.CODE_LOGIC, "Valid adjacent time not found !");
        return null;
      }
    }
    else {
//      Tracer.print(Tracer.CODE_LOGIC, "Invalid time or time interval sequence");
      return null;
    }
  }
//  last date of month
//   Date date=new Date();
//        
////        DateAndTimeUtility.getLastDateOfMonth(WIDTH, WIDTH)
//        DateTime dt=new DateTime();
//        dt.dayOfMonth().withMaximumValue();
//        dt.dayOfMonth().withMinimumValue();
//        Date sd=dt.withDayOfMonth(1).toDate();
////        Date ed=dt.withDayOfMonth(dt.getl).toDate();
//        

}
