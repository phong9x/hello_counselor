package org.trams.hello.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by bryanlee on 19/01/2017.
 */
public class DateTimeUtils {

    public enum DateType {
        GMT
    }

    private static final String DATE_FORMAT_GMT = "E MMM dd yyyy HH:mm:ss Z";

    private static String formatDate(Date date, DateType type) {
        DateFormat format;
        switch (type) {
            case GMT:
                format = new SimpleDateFormat(DATE_FORMAT_GMT, Locale.US);
                break;
            default:
                format = new SimpleDateFormat(DATE_FORMAT_GMT, Locale.US);
                break;
        }

        return format.format(date);
    }

    private static Date getDate(String date, DateType type) throws ParseException {
        DateFormat format;
        switch (type) {
            case GMT:
                format = new SimpleDateFormat(DATE_FORMAT_GMT, Locale.US);
                break;
            default:
                format = new SimpleDateFormat(DATE_FORMAT_GMT, Locale.US);
                break;
        }

        return format.parse(date);
    }

}
