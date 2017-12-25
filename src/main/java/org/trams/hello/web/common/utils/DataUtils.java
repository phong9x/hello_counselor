package org.trams.hello.web.common.utils;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;

import org.json.simple.JSONArray;

public class DataUtils {

    static CharsetEncoder asciiEncoder = Charset.forName("UTF-8").newEncoder();
    
    public enum DatePattern {
        YYYYMMDD("yyyy/MM/dd"), YYYYMM("yyyy.MM"), YYYYMMDDHHMMSS("yyyy/MM/dd HH:mm:ss");

        String pattern;

        DatePattern(String pattern) {
            this.pattern = pattern;
        }

        public String getPattern() {
            return pattern;
        }
    }
  
    public static Integer parseInt(String str) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }

    public static Integer parseInt(Object obj) {
        try {
            return Integer.parseInt(obj.toString());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return 0;
    }

    public static Long parseLong(Object obj) {
        try {
            return Long.parseLong(obj.toString());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return (long) 0;
    }

    public static String toString(Object str) {
        try {
            return String.valueOf(str);
        } catch (Exception e) {
            System.out.println(e.toString());
            return "";
        }

    }


    public static Date getDate(String formatDate, String startDateString) {
        DateFormat df = new SimpleDateFormat(formatDate);
        Date startDate;
        try {
            startDate = df.parse(startDateString);
            return startDate;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static java.util.Date parseDate(String date, String formatDate) {
        SimpleDateFormat format = new SimpleDateFormat(formatDate);
        java.util.Date parsed = null;
        try {
            parsed = format.parse(date);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return parsed;
    }

    /**
     * @param email
     * @return
     */
    public static String getFindEmail(String email) {
        try {
            String[] e = email.split("@");
            int lenght = e[0].length();
            String rex = e[0].substring(lenght - 3, lenght);
            return email.replaceAll(rex, "***");

        } catch (Exception e) {
        }
        return "";
    }

    /**
     * @param email
     * @return
     */
    public static String getHiddentString(String str, int hiddneNumber) {
        try {
            int lenght = str.length();
            String rex = str.substring(lenght - hiddneNumber, lenght);
            return str.replaceAll(rex, "***");

        } catch (Exception e) {
        }
        return "";
    }

    /**
     * @param input
     * @return
     */
    public static String getMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            // Now we need to zero pad it if you actually want the full 32 chars.
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param format
     * @return
     */
    public static Date convertToDate(String value, String format) {

        SimpleDateFormat formatter = new SimpleDateFormat(format);

        try {

            Date date = formatter.parse(value);
            return date;

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Generate autt code
     *
     * @param email
     * @param password
     * @return
     */
    public static String getAuthPhoneCode(String srt, String str2) {

        String str = srt + "ML^#09X#" + str2;
        try {
            String hexStr = getMD5(str);
            return hexStr.substring(3, 9).toUpperCase();
        } catch (Exception e) {
        }
        return "";
    }


    public static Cookie createCookie(String cookieName, String cookieValue) {
        Cookie cookie = new Cookie(cookieName, cookieValue);
        final int expiryTime = 60 * 60 * 24;  // 24h in seconds
        cookie.setPath("/");
        cookie.setMaxAge(expiryTime);
        cookie.setSecure(true);
        return cookie;
    }

    public static String convertStringListToJson(List<Integer> list) {
        JSONArray listData = new JSONArray();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                listData.add(list.get(i));
            }
        }
        return listData.toJSONString();
    }


    public static Long convertFromTime(Date date) {
        try {
            return date.getTime();
        } catch (Exception e) {
        }

        return new Long(0);
    }

    public static String formatPhoneNumber(String str) {
        String temp = "";
        Integer a = (Integer) str.length() / 4;
        Integer b = str.length() - (a) * 4;
        int index = 0;
        for (int i = a; i >= 0; i--) {
            if (i == a) {
                temp = str.substring(str.length() - (index * 4 + 4), str.length() - index * 4) + temp;
            } else if (i == 0) {
                temp = str.substring(0, b) + "-" + temp;
            } else {
                temp = str.substring(str.length() - (index * 4 + 4), str.length() - index * 4) + "-" + temp;
            }
            index++;

        }

        return temp;
    }

    public static Integer diffirentTwoDate(Date beforeDate, Date afterDate) {
        try {
            long diff = afterDate.getTime() - beforeDate.getTime();

            Integer diffDays = Math.round(DataUtils.parseLong(diff) / (24 * 60 * 60 * 1000));
            return diffDays;
        } catch (Exception e) {
        }

        return 0;
    }
    
    public static Integer diffirentMinutefromTwoTime(Date beforeDate, Date afterDate) {
        try {
            long diff = afterDate.getTime() - beforeDate.getTime();
            Integer diffDays = Math.round(DataUtils.parseLong(diff) / ( 60 * 1000));
            return diffDays;
        } catch (Exception e) {
        }

        return 0;
    }
    
    public static Integer diffirentSecondfromTwoTime(Date beforeDate, Date afterDate) {
        try {
            long diff = afterDate.getTime() - beforeDate.getTime();
            Integer diffDays = Math.round(DataUtils.parseLong(diff) / ( 1000));
            return diffDays;
        } catch (Exception e) {
        }

        return 0;
    }

    public static Date parseDate(String date, DatePattern pattern) {
        try {
            DateFormat format = new SimpleDateFormat(pattern.getPattern());
            return format.parse(date);
        } catch (ParseException e) {
            return null;
        }

    }
    
    public static String parseStringFromDate(Date date, String formatDate) {
        try {
            DateFormat df = new SimpleDateFormat(formatDate);
            return df.format(date);
        } catch (Exception e) {
            return null;
        }

    }
    
    public static Date firstDayCurrentMonth(Calendar c) {
		c.getTime();
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
	    c.set(Calendar.MINUTE, 0);
	    c.set(Calendar.SECOND, 0);
	    c.set(Calendar.MILLISECOND, 0);
	    return c.getTime();
	}
	
	public static Date firstDayLastMonth(Calendar c) {
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
	    c.set(Calendar.MINUTE, 0);
	    c.set(Calendar.SECOND, 0);
	    c.set(Calendar.MILLISECOND, 0);
		c.add(Calendar.MONTH, -1);
	    return c.getTime();
	}
	
	public static Date lastDayLastMonth(Calendar c) {
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		c.set(Calendar.HOUR_OF_DAY, c.getActualMaximum(Calendar.HOUR_OF_DAY));
	    c.set(Calendar.MINUTE, c.getActualMaximum(Calendar.MINUTE));
	    c.set(Calendar.SECOND, c.getActualMaximum(Calendar.SECOND));
	    c.set(Calendar.MILLISECOND, c.getActualMaximum(Calendar.MILLISECOND));
		c.add(Calendar.MONTH, -1);
	    return c.getTime();
	}

	public static Integer getOffset(Integer page, Integer size) {
		int offset = 0;
		if (page > 1) {
			offset = ((page - 1) * size);
		}
		
		return offset;
	}
}
