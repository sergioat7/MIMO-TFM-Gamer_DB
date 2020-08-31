package es.upsa.mimo.gamerdb.utils;

import android.annotation.SuppressLint;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Constants {

    // MARK: - Retrofit constants

    public static String baseEndpoint = "https://api.rawg.io/api/";
    public static int connectTimeout = 60;
    public static int readTimeout = 30;
    public static int writeTimeout = 15;
    public static String pageParam = "page";
    public static String pageSizeParam = "page_size";
    public static int firstPage = 1;
    public static int pageSize = 20;
    public static String baseYoutubeUrl = "https://www.youtube.com/embed/";

    // MARK: Date format

    public static String dateFormat = "yyyy-MM-dd";
    public static String dateFormatToShow = "MMMM d, yyyy";

    @SuppressLint("SimpleDateFormat")
    public static String dateToString(Date date, String dateFormat) {

        try {
            return new SimpleDateFormat(dateFormat, Locale.ENGLISH).format(date);
        } catch (Exception e) {
            return null;
        }
    }

    @SuppressLint("SimpleDateFormat")
    public static Date stringToDate(String dateString, String dateFormat) {

        try {
            return new SimpleDateFormat(dateFormat, Locale.ENGLISH).parse(dateString);
        } catch (Exception e) {
            return null;
        }
    }

    //MARK: - Intent extras

    public static String gameId = "gameId";
    public static String imageUrl = "imageUrl";

    //MARK: - Game list

    public static int marginList = 150;
    public static int noMarginList = 0;

    //MARK: - Game detail

    public static int maxLines = Integer.MAX_VALUE;
    public static int storeButtonSeparatorWidth = 40;
    public static int storeButtonHeight = 50;

    //MARK: - Image detail

    public static float initialZoom = 1.0f;
    public static float maxZoom = 10.0f;
}
