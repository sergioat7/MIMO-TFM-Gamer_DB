package es.upsa.mimo.gamerdb.utils;

import android.annotation.SuppressLint;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Constants {

    // MARK: - Retrofit constants

    public static String baseEndpoint = "https://api.rawg.io/api/";
    public static int pageSize = 20;

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

    //MARK: - Intent extras

    public static String gameId = "gameId";

    //MARK: - Game detail

    public static int maxLines = Integer.MAX_VALUE;
    public static int minLines = 7;
}
