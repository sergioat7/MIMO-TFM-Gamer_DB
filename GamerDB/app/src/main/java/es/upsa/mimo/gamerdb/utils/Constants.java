package es.upsa.mimo.gamerdb.utils;

import android.annotation.SuppressLint;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Constants {

    // MARK: - Retrofit constants

    public static String baseEndpoint = "https://api.rawg.io/api/";

    // MARK: Date format

    public static String dateFormat = "yyyy-MM-dd";

    @SuppressLint("SimpleDateFormat")
    public static String dateToString(Date date) {

        try {
            return new SimpleDateFormat(dateFormat).format(date);
        } catch (Exception e) {
            return null;
        }
    }

    @SuppressLint("SimpleDateFormat")
    public static Date stringToDate(String dateString) {

        try {
            return new SimpleDateFormat(dateFormat).parse(dateString);
        } catch (Exception e) {
            return null;
        }
    }
}
