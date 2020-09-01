package es.upsa.mimo.gamerdb.utils;

import android.annotation.SuppressLint;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import es.upsa.mimo.gamerdb.R;

public class Constants {

    // MARK: - Retrofit constants

    public static String BASE_ENDPOINT = "https://api.rawg.io/api/";
    public static int CONNECT_TIMEOUT = 60;
    public static int READ_TIMEOUT = 30;
    public static int WRITE_TIMEOUT = 15;
    public static String PAGE_PARAM = "page";
    public static String PAGE_SIZE_PARAM = "page_size";
    public static int FIRST_PAGE = 1;
    public static int PAGE_SIZE = 20;
    public static String BASE_YOUTUBE_URL = "https://www.youtube.com/embed/";

    // MARK: Date format

    public static String DATE_FORMAT = "yyyy-MM-dd";
    public static String DATE_FORMAT_TO_SHOW = "MMMM d, yyyy";

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

    public static String GAME_ID = "gameId";
    public static String IMAGE_URL = "imageUrl";

    //MARK: - Game list

    public static int MARGIN_LIST = 150;
    public static int NO_MARGIN_LIST = 0;

    //MARK: - Game detail

    public static int MAX_LINES = Integer.MAX_VALUE;
    public static int STORE_BUTTON_SEPARATOR_WIDTH = 40;
    public static int STORE_BUTTON_HEIGHT = 50;

    public static int getStoreImageId(String storeId) {

        switch (storeId) {
            case "steam":
                return R.drawable.steam;
            case "playstation-store":
                return R.drawable.playstation_store;
            case "xbox-store": case "xbox360":
                return R.drawable.xbox_store;
            case "apple-appstore":
                return R.drawable.apple_store;
            case "gog":
                return R.drawable.gog;
            case "nintendo":
                return R.drawable.nintendo_eshop;
            case "google-play":
                return R.drawable.google_play;
            case "itch":
                return R.drawable.itchio;
            case "epic-games":
                return R.drawable.epic_games;
            default:
                return 0;
        }
    }

    //MARK: - Image detail

    public static float INITIAL_ZOOM = 1.0f;
    public static float MAX_ZOOM = 10.0f;
}
